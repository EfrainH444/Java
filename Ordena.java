import java.io.*;
import java.lang.*;
import java.util.*;
public class Ordena implements Comparator <Coordenada>{
    
    @Override
    public int compare(Coordenada a, Coordenada b)
    {
        int aux = (int)a.distancia() - (int)b.distancia();
        return aux;
    }
}
