/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gramschmidt;

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.*;

/**
 *
 * @author Efrain Chavez
 */
public class Metodos {

    public ArrayList<Modelo_Complejo[][]> Pide_Vectores() {
        int aux = -1, cont = 1;
        Scanner teclado = new Scanner(System.in);
        ArrayList<Modelo_Complejo[][]> datos = new ArrayList<>();
        do {
            System.out.println("Ingresa los numeros complejos del vector |V" + cont + "> (3x3):\n");
            Modelo_Complejo[][] Vector = new Modelo_Complejo[3][3];
            for (int i = 0; i < 3; i++) {
                System.out.println("Ingresa la parte real del numero " + i + ": ");
                float auxR = Float.parseFloat(teclado.nextLine());
                System.out.println("Ingresa la parte imaginara del numero " + i + ": ");
                float auxI = Float.parseFloat(teclado.nextLine());
                Modelo_Complejo complejo = new Modelo_Complejo(auxR, auxI);
                Vector[i][0] = complejo;
            }
            for (int j = 0; j < 3; j++) {
                for (int k = 1; k < 3; k++) {
                    Modelo_Complejo complejo = new Modelo_Complejo(0, 0);
                    Vector[j][k] = complejo;
                }
            }
            datos.add(Vector);
            System.out.println("Desea agregar un vector nuevo? (Y/N)");
            String respuesta = teclado.nextLine();
            if (respuesta.equals("Y")) {
                cont++;
            } else {
                return datos;
            }
        } while (aux == -1);

        return datos;
    }

    public Modelo_Complejo[][] Llena0() {
        Modelo_Complejo[][] aux = new Modelo_Complejo[3][3];
        for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
                Modelo_Complejo complejo = new Modelo_Complejo(0, 0);
                aux[j][k] = complejo;
            }
        }
        return aux;
    }

    public Modelo_Complejo[][] Hermitian(Modelo_Complejo[][] Vector) {
        Modelo_Complejo[][] aux = Llena0();

        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                aux[i][k].setImaginario(Vector[k][i].getImaginario());
                aux[i][k].setReal(Vector[k][i].getReal());
            }
        }

        for (int i2 = 0; i2 < 3; i2++) {
            for (int j2 = 0; j2 < 3; j2++) {
                float Aux_F = aux[i2][j2].getImaginario() * -1;
                aux[i2][j2].setImaginario(Aux_F);
            }
        }
        return aux;
    }

    public float PPuntoReal(Modelo_Complejo[][] V1, Modelo_Complejo[][] V2) {
        return V1[0][0].getReal() * V2[0][0].getReal() + V1[0][1].getReal() * V2[1][0].getReal() + V1[0][2].getReal() * V2[2][0].getReal();
    }

    public Modelo_Complejo[][] MultVectorEscalar(Float Num, Modelo_Complejo[][] V1) {
        Modelo_Complejo[][] aux = Llena0();
        aux[0][0].setReal(V1[0][0].getReal() * Num);
        aux[1][0].setReal(V1[1][0].getReal() * Num);
        aux[2][0].setReal(V1[2][0].getReal() * Num);
        return aux;
    }

    public Modelo_Complejo[][] Resta(Modelo_Complejo[][] V1, Modelo_Complejo[][] V2) {
        Modelo_Complejo[][] aux = Llena0();
        aux[0][0].setReal(V1[0][0].getReal() - V2[0][0].getReal());
        aux[1][0].setReal(V1[1][0].getReal() - V2[1][0].getReal());
        aux[2][0].setReal(V1[2][0].getReal() - V2[2][0].getReal());
        return aux;
    }

    public ArrayList<Modelo_Complejo[][]> Resuelve(ArrayList<Modelo_Complejo[][]> datos) {
        ArrayList<Modelo_Complejo[][]> res = new ArrayList<>();
        //Agrega el primer vector  al resultado
        for (int i = 0; i < datos.size(); i++) {
            if (i == 0) {
                res.add(CopiaArreglo(datos.get(i)));
            } else {
                Modelo_Complejo[][] aux = Llena0();
                aux = datos.get(i);
                for (int j = 0; j < i; j++) {
                    aux = Resta(aux, MultVectorEscalar((PPuntoReal(Hermitian(res.get(j)), aux) / PPuntoReal(Hermitian(res.get(j)), res.get(j))), res.get(j)));
                }
                res.add(aux);
            }
        }
        return res;
    }

    public void Comprueba(ArrayList<Modelo_Complejo[][]> datos) {
        for (int i = 0; i < datos.size(); i++) {
            for (int j = 0; j < datos.size(); j++) {
                if (i != j) {
                    System.out.println("<W" + i + "|W" + j + "> : " + PPuntoReal(Hermitian(datos.get(i)), datos.get(j)));
                }
            }
        }
    }

    public void Normaliza(ArrayList<Modelo_Complejo[][]> datos) {
        Float aux[] = new Float[datos.size()];
        for (int i = 0; i < datos.size(); i++) {
            for (int j = 0; j < datos.size(); j++) {
                if (i == j) {
                    Float aux2 = PPuntoReal(Hermitian(datos.get(i)), datos.get(j));
                    System.out.println("<W" + i + "|W" + j + "> : " + aux2);
                    aux[i] = aux2;
                }
            }
        }
        System.out.println("LA BASE ORTONORMALIZADA ES:");
        for (int k = 0; k < datos.size(); k++) {
            String AuxF =Double.toString(1/Math.sqrt(aux[k].doubleValue()));   
            Lee_Vector(MultVectorEscalar(Float.parseFloat(AuxF),datos.get(k)));
        }

    }

    public Modelo_Complejo[][] CopiaArreglo(Modelo_Complejo[][] Vector) {
        Modelo_Complejo[][] aux = Llena0();
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                aux[i][k].setImaginario(Vector[i][k].getImaginario());
                aux[i][k].setReal(Vector[i][k].getReal());
            }
        }
        return aux;
    }

    public void Lee_Datos(ArrayList<Modelo_Complejo[][]> datos) {
        for (int i = 0; i < datos.size(); i++) {
            Modelo_Complejo[][] aux = datos.get(i);
            for (int j = 0; j < 3; j++) {
                System.out.print("[");
                for (int k = 0; k < 3; k++) {
                    Modelo_Complejo Complejo = aux[j][k];
                    System.out.print(Complejo.getReal() + " + (" + Complejo.getImaginario() + "i)" + "|");
                }
                System.out.print("]  \n");
            }
            System.out.println("-------------------------");
        }
    }

    public void Lee_Vector(Modelo_Complejo[][] datos) {
        Modelo_Complejo[][] aux = datos;
        for (int j = 0; j < 3; j++) {
            System.out.print("[");
            for (int k = 0; k < 3; k++) {
                Modelo_Complejo Complejo = aux[j][k];
                System.out.print(Complejo.getReal() + " + (" + Complejo.getImaginario() + "i)" + "|");
            }
            System.out.print("]  \n");
        }
        System.out.println("-------------------------");

    }

}
