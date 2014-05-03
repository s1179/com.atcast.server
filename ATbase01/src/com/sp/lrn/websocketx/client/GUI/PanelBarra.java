package com.sp.lrn.websocketx.client.GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.Border;

import com.sp.lrn.websocketx.client.GraphicUI;

@SuppressWarnings("serial")
public class PanelBarra extends JPanel implements ActionListener {
	// Components
	private JButton bot1;
	private JButton bot2;
	private JButton bot3;
	private JSeparator separador;
	// Listeners
	private ToolbarListener saleTexto;

	// Constructor
	public PanelBarra() {
		// Layout
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 3));
		// Bordes
		Border bordExt = BorderFactory.createEmptyBorder(1, 1, 1, 1);
		Border bordInt = BorderFactory.createLineBorder(
				new Color(220, 220, 220), 1);
		setBorder(BorderFactory.createCompoundBorder(bordExt, bordInt));
		// Bot 1:
		bot1 = new JButton();
		bot1.setToolTipText("Env\u00EDa el mensaje");
		bot1.setIcon(new ImageIcon(
				GraphicUI.class
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
		bot3.setIcon(new ImageIcon(GraphicUI.class
				.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		bot3.addActionListener(this);
		// Separador que no he visto funcionar
		separador = new JSeparator();
		// Armado
		add(bot1);
		add(separador);
		add(separador);
		add(separador);
		add(bot2);
		add(bot3);

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
	public void setToolbarListener(ToolbarListener escuchador) {
		this.saleTexto = escuchador;
	}

	// implemnetation of actionperformed (WHY?)
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();

		if (clicked == bot1) {// igualdad pq clickd es ref y eso es lo necesario
			if (saleTexto != null) {
				saleTexto.tbEvent("toolbar but int 1");
			}
		} else if (clicked == bot2) {
			if (saleTexto != null) {
				saleTexto.tbEvent("toolbar but int 2");
			}
		} else if (clicked == bot3) {
			if (saleTexto != null) {
				saleTexto.tbEvent("toolbar but int 3");
			}
		}

	}

}
