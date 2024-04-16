/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex17;

import static ex17.MainTxt.parsearBooleano;
import static ex17.MainTxt.parsearFecha;
import static ex17.MainTxt.parsearHora;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 *
 * @author victor
 */
public class MainCsv {

    public static void main(String[] args) {

        // Leo el fichero y lo guardo en una lista de empleados
        String ruta = "./RelEmpCenAus.csv";
        List<Empleado> empleados = leerFichero(ruta);
        empleados.forEach(e -> System.out.println(e));
        System.out.println("--------------------------------------------------");

        
        // Empiezo la parte de la API stream
        // Obtengo la lista de los puestos de trabajo
        List<String> puestos = obtenerPuestosTrabajo(empleados);
        System.out.println("Los puestros de trabajo son:");
        puestos.forEach(p -> System.out.println(p));
        System.out.println("--------------------------------------------------");
        
        // Obtengo el número de trabajadores del centro
        int numTrabajadores = calcularNumTrabajadores(empleados);
        System.out.println("En el centro hay: " + numTrabajadores);
        System.out.println("--------------------------------------------------");
        
        // Obtengo la lista ordenada de dnis de no activos
        List<String> dnis = filtrarDni(empleados);
        System.out.println("Los dnis de los trabajadores no activos son:");
        dnis.forEach(d -> System.out.println(d));
        System.out.println("--------------------------------------------------");
        
        // Obtengo al trabajador que más horas ha faltado e imprimo su dni
        Empleado falton = encontrarFalton(empleados);
        System.out.println("El dni del trabajador más faltón es: " + falton.getDni());
        
        
        
    }

    // Método para leer el fichero y guardarlo en una lista de empleados
    private static List<Empleado> leerFichero(String ruta) {
        List<Empleado> empleados = new ArrayList<>();
        String linea = "";
        String[] tokens;
        try ( Scanner flujo = new Scanner(new FileReader(ruta, StandardCharsets.ISO_8859_1))) {
            // Paso primera línea que no me interesa
            flujo.nextLine();

            // Empiezo a leer la información que me interesa
            while (flujo.hasNext()) {
                linea = flujo.nextLine();
                tokens = linea.split("(\",\")|(\")");
                // Añado el nuevo empleado a la lista de empleados
                empleados.add(new Empleado(tokens[1].trim(), tokens[2].trim(),
                        tokens[3].trim(), tokens[4].trim(),
                        parsearBooleano(tokens[5]), parsearFecha(tokens[6]),
                        parsearFecha(tokens[7]), parsearHora(tokens[8]),
                        tokens[9].trim(), parsearBooleano(tokens[10])));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return empleados;
    }
    
    // Método para obtener la lista de diferentes puestos de trabajo
    private static List<String> obtenerPuestosTrabajo(List<Empleado> lista){
        List<String> puestos = lista.stream()
                .map(e -> e.getPuestoTrabajo())
                .distinct()
                .toList();
        return puestos;
    }
    
    // Método para obtener el número de trabajadores que hay en el centro (sólo los activos)
    private static int calcularNumTrabajadores(List<Empleado> lista){
        int numTrabajadores = (int) lista.stream()
                .distinct()
                .filter(e -> e.isActivo())
                .count();
        return numTrabajadores;
    }
    
    // Método para obtener una lista de todos los dnis distintos de los trabajadores
    // no activos y ordenada alfabéticamente
    private static List<String> filtrarDni(List<Empleado> lista){
        List<String> dnis = lista.stream()
                .filter(e -> !e.isActivo())
                .map(e -> e.getDni())
                .distinct()
                .sorted()
                .toList();
        return dnis;
    }
    
    // Método para encontrar al trabajador que más horas ha faltado
    private static Empleado encontrarFalton(List<Empleado> lista){
        Optional<Empleado> falton = lista.stream() // Compara la parte de las horas
                .max((e1, e2)-> Integer.compare(Integer.parseInt(e1.getTotalHoras().split(":")[0]),
                        Integer.parseInt(e2.getTotalHoras().split(":")[0])));
        return falton.get();
    }
    
}
