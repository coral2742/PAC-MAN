package uo.cpm.modulo.game;

public class TableroInvasores {
	private int DEBUG;
	private Casilla[] casillas;
	private int DIM;
	private int numInvasores;
	
	protected final String[] coloresInvasor = new String [] {"Amarillo", "Celeste", "Morado", "Naranja", "Rojo", "Rosa", "Verde"};
	
	public TableroInvasores(int dim,int nInvasores, int DEBUG) {
		this.DEBUG = DEBUG;
		this.DIM = dim;
		this.numInvasores = nInvasores;
		casillas = new Casilla[DIM];
		for (int i = 0; i < DIM; i++) {
			casillas[i] = new Espacio();
		}
	}
	
	public void colocarCincoInvasores(String colorAleatorio) {
		// Comprobamos el DEBUG
		// Si DEBUG = 0 -> Se sitúan 5 invasores aleatorios
		if (DEBUG == 0) {
			// Añadimos 5 invasores
			for (int invasor = 0; invasor < numInvasores; invasor++) {
				// Colocar invasor aleatorio
				casillas[invasor] = new Invasor();
				
			}
		}
		
		// Si DEBUG = 1 -> Se sitúan 5 cabecillas
		else if (DEBUG == 1) {
			// Añadimos 5 invasores de tipo cabecilla
			for (int invasor = 0; invasor < numInvasores; invasor++) {
				// Colocar invasor de tipo cabecilla
				casillas[invasor] = new Invasor("Cabecilla");
				
			}
		}
		
		// Si DEBUG = 2 -> Se sitúan 5 invasores del mismo tipo
		else if (DEBUG == 2) {
			// Añadimos 5 invasores
			for (int invasor = 0; invasor < numInvasores; invasor++) {
				// Colocar invasor de un solo tipo
				// Elegir color de invasor al azar y tipo distinto al del líder
				casillas[invasor] = new Invasor(colorAleatorio);
			}
		}
	}
	

	public int getNumInvasores() {
		return numInvasores;
	}

	public Casilla[] getCasillas() {
		return casillas;
	}

	public void quitarInvasor(int pos) {
		casillas[pos] = new Espacio();
	}

}