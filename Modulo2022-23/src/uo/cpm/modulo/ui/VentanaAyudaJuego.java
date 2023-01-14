package uo.cpm.modulo.ui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class VentanaAyudaJuego extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VentanaPrincipal VP;
	private JPanel pnlPrincipal;
	private JLabel lblCabecillaImg;
	private JLabel lblInstrucciones;
	private JTextArea txtInstrucciones;
	private JButton btnAceptar;
	private JTextArea txtInstruccionesGanar;



	/**
	 * Create the frame.
	 */
	public VentanaAyudaJuego(VentanaPrincipal VP) {
		setResizable(false);
		this.VP = VP;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaAyudaJuego.class.getResource("/img/pacman-icon.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 601, 418);
		pnlPrincipal = new JPanel();
		pnlPrincipal.setBackground(Color.BLACK);
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnlPrincipal);
		pnlPrincipal.setLayout(null);
		pnlPrincipal.add(getLblCabecillaImg());
		pnlPrincipal.add(getLblInstrucciones());
		pnlPrincipal.add(getTxtInstrucciones());
		pnlPrincipal.add(getBtnAceptar());
		pnlPrincipal.add(getTxtInstruccionesGanar());
		
		localizar(VP.getLocalizacion());
		// Reproducir sonido
		VP.reproducirSonidoJuego();
	}
	
	private void localizar(Locale localizacion) {
		ResourceBundle textos = VP.getTextosLocalizacion();
		
		System.out.println(localizacion.getLanguage());
		
		
		// Título de la ventana
		setTitle(textos.getString("ayudaJuego.titulo"));
		
		getLblInstrucciones().setText(textos.getString("ayudaJuego.instrucciones"));
		
		getTxtInstrucciones().setText(textos.getString("ayudaJuego.instruccionesTexto"));
		
		getBtnAceptar().setText(textos.getString("ayudaJuego.aceptar"));
		getBtnAceptar().setMnemonic(textos.getString("ayudaJuego.aceptarMnemonico").charAt(0));
		
		getTxtInstruccionesGanar().setText(textos.getString("ayudaJuego.instruccionesGanar"));
		
		
	}
	
	
	private JLabel getLblCabecillaImg() {
		if (lblCabecillaImg == null) {
			lblCabecillaImg = new JLabel();
			lblCabecillaImg.setBounds(407, 79, 160, 160);
			VP.setImagenAdaptada(lblCabecillaImg, "/img/fantasmaCabecilla.png");
		}
		return lblCabecillaImg;
	}
	private JLabel getLblInstrucciones() {
		if (lblInstrucciones == null) {
			lblInstrucciones = new JLabel();
			lblInstrucciones.setBounds(10, 11, 565, 44);
			lblInstrucciones.setForeground(Color.ORANGE);
			lblInstrucciones.setFont(VP.pacmanFont.deriveFont(40f));
		}
		return lblInstrucciones;
	}
	private JTextArea getTxtInstrucciones() {
		if (txtInstrucciones == null) {
			txtInstrucciones = new JTextArea();
			txtInstrucciones.setBackground(new Color(0, 0, 0));
			txtInstrucciones.setEditable(false);
			txtInstrucciones.setWrapStyleWord(true);
			txtInstrucciones.setLineWrap(true);
			txtInstrucciones.setBounds(10, 66, 310, 148);
			txtInstrucciones.setForeground(Color.ORANGE);
			txtInstrucciones.setFont(VP.pixelFont.deriveFont(20f));
		}
		return txtInstrucciones;
	}
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton();
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnAceptar.setBounds(332, 321, 243, 47);
			btnAceptar.setFont(VP.pixelFont.deriveFont(25f));
		}
		return btnAceptar;
	}
	private JTextArea getTxtInstruccionesGanar() {
		if (txtInstruccionesGanar == null) {
			txtInstruccionesGanar = new JTextArea();
			txtInstruccionesGanar.setBackground(new Color(0, 0, 0));
			txtInstruccionesGanar.setWrapStyleWord(true);
			txtInstruccionesGanar.setLineWrap(true);
			txtInstruccionesGanar.setEditable(false);
			txtInstruccionesGanar.setBounds(10, 225, 310, 143);
			txtInstruccionesGanar.setForeground(Color.ORANGE);
			txtInstruccionesGanar.setFont(VP.pixelFont.deriveFont(20f));
		}
		return txtInstruccionesGanar;
	}
}
