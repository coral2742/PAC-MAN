package uo.cpm.modulo.ui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uo.cpm.modulo.service.Juego;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class VentanaGameOver extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VentanaPrincipal VP;
	private VentanaJuego VJ;
	private Juego juego;
	
	private JPanel pnlPrincipal;
	private JLabel lblFinPartida;
	private JButton btnConfirmar;
	private JTextArea lblMotivoFin;
	private JLabel lblFantasmaImg;


	/**
	 * Create the frame.
	 */
	public VentanaGameOver(VentanaPrincipal VP, VentanaJuego VJ) {
		this.VP = VP;
		this.VJ = VJ;
		this.juego = VP.getParquePrin().getJuego();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaGameOver.class.getResource("/img/pacman-icon.png")));
		setBounds(100, 100, 461, 307);
		pnlPrincipal = new JPanel();
		pnlPrincipal.setBackground(new Color(0, 0, 0));
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlPrincipal);
		pnlPrincipal.setLayout(null);
		pnlPrincipal.add(getLblFinPartida());
		pnlPrincipal.add(getBtnConfirmar());
		pnlPrincipal.add(getLblMotivoFin());
		pnlPrincipal.add(getLblFantasmaImg());
		
		localizar(VP.getLocalizacion());
		// Reproducir sonido
		VP.reproducirSonidoGameOver();
	}
	
	private void localizar(Locale localizacion) {
		ResourceBundle textos = VP.getTextosLocalizacion();
		// Título de la ventana
		setTitle(textos.getString("fin.titulo"));
		
		// Si se termina la partida con más de cero puntos
		if (juego.getPuntos() > 0) {
			getLblMotivoFin().setText(textos.getString("fin.canjeaPuntos"));
			getBtnConfirmar().setText(textos.getString("fin.siguiente"));
			getBtnConfirmar().setMnemonic(textos.getString("fin.siguienteMnemonico").charAt(0));
		}
		else {
			getLblMotivoFin().setText(textos.getString("fin.finalPartida"));
			getBtnConfirmar().setText(textos.getString("fin.finalizar"));
			getBtnConfirmar().setMnemonic(textos.getString("fin.finalizarMnemonico").charAt(0));
		}
		
		
		getLblFinPartida().setText(textos.getString("fin.finPartida"));
		getBtnConfirmar().setText(textos.getString("fin.confirmar"));
		getBtnConfirmar().setMnemonic(textos.getString("fin.confirmarMnemonico").charAt(0));
		
		
	}
	

	private JLabel getLblFinPartida() {
		if (lblFinPartida == null) {
			lblFinPartida = new JLabel();
			lblFinPartida.setHorizontalAlignment(SwingConstants.LEFT);
			lblFinPartida.setBounds(10, 11, 425, 65);
			lblFinPartida.setForeground(Color.ORANGE);
			lblFinPartida.setFont(VP.pacmanFont.deriveFont(40f));
		}
		return lblFinPartida;
	}
	private JButton getBtnConfirmar() {
		if (btnConfirmar == null) {
			btnConfirmar = new JButton();
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VJ.dispose();
					
					if (juego.getPuntos() > 0) {
						mostrarVentanaPremios();
					}
					else {
						mostrarVentanaPrincipal();
					}
				}
			});
			btnConfirmar.setBounds(284, 223, 151, 38);
			btnConfirmar.setFont(VP.pixelFont.deriveFont(25f));
		}
		return btnConfirmar;
	}
	
	
	
	
	protected void mostrarVentanaPremios() {
		VentanaPremios Vpremios = new VentanaPremios(this.VP);
		Vpremios.setLocationRelativeTo(this);
		this.dispose();
		Vpremios.setVisible(true);
		
	}
	
	
	protected void mostrarVentanaPrincipal() {		
		this.dispose();
		VP.setVisible(true);
		VP.reproducirSonidoInicio();
	}
	
	
	private JTextArea getLblMotivoFin() {
		if (lblMotivoFin == null) {
			lblMotivoFin = new JTextArea();
			lblMotivoFin.setLineWrap(true);
			lblMotivoFin.setWrapStyleWord(true);
			lblMotivoFin.setEditable(false);
			lblMotivoFin.setBackground(new Color(0, 0, 0));
			lblMotivoFin.setForeground(new Color(255, 255, 255));
			lblMotivoFin.setBounds(10, 101, 425, 65);
			lblMotivoFin.setFont(VP.pixelFont.deriveFont(22f));
		}
		return lblMotivoFin;
	}
	
	
	private JLabel getLblFantasmaImg() {
		if (lblFantasmaImg == null) {
			lblFantasmaImg = new JLabel();
			lblFantasmaImg.setHorizontalAlignment(SwingConstants.CENTER);
			lblFantasmaImg.setBounds(10, 171, 90, 90);
			VP.setImagenAdaptada(lblFantasmaImg, "/img/fantasmaVerde.png");
		}
		return lblFantasmaImg;
	}
}
