import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.*;

public class Consultas implements Serializable {
	public static void main(String[] args) {
		try (FileInputStream Archivo_Entrada = new FileInputStream("curps.txt");
		ObjectInputStream Objeto_Entrada = new ObjectInputStream(Archivo_Entrada)) {
			ArrayList<Voto> Lista = (ArrayList<Voto>) Objeto_Entrada.readObject();
			//System.out.println(Lista);
			Consultas(Lista);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void Consultas (ArrayList<Voto> Lista){
	String Sigue = "";
	do{
		System.out.println("Bienvenido: Selecione la opción que desea consultar.\n");   		
		System.out.println("1.-¿Cuántos votos totales se han realizado por sexo?.");
		System.out.println("2.-¿Cuántos votos totales se han realizado por cada estado de la república?.");
		System.out.println("3.-¿Cuántos votos se han realizado por ciudadanos de x años de edad?");
		System.out.println("4.-¿Cuántos votos van por cada partido? \n");
		Scanner Entrada =  new Scanner(System.in);
		int Opcion = Entrada.nextInt();
		switch(Opcion){
		case 1:
			Consulta_Sexo(Lista);
		break;
		case 2:
		    	Consulta_Estado(Lista);		
		break;
		case 3:

		break;
		case 4: 
			Consulta_Partido(Lista);
		break;    		
	}
	System.out.println("Desea consultar otra opción (Si/No) .\n");   		
	Sigue = Entrada.next();
	}
	while(Sigue.equals("Si")||Sigue.equals("si")||Sigue.equals("s"));
    		
	}
	
	public static void Consulta_Sexo(ArrayList<Voto> Lista){
	int Cantidad_M = (int)Lista.stream().filter(v -> v.getCurp().charAt(10) == 'M').count();
	int Cantidad_H = (int)Lista.stream().filter(v -> v.getCurp().charAt(10) == 'H').count();
	System.out.println("\nCANTIDAD DE VOTOS POR SEXO --------------------\n");

	System.out.println("HOMBRES: "+Cantidad_H+" votos.");
	System.out.println("MUJERES: "+Cantidad_M+" votos.");
        System.out.println("--------------------------------------------------\n");
	}
	
	public static void Consulta_Estado(ArrayList<Voto> Lista){
	List<String> Entidad = Arrays.asList("AS", "BC", "BS", "CC", "CS", "CH", "CL", "CM", "DF", "DG", "GT", "GR", "HG", "JC", "MC", 
	"MN", "MS", "NT", "NL", "OC", "PL", "QT", "QR", "SP", "SL", "SR", "TC", "TL", "TS", "VZ", "YN", "ZS");
	System.out.println("\nCANTIDAD DE VOTOS POR ESTADO --------------------\n");
	
	Entidad.forEach(e -> System.out.println(e+":	"+(int)Lista.stream().filter(v -> v.getCurp().charAt(11) == e.charAt(0) &&
	v.getCurp().charAt(12) ==e.charAt(1)).count()+" votos." ));

	}
	
	
	public static void Consulta_Partido(ArrayList<Voto> Lista){
	int Cantidad_PAN = (int)Lista.stream().filter(v -> v.getPartido().equals("PAN")).count();
	int Cantidad_PRI = (int)Lista.stream().filter(v -> v.getPartido().equals("PRI")).count();
	int Cantidad_PRD = (int)Lista.stream().filter(v -> v.getPartido().equals("PRD")).count();
	int Cantidad_P_T = (int)Lista.stream().filter(v -> v.getPartido().equals("P T")).count();
	int Cantidad_PVE = (int)Lista.stream().filter(v -> v.getPartido().equals("PVE")).count();
	int Cantidad_MOR = (int)Lista.stream().filter(v -> v.getPartido().equals("MOR")).count();
	System.out.println("\nCANTIDAD DE VOTOS POR PARTIDO --------------------\n");
	System.out.println("PAN: "+Cantidad_PAN+" votos.");
	System.out.println("PRI: "+Cantidad_PRI+" votos.");
	System.out.println("PRD: "+Cantidad_PRD+" votos.");
	System.out.println("P T: "+Cantidad_P_T+" votos.");
	System.out.println("PVE: "+Cantidad_PVE+" votos.");
	System.out.println("MOR: "+Cantidad_MOR+" votos.");
        System.out.println("--------------------------------------------------\n");
	}
	
}


