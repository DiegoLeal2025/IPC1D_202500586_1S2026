/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author elven
 */
public class Nodo {
    public String Nombre;
    public double Precio;
    public int cantidad;
    public Nodo siguiente;
    

    public Nodo(String Nombre, double Precio, int cantidad) {
        this.Nombre = Nombre;
        this.Precio = Precio;
        this.cantidad = cantidad;
        this.siguiente = null;
    }
}
