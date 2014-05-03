package com.sp.lrn.websocketx.client.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

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
		super(parent, "Opciones", true);// boolean modal: se puede hacer algo
										// en la vent ppal
		setSize(400, 200);
		setLocationRelativeTo(parent);

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
}
