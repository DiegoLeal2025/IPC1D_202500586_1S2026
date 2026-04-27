/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author elven
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GestorArchivos {

    private static final String RUTA_ALBUM   = "data/album.txt";
    private static final String RUTA_EQUIPO  = "data/album_equipo.txt";
    private static final String RUTA_USUARIO = "data/album_usuario.txt";
    private static final String SEP          = "\\|";

    private int contarLineas(String ruta) {
        int n = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String l;
            while ((l = br.readLine()) != null) {
                l = l.trim();
                if (!l.isEmpty() && !l.startsWith("#") && !l.startsWith("usuario|")) n++;
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer: " + ruta);
        }
        return n;
    }

    public CartaUsuario[] cargarCartasUsuario() {
        verificarYCrearArchivo(RUTA_USUARIO);
    int total = contarLineas(RUTA_USUARIO);
    
    // Usamos un arreglo temporal del tamaño de las líneas
    CartaUsuario[] reg = new CartaUsuario[total];
    int idx = 0;
    
    try (BufferedReader br = new BufferedReader(new FileReader(RUTA_USUARIO))) {
        String l;
        while ((l = br.readLine()) != null) {
            l = l.trim();
            if (l.isEmpty() || l.startsWith("#")) continue;
            
            String[] p = l.split("\\|"); 
            if (p.length >= 5) {
                reg[idx++] = new CartaUsuario(
                    p[0].trim(), // Nombre de usuario
                    p[1].trim(), // Código de la carta (CARTA-001)
                    Integer.parseInt(p[2].trim()), // Página
                    Integer.parseInt(p[3].trim()), // Fila
                    Integer.parseInt(p[4].trim())  // Columna
                );
            }
        }
    } catch (IOException | NumberFormatException e) {
        System.err.println("Error al procesar album_usuario.txt: " + e.getMessage());
    }
        CartaUsuario[] resultado = new CartaUsuario[idx];
        System.arraycopy(reg, 0, resultado, 0, idx); 
    return resultado;
    }


    public void guardarCartasUsuario(CartaUsuario[] registros, int usados) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_USUARIO))) {
            bw.write("usuario|no_carta|cantidad|pegada|pagina|fila|columna");
            bw.newLine();
            for (int i = 0; i < usados; i++) {
                if (registros[i] != null) {
                    bw.write(registros[i].toLinea());
                    bw.newLine();
                }
            }
            System.out.println("album_usuario.txt guardado ("
                    + usados + " registros).");
        } catch (IOException e) {
            System.out.println("No se pudo guardar: " + e.getMessage());
        }
    }
    
    
    public Carta[] cargarCartas() {
    verificarYCrearArchivo(RUTA_ALBUM);
    
    int total = contarLineas(RUTA_ALBUM);
    Carta[] catalogo = new Carta[total];
    int idx = 0;

    try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ALBUM))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            linea = linea.trim();
            
            if (linea.isEmpty() || linea.startsWith("#")) continue;

            String[] datos = linea.split("\\|");

            if (datos.length == 8) {
                try {
                    catalogo[idx++] = new Carta(
                        datos[0].trim(), // codigo (CARTA-001)
                        datos[1].trim(), // nombre
                        datos[2].trim(), // tipo
                        datos[3].trim(), // rareza
                            
                        Integer.parseInt(datos[4].trim()), // ataque
                        Integer.parseInt(datos[5].trim()), // defensa
                        Integer.parseInt(datos[6].trim()), // puntosSalud
                        datos[7].trim()  // rutaImagen
                    );
                    
                } catch (NumberFormatException e) {
                    System.err.println("Error en formato numérico en línea: " + linea);
                }
            }
        }
    } catch (IOException e) {
        System.err.println("Error al leer el catálogo de cartas: " + e.getMessage());
    }

    // Ajustamos el arreglo por si hubo líneas vacías o errores
    if (idx < total) {
        Carta[] resultado = new Carta[idx];
        System.arraycopy(catalogo, 0, resultado, 0, idx);
        return resultado;
    }

    return catalogo;
}
    
    private void verificarYCrearArchivo(String ruta) {
    try {
        File archivo = new File(ruta);
        File carpeta = archivo.getParentFile();
        
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
        
        if (!archivo.exists()) {
            archivo.createNewFile();
            System.out.println("Archivo creado automáticamente: " + ruta);
        }
    } catch (IOException e) {
        System.err.println("Error crítico al crear archivo: " + e.getMessage());
    }
}
    
}
