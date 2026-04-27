/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;


public class MallaOrtogonal {
    
    private NodoMatriz origen;
    private int filas;
    private int columnas;
    private int numeroPagina;

    public MallaOrtogonal(int filas, int columnas, int numeroPagina) {
        this.filas = filas;
        this.columnas = columnas;
        this.numeroPagina = numeroPagina;
        construir();
    }
    
    public Carta ObtenerCarta(int fila, int columna){
        NodoMatriz nodo = getNodo(fila, columna);
        if(nodo!=null){
            return nodo.dato;
        }
        return null;
    }

    private void construir() {
        NodoMatriz[][] temp = new NodoMatriz[filas][columnas];
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                temp[f][c] = new NodoMatriz(f, c);
            }
        }
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                if (f > 0) {
                    temp[f][c].arriba = temp[f - 1][c];
                }
                if (f < filas - 1) {
                    temp[f][c].abajo = temp[f + 1][c];
                }
                if (c > 0) {
                    temp[f][c].izquierda = temp[f][c - 1];
                }
                if (c < columnas - 1) {
                    temp[f][c].derecha = temp[f][c + 1];
                }
            }
        }
        origen = temp[0][0];
    }

    public NodoMatriz getNodo(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas) {
            return null;
        }
        NodoMatriz nodo = origen;
        for (int f = 0; f < fila; f++) {
            nodo = nodo.abajo;
        }
        for (int c = 0; c < columna; c++) {
            nodo = nodo.derecha;
        }
        return nodo;
    }

    public boolean pegarCarta(int fila, int columna, Carta carta) {
        NodoMatriz nodo = getNodo(fila, columna);
        if (nodo == null || !nodo.estaVacia()) {
            return false;
        }
        nodo.dato = carta;
        return true;
    }

    public NodoMatriz buscarPorCodigo(String noCarta) {
        NodoMatriz fila = origen;
        while (fila != null) {
            NodoMatriz nodo = fila;
            while (nodo != null) {
                if (nodo.dato != null && nodo.dato.getCodigo().equals(noCarta)) {
                    return nodo;
                }
                nodo = nodo.derecha;
            }
            fila = fila.abajo;
        }
        return null;
    }

    public NodoMatriz primerVacioEnFila(int fila) {
        NodoMatriz nodo = getNodo(fila, 0);
        while (nodo != null) {
            if (nodo.estaVacia()) {
                return nodo;
            }
            nodo = nodo.derecha;
        }
        return null;
    }

    public boolean filaCompleta(int fila) {
        NodoMatriz nodo = getNodo(fila, 0);
        while (nodo != null) {
            if (nodo.estaVacia()) {
                return false;
            }
            nodo = nodo.derecha;
        }
        return true;
    }

    public int contarPegadas() {
        int count = 0;
        NodoMatriz filaNode = origen;
        while (filaNode != null) {
            NodoMatriz nodo = filaNode;
            while (nodo != null) {
                if (!nodo.estaVacia()) {
                    count++;
                }
                nodo = nodo.derecha;
            }
            filaNode = filaNode.abajo;
        }
        return count;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public NodoMatriz getOrigen() {
        return origen;
    }

}
