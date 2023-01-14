package uo.cpm.modulo.model;

public class Premio {
	// Código de identificación del premio
	private String codigo;
	// Nombre del premio
	private String denominacion;
	// Detalles del premio
	private String descripcion;
	// Categoría a la que pertenece el premio (Accesorios, Consolas o Videojuegos)
	private String tipo;
	// Puntos por los que se canjea el premio
	private int puntos;
	// Unidades del premio
	private int unidades;
	
	
	
	public Premio(String codigo, String denominacion, String descripcion, String tipo, int puntos, int unidades) {
		this.codigo = codigo;
		this.denominacion = denominacion;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.puntos = puntos;
		this.unidades = unidades;
	}
	
	public Premio(Premio articuloDelCatalogo) {
		this.codigo = articuloDelCatalogo.codigo;
		this.denominacion = articuloDelCatalogo.denominacion;
		this.descripcion = articuloDelCatalogo.descripcion;
		this.tipo = articuloDelCatalogo.tipo;
		this.puntos = articuloDelCatalogo.puntos;
		this.unidades = articuloDelCatalogo.unidades;
	}

	public String getCodigo() {
		return codigo;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getTipo() {
		return tipo;
	}
	public int getPuntos() {
		return puntos;
	}
	public int getUnidades() {
		return unidades;
	}
	
	
	
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	
	
	public String toString() {
		String strArticulo;
		strArticulo = this.denominacion + " - " + this.puntos + " pts.";
		if (this.unidades != 0) {
			strArticulo += " (" + this.unidades + " u." + ")";
		}
		return strArticulo;
	}


	
	
	
}
