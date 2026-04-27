/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author elven
 */
public class Carta {
     private String codigo;        // e.g., CARTA-001
    private String nombre;        // Nombre de la criatura
    private String tipo;          // Fuego, Agua, etc.
    private String rareza;        // Común, Legendaria, etc.
    private int ataque;           // Entero positivo
    private int defensa;          // Entero positivo
    private int puntosSalud;      // PS
    private String rutaImagen;
    private String usuario;

    public Carta(String codigo, String nombre, String tipo, String rareza, 
             int ataque, int defensa, int puntosSalud, String rutaImagen) {
        this.usuario = usuario;
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.rareza = rareza;
        this.ataque = ataque;
        this.defensa = defensa;
        this.puntosSalud = puntosSalud;
        this.rutaImagen = rutaImagen;
    }
    
    
    public String getUsuario() {
        return usuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getPuntosSalud() {
        return puntosSalud;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public String getRareza() {
        return rareza;
    }

    public int getPesoRareza() {
        switch (rareza) {
            case "Comun":
                return 50;
            case "Poco Comun":
                return 25;
            case "Rara":
                return 15;
            case "Ultra Rara":
                return 8;
            case "Legendaria":
                return 2;
            default:
                return 10;
        }
    }

    @Override
    public String toString() {
        return String.format("%-8s | %-2s | %-25s | #%-2d | %-15s | %s",
                 codigo,  nombre,  tipo,  rareza, 
                  ataque,  defensa,  puntosSalud,  rutaImagen);
    }
}
