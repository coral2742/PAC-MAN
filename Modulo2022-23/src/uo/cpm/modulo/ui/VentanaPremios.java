package uo.cpm.modulo.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import java.awt.Point;

import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.JButton;
import uo.cpm.modulo.model.Premio;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPremios extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private VentanaPrincipal VP;

	private JPanel pnlPrincipal;
	private JLabel lblPremios;
	private JLabel lblPuntos;
	private JTextField txtPuntos;
	private JComboBox<String> cbFiltroCategoria;
	private JLabel lblFiltrar;
	private JScrollPane scrollPremios;
	private JList<Premio> listPremios;
	private JPanel pnlPremioSeleccionado;
	private JButton btnConfirmarPremios;
	private JLabel lblPremioImg;
	private JTextArea txtDescripcionPremio;
	private JTextArea txtNombrePremio;
	private JScrollPane scrollDescription;
	private JLabel lblCoste;
	private JTextField txtCostePuntos;
	private JLabel lblUnidades;
	private JSpinner spnUnidades;
	private JButton btnAñadir;
	private JButton btnEliminar;
	private JLabel lblFiltrarPuntos;
	private JComboBox<String> cbFiltroPuntos;
	private JLabel lblCategoria;
	
	
	private DefaultListModel<Premio> modelListPremios;
	private JLabel lblSuficientesPuntos;
	private JLabel lblDemasiadasUnidades;
	
	private ResourceBundle textos;


	/**
	 * Create the frame.
	 */
	public VentanaPremios(VentanaPrincipal VP) {
		setResizable(false);
		this.VP = VP;
		this.textos = VP.getTextosLocalizacion();
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPremios.class.getResource("/img/pacman-icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1162, 670);
		pnlPrincipal = new JPanel();
		pnlPrincipal.setBackground(new Color(0, 0, 0));
		pnlPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pnlPrincipal);
		pnlPrincipal.setLayout(null);
		pnlPrincipal.add(getPnlPremioSeleccionado());
		pnlPrincipal.add(getLblPremios());
		pnlPrincipal.add(getLblPuntos());
		pnlPrincipal.add(getTxtPuntos());
		pnlPrincipal.add(getCbFiltroCategoria());
		pnlPrincipal.add(getLblFiltrar());
		pnlPrincipal.add(getScrollPremios());
		pnlPrincipal.add(getBtnConfirmarPremios());
		pnlPrincipal.add(getLblFiltrarPuntos());
		pnlPrincipal.add(getCbFiltroPuntos());
		pnlPrincipal.add(getLblCategoria());
		
		localizar(VP.getLocalizacion());
	}
	
	private void localizar(Locale localizacion) {
		textos = VP.getTextosLocalizacion();
		// Título de la ventana
		setTitle(textos.getString("premios.titulo"));
		
		getLblPremios().setText(textos.getString("premios.premios"));
		
		getLblPuntos().setText(textos.getString("premios.puntosRestantes"));
		
		getLblFiltrar().setText(textos.getString("premios.filtrar"));
		
		getBtnConfirmarPremios().setText(textos.getString("premios.confirmarPremios"));
		
		getBtnConfirmarPremios().setMnemonic(textos.getString("premios.confirmarPremiosMnemonico").charAt(0));
		
		getLblCoste().setText(textos.getString("premios.coste"));
		
		getLblUnidades().setText(textos.getString("premios.unidades"));
		
		getLblUnidades().setDisplayedMnemonic(textos.getString("premios.unidadesMnemonico").charAt(0));
		
		getBtnAñadir().setText(textos.getString("premios.add"));
		
		getBtnAñadir().setMnemonic(textos.getString("premios.addMnemonico").charAt(0));
		
		getBtnEliminar().setText(textos.getString("premios.eliminar"));
		getBtnEliminar().setMnemonic(textos.getString("premios.eliminarMnemonico").charAt(0));
		
		getLblFiltrarPuntos().setText(textos.getString("premios.puntosFiltrar"));
		getLblFiltrarPuntos().setDisplayedMnemonic(textos.getString("premios.puntosFiltrarMnemonico").charAt(0));
		
		getLblCategoria().setText(textos.getString("premios.categoria"));
		getLblCategoria().setDisplayedMnemonic(textos.getString("premios.categoriaMnemonico").charAt(0));
		
		getLblSuficientesPuntos().setText(textos.getString("premios.errorPuntos"));
		getLblDemasiadasUnidades().setText(textos.getString("premios.errorUnidades"));
	}
	
	

	
	protected void actualizarPremioSeleccionado() {
		// Actualizar imagen y adaptarla a las dimensiones
		VP.setImagenAdaptada(lblPremioImg, "/img/" + getListPremios().getSelectedValue().getCodigo() + ".PNG");
		// Cambiar nombre del premio
		getTxtNombrePremio().setText(getListPremios().getSelectedValue().getDenominacion());
		// Cambiar descripción
		getTxtDescripcionPremio().setText(getListPremios().getSelectedValue().getDescripcion());
		// Colocar scroll por defecto en posición 0
		getTxtDescripcionPremio().setCaretPosition(0); 
		// Cambiar valor en puntos del premio seleccionado
		getTxtCostePuntos().setText(String.valueOf(getListPremios().getSelectedValue().getPuntos()) + " " + textos.getString("premios.puntos"));
		// Comprobar si tiene puntos suficientes y actualizar puntos restantes
		comprobarPuntos();
	}
	
	private void comprobarPuntos() {
		int coste = getListPremios().getSelectedValue().getPuntos();
		
		// Actualizar puntos restantes
		getTxtPuntos().setText(String.valueOf(VP.getParquePrin().getPuntosRestantes()));
		
		if (coste > VP.getParquePrin().getPuntosRestantes()) {
			getLblSuficientesPuntos().setVisible(true);
			// Deshabilitamos el botón de Añadir
			getBtnAñadir().setEnabled(false);
		}
		else {
			getLblSuficientesPuntos().setVisible(false);
			// Habilitamos el botón de Añadir
			getBtnAñadir().setEnabled(true);
		}
		
	}




	private JLabel getLblPremios() {
		if (lblPremios == null) {
			lblPremios = new JLabel();
			lblPremios.setBounds(10, 11, 445, 66);
			lblPremios.setForeground(Color.ORANGE);
			lblPremios.setFont(VP.pacmanFont.deriveFont(45f));
		}
		return lblPremios;
	}
	
	private JLabel getLblPremioImg() {
		if (lblPremioImg == null) {
			lblPremioImg = new JLabel();
			lblPremioImg.setBackground(new Color(255, 255, 255));
			lblPremioImg.setBounds(23, 96, 172, 172);	
		}
		return lblPremioImg;
	}
	
	private JLabel getLblPuntos() {
		if (lblPuntos == null) {
			lblPuntos = new JLabel();
			lblPuntos.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPuntos.setBounds(902, 12, 234, 32);
			lblPuntos.setForeground(Color.ORANGE);
			lblPuntos.setFont(VP.pacmanFont.deriveFont(20f));
		}
		return lblPuntos;
	}
	private JTextField getTxtPuntos() {
		if (txtPuntos == null) {
			txtPuntos = new JTextField();
			txtPuntos.setHorizontalAlignment(SwingConstants.RIGHT);
			txtPuntos.setBackground(Color.BLACK);
			txtPuntos.setEditable(false);
			txtPuntos.setBounds(1023, 38, 113, 39);
			txtPuntos.setColumns(10);
			txtPuntos.setForeground(Color.ORANGE);
			txtPuntos.setFont(VP.pixelFont.deriveFont(30f));
			txtPuntos.setBorder(null);
			comprobarPuntos();
		}
		return txtPuntos;
	}
	
	
	private JComboBox<String> getCbFiltroCategoria() {
		if (cbFiltroCategoria == null) {
			cbFiltroCategoria = new JComboBox<String>();
			cbFiltroCategoria.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String categoria = (String)getCbFiltroCategoria().getSelectedItem();
					if (categoria == textos.getString("premios.todos")) {
						addToPremiosList();
					}
					else {
						addToPremiosListFiltrado(categoria);
					}
					getListPremios().setSelectedIndex(0);
					actualizarPremioSeleccionado();
					
					// Poner por defecto "Todos" en filtro por puntos
					getCbFiltroPuntos().setSelectedIndex(0);
					
				}
			});
			cbFiltroCategoria.setModel(new DefaultComboBoxModel<String>(this.VP.getParquePrin().getCategoriasPremio()));
			cbFiltroCategoria.setBounds(10, 200, 256, 32);
			cbFiltroCategoria.setFont(VP.pixelFont.deriveFont(20f));
		}
		return cbFiltroCategoria;
	}
	private JLabel getLblFiltrar() {
		if (lblFiltrar == null) {
			lblFiltrar = new JLabel();
			lblFiltrar.setBounds(10, 108, 205, 27);
			lblFiltrar.setForeground(Color.ORANGE);
			lblFiltrar.setFont(VP.pixelFont.deriveFont(30f));
		}
		return lblFiltrar;
	}
	private JScrollPane getScrollPremios() {
		if (scrollPremios == null) {
			scrollPremios = new JScrollPane();
			scrollPremios.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPremios.setBounds(10, 258, 570, 280);
			scrollPremios.setViewportView(getListPremios());
		}
		return scrollPremios;
	}
	private JList<Premio> getListPremios() {
		if (listPremios == null) {
			listPremios = new JList<Premio>();
			listPremios.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					if (getListPremios().getSelectedValue() != null) {
						// Habilitamos o deshabilitamos el botón de eliminar
						habilitarEliminar();
						// Poner spinner por defecto a 1
						getSpnUnidades().setValue(1);
						actualizarPremioSeleccionado();
					}
				}
			});
			listPremios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			modelListPremios = new DefaultListModel<Premio>();
			listPremios.setModel(modelListPremios);
			listPremios.setFont(VP.pixelFont.deriveFont(20f));
			
			// Añadir elementos a la lista de premios
			addToPremiosList();
			listPremios.setSelectedIndex(0);
			actualizarPremioSeleccionado();
		}
		return listPremios;
	}


	protected void habilitarEliminar() {
		Premio premioSeleccionado = getListPremios().getSelectedValue();
		if (VP.getParquePrin().estaEnPedido(premioSeleccionado)) {
			getBtnEliminar().setEnabled(true);
		}
		else if (getBtnEliminar().isEnabled()){
			getBtnEliminar().setEnabled(false);
		}
		
	}
	
	protected void habilitarConfirmar() {
		// Si NO está vacío el pedido
		if (!VP.getParquePrin().getArticulosPedido().isEmpty()) {
			getBtnConfirmarPremios().setEnabled(true);
		}
		else if (getBtnConfirmarPremios().isEnabled()){
			getBtnConfirmarPremios().setEnabled(false);
		}
	}




	private JPanel getPnlPremioSeleccionado() {
		if (pnlPremioSeleccionado == null) {
			pnlPremioSeleccionado = new JPanel();
			pnlPremioSeleccionado.setBackground(new Color(255, 255, 255));
			pnlPremioSeleccionado.setBounds(604, 117, 532, 421);
			pnlPremioSeleccionado.setLayout(null);
			pnlPremioSeleccionado.add(getLblPremioImg());
			pnlPremioSeleccionado.add(getTxtNombrePremio());
			pnlPremioSeleccionado.add(getScrollDescription());
			pnlPremioSeleccionado.add(getLblCoste());
			pnlPremioSeleccionado.add(getTxtCostePuntos());
			pnlPremioSeleccionado.add(getLblUnidades());
			pnlPremioSeleccionado.add(getSpnUnidades());
			pnlPremioSeleccionado.add(getBtnAñadir());
			pnlPremioSeleccionado.add(getBtnEliminar());
			pnlPremioSeleccionado.add(getLblSuficientesPuntos());
			pnlPremioSeleccionado.add(getLblDemasiadasUnidades());
		}
		return pnlPremioSeleccionado;
	}
	private JButton getBtnConfirmarPremios() {
		if (btnConfirmarPremios == null) {
			btnConfirmarPremios = new JButton();
			btnConfirmarPremios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mostrarVentanaConfirmarPremios();
				}
			});
			btnConfirmarPremios.setEnabled(false);
			btnConfirmarPremios.setBounds(931, 556, 205, 64);
			btnConfirmarPremios.setForeground(new Color(0, 0, 0));
			btnConfirmarPremios.setFont(VP.pixelFont.deriveFont(20f));
		}
		return btnConfirmarPremios;
	}
	
	protected void mostrarVentanaConfirmarPremios() {
		VentanaConfirmarPremios vConfirmar = new VentanaConfirmarPremios(this.VP, this);
		vConfirmar.setLocationRelativeTo(this);
		vConfirmar.setModal(true);
		vConfirmar.setVisible(true);
	}




	private JTextArea getTxtDescripcionPremio() {
		if (txtDescripcionPremio == null) {
			txtDescripcionPremio = new JTextArea();
			txtDescripcionPremio.setBackground(new Color(255, 255, 255));
			txtDescripcionPremio.setBorder(new EmptyBorder(5, 5, 5, 5));
			txtDescripcionPremio.setEditable(false);
			txtDescripcionPremio.setWrapStyleWord(true);
			txtDescripcionPremio.setLineWrap(true);
			txtDescripcionPremio.setFont(this.VP.pixelFont.deriveFont(20f));
			txtDescripcionPremio.setSelectionStart(0);
		}
		return txtDescripcionPremio;
	}
	private JTextArea getTxtNombrePremio() {
		if (txtNombrePremio == null) {
			txtNombrePremio = new JTextArea();
			txtNombrePremio.setLineWrap(true);
			txtNombrePremio.setWrapStyleWord(true);
			txtNombrePremio.setBounds(23, 11, 483, 78);
			txtNombrePremio.setFont(this.VP.pacmanFont.deriveFont(25f));
		}
		return txtNombrePremio;
	}
	private JScrollPane getScrollDescription() {
		if (scrollDescription == null) {
			scrollDescription = new JScrollPane();
			scrollDescription.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollDescription.setBounds(205, 84, 314, 184);
			scrollDescription.setViewportView(getTxtDescripcionPremio());
			scrollDescription.setBorder(null);
			scrollDescription.getViewport().setViewPosition(new Point(0,0));
			
		}
		return scrollDescription;
	}
	private JLabel getLblCoste() {
		if (lblCoste == null) {
			lblCoste = new JLabel();
			lblCoste.setBackground(new Color(255, 255, 255));
			lblCoste.setHorizontalAlignment(SwingConstants.LEFT);
			lblCoste.setForeground(Color.BLACK);
			lblCoste.setFont(this.VP.pacmanFont.deriveFont(20f));
			lblCoste.setBounds(23, 339, 126, 32);
		}
		return lblCoste;
	}
	private JTextField getTxtCostePuntos() {
		if (txtCostePuntos == null) {
			txtCostePuntos = new JTextField();
			txtCostePuntos.setBackground(new Color(255, 255, 255));
			//txtCostePuntos.setText("0");
			txtCostePuntos.setHorizontalAlignment(SwingConstants.LEFT);
			txtCostePuntos.setForeground(Color.BLACK);
			txtCostePuntos.setFont(this.VP.pixelFont.deriveFont(25f));
			txtCostePuntos.setEditable(false);
			txtCostePuntos.setColumns(10);
			txtCostePuntos.setBorder(null);
			txtCostePuntos.setBounds(23, 365, 172, 39);
		}
		return txtCostePuntos;
	}
	private JLabel getLblUnidades() {
		if (lblUnidades == null) {
			lblUnidades = new JLabel();
			lblUnidades.setLabelFor(getSpnUnidades());
			lblUnidades.setHorizontalAlignment(SwingConstants.LEFT);
			lblUnidades.setForeground(Color.BLACK);
			lblUnidades.setFont(null);
			lblUnidades.setBackground(Color.WHITE);
			lblUnidades.setBounds(205, 339, 126, 32);
			lblUnidades.setForeground(Color.BLACK);
			lblUnidades.setFont(this.VP.pacmanFont.deriveFont(20f));
		}
		return lblUnidades;
	}
	private JSpinner getSpnUnidades() {
		if (spnUnidades == null) {
			spnUnidades = new JSpinner();
			spnUnidades.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			spnUnidades.setBounds(205, 374, 126, 30);
			spnUnidades.setForeground(Color.BLACK);
			spnUnidades.setFont(this.VP.pixelFont.deriveFont(25f));
		}
		return spnUnidades;
	}
	private JButton getBtnAñadir() {
		if (btnAñadir == null) {
			btnAñadir = new JButton();
			btnAñadir.setBackground(new Color(0, 102, 0));
			btnAñadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Premio premioSeleccionado = getListPremios().getSelectedValue();
					int cantidadPremioSeleccionado = (int)getSpnUnidades().getValue();
					
					// Comprobamos que el número de unidades no supera el valor de puntos restantes al añadirlo
					if (cantidadPremioSeleccionado * premioSeleccionado.getPuntos() <= VP.getParquePrin().getPuntosRestantes()) {
						VP.getParquePrin().añadirAPedido(premioSeleccionado, cantidadPremioSeleccionado);
						getLblDemasiadasUnidades().setVisible(false);
					}
					else {
						getLblDemasiadasUnidades().setVisible(true);
					}
					
					System.out.println(VP.getParquePrin().getArticulosPedido().toString());
					
					// Habilitamos o deshabilitamos el botón de eliminar
					habilitarEliminar();
					// Habilitamos o deshabilitamos el botón de confirmar
					habilitarConfirmar();
					// Poner spinner por defecto a 1
					getSpnUnidades().setValue(1);
					
					// Actualizar puntos
					comprobarPuntos();
					
				}
			});
			btnAñadir.setBounds(354, 339, 165, 32);
			btnAñadir.setForeground(new Color(255, 255, 255));
			btnAñadir.setFont(VP.pixelFont.deriveFont(20f));
		}
		return btnAñadir;
	}
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton();
			btnEliminar.setBackground(new Color(153, 0, 0));
			btnEliminar.setEnabled(false);
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Premio premioSeleccionado = getListPremios().getSelectedValue();
					int unidadesSeleccionadas = (int)getSpnUnidades().getValue();
					// Eliminar del pedido
					VP.getParquePrin().eliminarDePedido(premioSeleccionado, unidadesSeleccionadas);
					// Habilitamos o deshabilitamos el botón de eliminar
					habilitarEliminar();
					// Habilitamos o deshabilitamos el botón de confirmar
					habilitarConfirmar();
					// Poner spinner por defecto a 1
					getSpnUnidades().setValue(1);
					
					System.out.println(VP.getParquePrin().getArticulosPedido().toString());
					
					// Comprobar si tiene puntos suficientes y actualizar puntos restantes
					comprobarPuntos();

					
				}
			});
			btnEliminar.setBounds(354, 378, 165, 32);
			btnEliminar.setForeground(new Color(255, 255, 255));
			btnEliminar.setFont(VP.pixelFont.deriveFont(20f));
		}
		return btnEliminar;
	}
	private JLabel getLblFiltrarPuntos() {
		if (lblFiltrarPuntos == null) {
			lblFiltrarPuntos = new JLabel();
			lblFiltrarPuntos.setLabelFor(getCbFiltroPuntos());
			lblFiltrarPuntos.setForeground(Color.ORANGE);
			lblFiltrarPuntos.setFont(VP.pixelFont.deriveFont(25f));
			lblFiltrarPuntos.setBounds(324, 162, 256, 27);
		}
		return lblFiltrarPuntos;
	}
	private JComboBox<String> getCbFiltroPuntos() {
		if (cbFiltroPuntos == null) {
			cbFiltroPuntos = new JComboBox<String>();
			cbFiltroPuntos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ordenarPremiosPorPuntos();
				}
			});
			cbFiltroPuntos.setModel(new DefaultComboBoxModel<String>(this.VP.getParquePrin().getFiltroPuntos()));
			cbFiltroPuntos.setFont(null);
			cbFiltroPuntos.setBounds(324, 200, 256, 32);
			cbFiltroPuntos.setFont(VP.pixelFont.deriveFont(20f));
			ordenarPremiosPorPuntos();
			
		}
		return cbFiltroPuntos;
	}
	
	
	
	protected void ordenarPremiosPorPuntos() {
		String filtro = (String)getCbFiltroPuntos().getSelectedItem();
		if (filtro.equals(textos.getString("premios.descendente"))) {
			sortModel(modelListPremios, true);
		}
		else {
			sortModel(modelListPremios, false);
		}
		getListPremios().setSelectedIndex(0);
	}
	
	
	private void sortModel(DefaultListModel<Premio> model, boolean descent) {
	    List<Premio> list = new ArrayList<>();
	    for (int i = 0; i < model.size(); i++) {
	        list.add((Premio)model.get(i));
	    }
	    Collections.sort(list, Comparator.comparing(Premio::getPuntos));
	    if (descent) {
	    	Collections.reverse(list);
	    }
	    model.removeAllElements();
	    for (Premio s : list) {
	        model.addElement(s);
	    }
	    
	    getListPremios().setSelectedIndex(0);
	}






	private JLabel getLblCategoria() {
		if (lblCategoria == null) {
			lblCategoria = new JLabel();
			lblCategoria.setLabelFor(getCbFiltroCategoria());
			lblCategoria.setForeground(Color.ORANGE);
			lblCategoria.setFont(VP.pixelFont.deriveFont(25f));
			lblCategoria.setBounds(10, 162, 256, 27);
		}
		return lblCategoria;
	}
	
	
	
	
	protected void addToPremiosList() {
		modelListPremios.removeAllElements();
		List<Premio> premios = Arrays.asList(VP.getParquePrin().getPremiosCarta());
		// Recorrer todos los premios
		for (Premio premio : premios) {
			// Añadimos al modelo de premios
			modelListPremios.addElement(premio);
		}
	}
	
	
	protected void addToPremiosListFiltrado(String categoria) {
		modelListPremios.removeAllElements();
		List<Premio> premios = Arrays.asList(VP.getParquePrin().filtrarPorCategoria(categoria));
		// Recorrer todos los premios
		for (Premio premio : premios) {
			// Añadimos al modelo de premios
			modelListPremios.addElement(premio);
		}
	}
	private JLabel getLblSuficientesPuntos() {
		if (lblSuficientesPuntos == null) {
			lblSuficientesPuntos = new JLabel();
			lblSuficientesPuntos.setEnabled(true);
			lblSuficientesPuntos.setBounds(23, 279, 308, 32);
			lblSuficientesPuntos.setForeground(new Color(153, 0, 0));
			lblSuficientesPuntos.setFont(this.VP.pixelFont.deriveFont(20f));
			lblSuficientesPuntos.setVisible(false);
			
		}
		return lblSuficientesPuntos;
	}
	private JLabel getLblDemasiadasUnidades() {
		if (lblDemasiadasUnidades == null) {
			lblDemasiadasUnidades = new JLabel();
			lblDemasiadasUnidades.setBounds(23, 310, 308, 32);
			lblDemasiadasUnidades.setForeground(new Color(153, 0, 0));
			lblDemasiadasUnidades.setFont(this.VP.pixelFont.deriveFont(20f));
			lblDemasiadasUnidades.setVisible(false);
		}
		return lblDemasiadasUnidades;
	}
}
