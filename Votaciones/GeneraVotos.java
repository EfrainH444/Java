import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GeneraVotos {

static ArrayList <Voto> Votos = new ArrayList<>();

public static void main(String[] args)
{                 

	if (args.length > 0) {
            int Cuantos = Integer.parseInt(args[0]);
            new Thread(() -> {
	    while (true) {
		try {
		    Thread.sleep(1000);
		    for(int i = 0;i <Cuantos;i++)
		    { 
		    String curpAux = getCURP();
		    String partidoAux = getPartido();
		    Voto auxVoto = new Voto(curpAux,partidoAux);
		    System.out.println(auxVoto);
		    Votos.add(auxVoto);
		    }
		    try (FileOutputStream fileOut = new FileOutputStream("curps.txt");
		         ObjectOutputStream objOut = new ObjectOutputStream(fileOut)) {
		         objOut.writeObject(Votos);
		    } catch (Exception ex) {
		        ex.printStackTrace();
		    }
		    
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
	    }
	}).start();
        } else {
            System.out.println("Debe proporcionar un parámetro entero");
        }
	
}  

static String getPartido(){
String Partidos[] = {"PAN", "PRI", "PRD", "P T", "PVE", "MOR"};
StringBuilder sb = new StringBuilder(3);    
sb.append(Partidos[(int)(Math.random()*6)]);  
return sb.toString();  
}      

static String getCURP()
{
String Letra = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
String Numero = "0123456789";
String Sexo = "HM";
String Entidad[] = {"AS", "BC", "BS", "CC", "CS", "CH", "CL", "CM", "DF", "DG", "GT", "GR", "HG", "JC", "MC", "MN", "MS", "NT", "NL", "OC", "PL", "QT", "QR", "SP", "SL", "SR", "TC", "TL", "TS", "VZ", "YN", "ZS"};
int indice;
StringBuilder sb = new StringBuilder(18);        
for (int i = 1; i < 5; i++) {
    indice = (int) (Letra.length()* Math.random());
    sb.append(Letra.charAt(indice));        
}

for (int i = 5; i < 11; i++) {
    indice = (int) (Numero.length()* Math.random());
    sb.append(Numero.charAt(indice));        
}

indice = (int) (Sexo.length()* Math.random());
sb.append(Sexo.charAt(indice));   
            
sb.append(Entidad[(int)(Math.random()*32)]);
for (int i = 14; i < 17; i++) {
    indice = (int) (Letra.length()* Math.random());
    sb.append(Letra.charAt(indice));        
}
for (int i = 17; i < 19; i++) {
    indice = (int) (Numero.length()* Math.random());
    sb.append(Numero.charAt(indice));        
}
return sb.toString();
}           

}
