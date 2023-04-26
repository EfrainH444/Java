import java.io.Serializable;

public class Voto implements Serializable{

private String Curp, Partido;

public Voto() {}

public Voto(String Curp, String Partido) {
        this.Curp = Curp;
        this.Partido = Partido;
    }
    
public String getCurp (){
	return this.Curp;
	} 
	
public String getPartido (){
	return this.Partido;
	} 
	
public void setCurp (String curp){
	this.Curp = curp;
}

public void setPartido (String partido){
	this.Partido = partido;
}

@Override
public String toString( ) {

return "[Curp: " + Curp + " | Partido: " + Partido + "]";

}


}
