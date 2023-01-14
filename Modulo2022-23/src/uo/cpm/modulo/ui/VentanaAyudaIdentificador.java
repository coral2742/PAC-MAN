package uo.cpm.modulo.ui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class VentanaAyudaIdentificador extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VentanaPrincipal VP;
	private JPanel pnlPrincipal;
	private JLabel lblDondeEncontrar;
	private JButton btnAceptar;
	private JLabel lblIdentificadorImagen;
	private JTextArea txtIndicaciones;


	/**
	 * Create the frame.
	 */
	public VentanaAyudaIdentificador(VentanaPrincipal vP) {
		setResizable(false);
		this.VP = vP;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaAyudaIdentificador.class.getResource("/img/pacman-icon.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 544, 366);
		pnlPrincipal = new JPanel();
		pnlPrincipal.setBackground(new Color(0, 0, 0));
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnlPrincipal);
		pnlPrincipal.setLayout(null);
		pnlPrincipal.add(getLblDondeEncontrar());
		pnlPrincipal.add(getBtnAceptar());
		pnlPrincipal.add(getLblIdentificadorImagen());
		pnlPrincipal.add(getTxtIndicaciones());
		
		localizar(VP.getLocalizacion());

	}
	
	private void localizar(Locale localizacion) {
		ResourceBundle textos = VP.getTextosLocalizacion();
		// Título de la ventana
		setTitle(textos.getString("ayuda.titulo"));
		// Título dónde encontrarlo
		getLblDondeEncontrar().setText(textos.getString("ayuda.dondeEncontrar"));
		
		// Botón aceptar
		getBtnAceptar().setText(textos.getString("ayuda.aceptar"));
		getBtnAceptar().setMnemonic(textos.getString("ayuda.aceptarMnemonico").charAt(0));
		
		// Label de indicaciones
		getTxtIndicaciones().setText(textos.getString("ayuda.indicaciones"));
		
		
	}

	private JLabel getLblDondeEncontrar() {
		if (lblDondeEncontrar == null) {
			lblDondeEncontrar = new JLabel();
			lblDondeEncontrar.setBounds(10, 11, 591, 47);
			lblDondeEncontrar.setForeground(Color.ORANGE);
			lblDondeEncontrar.setFont(VP.pacmanFont.deriveFont(22f));
		}
		return lblDondeEncontrar;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton();
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnAceptar.setBounds(366, 276, 152, 40);
			btnAceptar.setFont(VP.pixelFont.deriveFont(25f));
		}
		return btnAceptar;
	}
	private JLabel getLblIdentificadorImagen() {
		if (lblIdentificadorImagen == null) {
			lblIdentificadorImagen = new JLabel();
			lblIdentificadorImagen.setBounds(10, 71, 189, 208);
			VP.setImagenAdaptada(lblIdentificadorImagen, "/img/ticketEjemploCPM.png");
		}
		return lblIdentificadorImagen;
	}
	private JTextArea getTxtIndicaciones() {
		if (txtIndicaciones == null) {
			txtIndicaciones = new JTextArea();
			txtIndicaciones.setForeground(Color.ORANGE);
			txtIndicaciones.setBackground(new Color(0, 0, 0));
			txtIndicaciones.setLineWrap(true);
			txtIndicaciones.setWrapStyleWord(true);
			txtIndicaciones.setBounds(209, 69, 309, 196);
			txtIndicaciones.setFont(VP.pixelFont.deriveFont(20f));
		}
		return txtIndicaciones;
	}
}
