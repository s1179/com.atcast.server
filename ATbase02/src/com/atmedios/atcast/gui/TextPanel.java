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

@SuppressWarnings("serial")
public class TextPanel extends JPanel {
	private JTextArea txtInput;
	private JPanel minipanelInf;
	private JButton borrar;

	// Constructor
	public TextPanel() {
		Border titleBorder = BorderFactory.createTitledBorder("Entrada de Texto");
		int space=7;
		Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
		setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
		
		setLayout(new BorderLayout());
		txtInput = new JTextArea(5, 15);
		txtInput.setEditable(true);

		minipanelInf = new JPanel();
		minipanelInf.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 3));
		borrar = new JButton("Borrar");
		borrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});

		minipanelInf.add(borrar);
		add(new JScrollPane(txtInput), BorderLayout.CENTER);// add(txtConsola);
		add(minipanelInf, BorderLayout.SOUTH);
	}

	/**
	 * @param inTxt
	 *            String with the text to set.
	 */
	public void setText(String inTxt) {
		txtInput.setText(inTxt);
	}

	/**
	 * @param inTxt
	 *            String with the text to add.
	 */
	public void addText(String inTxt) {
		txtInput.append(inTxt);
	}

	/**
	 * @return The contents of the console
	 */
	public String getText() {
		return txtInput.getText();
	}

	/**
	 * Clears whole console
	 */
	public void clear() {
		txtInput.setText("");
	}

}