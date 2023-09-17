# PAC-MAN - De vuelta a los clásicos de los 80
Proyecto realizado para la asignatura de Comunicación Persona Máquina. Se planteó desarrollar una aplicación visual en Java con Swing implementando un juego de estrategia con un tablero de juego y varios personajes basándose en el diseño de un videojuego de los años 80.

![PAC-MAN - A game project for CPM](img/InicioPACMAN.png)

## Tabla de contenidos

- [PAC-MAN - De vuelta a los clásicos de los 80](#pac-man---de-vuelta-a-los-clásicos-de-los-80)
  - [Tabla de contenidos](#tabla-de-contenidos)
  - [Introducción](#introducción)
  - [Estructura del proyecto](#estructura-del-proyecto)
  - [Instalación (Windows)](#instalación-windows)
    - [Opción 1](#opción-1)
    - [Opción 2](#opción-2)
  - [Instrucciones](#instrucciones)
    - [Cómo jugar](#cómo-jugar)
      - [Códigos de tickets válidos](#códigos-de-tickets-válidos)
    - [Puntuación](#puntuación)
    - [Selección de premios](#selección-de-premios)
    - [Fin de la partida](#fin-de-la-partida)
  - [Librerías y recursos utilizados](#librerías-y-recursos-utilizados)
  - [Autor](#autor)
  - [Licencia](#licencia)


## Introducción
El contexto de esta aplicación consiste en que una conocida cadena de tiendas de videojuegos quiere premiar a sus clientes ofreciéndoles la participación en un juego basado en la estética de los personajes de los juegos de consola de los años 80. Cada tienda de la cadena ha instalado varios terminales en los que cada cliente tiene la posibilidad de jugar una partida al juego y llevarse, si tiene suerte y destreza, algún premio dependiendo de los puntos obtenidos durante la partida. Cada cliente que haya realizado una compra en una tienda obtendrá un ticket con el código de la tienda, código del ticket y cuyo importe si es superior a 20 euros, podrá optar a jugar a este juego y podrá ganar premios en relación al número de puntos que el usuario ha sido capaz de conseguir. Cuando el usuario seleccione sus premios y confirme su selección, se creará un fichero con su DNI, código de la tienda y los códigos de cada uno de los premios elegidos para que pueda reclamar los premios en la tienda.

Esta aplicación está internacionalizada, por lo que tiene la opción de cambio de idioma entre español e inglés, contiene sonidos en el inicio de la aplicación, inicio de juego, cada vez que se agrupan los personajes cada ronda y al final de la partida, y además, se utilizan tipografías personalizadas adaptadas a la temática para los textos de la aplicación.

## Estructura del proyecto
El proyecto se estructura de la siguiente forma:

- `bin/`: Contiene archivos binarios generados durante la compilación.
- `files/`: Almacena archivos de datos necesarios para el proyecto.
- `font/`: Contiene fuentes tipográficas utilizadas en el proyecto.
- `help/`: Contiene recursos de ayuda y documentación.
- `sounds/`: Almacena archivos de sonido utilizados en el proyecto.
- `src/`: Contiene el código fuente del proyecto.
  - `img/`: Almacena imágenes utilizadas en la interfaz gráfica.
  - `rcs/`: Archivos de recursos adicionales para el idioma.
  - `uo/cpm/modulo/`: Paquete principal del proyecto.
    - `game/`: Clases relacionadas con la lógica del juego.
    - `model/`: Clases que definen el modelo de datos.
    - `service/`: Clases de servicios y utilidades.
    - `ui/`: Clases relacionadas con la interfaz de usuario.
    - `util/`: Clases que gestionan operaciones de lectura y escritura de ficheros.
  - `Main.java`: Punto de entrada principal del proyecto.


## Instalación (Windows)
### Opción 1
En el apartado de [Releases](https://github.com/coral2742/PAC-MAN/releases) se ha subido un instalador creado con InstallAnyWhere con la primera versión [v1.0.0](https://github.com/coral2742/PAC-MAN/releases/tag/v1.0.0) de PAC-MAN.

- Descargar el archivo `.exe`.
- Doble click en `PAC-MAN installer.exe` y seguir el instalador.
- Una vez que se instale, se creará un acceso directo a la aplicación.

### Opción 2
Para configurar el entorno de desarrollo y ejecutar el proyecto, siga estos pasos:

1. Clone este repositorio: `git clone https://github.com/coral2742/PAC-MAN.git`.
2. Abra su entorno de desarrollo preferido.
3. Importe el proyecto en su entorno.
4. Compile y ejecute la clase `uo.cpm.modulo.Main` para iniciar la aplicación.



## Instrucciones
Una vez iniciada la aplicación, en la ventana principal se facilita la opción de cambio de idioma entre español e inglés. Una vez que se hace click en "Iniciar", se le pedirá un código de tienda y de ticket que posteriormente se validará con los [códigos válidos](#códigos-de-tickets-válidos) que se mencionan más adelante.

### Cómo jugar

Al empezar la partida, se verán dos tableros, entre otros elementos. El tablero grande es el tablero de juego sobre el que aparecen ya 5 fantasmas. Debajo de este tablero, aparece otro tablero más pequeño con 5 fantasmas que el usuario deberá de colocar estratégicamente para ganar la partida.

Para poder posicionar los siguientes fantasmas del tablero inferior sobre el tablero de juego, deberás seleccionar uno de los fantasmas haciendo click sobre el mismo, y seguido, hacer click en una de las posiciones libres del tablero de juego superior. Una casilla libre del tablero de juego, se representa con una casilla en gris. Las demás casillas, pueden estar ocupadas por otro fantasma, o bien, porque existe un hueco blanco inválido del juego.

![Ventana de juego](img/VentanaJuego.png)

#### Códigos de tickets válidos
Se le pedirá al usuario que introduzca el código de la tienda y el número de ticket que podrá encontrar en la copia del ticket de compra. Cada código de ticket, tiene asociado un importe cuyo mínimo para poder optar al juego PACMAN es de 20 euros. A continuación, se indican varios ejemplos de tickets válidos.

Código de tienda: `33429_01`


|  Código de ticket  |  Importe (€)  |     Validez     |
|--------------------|---------------|-----------------|
| 12314              | 10            | **No** válido   |
| 24572              | 110,75        | Válido          |
| 36517              | 25,85         | Válido          |
| 16416              | 20            | Válido          |
| 68234              | 75            | Válido          |
| 49801              | 19,95         | **No** válido   |


### Puntuación
El jugador comienza la partida con 0 puntos y se irán sumando al contador de la puntuación una vez que los 5 fantasmas se hayan posicionado y termine la ronda. Si se agrupan varios grupos de fantasmas en una misma ronda, se sumarán cada uno de ellos con las puntuaciones que se indican a continuación:

- **Grupo de 3 fantasmas**: incrementa 50 puntos.
- **Grupo de 4 fantasmas**: incrementa 200 puntos.
- **Grupo de 5 fantasmas**: incrementa 1000 puntos.
- **Grupo de 6 fantasmas**: incrementa 5000 puntos.
- **Grupo de 7 fantasmas**: incrementa 10000 puntos.

En una ronda, si se agrupan 5 o más fantasmas de tipo Cabecilla de golpe, finalizará la partida y se añadirán 20000 puntos.

### Selección de premios
Si el jugador finaliza la partida y gana, accederá a la ventana de selección de premios. En esta ventana, el jugador podrá filtrar los premios disponibles por categoría según el tipo de premio (Todos, Accesorios, Consolas o Videojuegos) o por puntos de forma ascendente o descendente.

Al seleccionar un premio, se muestra un panel a la derecha con detalles como nombre, imagen, descripción y coste en puntos. Se puede elegir la cantidad y hay botones para añadir o eliminar unidades. Si se superan los puntos disponibles o se seleccionan demasiadas unidades, se muestra un mensaje de advertencia.

![Ventana de Premios](img/VentanaPremios.png)

Para continuar, se debe elegir al menos un premio. Al confirmar, se pide al usuario su DNI y se le advierte que perderá los puntos restantes. Se muestra una ventana de confirmación con los premios seleccionados. Si no está satisfecho, puede volver atrás. Después de confirmar, se genera un archivo con DNI, código de tienda y códigos de los premios para reclamarlos en la tienda. Al finalizar, la aplicación se reinicia para un nuevo registro de ticket.

![Ventana de Confirmación de Premios](img/VentanaConfirmacionPremios.png)


### Fin de la partida
La partida puede finalizar por diversos motivos que se expresan a continuación:
- Si el jugador alcanza la ronda número 10 y la finaliza con huecos libres sobre el tablero de juego, finalizará la partida con la puntuación obtenida hasta ese momento.
- Si el jugador consigue eliminar un grupo de 5 o más fantasmas de tipo Cabecilla de golpe, la partida finalizará instantáneamente sumándose 20000 puntos al jugador.
- Si el tablero de juego queda completamente vacío, la partida finalizará después de haber agrupado los últimos fantasmas en el tablero y el jugador gana la partida al haber podido evitar la invasión.
- Si todas las casillas están ocupadas por fantasmas, la partida finalizará y el jugador perderá la partida con 0 puntos al no haber sido capaz de evitar la invasión de los fantasmas.

Si el jugador finaliza la partida y gana, tendrá la opción de elegir premios según los puntos que haya obtenido. Aparecerá una ventana de felicitación informando de que la partida ha finalizado y que, en este caso, podrá seleccionar sus premios.

En caso contrario, aparecerá una ventana informando al jugador de que la partida ha finalizado con un botón de confirmación que le llevará a la ventana de inicio.

![Ventana de Fin de Partida](img/VentanaFinPartida.png)

## Librerías y recursos utilizados
https://www.guioteca.com/los-80/la-historia-del-inolvidable-pac-man-el-videojuego-mas-famoso-de-la-historia/
https://www.kindpng.com/
https://pnggrid.com/
https://www.schemecolor.com/pac-man-game-colors.php
https://www.dafont.com/es/
https://www.dafont.com/es/pacmania.font
https://www.dafont.com/es/pixeltype.font
http://www.sonidosmp3gratis.com/

## Autor
[Coral Izquierdo Muñiz](https://github.com/coral2742)

Podéis contactar conmigo a través de mis redes sociales:


<a>
    <a href="https://github.com/coral2742">
      <img align="center" alt="Coral's Github" width="30px" height="30px" src="https://github.com/coral2742/coral2742/blob/dc9d7cfc77d0e21dee471a2c09c34c38c67b3406/img/icons/Instagram.svg" />
</a>
<a href="https://twitter.com/coral2742">
  <img align="center" alt="Coral's Twitter" width="30px" height="30px" src="https://github.com/coral2742/coral2742/blob/dc9d7cfc77d0e21dee471a2c09c34c38c67b3406/img/icons/twitterx.svg" />
</a>
<a href="mailto:coral2742@gmail.com">
  <img align="center" alt="Send email" width="30px" height="30px" src="https://github.com/coral2742/coral2742/blob/dc9d7cfc77d0e21dee471a2c09c34c38c67b3406/img/icons/Mail.svg" />
</a>
<a href="https://www.linkedin.com/in/coral-izquierdo">
  <img align="center" alt="Coral's Linkdein" width="30px" height="30px" src="https://github.com/coral2742/coral2742/blob/dc9d7cfc77d0e21dee471a2c09c34c38c67b3406/img/icons/linkedin.svg" />
</a>


## Licencia
Este proyecto se distribuye bajo la [Licencia MIT](LICENSE). Consulta el archivo `LICENSE` para obtener más detalles.
