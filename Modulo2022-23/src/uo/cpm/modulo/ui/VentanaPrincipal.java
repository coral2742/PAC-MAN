package uo.cpm.modulo.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uo.cpm.modulo.service.Juego;
import uo.cpm.modulo.service.ParquePrincipado;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.sound.sampled.*;
import java.io.File;


public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Constante para facilitar pruebas de funcionamiento
	protected int DEBUG;
	
	protected Font pacmanFont;
	protected Font pixelFont;
	
	private Clip sonidoPACMAN;
	private Clip sonidoGameOver;
	private Clip sonidoComer;
	private Clip sonidoJuego;
	
	private ParquePrincipado parquePrin;
	private Juego juego;
	
	private Locale localizacion;
	private ResourceBundle textos;
	
	
	private JPanel pnlPrincipal;
	private JButton btnIniciar;
	private JLabel lblPacManImg;
	private JLabel lblDeVuelta;
	private JLabel lblFantasmaImg;
	private JButton btnIdioma;

	
	public ParquePrincipado getParquePrin() {
		return parquePrin;
	}
	
	public Juego getJuego() {
		return juego;
	}
	
	public Locale getLocalizacion() {
		return localizacion;
	}
	
	public ResourceBundle getTextosLocalizacion() {
		return textos;
	}


	/**
	 * Create the frame.
	 * @param DEBUG 
	 */
	public VentanaPrincipal(int DEBUG) {
		// Se asigna la constante DEBUG pasada como parámetro desde Main
		this.DEBUG = DEBUG;
		
		// Localización
		this.localizacion = Locale.getDefault();
		
		

		
		// Añadir font de PACMAN
		try {
			pacmanFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/Pacmania.otf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(pacmanFont);
			// Añadir font de Pixel
			pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("font/FreePixel.ttf"));
			ge.registerFont(pacmanFont);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// Añadimos sonidos
		try {
		    // Carga el archivo de sonido
			sonidoPACMAN = AudioSystem.getClip();
		    File soundFile = new File("sounds/SonidoInicioPACMAN.wav");
		    sonidoPACMAN.open(AudioSystem.getAudioInputStream(soundFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
		    // Carga el archivo de sonido Game Over
			sonidoGameOver = AudioSystem.getClip();
		    File soundFile = new File("sounds/SonidoGameOverPACMAN.wav");
		    sonidoGameOver.open(AudioSystem.getAudioInputStream(soundFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
		    // Carga el archivo de sonido Comer fantasma
			sonidoComer = AudioSystem.getClip();
		    File soundFile = new File("sounds/SonidoComerPACMAN.wav");
		    sonidoComer.open(AudioSystem.getAudioInputStream(soundFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
		    // Carga el archivo de sonido de juego
			sonidoJuego = AudioSystem.getClip();
		    File soundFile = new File("sounds/SonidoJuego.wav");
		    sonidoJuego.open(AudioSystem.getAudioInputStream(soundFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setResizable(false);
		setTitle("PAC-MAN");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/img/pacman-icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 826, 444);
		pnlPrincipal = new JPanel();
		pnlPrincipal.setBackground(new Color(0, 0, 0));
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlPrincipal);
		pnlPrincipal.setLayout(null);
		pnlPrincipal.add(getBtnIniciar());
		pnlPrincipal.add(getLblPacManImg());
		pnlPrincipal.add(getLblDeVuelta());
		pnlPrincipal.add(getLblFantasmaImg());
		pnlPrincipal.add(getBtnIdioma());
		
		
		
		localizar();
		this.parquePrin = new ParquePrincipado(DEBUG, textos);
	    // Reproduce el sonido
		sonidoPACMAN.start();
	}
	
	
	private void localizar() {
		this.textos = ResourceBundle.getBundle("rcs/textos", localizacion);
		String imagenBandera = "/img/" + textos.getString("vp.imgFlag");
		// Botón de idioma
		setImagenAdaptadaBoton(getBtnIdioma(), imagenBandera);
		getBtnIdioma().setToolTipText(textos.getString("vp.tooltipFlag"));
		// Label de vuelta
		getLblDeVuelta().setText(textos.getString("vp.title"));
		// Botón de iniciar
		getBtnIniciar().setText(textos.getString("vp.iniciar"));
		getBtnIniciar().setMnemonic(textos.getString("vp.iniciarMnemonico").charAt(0));
	}
	
	public void reproducirSonidoGameOver() {
		
	    if (!sonidoGameOver.isRunning()) {
	        // Si el Clip no está en ejecución, vuelve al principio
	    	sonidoGameOver.setFramePosition(0);
	      }
	      // Reproduce el sonido
	    sonidoGameOver.start();
	}
	
	public void reproducirSonidoComer() {
	    if (!sonidoComer.isRunning()) {
	        // Si el Clip no está en ejecución, vuelve al principio
	    	sonidoComer.setFramePosition(0);
	      }
	      // Reproduce el sonido
	    sonidoComer.start();
	}
	
	public void reproducirSonidoInicio() {
	    if (!sonidoPACMAN.isRunning()) {
	        // Si el Clip no está en ejecución, vuelve al principio
	    	sonidoPACMAN.setFramePosition(0);
	      }
	      // Reproduce el sonido
	    sonidoPACMAN.start();
	}
	
	public void reproducirSonidoJuego() {
	    if (!sonidoJuego.isRunning()) {
	        // Si el Clip no está en ejecución, vuelve al principio
	    	sonidoJuego.setFramePosition(0);
	      }
	      // Reproduce el sonido
	    sonidoJuego.start();
	}
	


	private JButton getBtnIniciar() {
		if (btnIniciar == null) {
			btnIniciar = new JButton();
			btnIniciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					parquePrin.inicializar(DEBUG,textos);
					mostrarVentanaLogin();
				}
			});
			btnIniciar.setBounds(273, 292, 264, 52);
			btnIniciar.setFont(pixelFont.deriveFont(25f));
			btnIniciar.setFocusPainted(false);
		}
		return btnIniciar;
	}

	protected void mostrarVentanaLogin() {
		VentanaLogin vL = new VentanaLogin(this);		
		vL.setLocationRelativeTo(this);
		vL.setModal(true);
		vL.setVisible(true);
	}
	
	private JLabel getLblPacManImg() {
		if (lblPacManImg == null) {
			lblPacManImg = new JLabel();
			lblPacManImg.setBounds(184, 11, 441, 139);
			lblPacManImg.setHorizontalAlignment(SwingConstants.CENTER);
			setImagenAdaptada(lblPacManImg,"/img/pacman-text.png");
		}
		return lblPacManImg;
	}
	
	
	protected void setImagenAdaptada(JLabel item, String rutaImagen){
		 Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		 Image imgEscalada = imgOriginal.getScaledInstance(item.getWidth(),item.getHeight(), Image.SCALE_FAST);
		 ImageIcon icon = new ImageIcon(imgEscalada);
		 item.setIcon(icon);
	}
	
	protected void setImagenAdaptadaBoton(JButton item, String rutaImagen){
		 Image imgOriginal = new ImageIcon(getClass().getResource(rutaImagen)).getImage(); 
		 Image imgEscalada = imgOriginal.getScaledInstance(item.getWidth(),item.getHeight(), Image.SCALE_FAST);
		 ImageIcon icon = new ImageIcon(imgEscalada);
		 item.setIcon(icon);
	}
	
	
	private JLabel getLblDeVuelta() {
		if (lblDeVuelta == null) {
			lblDeVuelta = new JLabel();
			lblDeVuelta.setHorizontalAlignment(SwingConstants.CENTER);
			lblDeVuelta.setBounds(130, 148, 549, 39);
			lblDeVuelta.setForeground(Color.ORANGE);
			lblDeVuelta.setFont(pacmanFont.deriveFont(25f));
		}
		return lblDeVuelta;
	}
	private JLabel getLblFantasmaImg() {
		if (lblFantasmaImg == null) {
			lblFantasmaImg = new JLabel();
			lblFantasmaImg.setBounds(636, 243, 151, 151);
			setImagenAdaptada(lblFantasmaImg,"/img/fantasmaRojo.png" );
		}
		return lblFantasmaImg;
	}
	private JButton getBtnIdioma() {
		if (btnIdioma == null) {
			btnIdioma = new JButton();
			btnIdioma.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comprobarIdioma();
				}
			});
			btnIdioma.setBackground(new Color(0, 0, 0));
			btnIdioma.setBounds(10, 306, 88, 88);
			btnIdioma.setFocusable(false);
			btnIdioma.setFocusPainted(false);
			btnIdioma.setFocusPainted(false);
		}
		return btnIdioma;
	}

	protected void comprobarIdioma() {
		// Cambiamos el idioma
		if (this.localizacion.getLanguage().equals("es")) {
			this.localizacion = new Locale("en");
		}
		else {
			this.localizacion = new Locale("es");
		}
		System.out.println("Cambio idioma a: " + this.localizacion.getLanguage());
		localizar();
		
	}
}
