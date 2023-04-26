En este proyecto realizaremos la simulación de un proceso de votaciones con programas en Java e interfaz en línea de comandos UNIX.

En las siguientes votaciones para presidente de México, el INE pretende implementar un sistema de votos por vía electrónica mediante mensajes SMS.
El INE generará registrosde los votosen tiempo real que se irán guardando por seguridad en un único archivo. 
Cada registro incluye dos cadenas, el CURP del votante y el partido por el cual votó.Reutilice el programade la clase 12para generar CURPs, y modifíquelo
para generar n registros por segundo (donde n se le pasa como parámetro al programa). El programa irá guardando los registros generados en un archivo 
Curps.txt . Dicho programa se ejecuta en una terminal y nunca termina pues está simulando las votaciones en tiempo real de los ciudadanos.
Elabore otro programa que se encargará de imprimir las estadísticas de las votaciones (cuantos votos van por partido) en una terminal y en modo 
texto Esta gráfica se debere frescar cada tres segundos.

Por último,elaborar un programa que se ejecutará en un tercer terminal, la cualmediante interfaz de modo texto permitirá hacer cualquiera de las siguientes 
consultas mediante un menú:
1.-¿Cuántos votos totales se han realizado por sexo?
2.-¿Cuántos votos totales se han realizado por cada estado de la república?
3.-¿Cuántos votos se han realizado por ciudadanos de x años de edad?
4.-¿Cuántos votos van por cada partido?
