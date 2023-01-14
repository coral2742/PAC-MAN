package uo.cpm.modulo.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class VentanaFinalizar extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnlPrincipal;
	private JButton btnFinalizar;
	private JTextArea txtFinalTexto;


	private VentanaPrincipal VP;
	private VentanaPremios Vpremios;
	private JLabel lblFantasma;
	private JTextArea txtYaPuedePasar;

	/**
	 * Create the frame.
	 */
	public VentanaFinalizar(VentanaPrincipal VP, VentanaPremios Vpremios) {
		setResizable(false);
		this.Vpremios = Vpremios;
		this.VP = VP;
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaFinalizar.class.getResource("/img/pacman-icon.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 540, 308);
		pnlPrincipal = new JPanel();
		pnlPrincipal.setBackground(new Color(0, 0, 0));
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnlPrincipal);
		pnlPrincipal.setLayout(null);
		pnlPrincipal.add(getBtnFinalizar());
		pnlPrincipal.add(getTxtFinalTexto());
		pnlPrincipal.add(getLblFantasma());
		pnlPrincipal.add(getTxtYaPuedePasar());
		
		localizar(VP.getLocalizacion());
		// Reproducir sonido
		VP.reproducirSonidoGameOver();
	}
	
	private void localizar(Locale localizacion) {
		ResourceBundle textos = VP.getTextosLocalizacion();
		// Título de la ventana
		setTitle(textos.getString("fin.titulo"));
		
		getBtnFinalizar().setText(textos.getString("final.finalizar"));
		getBtnFinalizar().setMnemonic(textos.getString("final.finalizarMnemonico").charAt(0));
		
		getTxtFinalTexto().setText(textos.getString("final.gracias"));
		getTxtYaPuedePasar().setText(textos.getString("final.recoger"));		
	}

	private JButton getBtnFinalizar() {
		if (btnFinalizar == null) {
			btnFinalizar = new JButton();
			btnFinalizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaPrincipal();
				}
			});
			btnFinalizar.setBounds(374, 219, 140, 39);
		}
		return btnFinalizar;
	}
	private JTextArea getTxtFinalTexto() {
		if (txtFinalTexto == null) {
			txtFinalTexto = new JTextArea();
			txtFinalTexto.setBackground(new Color(0, 0, 0));
			txtFinalTexto.setLineWrap(true);
			txtFinalTexto.setWrapStyleWord(true);
			txtFinalTexto.setEditable(false);
			txtFinalTexto.setBounds(10, 11, 670, 57);
			txtFinalTexto.setForeground(Color.ORANGE);
			txtFinalTexto.setFont(VP.pacmanFont.deriveFont(25f));
		}
		return txtFinalTexto;
	}
	private JLabel getLblFantasma() {
		if (lblFantasma == null) {
			lblFantasma = new JLabel();
			lblFantasma.setBounds(374, 69, 134, 134);
			lblFantasma.setHorizontalAlignment(SwingConstants.CENTER);
			VP.setImagenAdaptada(lblFantasma, "/img/fantasmaNaranja.png");
		}
		return lblFantasma;
	}
	
	protected void mostrarVentanaPrincipal() {
		Vpremios.dispose();
		this.dispose();
		VP.setVisible(true);
		VP.reproducirSonidoInicio();
		
	}
	
	private JTextArea getTxtYaPuedePasar() {
		if (txtYaPuedePasar == null) {
			txtYaPuedePasar = new JTextArea();
			txtYaPuedePasar.setWrapStyleWord(true);
			txtYaPuedePasar.setLineWrap(true);
			txtYaPuedePasar.setForeground(Color.ORANGE);
			txtYaPuedePasar.setEditable(false);
			txtYaPuedePasar.setBackground(Color.BLACK);
			txtYaPuedePasar.setBounds(10, 69, 352, 129);
			txtYaPuedePasar.setFont(VP.pixelFont.deriveFont(25f));
		}
		return txtYaPuedePasar;
	}
}
