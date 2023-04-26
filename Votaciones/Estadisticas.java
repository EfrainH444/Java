import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Estadisticas implements Serializable {
	public static void main(String[] args) {
	while (true) {
		try (FileInputStream Archivo_Entrada = new FileInputStream("curps.txt");
		 ObjectInputStream Objeto_Entrada = new ObjectInputStream(Archivo_Entrada)) {
		ArrayList<Voto> Lista = (ArrayList<Voto>) Objeto_Entrada.readObject();
		//System.out.println(Lista);
		Despliega(Lista);

		} catch (Exception e) {
		e.printStackTrace();
		}

		try {
		Thread.sleep(3000);
		} catch (InterruptedException e) {
		e.printStackTrace();
		}
	}
	}
    
    	public static void Despliega (ArrayList<Voto> Lista){
	ArrayList<Integer> Cantidades = new ArrayList<Integer>();
	int Cantidad_PAN = (int)Lista.stream().filter(v -> v.getPartido().equals("PAN")).count();
	int Cantidad_PRI = (int)Lista.stream().filter(v -> v.getPartido().equals("PRI")).count();
	int Cantidad_PRD = (int)Lista.stream().filter(v -> v.getPartido().equals("PRD")).count();
	int Cantidad_P_T = (int)Lista.stream().filter(v -> v.getPartido().equals("P T")).count();
	int Cantidad_PVE = (int)Lista.stream().filter(v -> v.getPartido().equals("PVE")).count();
	int Cantidad_MOR = (int)Lista.stream().filter(v -> v.getPartido().equals("MOR")).count();
	Cantidades.add(Cantidad_PAN);
	Cantidades.add(Cantidad_PRI);
	Cantidades.add(Cantidad_PRD);
	Cantidades.add(Cantidad_P_T);
	Cantidades.add(Cantidad_PVE);
	Cantidades.add(Cantidad_MOR);
	int maximo = Cantidades.stream().mapToInt(Integer::intValue).max().getAsInt();
	System.out.println("     "+Encabezado(maximo));
	System.out.println("PAN: "+ImprimeChar(Cantidad_PAN));
	System.out.println("PRI: "+ImprimeChar(Cantidad_PRI));
	System.out.println("PRD: "+ImprimeChar(Cantidad_PRD));
	System.out.println("P T: "+ImprimeChar(Cantidad_P_T));
	System.out.println("PVE: "+ImprimeChar(Cantidad_PVE));
	System.out.println("MOR: "+ImprimeChar(Cantidad_MOR));
    
    }
    
	public static String Encabezado(int Mayor){
	StringBuilder sb = new StringBuilder(Mayor); 
	sb.append('1');
	for(int i = 1 ; i < Mayor-1; i++){
		sb.append(' ');  
	}
	sb.append(String.valueOf(Mayor+"\n     "));
	sb.append('|');
	for(int i = 1 ; i < Mayor-1; i++){
		sb.append(' ');  
	}
	sb.append('|');
	return sb.toString(); 
	}

	public static String ImprimeChar (int Cantidad){
	StringBuilder sb = new StringBuilder(Cantidad); 
	for(int i = 0 ; i < Cantidad; i++){
		sb.append('*');  
	}
	return sb.toString();  
	}
}

