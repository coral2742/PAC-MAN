package uo.cpm.modulo.game;

public class Hueco extends Casilla{
	
	@Override
	public String toString() {
		return "Hueco [getTipo()=" + getTipo() + "]";
	}

	public Hueco() {
		setTipo("Hueco");
	}

}
