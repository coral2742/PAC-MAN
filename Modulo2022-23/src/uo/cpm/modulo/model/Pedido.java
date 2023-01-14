package uo.cpm.modulo.model;

import java.util.ArrayList;
import java.util.List;

import uo.cpm.modulo.util.FileUtil;


public class Pedido {
	private List<Premio> listaPedido;
	private String codigoTienda;
	private int puntosGastados;
	private String DNI;
	
	private final String FICHERO_ENTREGAS = "entregas";
	
	
	
	
	public Pedido(Tienda tienda) {
		codigoTienda = tienda.getCodigoTienda();
		listaPedido = new ArrayList<Premio>();
		inicializar();
	}

	public void inicializar() {
		listaPedido.clear();
		puntosGastados = 0;
		DNI = "";
	}
	
	
	
	public void add(Premio articuloDelCatalogo, int unidades) {
		Premio articuloEnPedido = null;

		for (Premio a : listaPedido) {
			if (a.getCodigo().equals(articuloDelCatalogo.getCodigo())) {
				articuloEnPedido = a;
				articuloEnPedido.setUnidades(articuloEnPedido.getUnidades() + unidades);
				break;
			}
		}

		if (articuloEnPedido == null) {
			Premio articuloAPedido = new Premio(articuloDelCatalogo);
			articuloAPedido.setUnidades(unidades);
			listaPedido.add(articuloAPedido);
		}
		
		puntosGastados += unidades * articuloDelCatalogo.getPuntos();
	}

	public float getTotal() {
		float precio = 0;
		for (Premio a : listaPedido) {
			precio += a.getPuntos() * a.getUnidades();
		}
		return precio;
	}
	
	public int getCantidadArticulo(Premio a) {
		if (listaPedido.size() > 0) {
			for (int i = 0; i < listaPedido.size(); i ++) {
				if (listaPedido.get(i).getCodigo() == a.getCodigo()) {
					return listaPedido.get(i).getUnidades();
				}
			}
		}
		return 0;

	}
	
	
	public boolean estaEnPedido(Premio articulo) {
		for (Premio a : listaPedido) {
			if (a.getCodigo().equals(articulo.getCodigo()))
				return true;
		}
		return false;
	}
	
	
	public void delete(Premio articuloABorrar, int unidades) {
		Premio articuloEnPedido = null;

		for (Premio a : listaPedido) {
			if (a.getCodigo().equals(articuloABorrar.getCodigo())) {
				articuloEnPedido = a;

				break;

			}
		}

		if (articuloEnPedido != null) {
			if (unidades >= articuloEnPedido.getUnidades()) {
				puntosGastados -= articuloEnPedido.getUnidades() * articuloABorrar.getPuntos();
				articuloEnPedido.setUnidades(0);
				listaPedido.remove(articuloEnPedido);
			}
			else {
				articuloEnPedido.setUnidades(articuloEnPedido.getUnidades() - unidades);
				puntosGastados -= unidades * articuloABorrar.getPuntos();
			}
		}
		
		
	}

	public List<Premio> getListaPedido() {
		return listaPedido;
	}

	public void setListaPedido(List<Premio> listaPedido) {
		this.listaPedido = listaPedido;
	}

	public String getCodigo() {
		return codigoTienda;
	}


	public int getPuntosGastados() {
		return puntosGastados;
	}
	
	public String getDNI() {
		return DNI;
	}
	
	public void setDNI(String dniUsuario) {
		DNI = dniUsuario;
	}

	public void guardarPedido() {
		FileUtil.saveToFile(FICHERO_ENTREGAS, this);
		
	}

	@Override
	public String toString() {
		String s = "";
		// DNI del cliente
		s += DNI + "@";
		// Código de la tienda
		s += getCodigo();
		
		// Añadimos los códigos de cada premio
		for (Premio p : listaPedido) {
			s += "@" + p.getCodigo();
		}
		
		
		return s;
	}
	
	
	

}
