/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author elven
 */
public class AlbumMundial { 
    private MallaOrtogonal[] mallas;
    private Carta[] Catalogo;
    private CartaUsuario[] registrosUsuario;
    private int totalRegistros;
    
    private GestorArchivos gestor;
    private GeneradorCartas generador;
    
    public AlbumMundial(){
        this.gestor = new GestorArchivos();
        this.Catalogo = gestor.cargarCartas();
        this.registrosUsuario = gestor.cargarCartasUsuario();
        this.totalRegistros = registrosUsuario.length;
        
        this.registrosUsuario = gestor.cargarCartasUsuario();

        if (this.registrosUsuario != null) {
        for (CartaUsuario reg : registrosUsuario) {
            
            if (reg == null) continue; 
            
            Carta cartaEncontrada = buscarCarta(reg.getCodigoCarta());
            
            if (cartaEncontrada != null) {
                mallas[reg.getPagina()].pegarCarta(reg.getFila(), reg.getColumna(), cartaEncontrada);
            }
        }
    }
        
        this.generador = new GeneradorCartas(Catalogo);
        
        // Inicializar datos del album en memoria
        mallas = new MallaOrtogonal[5]; 
        for (int i = 0; i < mallas.length; i++) {
            mallas[i] = new MallaOrtogonal(4, 6, i);
        }
        
    }

    public void mostrarPaginaEnPanel(JPanel panelContenedor, int numPagina) {
        panelContenedor.removeAll();
        panelContenedor.setLayout(new java.awt.GridLayout(4, 6, 5, 5)); 

        if (numPagina < 0 || numPagina >= mallas.length) return;

        MallaOrtogonal mallaActual = this.mallas[numPagina];

        for (int f = 0; f < 4; f++) {
            for (int c = 0; c < 6; c++) {
                Carta carta = mallaActual.ObtenerCarta(f, c);

                if (carta != null) {
                    panelContenedor.add(crearTarjetaCarta(carta));
                } else {
                    panelContenedor.add(crearEspacioVacio());
                }
            }
        }

        panelContenedor.revalidate();
        panelContenedor.repaint();
    }
    
    private JPanel crearTarjetaCarta(Carta carta) {
        JPanel card = new JPanel(new GridLayout(0, 1)); 
        
        // Color por tipo
        switch (carta.getTipo().toLowerCase()) {
            case "fuego": card.setBackground(new Color(255, 100, 100)); break;
            case "agua":  card.setBackground(new Color(100, 150, 255)); break;
            case "planta": card.setBackground(new Color(120, 255, 120)); break;
            case "eléctrico": card.setBackground(Color.YELLOW); break;
            default: card.setBackground(Color.WHITE);
        }
        
        card.setBorder(BorderFactory.createTitledBorder(carta.getCodigo()));
        
        card.add(new JLabel(" " + carta.getNombre(), SwingConstants.CENTER));
        card.add(new JLabel(" ATK: " + carta.getAtaque()));
        card.add(new JLabel(" DEF: " + carta.getDefensa()));
        card.add(new JLabel(" PS: " + carta.getPuntosSalud())); // Corregido: getPuntosSalud()
        
        return card;
    }

    public MallaOrtogonal[] getMallas() { return mallas; }
    


private JPanel crearEspacioVacio() {
    JPanel empty = new JPanel();
    empty.setBackground(Color.DARK_GRAY);
    empty.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    // Podrías poner un JLabel con "?" en el centro
    empty.add(new JLabel("?"));
    return empty;
}

public boolean agregarCartaEnPrimerVacio(int numPagina, Carta nueva) {
    MallaOrtogonal malla = mallas[numPagina];
    
    // Recorremos filas y columnas
    for (int f = 0; f < 4; f++) {
        for (int c = 0; c < 6; c++) {
            if (malla.ObtenerCarta(f, c) == null) {
                malla.pegarCarta(f, c, nueva);
                return true; // Éxito
            }
        }
    }
    return false; 
}

public void intercambiarCartas(int pag, int f1, int c1, int f2, int c2) {
    MallaOrtogonal malla = mallas[pag];
    
    NodoMatriz nodo1 = malla.getNodo(f1, c1);
    NodoMatriz nodo2 = malla.getNodo(f2, c2);
    
    if (nodo1 != null && nodo2 != null) {
        Carta temp = nodo1.dato;
        nodo1.dato = nodo2.dato;
        nodo2.dato = temp;
    }
}

public void mostrarPaginaConBusqueda(JPanel panel, int numPag, String criterio) {
    panel.removeAll();
    MallaOrtogonal mallaActual = mallas[numPag];
    String filtro = criterio.toLowerCase();

    for (int f = 0; f < 4; f++) {
        for (int c = 0; c < 6; c++) {
            Carta carta = mallaActual.ObtenerCarta(f, c);
            if (carta != null) {
                JPanel tarjeta = crearTarjetaCarta(carta);
                
                // Si la carta coincide con la búsqueda, resaltamos el borde
                if (!filtro.isEmpty() && (
                    carta.getNombre().toLowerCase().contains(filtro) ||
                    carta.getTipo().toLowerCase().contains(filtro) ||
                    carta.getRareza().toLowerCase().contains(filtro))) {
                    
                    tarjeta.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
                }
                panel.add(tarjeta);
            } else {
                panel.add(crearEspacioVacio());
            }
        }
    }
    panel.revalidate();
    panel.repaint();
}

    public Carta buscarCarta(String id) {
        if (id == null || Catalogo == null)
        {
            return null;         
        }
                
        for (int i = 0; i < Catalogo.length; i++) {
            Carta c = Catalogo[i];
            if (c != null) {
                // Imprimimos solo la primera carta para comparar cómo se ve
                if (i == 0) {
                }
                
                if (c.getCodigo().trim().equalsIgnoreCase(id.trim())) {
                    System.out.println("¡ÉXITO! Carta encontrada.");
                    return c;
                }
            }
        } 
        return null;
    }
    
    public void guardarProgreso(String nombreUsuario) {
    // 1. Contar cuántas cartas hay pegadas en total para el tamaño del arreglo
    int contador = 0;
    for (MallaOrtogonal malla : mallas) {
        for (int f = 0; f < 4; f++) {
            for (int c = 0; c < 6; c++) {
                if (malla.ObtenerCarta(f, c) != null) contador++;
            }
        }
    }

    // 2. Crear el arreglo de registros
    CartaUsuario[] registros = new CartaUsuario[contador];
    int i = 0;

    // 3. Llenar el arreglo recorriendo las mallas
    for (int p = 0; p < mallas.length; p++) {
        for (int f = 0; f < 4; f++) {
            for (int c = 0; c < 6; c++) {
                Carta carta = mallas[p].ObtenerCarta(f, c);
                if (carta != null) {
                    registros[i++] = new CartaUsuario(nombreUsuario, carta.getCodigo(), p, f, c);
                }
            }
        }
    }

    // 4. Llamar al gestor para que lo escriba en el disco duro
    gestor.guardarCartasUsuario(registros, contador);
}
}

