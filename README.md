# PACMAN
Proyecto realizado para la asignatura de Comunicación Persona Máquina. Se planteó desarrollar una aplicación visual implementando un juego de estrategia con un tablero de juego y varios personajes basándose en el diseño de un videojuego de los años 80.

## Tabla de contenidos

- [Introducción](#PACMAN)
- [Instalación](#Instalacion)
- [Instrucciones](#Instrucciones)
- [Códigos de tickets válidos](#Códigos-de-tickets-válidos)



## Instalación
PACMAN requiere `Java`.


## Instrucciones


## Códigos de tickets válidos
Se le pedirá al usuario que introduzca el código de la tienda y el número de ticket que podrá encontrar en la copia del ticket de compra. Aparece un botón de ayuda para encontrar los datos necesarios que le indicarán de forma gráfica y escrita dónde encontrar estos códigos en el ticket de compra. Un ticket válido será aquel cuyo código de tienda pertenezca al código de tienda del terminal de la aplicación (en este caso “33429_01”) y cuyo número de ticket esté registrado en el fichero de tickets válidos (“tickets.dat”) y su importe sea mayor o igual a 20 euros. En caso de que se introduzca un ticket no válido, se le indicará al usuario mediante un texto.

### Ejemplos de tickets válidos
Se pedirá un código de tienda y un código de ticket. Cada código de ticket, tiene asociado un importe cuyo mínimo para poder optar al juego PACMAN es de 20 euros.

Código de tienda: `33429_01`

Códigos de ticket con importes:
- `12314`
    - 10 euros (No válido)
- `24672`
    - 110,75 euros (Válido)
- `36517`
    - 25,85 euros (Válido)
- `16416`
    - 20 euros (Válido)
- `68234`
    - 75 euros (Válido)
- `49801`
    - 19,95 euros (No válido)


