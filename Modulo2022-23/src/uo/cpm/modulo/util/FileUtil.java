package uo.cpm.modulo.util;

import java.io.*;
import java.util.*;

import uo.cpm.modulo.model.Premio;
import uo.cpm.modulo.model.Ticket;
import uo.cpm.modulo.model.Tienda;
import uo.cpm.modulo.model.Pedido;



public abstract class FileUtil {

	public static void obtenerDatosTienda(String nombreFicheroTienda, Tienda tienda) {
		String linea;
		String[] datosTienda = null;

		try {
			BufferedReader fichero = new BufferedReader(new FileReader(nombreFicheroTienda));
			while (fichero.ready()) {
				linea = fichero.readLine();
				datosTienda = linea.split("@");
				// String codigo, String denominacion, String descripcion, String tipo, int puntos, int unidades

				tienda.setCodigoTienda(datosTienda[0]);
				tienda.setNombreTienda(datosTienda[1]);
			}
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
	}


	public static void obtenerDatosTickets(String nombreFicheroTickets, List<Ticket> listaTickets) {
		String linea;
		String[] datosTicket = null;

		try {
			BufferedReader fichero = new BufferedReader(new FileReader(nombreFicheroTickets));
			while (fichero.ready()) {
				linea = fichero.readLine();
				datosTicket = linea.split("@");
				listaTickets.add(new Ticket(datosTicket[0], datosTicket[1], Double.parseDouble(datosTicket[2])));
			}
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
		
	}






	public static void loadFile (String nombreFicheroEntrada, List<Premio> listaCatalogo) {

		String linea;
		String[] datosArticulo = null;

		try {
			BufferedReader fichero = new BufferedReader(new FileReader(nombreFicheroEntrada));
			while (fichero.ready()) {
				linea = fichero.readLine();
				datosArticulo = linea.split("@");
				// String codigo, String denominacion, String descripcion, String tipo, int puntos, int unidades
				listaCatalogo.add(new Premio(datosArticulo[0], datosArticulo[1], datosArticulo[2], datosArticulo[3],
						Integer.parseInt(datosArticulo[4]), 0));
			}
			fichero.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha encontrado.");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida.");
		}
	}

	public static void saveToFile(String nombreFicheroSalida, Pedido pedido) {
		try {
			BufferedWriter fichero = new BufferedWriter(new FileWriter("files/" + nombreFicheroSalida + ".dat"));
			String linea = pedido.toString();
			fichero.write(linea);
			fichero.close();
		}

		catch (FileNotFoundException fnfe) {
			System.out.println("El archivo no se ha podido guardar");
		} catch (IOException ioe) {
			new RuntimeException("Error de entrada/salida");
		}
	}


}
