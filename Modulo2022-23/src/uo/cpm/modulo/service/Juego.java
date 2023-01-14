package uo.cpm.modulo.service;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import uo.cpm.modulo.game.Invasor;
import uo.cpm.modulo.game.Tablero;
import uo.cpm.modulo.game.TableroInvasores;

public class Juego {
	private int DEBUG;
	private int DIM;
	private int numInvasores;
	private int puntos;
	private int numIteraciones;
	private String colorAleatorio;
	private boolean finPartida;
	protected final String[] coloresInvasor = new String [] {"Amarillo", "Celeste", "Morado", "Naranja", "Rojo", "Rosa", "Verde"};
	private Tablero tablero;
	private TableroInvasores tableroInvasores;
	private Invasor invasorSeleccionado = null;
	private int posInvasorSeleccionado = 0;
	private int invasoresColocados = 0;

	public int getInvasoresColocados() {
		return invasoresColocados;
	}

	public void setInvasoresColocados(int invasoresColocados) {
		this.invasoresColocados = invasoresColocados;
	}

	public Juego(int DEBUG) {
		this.DEBUG = DEBUG;
		setDIM(7);
		setNumInvasores(5);

	}

	public void inicializar() {
		finPartida = false;
		colorAleatorio = coloresInvasor[(int) (Math.random() * DIM)]; 

		tablero = new Tablero(DIM, numInvasores, DEBUG);
		tableroInvasores = new TableroInvasores(numInvasores, numInvasores, DEBUG);


		puntos = 0;
		setNumIteraciones(1);
		setInvasorSeleccionado(null);
		invasoresColocados = 0;

		tablero.inicializar(DEBUG, colorAleatorio);
		tableroInvasores.colocarCincoInvasores(colorAleatorio);

	}


	public void setNumIteraciones(int numIteraciones) {
		this.numIteraciones = numIteraciones;
	}

	public boolean isFinJuego() {
		// Fin de partida si:
		// - N�mero de rondas (iteraciones) es 10
		// - Si se han eliminado m�s de 5 cabecillas del tir�n
		// - Si se ha quedado el tablero vac�o
		// - Si est� totalmente lleno
		if (getNumInvasoresRestante() == (DIM*DIM) - 5) {
			puntos = 0;
			return true;
		}
		
		if (isFinPartida() || numIteraciones > 10 || getNumInvasoresRestante() == 0) {
			return true;
		}
		else {
			return false;
		}
	}


	private int getNumInvasoresRestante() {
		int contador = 0;
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				if (tablero.getCasillas()[i][j].getTipo().equals("Invasor")) {
					contador += 1;
				}
			}
		}
		return contador;
	}

	public boolean isFinRonda() {
		if (invasoresColocados == 5) {
			return true;
		}
		else {
			return false;
		}
	}



	public int getPuntos() {
		return puntos;
	}


	public int getDIM() {
		return DIM;
	}

	public void setPuntos(int puntos) {
	}


	public void setDIM(int dim) {
		this.DIM = dim;
	}

	public void setNumInvasores(int numInvasores) {
		this.numInvasores = numInvasores;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public TableroInvasores getTableroInvasores() {
		return tableroInvasores;
	}

	public int getNumIteraciones() {
		return numIteraciones;
	}


	public void setInvasorSeleccionado(Invasor i, int pos) {
		this.invasorSeleccionado = new Invasor(i);
		this.posInvasorSeleccionado = pos;
	}

	public void setInvasorSeleccionado(Invasor i) {
		this.invasorSeleccionado = i;
	}

	public Invasor getInvasorSeleccionado() {
		return invasorSeleccionado;
	}

	public int getPosicionInvasorSeleccionado() {
		return posInvasorSeleccionado;
	}
	
	public boolean isFinPartida() {
		return finPartida;
	}





	/**
	 * M�todo que comprueba si se han creado agrupaciones de invasores en cada ronda y suma puntos
	 */
	public void comprobarJuego() {
		int consecutivos;
		Invasor invasor;
		String color;
		ArrayList<Point> coordenadasEliminar = new ArrayList<Point>();

		// Recorremos por filas
		for (int i = 0; i < DIM; i++) {
			consecutivos = 0;
			invasor = null;
			color = "";
			// Recorremos columnas
			for (int j = 0; j < DIM; j++) {
				// Si es un invasor
				if (tablero.getCasillas()[i][j].getTipo().equals("Invasor")) {
					
					// Guardar el invasor actual.
					invasor = (Invasor) tablero.getCasillas()[i][j];

					// Si es el primer invasor de la fila, se almacena su color.
					if (color.equals("")) {
						color = invasor.getColor();
					}
					
					// Se cambia el orden. Primero se comprueba si cambia el color, luego si es el �ltimo.
					// No son del mismo color. Cualquier momento de la fila.
					if (!(invasor.getColor().equals(color))) {
						
						// Si al detectar un invasor de otro color, hay m�s de 3 consecutivos anteriormente.
						if (consecutivos >= 3) {
							System.out.printf("A�adiendo premiados por filas en la posici�n: %d %d\n", i, j);
							sumarPuntos(consecutivos, color);
							a�adirPremiadosFilas(i, j, consecutivos, coordenadasEliminar);
						}
						
						// Se reestablece el color de los invasores consecutivos, y la cuenta pasa a ser 1.
						color = invasor.getColor();
						consecutivos = 1;
						System.out.printf("Se pone en 1 consecutivo por el invasor (%d, %d). Invasores: %d\n", i, j, consecutivos);
					}					
					else {
					//if (invasor.getColor().equals(color)) {
						consecutivos += 1;
						System.out.printf("Se suma 1 consecutivo por el invasor (%d, %d). Invasores: %d\n", i, j, consecutivos);
						
						// Aqu� se comprueba si el invasor detectado es el �ltimo de su fila.
						if(j == DIM - 1) {
							// Si es el �ltimo y toca a�adir puntos, se a�aden.
							if (consecutivos >= 3) {
								System.out.printf("A�adiendo premiados por filas por terminar el tablero en la posici�n: %d %d\n", i, j+1);
								sumarPuntos(consecutivos, color);
								a�adirPremiadosFilas(i, j+1, consecutivos, coordenadasEliminar);
							}
						}
					}
				}
				else {
					
					if (consecutivos >= 3) {
						System.out.printf("A�adiendo premiados en el ELSE de por filas en la posici�n: %d %d\n", i, j);
						sumarPuntos(consecutivos, color);
						a�adirPremiadosFilas(i, j, consecutivos, coordenadasEliminar);
					}
					//System.out.printf("Restableciendo color y consecutivos en las filas en la posici�n: %d %d\n", i, j);
					color = "";
					consecutivos = 0;
				}
			}
		}

		System.out.println(coordenadasEliminar.toString());




		// Recorremos por columnas
		for (int j = 0; j < DIM; j++) {
			consecutivos = 0;
			invasor = null;
			color = "";
			// Recorremos columnas
			for (int i = 0; i < DIM; i++) {
				// Si es un invasor
				if (tablero.getCasillas()[i][j].getTipo().equals("Invasor")) {

					// Guardar el invasor actual.
					invasor = (Invasor) tablero.getCasillas()[i][j];

					// Si es el primer invasor de la columna, se almacena su color.
					if (color.equals("")) {
						color = invasor.getColor();
					}

					// Se cambia el orden. Primero se comprueba si cambia el color, luego si es el �ltimo.
					// No son del mismo color. Cualquier momento de la columna.
					if (!(invasor.getColor().equals(color))) {
						
						// Si al detectar un invasor de otro color, hay m�s de 3 consecutivos anteriormente.
						if (consecutivos >= 3) {
							System.out.printf("A�adiendo premiados por columnas en la posici�n: %d %d\n", i, j);
							sumarPuntos(consecutivos, color);
							a�adirPremiadosColumnas(i, j, consecutivos, coordenadasEliminar);
							
						}
						
						// Se reestablece el color de los invasores consecutivos, y la cuenta pasa a ser 1.
						color = invasor.getColor();
						consecutivos = 1;
						System.out.printf("Se pone en 1 consecutivo por el invasor (%d, %d). Invasores: %d\n", i, j, consecutivos);
					}
					else {
							consecutivos += 1;
							System.out.printf("Se suma 1 consecutivo por el invasor (%d, %d). Invasores: %d\n", i, j, consecutivos);
							
							// Aqu� se comprueba si el invasor detectado es el �ltimo de su columna.
							if(i == DIM - 1) {
								// Si es el �ltimo y toca a�adir puntos, se a�aden.
								if (consecutivos >= 3) {
									System.out.printf("A�adiendo premiados por columnas por terminar el tablero en la posici�n: %d %d\n", i+1, j);
									sumarPuntos(consecutivos, color);
									a�adirPremiadosColumnas(i+1, j, consecutivos, coordenadasEliminar);
								}
							}
						}
					


				}
				else {
					if (consecutivos >= 3) {
						System.out.printf("A�adiendo premiados en el ELSE de por columnas en la posici�n: %d %d\n", i, j);
						sumarPuntos(consecutivos, color);
						a�adirPremiadosColumnas(i, j, consecutivos, coordenadasEliminar);

					}
					//System.out.printf("Restableciendo color y consecutivos en las columnas en la posici�n: %d %d\n", i, j);
					color = "";
					consecutivos = 0;
				}
			}
		}
		
		System.out.println("Coordenadas finales: " + coordenadasEliminar.toString());
		eliminarCoordenadasPremiadas(coordenadasEliminar);
	}

	private void eliminarCoordenadasPremiadas(ArrayList<Point> coordenadasEliminar) {
		tablero.eliminarCoordenadasPremiadas(coordenadasEliminar);	
	}

	private void a�adirPremiadosFilas(int posI, int posJ, int consecutivos, ArrayList<Point> coordenadasEliminar) {
		Point p;
		for (int c = 0; c < consecutivos; c++) {
			posJ = posJ - 1;
			// Si no existe, a�adir posici�n
			p = new Point(posI, posJ);
			if (!coordenadasEliminar.contains(p)) {
				coordenadasEliminar.add(p);
			}
		}
	}
	
	private void a�adirPremiadosColumnas(int posI, int posJ, int consecutivos, ArrayList<Point> coordenadasEliminar) {
		Point p;
		for (int c = 0; c < consecutivos; c++) {
			posI = posI - 1;
			// Si no existe, a�adir posici�n
			p = new Point(posI, posJ);
			if (!coordenadasEliminar.contains(p)) {
				coordenadasEliminar.add(p);
			}
		}
	}


	private void sumarPuntos(int consecutivos, String color) {
		
		System.out.println(color);
		
		if (consecutivos >= 5 && color.equals("Cabecilla")) {
			puntos += 20000;
			finPartida = true;
			
		}

		else if (consecutivos == 3) {
			puntos += 50;
		}

		else if (consecutivos == 4) {
			puntos += 200;
		}

		else if (consecutivos == 5) {
			puntos += 1000;
		}

		else if (consecutivos == 6) {
			puntos += 5000;
		}

		else if (consecutivos == 7) {
			puntos += 10000;
		}

	}




	public void a�adirInvasor(Invasor invasor, int i, int j) {
		tablero.colocarInvasor(invasor, i, j);



	}

	public void quitarInvasor(int i) {
		tableroInvasores.quitarInvasor(i);
	}

	public void a�adirIteraccion() {
		numIteraciones += 1;

	}


	public void nuevaRonda() {
		colocarCincoInvasores();
		invasoresColocados = 0;
		invasorSeleccionado = null;
		numIteraciones += 1;
	}


	public void colocarCincoInvasores() {
		tableroInvasores.colocarCincoInvasores(colorAleatorio);
	}
	

}
