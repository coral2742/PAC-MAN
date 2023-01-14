package uo.cpm.modulo.game;

public class Invasor extends Casilla{
	
	private String color;
	
	public Invasor() {
		setTipo("Invasor");
		generarImagen();
	}
	
	/**
	 * Segundo constructor para crear un Invasor de un tipo de personaje pasado como parámetro
	 * @param imagen
	 */
	public Invasor(String color) {
		setTipo("Invasor");
		setImagen("fantasma" + color);
		setColor(color);
	}


	public Invasor(Invasor i) {
		this.color = i.getColor();
		this.setImagen(i.getImagen());
		this.setTipo(i.getTipo());
	}

	public boolean isCabecilla() {
		return (getImagen().equals("fantasmaCabecilla"));
	}
	
	public void generarImagen() {
		int color = (int) (Math.random() * coloresInvasor.length);
		setImagen("fantasma" + coloresInvasor[color]);
		this.color = coloresInvasor[color];
	}
	
	
	public String getColor() {
		return color;
	}
	
	private void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Invasor [color=" + color + ", Tipo=" + getTipo() + "]";
	}
	

}
