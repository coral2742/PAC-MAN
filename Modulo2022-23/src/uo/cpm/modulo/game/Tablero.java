package uo.cpm.modulo.game;

import java.awt.Point;
import java.util.ArrayList;

public class Tablero {
	private Casilla[][] casillas;
	private int DIM;
	private int numInvasores;
	
	protected final String[] coloresInvasor = new String [] {"Amarillo", "Celeste", "Morado", "Naranja", "Rojo", "Rosa", "Verde"};
	
	
	
	public Tablero(int dim, int nInvasores, int DEBUG) {
		this.DIM = dim;
		this.numInvasores = nInvasores;
		casillas = new Casilla[DIM][DIM];
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				casillas[i][j] = new Espacio();
			}
			
		}
	}
	
	
	
	public void inicializar(int DEBUG, String colorAleatorio) {
		limpiarCasillas();
		colocarHuecos();
		colocarCincoInvasores(DEBUG, colorAleatorio);
		
	}
	
	private void limpiarCasillas() {
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				casillas[i][j] = new Espacio();
			}
		}
	}
	



	public void colocarHuecos() {
		// Se colocan huecos en las esquinas y centro del tablero
		// Esquinas
		casillas[0][0] = new Hueco();
		casillas[0][DIM-1] = new Hueco();
		casillas[DIM-1][0] = new Hueco();
		casillas[DIM-1][DIM-1] = new Hueco();
		
		casillas[(DIM-1)/2][(DIM-1)/2] = new Hueco();
	}
	
	
	public void colocarCincoInvasores(int DEBUG, String colorAleatorio) {
		int posX = (int) (Math.random() * DIM);
		int posY = (int) (Math.random() * DIM);
		
		
		// Comprobamos el DEBUG
		// Si DEBUG = 0 -> Se sitúan 5 invasores aleatorios
		if (DEBUG == 0) {
			// Añadimos 5 invasores
			for (int invasor = 0; invasor < numInvasores; invasor++) {
				// Mientras no se encuentre un espacio libre
				while (!casillas[posX][posY].getTipo().equals("Espacio")) {
					posX = (int) (Math.random() * DIM);
					posY = (int) (Math.random() * DIM);
				}
				// Colocar invasor aleatorio
				casillas[posX][posY] = new Invasor();
				
			}
		}
		
		
		// Si DEBUG = 1 -> Se sitúan 5 cabecillas
		else if (DEBUG == 1) {
			// Añadimos 5 invasores de tipo cabecilla
			for (int invasor = 0; invasor < numInvasores; invasor++) {
				// Mientras no se encuentre un hueco libre
				while (!casillas[posX][posY].getTipo().equals("Espacio")) {
					posX = (int) (Math.random() * DIM);
					posY = (int) (Math.random() * DIM);
				}
				// Colocar invasor de tipo cabecilla
				casillas[posX][posY] = new Invasor("Cabecilla");
				
			}
		}
		
		
		// Si DEBUG = 2 -> Se sitúan 5 invasores del mismo tipo
		else if (DEBUG == 2) {
			// Añadimos 5 invasores
			for (int invasor = 0; invasor < numInvasores; invasor++) {
				// Mientras no se encuentre un hueco libre
				while (!casillas[posX][posY].getTipo().equals("Espacio")) {
					posX = (int) (Math.random() * DIM);
					posY = (int) (Math.random() * DIM);
				}
				// Colocar invasor de un solo tipo
				// Elegir color de invasor al azar y tipo distinto al del líder
				casillas[posX][posY] = new Invasor(colorAleatorio);
			}
		}
	}
	


	public Casilla[][] getCasillas() {
		return casillas;
	}



	public void colocarInvasor(Invasor invasor, int i, int j) {
		casillas[i][j] = new Invasor(invasor);
	}



	@Override
	public String toString() {
		String s = "Tablero = \n";
		
		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				if (casillas[i][j].getTipo().equals("Invasor")) {
					s += " I ";
				}
				
				else if (casillas[i][j].getTipo().equals("Espacio")) {
					s += " E ";
				}
				
				else if (casillas[i][j].getTipo().equals("Hueco")) {
					s += " H ";
				}
			}
			s += "\n";
		}
		
		return s;
	}



	public void eliminarCoordenadasPremiadas(ArrayList<Point> coordenadasEliminar) {
		int i;
		int j;
		
		
		System.out.println("Eliminar coordenadas:");
		
		System.out.println(toString());
		
		for (Point p : coordenadasEliminar) {
			i = (int) p.getX();
			j = (int) p.getY();
			
			casillas[i][j] = new Espacio();
		}
		
		
		System.out.println(toString());
		
	}




}
