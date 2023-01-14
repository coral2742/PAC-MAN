package uo.cpm.modulo.model;

import java.util.ArrayList;
import java.util.List;

import uo.cpm.modulo.util.FileUtil;

public class Tienda {
	private static final String FICHERO_TIENDA = "files/config.dat";
	private static final String FICHERO_TICKETS = "files/tickets.dat";
	
	private String codigoTienda;
	private String nombreTienda;
	
	private List<Ticket> listaTickets = null;
	
	public Tienda() {
		listaTickets = new ArrayList<Ticket>();
		cargarDatosTienda();
		cargarDatosTickets();
	}
	
	
	private void cargarDatosTienda() {
		FileUtil.obtenerDatosTienda(FICHERO_TIENDA, this);
	}
	
	private void cargarDatosTickets() {
		FileUtil.obtenerDatosTickets(FICHERO_TICKETS, listaTickets);
	}
	
	
	
	public List<Ticket> getListaTickets(){
		return listaTickets;
	}

	
	public void setCodigoTienda(String codigoTienda) {
		this.codigoTienda = codigoTienda;
	}
	
	public void setNombreTienda(String nombreTienda) {
		this.nombreTienda = nombreTienda;
	}

	public String getCodigoTienda() {
		return codigoTienda;
	}
	
	
	public String getNombreTienda() {
		return nombreTienda;
	}


	public void borrarTicket(Ticket ticketUsuario) {
		for (int i = 0; i < listaTickets.size(); i++) {
			if (listaTickets.get(i).getCodigoTienda().equals(ticketUsuario.getCodigoTienda()) && listaTickets.get(i).getNumTicket().equals(ticketUsuario.getNumTicket())) {
				listaTickets.remove(i);
			}
		}
		
	}


	public boolean comprobarTicket(Ticket ticket) {
		// Comprueba si pertenece a la misma tienda y es válido
		for (int i = 0; i < listaTickets.size(); i++) {
			// Misma tienda y código válido
			if (listaTickets.get(i).getCodigoTienda().equals(ticket.getCodigoTienda()) && listaTickets.get(i).getNumTicket().equals(ticket.getNumTicket())) {
				ticket.setImporte(listaTickets.get(i).getImporte());
				return true;
			}
		}
		return false;
	}
}
