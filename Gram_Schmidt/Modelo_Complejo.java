/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gramschmidt;

/**
 *
 * @author Efrain Chavez
 */
public class Modelo_Complejo {
    
    private float Real;
    private float Imaginario;

    public float getReal() {
        return Real;
    }

    public void setReal(float Real) {
        this.Real = Real;
    }

    public float getImaginario() {
        return Imaginario;
    }

    public void setImaginario(float Imaginario) {
        this.Imaginario = Imaginario;
    }

    public Modelo_Complejo(float Real, float Imaginario) {
        this.Real = Real;
        this.Imaginario = Imaginario;
    }
    
}
