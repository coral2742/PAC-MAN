package uo.cpm.modulo.game;

public class Casilla {
	private String tipo;
	private String imagen;
	private int x;
	private int y;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	protected final String[] coloresInvasor = new String [] {"Amarillo", "Cabecilla", "Celeste", "Morado", "Naranja", "Rojo", "Rosa", "Verde"};
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public boolean isInvasor() {
		return tipo.equals("Invasor");
	}
	
	public boolean isEspacio() {
		return tipo.equals("Espacio");
	}
	
	public boolean isHueco() {
		return tipo.equals("Hueco");
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	public String getImagen() {
		return imagen;
	}
	
	
	@Override
	public String toString() {
		return "Casilla [tipo=" + tipo + "]";
	}
	
	
	
	
}
