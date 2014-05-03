package com.atmedios.atcast.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JToolBar;

@SuppressWarnings("serial")
public class Toolbar extends JToolBar implements ActionListener {
	public static int ACTION_OPEN=5;
	public static int ACTION_SAVE=6;
	public static int ACTION_NEW=7;
	public static int ACTION_SERV_SETTINGS=8;
	public static int ACTION_TEXT_SETTINGS=9;
	public static int ACTION_AUDIO_SETTINGS=10;
	public static int ACTION_CONNECT=11;
	public static int ACTION_DISCONNECT=12;
	public static int ACTION_VIEWINCONSOLE=13;
	// Components
	private JButton bot1;
	private JButton bot2;
	private JButton bot3;
	private JSeparator separador;
	// Listeners
	private ToolbarListener tListener;

	// Constructor
	public Toolbar() {
		// Bordes
		//Border bordExt = BorderFactory.createEmptyBorder(1, 1, 1, 1);
		//Border bordInt = BorderFactory.createLineBorder(
				//new Color(220, 220, 220), 1);
		//setBorder(BorderFactory.createCompoundBorder(bordExt, bordInt));
		// Bot 1:
		setFloatable(false);
		bot1 = new JButton();
		bot1.setToolTipText("Env\u00EDa el mensaje");
		bot1.setIcon(new ImageIcon(
				Toolbar.class
						.getResource("/javax/swing/plaf/metal/icons/ocean/maximize.gif")));
		bot1.addActionListener(this);
		// Bot 2:
		bot2 = new JButton();
		bot2.setToolTipText("Boton 2");
		//bot2.setIcon(new ImageIcon(GraphicUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		bot2.setIcon(createIcon("/javax/swing/plaf/metal/icons/ocean/maximize.gif"));
		bot2.addActionListener(this);
		// Bot 3:
		bot3 = new JButton();
		bot3.setToolTipText("Bot 3");
		bot3.setIcon(new ImageIcon(Toolbar.class
				.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		bot3.addActionListener(this);
		// Separador que no he visto funcionar
		separador = new JSeparator();
		// Armado
		add(bot1);
		//add(separador);
		//add(separador);
		//add(separador);
		add(bot2);
		add(bot3);
		add(separador);
	}
	
	private ImageIcon createIcon(String path){
		URL url = getClass().getResource(path);
		if (url == null){
			System.err.println("Unable to load "+path);
		}
		ImageIcon icon = new ImageIcon(url);
		return icon;
	}

	// Aux class to aid in event management
	public void setToolbarListener(ToolbarListener tListener) {
		this.tListener = tListener;
	}

	// implemnetation of actionperformed (WHY?)
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();
		if (clicked == bot1) {// igualdad pq clickd es ref y eso es lo necesario
		if (tListener != null) {
			tListener.toolbarEvent(1);
		}
	} else if (clicked == bot2) {
		if (tListener != null) {
			tListener.toolbarEvent(5);
		}
	} else if (clicked == bot3) {
		if (tListener != null) {
			tListener.toolbarEvent(3);
		}
	}

	}

}