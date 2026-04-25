/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author elven
 */
public class Busqueda {
    public static String[][]Filtros(String[][] Juegos, String Busqueda, String Genero, String Plataforma)
    {
        Busqueda = Busqueda.toLowerCase();
        int encuentros = 0;
        
        for(int i=0; i<Juegos.length; i++)
        {
            String codigo=Juegos[i][0].toLowerCase();
            String nombre=Juegos[i][1].toLowerCase();
            String genero=Juegos[i][2].toLowerCase();
            String plataforma=Juegos[i][4].toLowerCase();
            
            boolean coincideTexto = nombre.contains(Busqueda) || codigo.contains(Busqueda);
            boolean coincideGenero = Genero.equals("Todos") || genero.equalsIgnoreCase(Genero);
            boolean coincidePlataforma = Plataforma.equals("Todas") || plataforma.equalsIgnoreCase(Plataforma);
            
            if(coincideTexto && coincideGenero && coincidePlataforma)
            {
                encuentros++;
            }
        }
        
        int CantColumnas = Juegos.length>0 ? Juegos[0].length : 0;
        String [][] matrizEncuentros = new String[encuentros][CantColumnas];
        
        int buscador=0;
        for(int i = 0; i < Juegos.length; i++) {
            String codigo = Juegos[i][0].toLowerCase();
            String nombre = Juegos[i][1].toLowerCase();
            String genero = Juegos[i][2];
            String plataforma = Juegos[i][4];

            boolean coincideTexto = nombre.contains(Busqueda) || codigo.contains(Busqueda);
            boolean coincideGenero = Genero.equals("Todos") || genero.equals(Genero);
            boolean coincidePlataforma = Plataforma.equals("Todas") || plataforma.equals(Plataforma);

            if (coincideTexto && coincideGenero && coincidePlataforma) {
                matrizEncuentros[buscador] = Juegos[i];
                buscador++; 
            }
        }
        
        return matrizEncuentros;
    }    
}
