/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import java.util.Random;

/**
 *
 * @author elven
 */
public class GeneradorCartas{
private final Carta[] catalogo;
    private final int     totalCatalogo;
    private final Random  random;
    private final int[]   pesosAcumulados;
    private final int     pesoTotal;

    public GeneradorCartas(Carta[] catalogo) {
        this.catalogo      = catalogo;
        this.totalCatalogo = catalogo.length;
        this.random        = new Random();

        pesosAcumulados = new int[totalCatalogo];
        int acum = 0;
        for (int i = 0; i < totalCatalogo; i++) {
            acum += catalogo[i].getPesoRareza();
            pesosAcumulados[i] = acum;
        }
        pesoTotal = acum;
    }


    private Carta sortearUnaCarta() {
        int objetivo = random.nextInt(pesoTotal) + 1;
        for (int i = 0; i < totalCatalogo; i++) {
            if (objetivo <= pesosAcumulados[i]) return catalogo[i];
        }
        return catalogo[totalCatalogo - 1];
    }


    public ListaSimple<Carta> generarLote(int cantidad) {
       ListaSimple<Carta> lote = new ListaSimple<>();
        for (int i = 0; i < cantidad; i++) {
            Carta c = sortearUnaCarta();
            if (c != null) {
                lote.insertarAlFinal(c);
            }
        }
        return lote;
    }

    public int aplicarLote(ListaSimple<Carta> lote, String usuario, CartaUsuario[] registros, int total, MallaOrtogonal[] mallas) {
    NodoSimple<Carta> actual = lote.getInicio();
    
    while (actual != null) {
        Carta c = actual.getDato();
        
        // 1. Usamos getCodigo() porque así está en tu clase Carta
        int numero = extraerNumeroDeCodigo(c.getCodigo()); 
        
        // Cálculo de posición (4x6 = 24 cartas por página)
        int numPagina = (numero - 1) / 24;
        int residuo = (numero - 1) % 24;
        int fila = residuo / 6;
        int columna = residuo % 6;

        if (numPagina >= 0 && numPagina < mallas.length) {

            mallas[numPagina].pegarCarta(fila, columna, c);
            
            registros[total++] = new CartaUsuario(usuario, c.getCodigo(), numPagina, fila, columna);        }
         actual = actual.getSiguiente();
    }
    return total;
    }

    private CartaUsuario buscarRegistro(CartaUsuario[] reg, int usados, String usuarioBuscado, String codigoBuscado) {
    for (int i = 0; i < usados; i++) {
        if (reg[i] != null) {
            // Se agregaron los paréntesis () después de getUsuario
            boolean mismoUsuario = reg[i].getUsuario().equals(usuarioBuscado);
            boolean mismaCarta = reg[i].getCodigoCarta().equals(codigoBuscado);
            
            if (mismoUsuario && mismaCarta) {
                return reg[i];
            }
        }
    }
        return null;
    }  
    
    private int extraerNumeroDeCodigo(String codigo) {
        try {
            // Elimina letras y guiones, dejando solo el número
            String soloNumeros = codigo.replaceAll("[^0-9]", "");
            return Integer.parseInt(soloNumeros);
        } catch (NumberFormatException e) {
            return 1; // Por defecto a la posición 1 si el formato falla
        }
    }
}
