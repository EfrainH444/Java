import java.util.ArrayList;
import java.util.Collection;
import java.io.*;
import java.lang.*;
import java.util.*;
public class PoligonoIrreg{
ArrayList <Coordenada> vertices = new ArrayList<>();
public void PoligonoIrreg(){
    
}
public void CoordenadaOrdenada() {
    Collections.sort(vertices, new Ordena());
    System.out.println("\nVertices Ordenados\n");
    for (int i = 0; i < vertices.size(); i++){
    String aux = "Vertice ["+ i +"]";
    System.out.println(aux + " " + vertices.get(i));
    }
}
public void añadeVertice(double x,double y){
    Coordenada vertice = new Coordenada (x,y);
    vertices.add(vertice);     
}
@Override
public String  toString(){
    String cadena ="";
    int cont_aux=0;
    for(Coordenada vertice : vertices ){ 
        String aux = "Vertice ["+cont_aux+"]";
        String vertice_string = vertice.toString();
        cadena = cadena + "" + aux + " " + vertice_string + "\n";
        cont_aux++;
    }   
        
    return cadena;
}
}
