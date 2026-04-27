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
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author elven
 */
public class RepositorioHistorial {
    private String nombreArchivo = "historial.txt";

    public RepositorioHistorial(){
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

    public String[][] obtenerHistorial() {
        int cantidadLineas = contarLineas();
        String[][] matrizHistorial = new String[cantidadLineas][3];

        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            int indice = 0;
            while ((linea = lector.readLine()) != null) {
                if (linea.contains(",")) {
                    String[] partes = linea.split(",");
                    if (partes.length >= 3) {
                        matrizHistorial[indice][0] = partes[0]; 
                        matrizHistorial[indice][1] = partes[1]; 
                        matrizHistorial[indice][2] = partes[2]; 
                        indice++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matrizHistorial;
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

    public void registrarVenta(String fecha, String cantidad, String total) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(nombreArchivo, true))) {
            escritor.println(fecha + "," + cantidad + "," + total);
        } catch (IOException e) {
            System.out.println("Error al escribir historial: " + e.getMessage());
        }
    }
    public void cargarEnTabla(DefaultTableModel modelo) {
        modelo.setRowCount(0); // Limpiamos la tabla visual
        String[][] datos = obtenerHistorial();
        for (String[] fila : datos) {
            if (fila[0] != null) {
                modelo.addRow(fila);
            }
        }
    }
}
