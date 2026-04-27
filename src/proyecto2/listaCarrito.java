/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author elven
 */
public class listaCarrito {
    private Nodo cabeza;

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }

    public Nodo getCabeza() {
        return cabeza;
    }
    
    public listaCarrito(){
        this.cabeza = null;
    }
    
    
    public void insertar(String nombre, double precio, int cantidad){
        Nodo nuevoNodo = new Nodo(nombre,precio,cantidad);
        
        if(cabeza==null){
            cabeza=nuevoNodo;
        }else{
            Nodo actual;
            actual = cabeza;
            while(actual.siguiente != null)
            {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
    }
    
    public void RehacerTabla(DefaultTableModel modeloTabla)
    {
        modeloTabla.setRowCount(0);
        Nodo actual=cabeza;
        while(actual!=null){
            modeloTabla.addRow(new Object[]{actual.Nombre,actual.Precio,actual.cantidad});
            actual= actual.siguiente;
        }
    }
    
    public double Total(){
        double total = 0.0;
        Nodo actual = cabeza;
        while(actual != null)
        {
            total = total+actual.Precio;
            actual = actual.siguiente;
        }
        return total;
    }
    
    public void vaciar() {
        this.cabeza = null;
    }   
}
