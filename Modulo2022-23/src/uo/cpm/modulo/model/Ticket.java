package uo.cpm.modulo.model;

public class Ticket {
	
	private String codigoTienda;
	private String numTicket;
	private double importe;
	
	public Ticket(String codigoTienda, String numTicket, double importe) {
		this.codigoTienda = codigoTienda;
		this.numTicket = numTicket;
		this.importe = importe;
	}

	public Ticket(String codigoTienda, String numTicket) {
		this.codigoTienda = codigoTienda;
		this.numTicket = numTicket;
		this.importe = 0;
	}

	public String getCodigoTienda() {
		return codigoTienda;
	}


	public void setCodigoTienda(String codigoTienda) {
		this.codigoTienda = codigoTienda;
	}


	public String getNumTicket() {
		return numTicket;
	}


	public void setNumTicket(String numTicket) {
		this.numTicket = numTicket;
	}


	public double getImporte() {
		return importe;
	}


	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	
	

}
