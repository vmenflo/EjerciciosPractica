/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex17;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author victor
 */
public class MainConcierto {

    public static void main(String[] args) {

        // Leo el fichero
        String ruta = "./concierto.txt";
        String[][] matriz = leerFichero(ruta);

        // Imprimo el array para comprobar
        System.out.println("Imprimo la matriz generada con el fichero:");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j]);
            }
            System.out.println("");
        }

    }

    // Método para leer el fichero
    private static String[][] leerFichero(String ruta) {
        List<String> lineas;
        String[][] matriz = null;
        try {
            lineas = Files.readAllLines(Paths.get(ruta));

            // Genero la matriz a partir del archivo
            matriz = new String[lineas.size()][]; // Le doy las filas que tiene el archivo
            for (int i = 0; i < lineas.size(); i++) {
                // A cada fila le pongo el número de columnas que tiene esa fila
                matriz[i] = new String[lineas.get(i).length()];
                // Relleno la matriz
                for (int j = 0; j < matriz[i].length; j++) {
                    matriz[i][j] = String.valueOf(lineas.get(i).charAt(j));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return matriz;
    }

}
