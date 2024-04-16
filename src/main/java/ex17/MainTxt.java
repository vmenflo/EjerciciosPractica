/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex17;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author victor
 */
public class MainTxt {

    // Líneas del archivo que no me interesan para leer
    private static int LINEAS_PASABLES = 8;

    public static void main(String[] args) {

        // Leo el fichero e imprimo la lista por consola
        String ruta = "./RelEmpCenAus.txt";
        List<Empleado> empleados = leerFichero(ruta);
        empleados.forEach(e -> System.out.println(e));
        System.out.println("----------------------------------------------------");

        // Genero el fichero json
        String rutaJson = "./totalHorasPorTrabajador.json";
        generarJson(generarMap(empleados), rutaJson);
        
        

    }

    // Método para leer el fichero y guardarlo en una lista de empleados
    public static List<Empleado> leerFichero(String ruta) {
        List<Empleado> empleados = new ArrayList<>();
        String linea = "";
        String[] tokens;
        try ( Scanner flujo = new Scanner(new FileReader(ruta, StandardCharsets.ISO_8859_1))) {
            // Paso las líneas del archivo que no contiene información que me interesa
            for (int i = 0; i < LINEAS_PASABLES; i++) {
                flujo.nextLine();
            }
            // Empiezo a leer la información que me interesa
            while (flujo.hasNext()) {
                linea = flujo.nextLine();
                tokens = linea.split("\\|");
                // Añado el nuevo empleado a la lista de empleados
                empleados.add(new Empleado(tokens[0].trim(), tokens[1].trim(),
                        tokens[2].trim(), tokens[3].trim(),
                        parsearBooleano(tokens[4]), parsearFecha(tokens[5]),
                        parsearFecha(tokens[6]), parsearHora(tokens[7]),
                        tokens[8].trim(), parsearBooleano(tokens[9])));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return empleados;
    }

    // Método para pasar los string a variables booleanas
    public static boolean parsearBooleano(String parametro) {
        boolean booleano = false;
        parametro = parametro.trim();
        if (parametro.equalsIgnoreCase("si") || parametro.equalsIgnoreCase("s")) {
            booleano = true;
        }
        return booleano;
    }

    // Método para pasear la hora
    public static LocalTime parsearHora(String parametro) {
        parametro = parametro.trim();
        return LocalTime.parse(parametro,
                DateTimeFormatter.ofPattern("H:mm"));
    }

    // Método para parsear la fecha
    public static LocalDate parsearFecha(String parametro) {
        parametro = parametro.trim();
        LocalDate fecha = null;
        // Si es una fecha la parseo
        if (!parametro.isEmpty()) {
            fecha = LocalDate.parse(parametro,
                    DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        }

        return fecha;
    }

    // Método que genera la estructura tipo Map
    private static Map<String, Integer> generarMap(List<Empleado> empleados) {
        Map<String, Integer> totalHoras = new HashMap<>();
        int horasEmpleado = 0;
        // Creo todos los empleados únicos en el map para tener todas las filas necesarias
        for (Empleado e : empleados) {
            // Guardo el valor de las horas de ese empleado del atributo total horas
            horasEmpleado = Integer.parseInt(e.getTotalHoras().split(":")[0]);

            // Compruebo si ya tengo ese empleado en el map
            if (totalHoras.containsKey(e.getDni())) {
                // Introduzco la suma de las horas anteriores que tenia ya ese empleado
                // con las de la repetición
                totalHoras.put(e.getDni(), totalHoras.get(e.getDni()) + horasEmpleado);
            } else {
                // Introduzco el empleado con sus horas
                totalHoras.put(e.getDni(), horasEmpleado);
            }

        }
        return totalHoras;
    }

    // Método para generar el json a partir del map
    private static void generarJson(Map<String, Integer> map, String ruta) {
        // Creo el mapeador
        ObjectMapper mapeador = new ObjectMapper();

        // Le añado el modulo para java time
        mapeador.registerModule(new JavaTimeModule());

        // Le pongo el formato bien indentado
        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);

        // Escribo el fichero
        try {
            mapeador.writeValue(new File(ruta), map);
            System.out.println("Fiche Json generado con éxito.");
        }catch (IOException e){
            System.out.println("Fichero Json no se ha podido generar.");
            System.out.println(e.getMessage());
        }
    }

}
