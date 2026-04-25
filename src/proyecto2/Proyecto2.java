/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.*;
/**
 *
 * @author elven
 */
public class Proyecto2 extends JFrame {
    public CardLayout Interfaz = new CardLayout();
    public JPanel mainPanel = new JPanel(Interfaz);
    public JPanel pestaña1 = new JPanel(); //Menu principal
    public JPanel pestaña2 = new JPanel(new GridLayout (1,2)); //Venta de videojuegos
    public JPanel Catalogo = new JPanel(new GridLayout (0,3,10,10)); //subdivision
    public JPanel pestaña3 = new JPanel(new BorderLayout());
    public JPanel Carrito = new JPanel(); //subdivision
    public JPanel Tarjetas = new JPanel(); //tarjetas del catalogo
    public JPanel Buscar = new JPanel(); //borde superior
    public JPanel JuntarF = new JPanel(new BorderLayout()); 
    
    JScrollPane Scroll1 = new JScrollPane(Catalogo);
    JButton btn1 = new JButton("Tienda de VideoJuegos");
    JButton btn2 = new JButton("Regresar al menu");
    JButton btn3 = new JButton("Ver Carrito");
    JButton btn4 = new JButton("Volver a la tienda");
    
    public JComboBox<String> Generos = new JComboBox<>(new String[]{"Todos","Accion","RPG","Estrategia","Deportes","Terror","Aventura"});
    public JComboBox<String> Plataforma = new JComboBox<>(new String[]{"Todas","PC","Playstation","Xbox","Nintendo Switch"});
    
    public JTextField Nombres = new JTextField(15);
    
    RepositorioCatalogoJuegos Juegos = new RepositorioCatalogoJuegos();
    public String[][] matrizBuscar = Juegos.obtenerTodosLosJuegos();
    private ModificarTienda modificador;
    
    Color Cpestaña1 = new Color(255,100,100);
    
    Proyecto2(){
        pestaña1.setBackground(Color.blue);
        pestaña2.setBackground(Color.CYAN);
        Catalogo.setBackground(Color.DARK_GRAY);
        Carrito.setBackground(Color.ORANGE);
        Catalogo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        Scroll1.getVerticalScrollBar().setUnitIncrement(16);
        Scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        modificador = new ModificarTienda(this);
        String[][] matrizJuegos = Juegos.obtenerTodosLosJuegos();
        
        Buscar.add(Generos);
        Buscar.add(Plataforma);
        Buscar.add(Nombres);
        JuntarF.add(Buscar, BorderLayout.NORTH);
        JuntarF.add(Scroll1, BorderLayout.CENTER);
        JuntarF.add(btn3, BorderLayout.SOUTH);
               
        for(int i=0; i<matrizJuegos.length; i++){
            String[] Datos=matrizJuegos[i];
            System.out.println("¿La matriz es nula? " + (matrizJuegos == null));
            Tarjetas = modificador.TarjetaVisual(Datos);
            Catalogo.add(Tarjetas);
        }     
  
        pestaña1.add(btn1);
        pestaña2.add(JuntarF);
        pestaña2.add(Carrito);  
        Carrito.add(btn2);
        pestaña3.add(btn4);
        
        mainPanel.add(pestaña1,"pestaña1");
        mainPanel.add(pestaña2,"pestaña2");
        mainPanel.add(pestaña3,"carrito");
        
        btn1.addActionListener((e)->{
            Interfaz.show(mainPanel,"pestaña2");
        });
        
        btn2.addActionListener((e)->{
            Interfaz.show(mainPanel,"pestaña1");
        });
        
        btn3.addActionListener((e)->{
            Interfaz.show(mainPanel,"carrito");
        });
        
        btn4.addActionListener((e)->{
            Interfaz.show(mainPanel,"pestaña2");
        });
        
        Generos.addActionListener((e)->modificador.filtrar());
        Plataforma.addActionListener((e)->modificador.filtrar());
        
        Nombres.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
            public void insertUpdate(javax.swing.event.DocumentEvent e) {modificador.filtrar();}
            public void removeUpdate(javax.swing.event.DocumentEvent e) {modificador.filtrar();}
            public void changedUpdate(javax.swing.event.DocumentEvent e) {modificador.filtrar();}
        });
        
        
        add(mainPanel);
        setSize(1050,750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    public static void main(String[] args) {       
        new Proyecto2();
    }
}
