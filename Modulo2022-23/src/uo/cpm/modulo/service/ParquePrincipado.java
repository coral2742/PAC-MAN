package uo.cpm.modulo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import uo.cpm.modulo.model.Carta;
import uo.cpm.modulo.model.Pedido;
import uo.cpm.modulo.model.Premio;
import uo.cpm.modulo.model.Ticket;
import uo.cpm.modulo.model.Tienda;

public class ParquePrincipado {
	private Carta carta;
	private Pedido pedido;
	private Juego juego;
	
	private Tienda tienda;
	
	private ResourceBundle textos;
	
	public ParquePrincipado(int DEBUG, ResourceBundle textos) {
		this.textos = textos;
		this.carta = new Carta(this.textos);
		this.tienda = new Tienda();
		this.pedido = new Pedido(tienda);
		
		// Sólo se inicializa la tienda cuando se abre la aplicación
		inicializarTienda();
		inicializar(DEBUG, textos);
	}
	
	public void inicializar(int DEBUG, ResourceBundle textos) {
		this.textos = textos;
		inicializarJuego(DEBUG);
		inicializarPedido();
		inicializarCarta();
	}
	
	/**
	 * Método que comprueba si el ticket pertenece a la misma tienda
	 */
	public boolean comprobarTicketTienda(Ticket ticket) {
		return tienda.comprobarTicket(ticket);
	}
	
	
	public boolean comprobarTicketImporte(Ticket ticket) {
		// Comprueba si el importe del ticket es mayor o igual que 20
		if (ticket.getImporte() >= 20) {
			return true;
		}
		return false;
	}
	
	public void borrarTicket(Ticket ticketUsuario) {
		tienda.borrarTicket(ticketUsuario);
		
	}
	
	
	private void inicializarTienda() {
		tienda = new Tienda();
		
	}

	public String obtenerCodigoTienda() {
		return tienda.getCodigoTienda();
	}
	
	public String obtenerNombreTienda() {
		return tienda.getNombreTienda();
	}
	
	
	
	public Premio[] getPremiosCarta() {
		return carta.getPremios();
	}
	
	public void inicializarPedido() {
		pedido.inicializar();
	}
	
	public void inicializarJuego(int DEBUG) {
		juego = new Juego(DEBUG);
	}
	
	public void añadirAPedido(Premio premio, int unidades) {
		pedido.add(premio, unidades);
	}
	
	
	public String getCodigoPedido() {
		return pedido.getCodigo();
	}
	
	// Devuelve todos los elementos del pedido
	public List<Premio> getArticulosPedido() {
		return pedido.getListaPedido();
	}
	
	// Devuelve todas las categorías disponibles
	public String[] getCategoriasPremio(){
		return carta.getCategorias();
	}
	
	
	public void eliminarDePedido(Premio seleccionado, int cantidad) {
		// Comprobamos si el articulo existe en el Pedido		
		if (pedido.estaEnPedido(seleccionado)) {
			pedido.delete(seleccionado, cantidad);
		}
	}

	public Premio getArticulo(int selectedPremioIndex) {
		return carta.getPremios()[selectedPremioIndex];
	}
	
	public boolean estaEnPedido(Premio seleccionado) {
		return pedido.estaEnPedido(seleccionado);
	}
	
	public Premio[] filtrarPorCategoria(String categoria) {
		List<Premio> listaFiltrado = new ArrayList<Premio>();
		for (Premio a : carta.getPremios()) {
			if (a.getTipo().equals(categoria)){
				listaFiltrado.add(a);
			}
		}		
		
		Premio[] articulosFiltrados = listaFiltrado.toArray(new Premio[listaFiltrado.size()]);
		return articulosFiltrados;
	}

	public int getPuntosRestantes() {
		return  juego.getPuntos() - pedido.getPuntosGastados();
	}
	
	
	// Devuelve un String con los posibles filtrados de puntos
	public String[] getFiltroPuntos() {
		String[] filtrado = new String[2];
		filtrado[0] = textos.getString("premios.ascendente");
		filtrado[1] = textos.getString("premios.descendente");
		
		return filtrado;
	}
	
	
	public Juego getJuego() {
		return juego;
	}

	public void guardarPedido() {
		pedido.guardarPedido();
		
	}

	public void setDNI(String dni) {
		pedido.setDNI(dni);
	}

	public void inicializarCarta() {
		carta.inicializar(this.textos);
	}



	
	

}
