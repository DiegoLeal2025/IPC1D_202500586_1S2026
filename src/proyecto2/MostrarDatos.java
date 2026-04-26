/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import javax.swing.*;
import java.awt.*;

public class MostrarDatos extends JDialog{
    public MostrarDatos(Proyecto2 ventana, String[] datos)
    {
        super(ventana, "Información del juego",true);
        setLayout(new GridLayout(8,1,10,0));
        JButton btn1 = new JButton("Regresar");
        JLabel Titulo = new JLabel(datos[1]);
        JLabel Precio = new JLabel(datos[3]);
        JLabel Genero = new JLabel (datos[2]);
        JLabel Plataforma = new JLabel(datos[4]);
        JLabel Cantidad= new JLabel(datos[5]);
        JLabel Descripcion = new JLabel (datos[6]);
             
        btn1.addActionListener(e->{
            dispose();
        });
        add(btn1);
        add(Titulo);
        add(Precio);
        add(Genero);
        add(Plataforma);
        add(Cantidad);
        add(Descripcion);
        
        this.setSize(350,350);
        this.setVisible(true);
    }
}
