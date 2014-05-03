package com.atmedios.atcast.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * Setup and initialization assistant with 2 jframes, a back/fwd/cancel bar with
 * buttons and a cardlayout with the steps
 * 
 * @author sergiopena
 * 
 */
public class InitSetupDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel barra;
	private JPanel cards;
	private CardLayout cl;

	// private Paso1 paso1;

	public InitSetupDialog(JFrame parent) {
		// general
		super(parent, "Configuraci\u00f3n Inicial", true);
		setSize(800, 500);
		setLocationRelativeTo(parent);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// frames
		setBarra();
		setCards();
		add(barra, BorderLayout.SOUTH);
		add(cards, BorderLayout.CENTER);
		// Add cards

		// Start

	}

	private void setBarra() {
		barra = new JPanel();
		barra.setLayout(new FlowLayout(FlowLayout.RIGHT));
		JButton botCancel = new JButton("Cancelar");
		botCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Espichado" + cl);
				setVisible(false);
			}
		});
		// JButton botAnt new JButton("Siguiente");
		// JButton botFin=new JButton("Siguiente");
		barra.add(botCancel);

	}

	private void setCards() {
		cards = new JPanel();
		cl = new CardLayout();
		cards.setLayout(cl);
		// /////////////////////////////////////
		// Paso 1:
		JPanel paso1 = new JPanel();
		Border titleBorder = BorderFactory
				.createTitledBorder("Nuevo Evento de ATCast");
		int space = 7;
		Border spaceBorder = BorderFactory.createEmptyBorder(space, space,
				space, space);
		paso1.setBorder(BorderFactory.createCompoundBorder(spaceBorder,
				titleBorder));
		paso1.setLayout(new GridBagLayout());
		// GridBag Constraints init
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(0, 0, 0, 0);
		// Row 1 (title) Desde aca se puede reformular un segundo paso
		gc.gridy = 0;
		gc.gridx = 0;
		gc.gridwidth = 3;
		gc.weightx = 0;
		gc.weighty = 0;
		// gc.anchor = GridBagConstraints.LINE_END;
		JLabel titulo;
		titulo = new JLabel("C\u00f3mo funcionar\u00e1 este equipo?");
		paso1.add(titulo, gc);
		// Row 1st option
		gc.gridy++;
		gc.gridx = 0;
		gc.gridwidth = 2;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		JButton imgServ1 = new JButton("  Abrir evento existente");
		//System.out.println("tam boton="+imgServ1.getPreferredSize());
		
		imgServ1.setIcon(new ImageIcon(InitSetupDialog.class
				.getResource("/sun/print/resources/duplex.png")));
		paso1.add(imgServ1, gc);

		gc.gridx++;
		gc.gridwidth = 1;

		// Row 2nd option
		gc.gridy++;
		gc.gridx = 0;
		gc.gridwidth = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		JButton imgServ2 = new JButton("  Entrada directa de sonido y texto");
		imgServ2.setIcon(new ImageIcon(InitSetupDialog.class
				.getResource("/sun/print/resources/duplex.png")));
		paso1.add(imgServ2, gc);
		// Row 3rd option
		gc.gridy++;
		gc.gridx = 0;
		gc.gridwidth = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		JButton imgServ3 = new JButton("  Recibir texto de otro servidor");
		imgServ3.setIcon(new ImageIcon(InitSetupDialog.class
				.getResource("/sun/print/resources/duplex.png")));
		paso1.add(imgServ3, gc);
		// Actions
		imgServ1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		// Add
		paso1.add(titulo);
		cards.add(paso1, "primero");
		cl.show(cards, "primero");

	}
}
/*
//////////
public class DialogPrefs extends JDialog {
private static final long serialVersionUID = 6690023271504347000L;
private JButton botOk;
private JButton botCancel;
private JSpinner portSpinner;
private SpinnerNumberModel spinnerModel;
private JTextField userField;
private JPasswordField passField;
private PrefsListener prefsListener;

public DialogPrefs(JFrame parent) {



spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);// 3306 es el
														// puerto de sql
portSpinner = new JSpinner(spinnerModel);
userField = new JTextField(10);
passField = new JPasswordField(10);

botOk = new JButton("Aceptar");
botCancel = new JButton("Cancelar");

layoutControls();

// Listeners
botOk.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
		String usuario = userField.getText();
		String pass = new String(passField.getPassword());
		Integer port = (Integer) portSpinner.getValue();
		// System.out.println(valS+" "+usuario+"+" +pass);
		setVisible(false);

		if (prefsListener != null) {
			prefsListener.prefsSet(usuario, pass, port);

		}
	}
});
botCancel.addActionListener(new ActionListener() {
	@Override
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
	}
});

}

public void layoutControls() {
JPanel controlsPanel = new JPanel();
JPanel buttonsPanel = new JPanel();
Border titleBorder = BorderFactory.createTitledBorder("Preferencias");
int space=7;
Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
// layout
controlsPanel.setLayout(new GridBagLayout());
GridBagConstraints gc = new GridBagConstraints();
// Fila usr
gc.fill = GridBagConstraints.NONE;
gc.insets = new Insets(0,0,0,10);
gc.gridy = 0;
gc.gridx = 0;
gc.weightx = 1;
gc.weighty = 1;
gc.anchor = GridBagConstraints.LINE_END;

controlsPanel.add(new JLabel("Usuario:"), gc);

gc.gridx++;
gc.anchor = GridBagConstraints.LINE_START;
controlsPanel.add(userField, gc);
// Fila pass
gc.weighty = 0.1;
gc.gridy++;
gc.gridx = 0;
gc.anchor = GridBagConstraints.LINE_END;
controlsPanel.add(new JLabel("Password:"), gc);
gc.gridx++;
gc.anchor = GridBagConstraints.LINE_START;
controlsPanel.add(passField, gc);
// Fila puerto
gc.weighty = 1;
gc.gridy++;
gc.gridx = 0;
gc.anchor = GridBagConstraints.LINE_END;
controlsPanel.add(new JLabel("Puerto"), gc);
gc.gridx++;
gc.anchor = GridBagConstraints.LINE_START;
controlsPanel.add(portSpinner, gc);

//  Botones
buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
buttonsPanel.add(botOk);
buttonsPanel.add(botCancel);
Dimension dBtn1=botCancel.getPreferredSize();
botOk.setPreferredSize(dBtn1);
//final
setLayout(new BorderLayout(5,5));
add(controlsPanel,BorderLayout.CENTER);
add(buttonsPanel,BorderLayout.SOUTH);
}

public void setDefaults(String user, String pass, int port) {
userField.setText(user);
passField.setText(pass);
portSpinner.setValue(port);
}

public void setPrefsListener(PrefsListener prefsListener) {
this.prefsListener = prefsListener;
}
}*/