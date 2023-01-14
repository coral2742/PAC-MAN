package uo.cpm.modulo.game;

public class Espacio extends Casilla {

	@Override
	public String toString() {
		return "Espacio [getTipo()=" + getTipo() + "]";
	}

	public Espacio() {
		setTipo("Espacio");
	}
}
