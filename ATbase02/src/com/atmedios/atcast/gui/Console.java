package com.atmedios.atcast.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 * JPanel custom que maneja la salida de la aplicacion. Tiene funciones para
 * agregar, borrar todo, etc.
 * 
 * @author sergiopena
 * 
 */
@SuppressWarnings("serial")
public class Console extends JPanel {
	private JTextArea txtConsola;
	private JPanel minipanelInf;
	private JButton borrar;

	// Constructor
	public Console() {
		Border titleBorder = BorderFactory.createTitledBorder("Consola");
		int space=7;
		Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
		setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
		
		setLayout(new BorderLayout());
		txtConsola = new JTextArea(5, 20);
		txtConsola.setEditable(false);

		minipanelInf = new JPanel();
		minipanelInf.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 3));
		borrar = new JButton("Borrar");
		borrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});

		minipanelInf.add(borrar);
		add(new JScrollPane(txtConsola), BorderLayout.CENTER);// add(txtConsola);
		add(minipanelInf, BorderLayout.SOUTH);

		txtConsola.setText("ATCast - v0.5\n");

	}

	/**
	 * @param inTxt
	 *            String with the text to set.
	 */
	public void setText(String inTxt) {
		txtConsola.setText(inTxt);
	}

	/**
	 * @param inTxt
	 *            String with the text to add.
	 */
	public void addText(String inTxt) {
		txtConsola.append(inTxt);
	}

	/**
	 * @return The contents of the console
	 */
	public String getText() {
		return txtConsola.getText();
	}

	/**
	 * Clears whole console
	 */
	public void clear() {
		txtConsola.setText("");
	}

}