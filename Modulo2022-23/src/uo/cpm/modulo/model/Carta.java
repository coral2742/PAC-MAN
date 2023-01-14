package uo.cpm.modulo.model;

import java.util.*;

import uo.cpm.modulo.util.FileUtil;

public class Carta {

	private String FICHERO_ARTICULOS;
	private List<Premio> listaPremios = null;
	private List<String> listaCategorias = null;

	private ResourceBundle textos;

	public Carta(ResourceBundle textos) {
		listaPremios = new ArrayList<Premio>();
		listaCategorias = new ArrayList<String>();
		inicializar(textos);
	}

	public void inicializar(ResourceBundle textos) {
		this.textos = textos;
		this.FICHERO_ARTICULOS = textos.getString("premios.fichero");
		this.listaPremios.clear();
		this.listaCategorias.clear();
		cargarArticulos();
		obtenerCategorias();
	}

	private void cargarArticulos() {
		FileUtil.loadFile(FICHERO_ARTICULOS, listaPremios);
	}



	public Premio[] getPremios() {
		Premio[] premios = listaPremios.toArray(new Premio[listaPremios.size()]);
		return premios;
	}

	public void obtenerCategorias() {
		listaCategorias.add(textos.getString("premios.todos"));
		for (Premio p : listaPremios) {
			// Si la categoría no se ha añadido
			if (!listaCategorias.contains(p.getTipo())) {
				listaCategorias.add(p.getTipo());
			}
		}
	}

	public String[] getCategorias() {
		String[] categorias = listaCategorias.toArray(new String[listaCategorias.size()]);
		return categorias;
	}

}
