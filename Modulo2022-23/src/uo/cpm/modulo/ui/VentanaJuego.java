package uo.cpm.modulo.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import uo.cpm.modulo.game.Casilla;
import uo.cpm.modulo.game.Invasor;
import uo.cpm.modulo.service.Juego;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.help.*;
import java.net.*;
import java.io.*;

public class VentanaJuego extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel pnlPrincipal;
	private JPanel pnlArriba;
	private JPanel pnlCentro;
	private JPanel pnlAbajo;
	private JPanel pnlArribaIzda;
	private JPanel pnlArribaDcha;
	private JPanel pnlRondas;
	private JLabel lblRondas;
	private JTextField txtRonda;
	private JPanel pnlPuntos;
	private JLabel lblPuntos;
	private JTextField txtPuntos;
	private JButton btnAyuda;
	private JLabel lblImagenPacMan;
	private JPanel pnlInvasores;
	private JPanel pnlTablero;
	private JMenuBar menuJuego;
	private JMenu mnJuego;
	private JMenu mnAyuda;
	private JMenuItem mnitmSalir;
	private JMenuItem mnitmAcercaDe;
	private JMenuItem mnitmContenidos;
	private JSeparator separadorAyuda;
	private JSeparator separadorJuego;

	private VentanaPrincipal VP;
	private Juego juego;

	private ProcesaInvasor pi;
	private ProcesaInvasoresJuego pj;
	private ProcesaVentana pv;
	private ProcesaBotonCerrarVentana pb;

	private ResourceBundle textos;


	/**
	 * Create the frame.
	 */
	public VentanaJuego(VentanaPrincipal VP) {
		this.VP = VP;
		this.pi = new ProcesaInvasor();
		this.pj = new ProcesaInvasoresJuego();
		this.pv = new ProcesaVentana();
		this.pb = new ProcesaBotonCerrarVentana();

		this.addWindowListener(pv);
		this.juego = VP.getParquePrin().getJuego();
		this.textos = VP.getTextosLocalizacion();
		inicializar();

		setTitle("PAC-MAN");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaJuego.class.getResource("/img/pacman-icon.png")));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 705, 611);
		setJMenuBar(getMenuJuego());
		pnlPrincipal = new JPanel();
		pnlPrincipal.setBackground(new Color(0, 0, 0));
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(pnlPrincipal);
		pnlPrincipal.setLayout(new BorderLayout(0, 0));
		pnlPrincipal.add(getPnlArriba(), BorderLayout.NORTH);
		pnlPrincipal.add(getPnlCentro(), BorderLayout.CENTER);
		pnlPrincipal.add(getPnlAbajo(), BorderLayout.SOUTH);

		setMinimumSize(new Dimension(705, 611));

		localizar();
		cargaAyuda();
	}

	private void localizar() {
		getLblRondas().setText(textos.getString("juego.ronda"));

		getLblPuntos().setText(textos.getString("juego.puntos"));

		getBtnAyuda().setToolTipText(textos.getString("juego.instruccionesToolTip"));

		getMnJuego().setText(textos.getString("juego.menuJuego"));
		getMnJuego().setMnemonic(textos.getString("juego.menuJuegoMnemonico").charAt(0));

		getMnAyuda().setText(textos.getString("juego.menuAyuda"));
		getMnAyuda().setMnemonic(textos.getString("juego.menuAyudaMnemonico").charAt(0));

		getMnitmSalir().setText(textos.getString("juego.menuItemSalir"));
		getMnitmSalir().setMnemonic(textos.getString("juego.menuItemSalirMnemonico").charAt(0));

		getMnitmAcercaDe().setText(textos.getString("juego.menuItemAcercaDe"));
		getMnitmAcercaDe().setMnemonic(textos.getString("juego.menuItemAcercaDeMnemonico").charAt(0));

		getMnitmContenidos().setText(textos.getString("juego.menuItemContenidos"));
		getMnitmContenidos().setMnemonic(textos.getString("juego.menuItemContenidosMnemonico").charAt(0));

		getBtnAyuda().setToolTipText(textos.getString("juego.instruccionesToolTip"));


	}

	private void cargaAyuda(){

		URL hsURL;
		HelpSet hs;

		try {
			File fichero = new File("help/ayuda.hs");
			hsURL = fichero.toURI().toURL();
			hs = new HelpSet(null, hsURL);
		}

		catch (Exception e){
			System.out.println("Ayuda no encontrada");
			return;
		}

		HelpBroker hb = hs.createHelpBroker();
		hb.initPresentation();

		// Activa el F1 en la aplicación
		// Le indicamos que nos indique el topic de intro
		hb.enableHelpKey(getRootPane(),"intro", hs);
		// Activa la ayuda en un elemento de menú o en un botón
		hb.enableHelpOnButton(getMnitmContenidos(), "intro", hs);

		// Ayuda sensible al contexto
		// Muestra ayuda asociada a un componente concreto
		hb.enableHelp(getPnlInvasores(), "personajes", hs);
		hb.enableHelp(getPnlTablero(), "jugada", hs);


	}

	public void inicializar() {
		juego.inicializar();
		getTxtPuntos().setText(String.valueOf(0));
		getTxtRonda().setText(String.valueOf(juego.getNumIteraciones()));
	}

	private void confimarCancelacion() {
		String[] options = {textos.getString("juego.aceptar"), textos.getString("juego.cancelar")}; 
		int result = JOptionPane.showOptionDialog(
				this,
				textos.getString("juego.cancelacion"), 
				textos.getString("juego.salirJuego"),            
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				new ImageIcon("/img/pacman-icon.png"),     //no custom icon
				options,  //button titles
				options[0] //default button
				);


		if (result == JOptionPane.YES_OPTION) {
			VP.setVisible(true);
			dispose();
			VP.reproducirSonidoInicio();
		}
	}

	class ProcesaInvasor implements ActionListener {
		public void actionPerformed(ActionEvent e){
			seleccionarInvasor((JButton) e.getSource());
		}
	}

	class ProcesaInvasoresJuego implements ActionListener {
		public void actionPerformed(ActionEvent e){
			MyJButton bt = (MyJButton) e.getSource();
			comprobarInvasoresJuego(bt.getI(), bt.getJ(), bt);
		}
	}

	class ProcesaVentana extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			confimarCancelacion();
		}
	}

	class ProcesaBotonCerrarVentana implements ActionListener {
		public void actionPerformed(ActionEvent e){
			confimarCancelacion();
		}
	}

	private class MyJButton extends JButton {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int i;
		private int j;

		public MyJButton(int i, int j) {
			super("");
			setSize(40,40);
			setBackground(Color.BLACK);
			setSelected(true);
			setFocusPainted(false);

			this.i = i;
			this.j = j;

		}

		public int getI() {
			return i;
		}

		public int getJ() {
			return j;
		}
	}

	private void seleccionarInvasor(JButton bt){
		if (juego.getTableroInvasores().getCasillas()[Integer.parseInt(bt.getActionCommand())].getTipo().equals("Invasor") && juego.getInvasorSeleccionado() == null) {
			System.out.println("Invasor seleccionado: " + String.valueOf(bt.getActionCommand()) + " " + ((Invasor) juego.getTableroInvasores().getCasillas()[Integer.parseInt(bt.getActionCommand())]).getColor());
			juego.setInvasorSeleccionado((Invasor) juego.getTableroInvasores().getCasillas()[Integer.parseInt(bt.getActionCommand())], Integer.parseInt(bt.getActionCommand()));

			bt.setBorder(new LineBorder(Color.ORANGE, 2));
			bt.setEnabled(false);

		}
	}

	private void comprobarInvasoresJuego(int i, int j, MyJButton bt) {

		if (juego.getTablero().getCasillas()[i][j].getTipo().equals("Invasor")) {
			System.out.println("TABLERO: " + String.valueOf(i) + ", " + String.valueOf(j));
		}
		else if (juego.getInvasorSeleccionado() != null) {
			juego.añadirInvasor(juego.getInvasorSeleccionado(), i, j);
			pintarCasilla(i,j,bt);
			juego.setInvasorSeleccionado(null);
			juego.setInvasoresColocados(juego.getInvasoresColocados() + 1);

			System.out.println("Invasores añadidos: " + juego.getInvasoresColocados());
			crearTablero();

			// Comprobamos si es el final de la ronda
			if (juego.isFinRonda()) {
				VP.reproducirSonidoComer();
				System.out.println("Fin de ronda");

				// Vaciamos los invasores a colocar
				getPnlInvasores().removeAll();
				repaint();

				// Calculamos los puntos
				juego.comprobarJuego();

				getPnlTablero().removeAll();
				crearTablero();

				System.out.println("Puntos: " + juego.getPuntos());
				getTxtPuntos().setText(String.valueOf(juego.getPuntos()));

				System.out.println(juego.getTablero().toString());
				

				// Comprobamos si es fin de Juego
				if (juego.isFinJuego()) {
					mostrarVentanaPremios();
				}

				else {
					juego.nuevaRonda();
					System.out.println("Invasores añadidos: " + juego.getInvasoresColocados());
					System.out.println("Ronda: " + juego.getNumIteraciones());
					// Actualizamos el número de ronda
					getTxtRonda().setText(String.valueOf(juego.getNumIteraciones()));

					crearPanelInvasores();

					if (juego.isFinJuego()) {
						mostrarVentanaPremios();
					}
				}


			}

			else if (juego.isFinJuego()) {
				mostrarVentanaPremios();
			}

		}
		else if (juego.isFinJuego()) {
			mostrarVentanaPremios();
		}

	}







	private JPanel getPnlArriba() {
		if (pnlArriba == null) {
			pnlArriba = new JPanel();
			pnlArriba.setBackground(new Color(0, 0, 0));
			pnlArriba.setBorder(new EmptyBorder(0, 0, 20, 0));
			pnlArriba.setLayout(new GridLayout(0, 2, 0, 0));
			pnlArriba.add(getPnlArribaIzda());
			pnlArriba.add(getPnlArribaDcha());
		}
		return pnlArriba;
	}
	private JPanel getPnlCentro() {
		if (pnlCentro == null) {
			pnlCentro = new JPanel();
			pnlCentro.setBackground(new Color(0, 0, 0));
			pnlCentro.setLayout(new BorderLayout(0, 0));
			pnlCentro.add(getPnlTablero(), BorderLayout.CENTER);
		}
		return pnlCentro;
	}




	private void crearPanelInvasores() {
		getPnlInvasores().removeAll();
		for (int i = 0; i < juego.getTableroInvasores().getNumInvasores(); i++) {
			getPnlInvasores().add(añadirCasillaInvasor(i));
		}

	}

	private JButton añadirCasillaInvasor(int i) {
		JButton bt = new JButton();
		bt.setSize(40,40);
		bt.setActionCommand(String.valueOf(i));
		bt.setBackground(Color.BLACK);
		bt.setSelected(true);
		bt.setFocusPainted(false);
		bt.setBorder(new EmptyBorder(2, 2, 2, 2));

		pintarCasillasInvasores(i, bt);

		bt.addActionListener(pi);
		return bt;
	}

	private JPanel getPnlAbajo() {
		if (pnlAbajo == null) {
			pnlAbajo = new JPanel();
			pnlAbajo.setBorder(new EmptyBorder(10, 0, 0, 0));
			pnlAbajo.setBackground(new Color(0, 0, 0));
			pnlAbajo.setLayout(new BorderLayout(0, 0));
			pnlAbajo.add(getPnlInvasores());
		}
		return pnlAbajo;
	}
	private JPanel getPnlArribaIzda() {
		if (pnlArribaIzda == null) {
			pnlArribaIzda = new JPanel();
			pnlArribaIzda.setBackground(new Color(0, 0, 0));
			pnlArribaIzda.setLayout(new BorderLayout(0, 0));
			pnlArribaIzda.add(getLblImagenPacMan(), BorderLayout.CENTER);
		}
		return pnlArribaIzda;
	}
	private JPanel getPnlArribaDcha() {
		if (pnlArribaDcha == null) {
			pnlArribaDcha = new JPanel();
			pnlArribaDcha.setBackground(new Color(0, 0, 0));
			pnlArribaDcha.setLayout(new GridLayout(0, 3, 0, 0));
			pnlArribaDcha.add(getPnlRondas());
			pnlArribaDcha.add(getPnlPuntos());
			pnlArribaDcha.add(getBtnAyuda());
		}
		return pnlArribaDcha;
	}
	private JPanel getPnlRondas() {
		if (pnlRondas == null) {
			pnlRondas = new JPanel();
			pnlRondas.setBorder(new EmptyBorder(0, 10, 0, 10));
			pnlRondas.setBackground(new Color(0, 0, 0));
			pnlRondas.setLayout(new GridLayout(2, 0, 0, 0));
			pnlRondas.add(getLblRondas());
			pnlRondas.add(getTxtRonda());
		}
		return pnlRondas;
	}
	private JLabel getLblRondas() {
		if (lblRondas == null) {
			lblRondas = new JLabel();
			lblRondas.setBackground(new Color(0, 0, 0));
			lblRondas.setHorizontalAlignment(SwingConstants.RIGHT);
			lblRondas.setForeground(Color.ORANGE);
			lblRondas.setFont(VP.pacmanFont.deriveFont(18f));
			lblRondas.setBounds(503, 23, 134, 32);
		}
		return lblRondas;
	}
	private JTextField getTxtRonda() {
		if (txtRonda == null) {
			txtRonda = new JTextField();
			txtRonda.setHorizontalAlignment(SwingConstants.RIGHT);
			txtRonda.setForeground(Color.ORANGE);
			txtRonda.setFont(VP.pixelFont.deriveFont(30f));
			txtRonda.setEditable(false);
			txtRonda.setColumns(10);
			txtRonda.setBorder(null);
			txtRonda.setBackground(Color.BLACK);
			txtRonda.setBounds(524, 49, 113, 39);
		}
		return txtRonda;
	}
	private JPanel getPnlPuntos() {
		if (pnlPuntos == null) {
			pnlPuntos = new JPanel();
			pnlPuntos.setBorder(new EmptyBorder(0, 10, 0, 20));
			pnlPuntos.setBackground(new Color(0, 0, 0));
			pnlPuntos.setLayout(new GridLayout(2, 0, 0, 0));
			pnlPuntos.add(getLblPuntos());
			pnlPuntos.add(getTxtPuntos());
		}
		return pnlPuntos;
	}
	private JLabel getLblPuntos() {
		if (lblPuntos == null) {
			lblPuntos = new JLabel();
			lblPuntos.setBackground(new Color(0, 0, 0));
			lblPuntos.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPuntos.setBounds(346, 23, 134, 32);
			lblPuntos.setForeground(Color.ORANGE);
			lblPuntos.setFont(VP.pacmanFont.deriveFont(18f));
		}
		return lblPuntos;
	}
	private JTextField getTxtPuntos() {
		if (txtPuntos == null) {
			txtPuntos = new JTextField();
			txtPuntos.setHorizontalAlignment(SwingConstants.RIGHT);
			txtPuntos.setText("0");
			txtPuntos.setBackground(Color.BLACK);
			txtPuntos.setEditable(false);
			txtPuntos.setBounds(367, 49, 113, 39);
			txtPuntos.setColumns(10);
			txtPuntos.setForeground(Color.ORANGE);
			txtPuntos.setFont(VP.pixelFont.deriveFont(30f));
			txtPuntos.setBorder(null);
		}
		return txtPuntos;
	}


	private JButton getBtnAyuda() {
		if (btnAyuda == null) {
			btnAyuda = new JButton("?");
			btnAyuda.setMnemonic('?');
			btnAyuda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaAyudaJuego();
				}
			});
			btnAyuda.setForeground(Color.ORANGE);
			btnAyuda.setBackground(new Color(0, 0, 0));
			btnAyuda.setBounds(690, 23, 65, 65);
			btnAyuda.setFont(VP.pixelFont.deriveFont(40f));
			btnAyuda.setFocusable(false);
		}
		return btnAyuda;
	}

	protected void mostrarVentanaAyudaJuego() {
		VentanaAyudaJuego VAJ = new VentanaAyudaJuego(VP);
		VAJ.setModal(true);
		VAJ.setLocationRelativeTo(this);
		VAJ.setVisible(true);

	}

	private JLabel getLblImagenPacMan() {
		if (lblImagenPacMan == null) {
			lblImagenPacMan = new JLabel();
			lblImagenPacMan.setBounds(23, 11, 313, 101);
			lblImagenPacMan.setHorizontalAlignment(SwingConstants.CENTER);
			VP.setImagenAdaptada(lblImagenPacMan,"/img/pacman-text.png");
		}
		return lblImagenPacMan;
	}

	private JPanel getPnlInvasores() {
		if (pnlInvasores == null) {
			pnlInvasores = new JPanel();
			pnlInvasores.setBackground(new Color(0, 0, 0));
			pnlInvasores.setBorder(new EmptyBorder(30, 50, 10, 50));
			pnlInvasores.setBounds(23, 411, 732, 72);
			pnlInvasores.setLayout(new GridLayout(1, 5, 0, 0));
			
			pnlInvasores.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(30, 50, 10, 50), new LineBorder(Color.black, 5)));

			crearPanelInvasores();

		}
		return pnlInvasores;
	}

	private JPanel getPnlTablero() {
		if (pnlTablero == null) {
			pnlTablero = new JPanel();
			pnlTablero.setBorder(new EmptyBorder(0, 150, 0, 150));
			pnlTablero.setBackground(new Color(0, 0, 0));
			pnlTablero.setBounds(242, 123, 280, 280);
			pnlTablero.setLayout(new GridLayout(7, 7, 2, 2));

			crearTablero();

		}
		return pnlTablero;
	}

	private void crearTablero() {
		getPnlTablero().removeAll();
		for (int i = 0; i < juego.getDIM(); i++) {
			for (int j = 0; j < juego.getDIM(); j++) {
				getPnlTablero().add(añadirCasilla(i,j));
			}
		}
	}


	private JButton añadirCasilla(int i, int j) {
		// Creamos un botón propio pasándole los dos parámetros
		MyJButton bt = new MyJButton(i,j);
		pintarCasilla(i,j,bt);
		bt.addActionListener(pj);
		return bt;
	}

	private void pintarCasillasInvasores(int i, JButton bt) {			
		Casilla casilla = juego.getTableroInvasores().getCasillas()[i];


		if (casilla.getTipo().equals("Invasor")) {
			VP.setImagenAdaptadaBoton(bt, "/img/" + casilla.getImagen() + ".png");
			bt.setDisabledIcon(bt.getIcon());
			bt.setBackground(Color.white);
			bt.setContentAreaFilled(false);
		}

		else if (casilla.getTipo().equals("Hueco")) {
			bt.setEnabled(false);
		}

		else if (casilla.getTipo().equals("Espacio")) {
			bt.setDisabledIcon(null);
			bt.setIcon(null);
		}

	}


	private void pintarCasilla(int i, int j, MyJButton bt) {
		Casilla casilla = juego.getTablero().getCasillas()[i][j];
		if (casilla.getTipo().equals("Invasor")) {
			VP.setImagenAdaptadaBoton(bt, "/img/" + casilla.getImagen() + ".png");
			bt.setDisabledIcon(bt.getIcon());
			bt.setBackground(Color.white);
			bt.setContentAreaFilled(false);


		}

		else if (casilla.getTipo().equals("Hueco")) {
			bt.setEnabled(false);
		}

		else if (casilla.getTipo().equals("Espacio")) {
			bt.setDisabledIcon(null);
			bt.setIcon(null);
		}

	}


	protected void mostrarVentanaPremios() {
		getTxtPuntos().setText(String.valueOf(juego.getPuntos()));
		VentanaGameOver Vfin = new VentanaGameOver(this.VP, this);
		Vfin.setLocationRelativeTo(this);
		Vfin.setVisible(true);
		
	}



	private JMenuBar getMenuJuego() {
		if (menuJuego == null) {
			menuJuego = new JMenuBar();
			menuJuego.setBackground(new Color(0, 0, 0));
			menuJuego.add(getMnJuego());
			menuJuego.add(getMnAyuda());
		}
		return menuJuego;
	}
	private JMenu getMnJuego() {
		if (mnJuego == null) {
			mnJuego = new JMenu();
			mnJuego.setBackground(new Color(0, 0, 0));
			mnJuego.add(getSeparadorJuego());
			mnJuego.add(getMnitmSalir());
			mnJuego.setFont(VP.pixelFont.deriveFont(20f));
		}
		return mnJuego;
	}
	private JMenu getMnAyuda() {
		if (mnAyuda == null) {
			mnAyuda = new JMenu();
			mnAyuda.add(getMnitmContenidos());
			mnAyuda.add(getSeparadorAyuda());
			mnAyuda.add(getMnitmAcercaDe());
			mnAyuda.setFont(VP.pixelFont.deriveFont(20f));
		}
		return mnAyuda;
	}
	private JMenuItem getMnitmSalir() {
		if (mnitmSalir == null) {
			mnitmSalir = new JMenuItem();
			mnitmSalir.setFont(VP.pixelFont.deriveFont(20f));
			mnitmSalir.addActionListener(pb);
		}
		return mnitmSalir;
	}
	private JMenuItem getMnitmAcercaDe() {
		if (mnitmAcercaDe == null) {
			mnitmAcercaDe = new JMenuItem();
			mnitmAcercaDe.setFont(VP.pixelFont.deriveFont(20f));
			mnitmAcercaDe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "PAC-MAN \nCoral Izquierdo Muñiz \nGrupo PL-07 \n71904567N",
							textos.getString("juego.menuItemAcercaDe"), JOptionPane.INFORMATION_MESSAGE);

				}
			});
		}
		return mnitmAcercaDe;
	}
	private JMenuItem getMnitmContenidos() {
		if (mnitmContenidos == null) {
			mnitmContenidos = new JMenuItem();
			mnitmContenidos.setFont(VP.pixelFont.deriveFont(20f));
			mnitmContenidos.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		}
		return mnitmContenidos;
	}
	private JSeparator getSeparadorAyuda() {
		if (separadorAyuda == null) {
			separadorAyuda = new JSeparator();
		}
		return separadorAyuda;
	}
	private JSeparator getSeparadorJuego() {
		if (separadorJuego == null) {
			separadorJuego = new JSeparator();
		}
		return separadorJuego;
	}
}
