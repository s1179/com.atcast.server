package com.atmedios.atcast;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class ParaWindowBuilder {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParaWindowBuilder window = new ParaWindowBuilder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ParaWindowBuilder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "name_1395062957858667000");
		
		JLabel lblNewLabel = new JLabel("LAbel");
		lblNewLabel.setIcon(new ImageIcon("/Users/sergiopena/Documents/workspace/ATbase02/resources/autonomo.png"));
		panel.add(lblNewLabel);
		
		JLabel lblPrimero = new JLabel("Primero");
		panel.add(lblPrimero);
		
		JPanel panel2 = new JPanel();
		frame.getContentPane().add(panel2, "name_1395062994242792000");
		
		JLabel lblSegundo = new JLabel("Segundo");
		panel2.add(lblSegundo);
	}

}
