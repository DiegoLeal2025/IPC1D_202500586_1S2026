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
    CardLayout Interfaz = new CardLayout();
    JPanel mainPanel = new JPanel(Interfaz);
    JPanel pestaña1 = new JPanel(); //Menu principal
    JPanel pestaña2 = new JPanel(new GridLayout (1,2)); //Venta de videojuegos
    JPanel Catalogo = new JPanel(new GridLayout (0,3,10,10)); //subdivision
    JPanel Carrito = new JPanel(); //subdivision
    JPanel Tarjetas = new JPanel(); //tarjetas del catalogo
    JPanel Buscar = new JPanel(); //borde superior
    JPanel JuntarF = new JPanel(new BorderLayout()); 
    
    JScrollPane Scroll1 = new JScrollPane(Catalogo);
    JButton btn1 = new JButton("Tienda de VideoJuegos");
    JButton btn2 = new JButton("Regresar al menu");
    JComboBox<String> Generos = new JComboBox<>(new String[]{"Todos","Accion","RPG","Estrategia","Deportes","Terror","Aventura"});
    JComboBox<String> Plataforma = new JComboBox<>(new String[]{"Todas","PC","Playstation","Xbox","Nintendo Switch"});
    
    JTextField Nombres = new JTextField(15);
    
    RepositorioCatalogoJuegos Juegos = new RepositorioCatalogoJuegos();
    String[][] matrizBuscar = Juegos.obtenerTodosLosJuegos();
    
    Color Cpestaña1 = new Color(255,100,100);
    
    Proyecto2(){
        pestaña1.setBackground(Color.blue);
        pestaña2.setBackground(Color.CYAN);
        Catalogo.setBackground(Color.DARK_GRAY);
        Carrito.setBackground(Color.ORANGE);
        Catalogo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        Scroll1.getVerticalScrollBar().setUnitIncrement(16);
        Scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                
        String[][] matrizJuegos = Juegos.obtenerTodosLosJuegos();
        
        Buscar.add(Generos);
        Buscar.add(Plataforma);
        Buscar.add(Nombres);
        JuntarF.add(Buscar, BorderLayout.NORTH);
        JuntarF.add(Scroll1, BorderLayout.CENTER);
               
        for(int i=0; i<matrizJuegos.length; i++){
            String nombre = matrizJuegos[i][1];
            String Genero = matrizJuegos[i][2];
            String Precio = matrizJuegos[i][3];
            Tarjetas = TarjetaVisual(nombre,Genero,Precio);
            Catalogo.add(Tarjetas);
        }     
  
        pestaña1.add(btn1);
        pestaña2.add(JuntarF);
        pestaña2.add(Carrito);  
        Carrito.add(btn2);

        mainPanel.add(pestaña1,"pestaña1");
        mainPanel.add(pestaña2,"pestaña2");
        
        btn1.addActionListener((e)->{
            Interfaz.show(mainPanel,"pestaña2");
        });
        
        btn2.addActionListener((e)->{
            Interfaz.show(mainPanel,"pestaña1");
        });
        
        Generos.addActionListener((e)->filtrar());
        Plataforma.addActionListener((e)->filtrar());
        
        Nombres.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
            public void insertUpdate(javax.swing.event.DocumentEvent e) {filtrar();}
            public void removeUpdate(javax.swing.event.DocumentEvent e) {filtrar();}
            public void changedUpdate(javax.swing.event.DocumentEvent e) {filtrar();}
        });

        add(mainPanel);
        setSize(1050,750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    public static void main(String[] args) {
        
        new Proyecto2();
    }
    
    private static JPanel TarjetaVisual(String nombre, String genero, String precio)
    {
        JPanel tarjeta = new JPanel();
        JLabel Titulo = new JLabel(nombre);
        JLabel Precio = new JLabel(precio);
        JLabel Genero = new JLabel (genero);
        
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(Color.WHITE);
        
        tarjeta.add(Titulo);
        tarjeta.add(Precio);
        tarjeta.add(Genero);
        
        return tarjeta;
    }
    
    public void filtrar()
    {
        String nombre, Genero, precio;
        Catalogo.removeAll();
        
        String busqeuda = Nombres.getText();
        String genero = Generos.getSelectedItem().toString();
        String plataforma = Plataforma.getSelectedItem().toString();
        
        String[][]ParametrosBuscados = Busqueda.Filtros(matrizBuscar, busqeuda, genero, plataforma);
        for(int j=0;j<ParametrosBuscados.length; j++)
        {
            nombre = ParametrosBuscados[j][1];
            Genero = ParametrosBuscados[j][2];
            precio = ParametrosBuscados[j][3];
            
            Catalogo.add(TarjetaVisual(nombre,Genero,precio));
        }
        Catalogo.revalidate();
        Catalogo.repaint();
    }
}
