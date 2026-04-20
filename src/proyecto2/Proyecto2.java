/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.*;
/**
 *
 * @author elven
 */
public class Proyecto2 extends JFrame {
    CardLayout Interfaz = new CardLayout();
    JPanel mainPanel = new JPanel(Interfaz);
    JPanel pestaña1 = new JPanel();
    JPanel pestaña2 = new JPanel();
    
    JButton btn1 = new JButton("Tienda de VideoJuegos");
    JButton btn2 = new JButton("Regresar al menu");
    
    Color Cpestaña1 = new Color(255,100,100);
    
    Proyecto2(){
        pestaña1.setBackground(Color.blue);
        pestaña2.setBackground(Color.CYAN);
        
        pestaña1.add(btn1);
        pestaña2.add(btn2);

        mainPanel.add(pestaña1,"pestaña1");
        mainPanel.add(pestaña2,"pestaña2");
        
        btn1.addActionListener((e)->{
            Interfaz.show(mainPanel,"pestaña2");
        });
        
        btn2.addActionListener((e)->{
            Interfaz.show(mainPanel,"pestaña1");
        });
        
        add(mainPanel);
        setSize(650,650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    public static void main(String[] args) {
        new Proyecto2();
    }
    
}
