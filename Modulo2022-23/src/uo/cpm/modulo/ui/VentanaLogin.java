package uo.cpm.modulo.ui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uo.cpm.modulo.model.Ticket;
import uo.cpm.modulo.service.ParquePrincipado;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class VentanaLogin extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnlPrincipal;
	private JLabel lblCodigoTienda;
	private JLabel lblNumeroTicket;
	private JButton btnVolver;
	private JButton btnSiguiente;
	private JTextField txtCodigoTienda;
	private JTextField txtNumeroTicket;
	private JLabel lblFantasmaImg;
	private JButton btnAyudaIdentificador;
	private JLabel lblErrorCodigo;
	
	private VentanaPrincipal VP;
	private ResourceBundle textos;


	/**
	 * Create the frame.
	 */
	public VentanaLogin(VentanaPrincipal vp) {
		setResizable(false);
		this.VP = vp;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaLogin.class.getResource("/img/pacman-icon.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 810, 433);
		pnlPrincipal = new JPanel();
		pnlPrincipal.setBackground(new Color(0, 0, 0));
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlPrincipal);
		pnlPrincipal.setLayout(null);
		
		pnlPrincipal.add(getLblCodigoTienda());
		pnlPrincipal.add(getLblNumeroTicket());
		pnlPrincipal.add(getTxtCodigoTienda());
		pnlPrincipal.add(getTxtNumeroTicket());

		pnlPrincipal.add(getBtnVolver());
		pnlPrincipal.add(getBtnSiguiente());
		pnlPrincipal.add(getLblFantasmaImg());
		pnlPrincipal.add(getBtnAyudaIdentificador());
		pnlPrincipal.add(getLblErrorCodigo());
		
		localizar(VP.getLocalizacion());
	}
	
	private void localizar(Locale localizacion) {
		textos = VP.getTextosLocalizacion();
		// Título de la ventana
		setTitle(textos.getString("vl.titulo"));
		
		// Introduce el código
		getLblCodigoTienda().setText(textos.getString("vl.introCodigo"));
		getLblCodigoTienda().setDisplayedMnemonic(textos.getString("vl.introCodigoMnemonico").charAt(0));
		getLblNumeroTicket().setText(textos.getString("vl.introNumero"));
		getLblNumeroTicket().setDisplayedMnemonic(textos.getString("vl.introNumeroMnemonico").charAt(0));
		
		// Ayuda con el identificador
		getBtnAyudaIdentificador().setText(textos.getString("vl.dondeEncontrar"));
		getBtnAyudaIdentificador().setMnemonic(textos.getString("vl.dondeEncontrarMnemonico").charAt(0));
		
		// Botón volver
		getBtnVolver().setText(textos.getString("vl.volver"));
		getBtnVolver().setMnemonic(textos.getString("vl.volverMnemonico").charAt(0));
		
		// Botón siguiente
		getBtnSiguiente().setText(textos.getString("vl.siguiente"));
		getBtnSiguiente().setMnemonic(textos.getString("vl.siguienteMnemonico").charAt(0));
		
		// Texto de error
		getLblErrorCodigo().setText(textos.getString("vl.error"));

	}

	private JLabel getLblCodigoTienda() {
		if (lblCodigoTienda == null) {
			lblCodigoTienda = new JLabel();
			lblCodigoTienda.setLabelFor(getTxtCodigoTienda());
			lblCodigoTienda.setBounds(10, 11, 460, 46);
			lblCodigoTienda.setForeground(Color.ORANGE);
			lblCodigoTienda.setFont(VP.pacmanFont.deriveFont(22f));
		}
		return lblCodigoTienda;
	}
	private JLabel getLblNumeroTicket() {
		if (lblNumeroTicket == null) {
			lblNumeroTicket = new JLabel();
			lblNumeroTicket.setBounds(10, 123, 460, 46);
			lblNumeroTicket.setForeground(Color.ORANGE);
			lblNumeroTicket.setFont(VP.pacmanFont.deriveFont(22f));
			lblNumeroTicket.setLabelFor(getTxtNumeroTicket());
		}
		return lblNumeroTicket;
	}
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton();
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnVolver.setBounds(10, 328, 199, 54);
			btnVolver.setForeground(new Color(0, 0, 0));
			btnVolver.setFont(VP.pixelFont.deriveFont(25f));
		}
		return btnVolver;
	}
	private JButton getBtnSiguiente() {
		if (btnSiguiente == null) {
			btnSiguiente = new JButton();
			btnSiguiente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					validarDatos();
				}
			});
			btnSiguiente.setBounds(585, 328, 199, 54);
			btnSiguiente.setForeground(new Color(0, 0, 0));
			btnSiguiente.setFont(VP.pixelFont.deriveFont(25f));
		}
		return btnSiguiente;
	}
	
	protected void validarDatos() {
		// Comprobar si se ha introducido todos los datos del ticket necesarios
		if (getTxtCodigoTienda().getText().isBlank() || getTxtNumeroTicket().getText().isBlank()) {
			getLblErrorCodigo().setVisible(true);
		}
		else {
			comprobarCodigoTienda();
		}
		
		
		
	}

	private void comprobarCodigoTienda() {
		Ticket ticketUsuario = new Ticket(getTxtCodigoTienda().getText(),getTxtNumeroTicket().getText());
		
		
		// Comprobar si tiene mismo código de la tienda
		if (VP.getParquePrin().obtenerCodigoTienda().equals(ticketUsuario.getCodigoTienda())) {
			
			// Comprobar si es un código válido (misma tienda y existe ticket en el fichero)
			if (VP.getParquePrin().comprobarTicketTienda(ticketUsuario)) {
				// Comprobar el importe del ticket
				if (VP.getParquePrin().comprobarTicketImporte(ticketUsuario)) {
					getLblErrorCodigo().setVisible(false);
					// Borramos el ticket de la lista de tickets válidos
					VP.getParquePrin().borrarTicket(ticketUsuario);
					
					// Inicializamos la carta por si cambia el idioma
					VP.getParquePrin().inicializarCarta();
					
					mostrarVentanaJuego();
					// Mostramos la ventana de instrucciones
					mostrarVentanaAyudaJuego();
				}
				else {
					getLblErrorCodigo().setVisible(true);
					getLblErrorCodigo().setText(textos.getString("vl.errorImporte"));
				}
			}
			// El código del ticket no es válido
			else {
				getLblErrorCodigo().setVisible(true);
				getLblErrorCodigo().setText(textos.getString("vl.errorCodigo"));
			}
			
		}
		else {
			getLblErrorCodigo().setVisible(true);
			getLblErrorCodigo().setText(textos.getString("vl.errorPerteneceTienda"));
		}
		
	}
	

	private void mostrarVentanaJuego() {
		VentanaJuego VJ = new VentanaJuego(VP);
		VP.dispose();
		this.dispose();
		VJ.setLocationRelativeTo(this);
		VJ.setVisible(true);
		
	}
	
	protected void mostrarVentanaAyudaJuego() {
		VentanaAyudaJuego VAJ = new VentanaAyudaJuego(VP);
		VAJ.setModal(true);
		VAJ.setLocationRelativeTo(this);
		VAJ.setVisible(true);

	}

	private JTextField getTxtCodigoTienda() {
		if (txtCodigoTienda == null) {
			txtCodigoTienda = new JTextField();
			txtCodigoTienda.setBackground(new Color(240, 240, 240));
			txtCodigoTienda.setBounds(10, 50, 460, 46);
			txtCodigoTienda.setColumns(10);
			txtCodigoTienda.setFont(VP.pixelFont.deriveFont(25f));
		}
		return txtCodigoTienda;
	}
	private JTextField getTxtNumeroTicket() {
		if (txtNumeroTicket == null) {
			txtNumeroTicket = new JTextField();
			txtNumeroTicket.setBackground(new Color(240, 240, 240));
			txtNumeroTicket.setBounds(10, 160, 460, 46);
			txtNumeroTicket.setColumns(10);
			txtNumeroTicket.setFont(VP.pixelFont.deriveFont(25f));
		}
		return txtNumeroTicket;
	}
	private JLabel getLblFantasmaImg() {
		if (lblFantasmaImg == null) {
			lblFantasmaImg = new JLabel();
			lblFantasmaImg.setBounds(671, 11, 113, 113);
			VP.setImagenAdaptada(lblFantasmaImg, "/img/fantasmaCeleste.png");
		}
		return lblFantasmaImg;
	}
	private JButton getBtnAyudaIdentificador() {
		if (btnAyudaIdentificador == null) {
			btnAyudaIdentificador = new JButton();
			btnAyudaIdentificador.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarAyudaIdentificador();
				}
			});
			btnAyudaIdentificador.setBackground(Color.BLACK);
			btnAyudaIdentificador.setForeground(Color.ORANGE);
			btnAyudaIdentificador.setHorizontalAlignment(SwingConstants.LEFT);
			btnAyudaIdentificador.setBounds(10, 282, 460, 35);
			btnAyudaIdentificador.setFont(VP.pixelFont.deriveFont(25f));
			btnAyudaIdentificador.setBorder(null);
			btnAyudaIdentificador.setFocusable(false);
			btnAyudaIdentificador.setRequestFocusEnabled(false);
		}
		return btnAyudaIdentificador;
	}

	protected void mostrarAyudaIdentificador() {
		VentanaAyudaIdentificador VAI = new VentanaAyudaIdentificador(this.VP);
		VAI.setLocationRelativeTo(this);
		VAI.setModal(true);
		VAI.setVisible(true);
		
		
	}
	private JLabel getLblErrorCodigo() {
		if (lblErrorCodigo == null) {
			lblErrorCodigo = new JLabel();
			lblErrorCodigo.setHorizontalAlignment(SwingConstants.LEFT);
			lblErrorCodigo.setForeground(new Color(128, 0, 0));
			lblErrorCodigo.setBounds(10, 217, 774, 54);
			lblErrorCodigo.setFont(VP.pixelFont.deriveFont(25f));
			lblErrorCodigo.setVisible(false);
		}
		return lblErrorCodigo;
	}
}
