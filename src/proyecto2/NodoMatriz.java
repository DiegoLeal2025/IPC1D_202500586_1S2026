/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author elven
 */
 public class NodoMatriz {

    Carta dato;
    NodoMatriz arriba;
    NodoMatriz abajo;
    NodoMatriz izquierda;
    NodoMatriz derecha;

    int fila;
    int columna;

    public NodoMatriz(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.dato = null;
        this.arriba = null;
        this.abajo = null;
        this.izquierda = null;
        this.derecha = null;
    }

    public boolean estaVacia() {
        return dato == null;
    }

    public Carta getDato() {
        return dato;
    }

    public NodoMatriz getArriba() {
        return arriba;
    }

    public NodoMatriz getAbajo() {
        return abajo;
    }

    public NodoMatriz getIzquierda() {
        return izquierda;
    }

    public NodoMatriz getDerecha() {
        return derecha;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

}
