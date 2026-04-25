/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.*;

/**
 *
 * @author elven
 */
public class ModificarTienda extends JDialog{
    private Proyecto2 ventana;
    
    public ModificarTienda(Proyecto2 ventana){
        this.ventana = ventana;
    }
    public void filtrar()
    {
        String nombre, Genero, precio;
        ventana.Catalogo.removeAll();
        
        String busqeuda = ventana.Nombres.getText();
        String genero = ventana.Generos.getSelectedItem().toString();
        String plataforma = ventana.Plataforma.getSelectedItem().toString();
        
        String[][]ParametrosBuscados = Busqueda.Filtros(ventana.matrizBuscar, busqeuda, genero, plataforma);
        for(int j=0;j<ParametrosBuscados.length; j++)
        {            
            ventana.Catalogo.add(TarjetaVisual(ParametrosBuscados[j]));
        }
        ventana.Catalogo.revalidate();
        ventana.Catalogo.repaint();
    }
    
    public JPanel TarjetaVisual(String [] Llamar)
    {
        JPanel tarjeta = new JPanel();
        JLabel Titulo = new JLabel(Llamar[1]);
        JLabel Precio = new JLabel(Llamar[3]);
        JLabel Genero = new JLabel (Llamar[2]);
        JButton btn3 = new JButton("Agregar al carrito");
        
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(Color.WHITE);
        
        tarjeta.add(Titulo);
        tarjeta.add(Precio);
        tarjeta.add(Genero);
        
        
        tarjeta.add(btn3);
        return tarjeta;
    }
}
