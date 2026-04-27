/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author elven
 */
public class CartaUsuario {
    private String usuario;
    private String codigoCarta;
    private int cantidad;
    private boolean pegada;
    private int pagina;
    private int fila;
    private int columna;
    
     public CartaUsuario(String usuario, String codigoCarta, int pagina, int fila, int columna) {
        this.usuario = usuario;
        this.codigoCarta = codigoCarta;
        this.pagina = pagina;
        this.fila = fila;
        this.columna = columna;
        this.cantidad = 1;      // Al crear un registro, al menos tiene 1
        this.pegada = true;     // Si tiene coordenadas, asumimos que está pegada
    }

    public CartaUsuario(String usuario, String noCarta) {
        this(usuario, noCarta, -1, -1, -1);
    }

    public String getCodigoCarta() {
        return codigoCarta;
    }

    public String getUsuario() {
        return usuario;
    }

    public int getPagina() {
        return pagina;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public void incrementar(int n) {
        cantidad += n;
    }

    public void pegar(int pagina, int fila, int columna) {
        this.pegada = true;
        this.pagina = pagina;
        this.fila = fila;
        this.columna = columna;
    }

    public String toLinea() {
        return usuario + "|" + codigoCarta + "|" + cantidad + "|" + pegada + "|" + pagina + "|" + fila + "|" + columna;
    }

    @Override
    public String toString() {
        String estado = pegada
                ? String.format("Pegada[pag=%d fil=%d col=%d]", pagina, fila, columna)
                : "En inventario";
        return String.format("Usuario: %-12s | Carta: %-8s | x%d | %s",
                usuario, codigoCarta, cantidad, estado);
    }
}
