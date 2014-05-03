package com.atmedios.atcast.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import com.atmedios.atcast.controller.AudioController;

@SuppressWarnings("serial")
public class AudioPanel extends JPanel {
	private JTextArea txtConsola;
	private JPanel minipanelInf;
	private JButton btnGrabar;
	private AudioController audioController;

	// Constructor
	public AudioPanel() {
		Border titleBorder = BorderFactory
				.createTitledBorder("Entrada de Sonido");
		int space = 7;
		Border spaceBorder = BorderFactory.createEmptyBorder(space, space,
				space, space);
		setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

		setLayout(new BorderLayout());
		// init
		audioController = new AudioController();
		
		txtConsola = new JTextArea(1, 15);

		minipanelInf = new JPanel();
		minipanelInf.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 3));
		btnGrabar = new JButton("Grabar");
		btnGrabar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					audioController.record("archivo.ogg");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		minipanelInf.add(btnGrabar);
		add(txtConsola, BorderLayout.CENTER);// add(txtConsola);
		add(minipanelInf, BorderLayout.SOUTH);

	}

}