import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class DemoLambda{
    public static void main(String[] args) {
    List<Alumno> listaAlumnos = new ArrayList<>();
    listaAlumnos.add(new Alumno(1, "1717213183", "Javier Ignacio", "Molina Cano", "Java 8", 7, 28));
    listaAlumnos.add(new Alumno(2, "1717456218", "Lilian Eugenia", "Gomez Alvarez", "Java 8", 10,33));
    listaAlumnos.add(new Alumno(3, "1717328901", "Sixto Naranjoe", "Marin", "Java 8", 8.6, 15));
    listaAlumnos.add(new Alumno(4, "1717567128", "Gerardo Emilio", "Duque Gutiérrez", "Java 8", 10, 13));
    listaAlumnos.add(new Alumno(5, "1717902145", "Jhony Alberto", "Sáenz Hurtado", "Java 8", 9.5, 15)); 
    listaAlumnos.add(new Alumno(6, "1717678456", "Germán Antonio", "Lotero Upegui", "Java 8", 8, 34));
    listaAlumnos.add(new Alumno(7, "1102156732", "Oscar Darío", "Murillo González", "Java 8", 8, 32));
    listaAlumnos.add(new Alumno(8, "1103421907", "Augusto Osorno", "Palacio Martinez", "PHP", 9.5, 17));
    listaAlumnos.add(new Alumno(9, "1717297015", "César Oswaldo", "Alzate Agudelo", "Java 8", 8, 26));
    listaAlumnos.add(new Alumno(10, "1717912056", "Gloria Amparo", "González Castaño", "PHP", 10, 28));
    listaAlumnos.add(new Alumno(11, "1717912058", "Jorge León", "Ruiz Ruiz", "Python", 8, 22)); 
    listaAlumnos.add(new Alumno(12, "1717912985", "John Jairo", "Duque García", "Java Script", 9.4, 32));
    listaAlumnos.add(new Alumno (13, "1717913851", "Julio Cesar", "González Castaño", "C Sharp", 10, 22));
    listaAlumnos.add(new Alumno(14, "1717986531", "Gloria Amparo", "Rodas Monsalve", "Ruby", 7, 18));
    listaAlumnos.add(new Alumno(15, "1717975232", "Gabriel Jaime", "Jiménez Gómez", "Java Script", 10, 18));

//Obtener todos los alumnos de la lista
System.out.println("***Lista de Alumnos***");
    listaAlumnos.stream().forEach(a->System.out.println(a));

//Alumnos cuyo nombre empiezan con L u G
System.out.println("\n***Alumnos cuyo nombre empiezan con L u G***");
listaAlumnos.stream().
        filter(c -> c.getApellidos().charAt(0) == 'L' || c.getApellidos().charAt(0) == 'G')
        .forEach(c -> System.out.println(c));

//Longitud de la lista
System.out.println("\n***Numero de Alumnos***");
    System.out.println(listaAlumnos.stream().count());

//Obtener alumnos con notas mayores a 9 y que el curso sea PHP
System.out.println("\n***alumnos con nota mayor a 9 y que sean del curso de PHP***");
    //alumnos con notas mayores a 9
    listaAlumnos.stream().
    filter(a -> a.getNota() > 9 && a.getNombreCurso().equals("PHP"))
        .forEach(p -> System.out.println(p));

//Imprimir los 2 alumnos de la lista con el metodo limit(numero_elementos)
System.out.println("\n***Imprimir los 2 primeros Alumnos de la lista***");
    listaAlumnos.stream().limit(2).forEach(a -> System.out.println(a));

//Obtener el lumno que tiene la menor edad
System.out.println("\n***Imprimir el alumno con menor edad***");
    //minimo por edad
    System.out.println(listaAlumnos.stream().min((a1, a2) -> a1.getEdad() - a2.getEdad()));

//Obtener el lumno que tiene la mayor edad
System.out.println("\n***Imprimir el alumno con mayor edad***");
    //maximo por edad
    System.out.println(listaAlumnos.stream().max((a1, a2) -> a1.getEdad() - a2.getEdad()));

//Obtener el primer alumno de la lista con el metodo findFirst()
System.out.println("\n***Encontrar el primer Alumno***");
    //encontrar el primer registro
    System.out.println(listaAlumnos.stream().findFirst());

//Obtener los alumnos cuyos nombres de curso termine con el caracter 't'
System.out.println("\n***Alumnos en los que los nombres de los cursos (lenguajes) que terminan en t***");
    listaAlumnos.stream().filter(a -> a.getNombreCurso().endsWith("t")).forEach(System.out::println);


//Obtener los alumnos cuyos nombres de curso contengan el caracter 'a'
System.out.println("\n***Alumnos que tienen un curso en elque el nomre contienen la A***");
    listaAlumnos.stream().filter(a -> a.getNombreCurso().contains("a")).forEach(System.out::println);

//Obtener los Alumnos cuya longitud de nombres sea mayor a 10
System.out.println("\n***Alumnos en que su tamaño de su nombre es mayor a 10 caracteres***");
    listaAlumnos.stream().filter(a -> a.getNombres().length() > 10).forEach(System.out::println);

//Obtener los alumnos, cuyo nombre empiece con el caracter 'P' y la longitud de su nombre sea <= 6
System.out.println("\n***Combinacion de predicados***");
    Predicate<Alumno> empiezaconP = a -> a.getNombreCurso().startsWith("P");
    Predicate<Alumno> longitud = a -> a.getNombreCurso().length() <= 6;
    //Obtener todo
    listaAlumnos.stream().filter(empiezaconP.and(longitud)).forEach(System.out::println);
    
//Asignar a una nueva lista
List<Alumno> nuevalista = listaAlumnos.stream().filter(a -> a.getNombreCurso().contains("a")).collect(Collectors.toList());
nuevalista.forEach(System.out::println);

    }
}

