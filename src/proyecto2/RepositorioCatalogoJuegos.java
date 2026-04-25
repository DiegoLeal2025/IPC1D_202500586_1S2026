/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author elven
 */
public class RepositorioCatalogoJuegos {
    
     private String nombreArchivo = "catalogo.txt";

    public RepositorioCatalogoJuegos() {
        asegurarArchivoExiste();
    }

    public void asegurarArchivoExiste() {
        File archivo = new File(nombreArchivo);
        System.out.println(archivo.getAbsolutePath());
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
        }
    }

    // Retorna una matriz [n][2] donde [i][0] es usuario y [i][1] es codigo del juego
    public String[][] obtenerTodosLosJuegos() {
        int cantidadLineas = contarLineas();
        String[][] matrizUsuarios = new String[cantidadLineas][7];

        try {
            BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            int indice = 0;
            while ((linea = lector.readLine()) != null) {
                if (linea.contains(",")) {
                    String[] partes = linea.split(",");
                    matrizUsuarios[indice][0] = partes[0]; //codigo
                    matrizUsuarios[indice][1] = partes[1]; //nombre
                    matrizUsuarios[indice][2] = partes[2]; //Genero
                    matrizUsuarios[indice][3] = partes[3]; //Precio
                    matrizUsuarios[indice][4] = partes[4]; //Plataforma
                    matrizUsuarios[indice][5] = partes[5]; //Stock
                    matrizUsuarios[indice][6] = partes[6]; //Descripcion
                    indice++;
                }
            }
            lector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matrizUsuarios;
    }

    private int contarLineas() {
        int lineas = 0;
        try {
            BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo));
            while (lector.readLine() != null) {
                lineas++;
            }
            lector.close();
        } catch (IOException e) {
            return 0;
        }
        return lineas;
    }

    public boolean juegoExiste(String codigo) {
        String[][] juegos = obtenerTodosLosJuegos();
        for (int i = 0; i < juegos.length; i++) {
            if (juegos[i][0] != null && juegos[i][0].equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    public boolean registrarJuego(String codigo, String nombre, String genero, String precio, String plataforma, String stock, String descripcion) {
        if (juegoExiste(codigo)) {
            return false;
        }
        boolean esRegistrado = false;
        PrintWriter escritor = null;
        try {
            escritor = new PrintWriter(new FileWriter(nombreArchivo, true));
            escritor.println(codigo + "," + nombre + "," + genero + "," + precio + "," + plataforma + "," + stock + "," + descripcion);
            esRegistrado = true;
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
            esRegistrado = false;
        } finally {
            if (escritor != null) {
                escritor.close();
            }
        }

        return esRegistrado;
    }
}
