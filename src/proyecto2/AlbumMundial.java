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
}

