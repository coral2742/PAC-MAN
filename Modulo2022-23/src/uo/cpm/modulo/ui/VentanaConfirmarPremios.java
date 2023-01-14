package uo.cpm.modulo.ui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uo.cpm.modulo.model.Premio;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class VentanaConfirmarPremios extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnlPrincipal;
	
	private VentanaPrincipal VP;
	private VentanaPremios Vpremios;
	
	private JScrollPane scrollPanePedido;
	private JPanel pnlPremiosPedido;
	private JLabel lblConfirmarPremios;
	private JLabel lblTusPremios;
	private JButton btnVolver;
	private JButton btnConfirmar;
	private JLabel lblIntroduceDNI;
	private JTextField txtFieldDNI;
	private JLabel lblDNIvacio;
	private JTextArea txtAdvertencia;


	/**
	 * Create the frame.
	 */
	public VentanaConfirmarPremios(VentanaPrincipal VP, VentanaPremios Vpremios) {
		setResizable(false);
		this.VP = VP;
		this.Vpremios = Vpremios;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaConfirmarPremios.class.getResource("/img/pacman-icon.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 744, 397);
		pnlPrincipal = new JPanel();
		pnlPrincipal.setBackground(new Color(0, 0, 0));
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnlPrincipal);
		pnlPrincipal.setLayout(null);
		pnlPrincipal.add(getScrollPanePedido());
		pnlPrincipal.add(getLblConfirmarPremios());
		pnlPrincipal.add(getLblTusPremios());
		pnlPrincipal.add(getBtnVolver());
		pnlPrincipal.add(getBtnConfirmar());
		pnlPrincipal.add(getLblIntroduceDNI());
		pnlPrincipal.add(getTxtFieldDNI());
		pnlPrincipal.add(getLblDNIvacio());
		pnlPrincipal.add(getTxtAdvertencia());
		
		localizar(VP.getLocalizacion());
	}
	
	private void localizar(Locale localizacion) {
		ResourceBundle textos = VP.getTextosLocalizacion();
		// Título de la ventana
		setTitle(textos.getString("confirmar.titulo"));
		
		getLblConfirmarPremios().setText(textos.getString("confirmar.confirmarPremios"));
		
		getLblTusPremios().setText(textos.getString("confirmar.premiosUsuario"));
		
		getBtnVolver().setText(textos.getString("confirmar.volver"));
		getBtnVolver().setMnemonic(textos.getString("confirmar.volverMnemonico").charAt(0));
		
		getBtnConfirmar().setText(textos.getString("confirmar.confirmar"));
		getBtnConfirmar().setMnemonic(textos.getString("confirmar.confirmarMnemonico").charAt(0));
		
		getLblIntroduceDNI().setText(textos.getString("confirmar.introduceDNI"));
		getLblIntroduceDNI().setDisplayedMnemonic(textos.getString("confirmar.introduceDNIMnemonico").charAt(0));
		
		getLblDNIvacio().setText(textos.getString("confirmar.errorDNIVacio"));
		
		getTxtAdvertencia().setText(textos.getString("confirmar.advertenciaPuntos"));
	}
	
	private void actualizarPremiosPedido() {
		// Recorremos todos los premios del pedido
		for (Premio p : VP.getParquePrin().getArticulosPedido()) {
			// Por cada unidad de ese premio, añadimos una imagen
			for (int i = 0; i < p.getUnidades(); i++) {
				getPnlPremiosPedido().add(getImagenPremio(p));
			}
		}
	}
	
	
	private JLabel getImagenPremio(Premio p) {
		JLabel lblPremio = new JLabel();
		lblPremio.setSize(100,100);
		lblPremio.setToolTipText(p.getDenominacion());
		VP.setImagenAdaptada(lblPremio, "/img/" + p.getCodigo() +".PNG");
		return lblPremio;
	}
	private JScrollPane getScrollPanePedido() {
		if (scrollPanePedido == null) {
			scrollPanePedido = new JScrollPane();
			scrollPanePedido.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPanePedido.setBounds(10, 199, 367, 148);
			scrollPanePedido.setViewportView(getPnlPremiosPedido());
		}
		return scrollPanePedido;
	}
	private JPanel getPnlPremiosPedido() {
		if (pnlPremiosPedido == null) {
			pnlPremiosPedido = new JPanel();
			pnlPremiosPedido.setBackground(new Color(255, 255, 255));
			pnlPremiosPedido.setLayout(new GridLayout(0, 3, 20, 20));
			actualizarPremiosPedido();
		}
		return pnlPremiosPedido;
	}
	private JLabel getLblConfirmarPremios() {
		if (lblConfirmarPremios == null) {
			lblConfirmarPremios = new JLabel();
			lblConfirmarPremios.setBounds(10, 11, 479, 59);
			lblConfirmarPremios.setForeground(Color.ORANGE);
			lblConfirmarPremios.setFont(VP.pacmanFont.deriveFont(40f));
		}
		return lblConfirmarPremios;
	}
	private JLabel getLblTusPremios() {
		if (lblTusPremios == null) {
			lblTusPremios = new JLabel();
			lblTusPremios.setBounds(10, 156, 290, 32);
			lblTusPremios.setForeground(Color.WHITE);
			lblTusPremios.setFont(this.VP.pixelFont.deriveFont(20f));
		}
		return lblTusPremios;
	}
	private JButton getBtnVolver() {
		if (btnVolver == null) {
			btnVolver = new JButton();
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnVolver.setBounds(398, 302, 155, 45);
		}
		return btnVolver;
	}
	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton();
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (getTxtFieldDNI().getText().equals("")) {
						getLblDNIvacio().setVisible(true);
					}
					else {
						getLblDNIvacio().setVisible(false);
						dispose();
						// Crear fichero con los datos del cliente y los premios seleccionados
						VP.getParquePrin().setDNI(getTxtFieldDNI().getText());
						VP.getParquePrin().guardarPedido();
						mostrarVentanaFinal();
					}
				}
			});
			btnConfirmar.setForeground(new Color(255, 255, 255));
			btnConfirmar.setBounds(563, 302, 155, 45);
			btnConfirmar.setBackground(new Color(0, 102, 0));
		}
		return btnConfirmar;
	}
	private JLabel getLblIntroduceDNI() {
		if (lblIntroduceDNI == null) {
			lblIntroduceDNI = new JLabel();
			lblIntroduceDNI.setLabelFor(getTxtFieldDNI());
			lblIntroduceDNI.setBounds(10, 72, 276, 39);
			lblIntroduceDNI.setForeground(Color.WHITE);
			lblIntroduceDNI.setFont(this.VP.pixelFont.deriveFont(20f));
		}
		return lblIntroduceDNI;
	}
	private JTextField getTxtFieldDNI() {
		if (txtFieldDNI == null) {
			txtFieldDNI = new JTextField();
			txtFieldDNI.setBounds(10, 113, 276, 32);
			txtFieldDNI.setColumns(10);
			txtFieldDNI.setForeground(Color.BLACK);
			txtFieldDNI.setFont(this.VP.pixelFont.deriveFont(20f));
		}
		return txtFieldDNI;
	}
	private JLabel getLblDNIvacio() {
		if (lblDNIvacio == null) {
			lblDNIvacio = new JLabel();
			lblDNIvacio.setBounds(398, 260, 320, 35);
			lblDNIvacio.setFont(this.VP.pixelFont.deriveFont(20f));
			lblDNIvacio.setForeground(new Color(153, 0, 0));
			lblDNIvacio.setVisible(false);
		}
		return lblDNIvacio;
	}
	
	
	
	protected void mostrarVentanaFinal() {
		VentanaFinalizar vFinalizar = new VentanaFinalizar(this.VP, this.Vpremios);
		vFinalizar.setLocationRelativeTo(this);
		vFinalizar.setModal(true);
		vFinalizar.setVisible(true);
	}
	
	
	private JTextArea getTxtAdvertencia() {
		if (txtAdvertencia == null) {
			txtAdvertencia = new JTextArea();
			txtAdvertencia.setBackground(new Color(0, 0, 0));
			txtAdvertencia.setEditable(false);
			txtAdvertencia.setLineWrap(true);
			txtAdvertencia.setWrapStyleWord(true);
			txtAdvertencia.setBounds(398, 205, 320, 59);
			txtAdvertencia.setFont(this.VP.pixelFont.deriveFont(20f));
			txtAdvertencia.setForeground(Color.ORANGE);
			
		}
		return txtAdvertencia;
	}
}
