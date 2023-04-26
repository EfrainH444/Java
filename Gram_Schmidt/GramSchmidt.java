/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gramschmidt;

import java.util.ArrayList;

/**
 *
 * @author Efrain Chavez
 */
public class GramSchmidt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList <Modelo_Complejo[][]> datos = new ArrayList <>();
        Metodos Logica = new Metodos();
        datos = Logica.Pide_Vectores();
        System.out.println("DATOS:");
        Logica.Lee_Datos(datos);
        ArrayList <Modelo_Complejo[][]> resultado = Logica.Resuelve(datos);
        System.out.println("RESULTADOS:");
        Logica.Lee_Datos(resultado);
        System.out.println("COMPROBANDO:");
        Logica.Comprueba(resultado);
        System.out.println("NORMALIZANDO: ");
        Logica.Normaliza(resultado);
       
    }
}
