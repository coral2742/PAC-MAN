package uo.cpm.modulo;

import java.awt.EventQueue;
import java.util.Properties;

import javax.swing.UIManager;

import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import uo.cpm.modulo.ui.VentanaPrincipal;


public class Main {
	// Constante para facilitar pruebas de funcionamiento
	// - DEBUG = 0: Funcionamiento normal.
	// - DEBUG = 1: los 5 invasores que se generan en cada iteración dejarán de ser aleatorios y serán todos del tipo “líder”.
	// - DEBUG = 2: los 5 invasores serán iguales, pero de cualquier tipo distinto al del líder.
	public static final int DEBUG = 0;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Cambia el look and feel
					Properties props = new Properties();
					props.put("logoString", "");
					GraphiteLookAndFeel.setCurrentTheme(props);
					UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
					VentanaPrincipal frame = new VentanaPrincipal(DEBUG);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
