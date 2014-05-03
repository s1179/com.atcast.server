package com.sp.lrn.websocketx.client;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class GraphicUI {

	private JFrame frmVentanaPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GraphicUI window = new GraphicUI();
					window.frmVentanaPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GraphicUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmVentanaPrincipal = new JFrame();
		frmVentanaPrincipal.setTitle("Ventana Principal");
		frmVentanaPrincipal.setBounds(100, 100, 450, 300);
		frmVentanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVentanaPrincipal.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JButton btnCasita = new JButton("casita");
		btnCasita.setToolTipText("Env\u00EDa el mensaje");
		btnCasita.setIcon(new ImageIcon(GraphicUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/maximize.gif")));
		frmVentanaPrincipal.getContentPane().add(btnCasita);
		
		JMenuBar menuBar = new JMenuBar();
		frmVentanaPrincipal.getContentPane().add(menuBar);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		frmVentanaPrincipal.getContentPane().add(separator);
	}

}
