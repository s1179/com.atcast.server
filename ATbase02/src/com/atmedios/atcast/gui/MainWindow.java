package com.atmedios.atcast.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.atmedios.atcast.controller.MainWindowController;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 8544169108489925688L;
	private InitSetupDialog initSetupDialog;
	private Toolbar toolbar;
	private AudioPanel audioPanel;
	private TextPanel textPanel;
	private Console console;
	private MainWindowController controller;

	public MainWindow() { // aca es donde uno dice inicializando
		super("ATCast");

		try {
			// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			// UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Can't set look and feel.");
		}

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() { // Dibuja la ventana principal y pone las demas
								// zonas
		// Cosas de la pantalla principal
		setSize(1024, 600);
		setMinimumSize(new Dimension(600, 400));
		setLocation(100, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// DO_NOTHING_ON_CLOSE);
		setLayout(new GridBagLayout());
		// Inicializar elementos
		initSetupDialog = new InitSetupDialog(this);
		toolbar = new Toolbar();
		audioPanel = new AudioPanel();
		textPanel = new TextPanel();
		console = new Console();
		controller = new MainWindowController();

		// TODO Layout
		// GridBag Constraints init
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.NONE;
		gc.insets = new Insets(10, 10, 10, 10);
		// Row 1 (toolbar)
		gc.gridy = 0;
		gc.gridx = 0;
		gc.gridwidth = 3;
		gc.weightx = 0;
		gc.weighty = 0;
		gc.anchor = GridBagConstraints.NORTH;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(toolbar, gc);

		// 2nd row (components) SET ACCORDING TO CFG
		// 1st comp
		gc.gridy++;
		gc.gridx = 0;
		gc.gridwidth = 1;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		// 1st comp code
		// add(new JLabel("Component Placeholder"), gc);
		add(audioPanel, gc);

		// 2nd comp
		gc.gridx++;
		// 2nd comp code
		// add(new JLabel("Component Placeholder"), gc);
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(textPanel, gc);

		// Last Row (Console)
		gc.gridy++;
		gc.gridx = 0;
		gc.gridwidth = 3;
		gc.weightx = 0;
		gc.weighty = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.SOUTH;

		add(console, gc);

		// TODO Action Listeners
		// Toolbar
		toolbar.setToolbarListener(new ToolbarListener() {
			@Override
			public void toolbarEvent(int clicked) {
				int port = 8080;

				switch (clicked) {
				case 1:
					controller.startStreamingServer(port);
					break;
				case 2:
					controller.sendMessage("mensaje de prueba");
					break;
				default:
					break;

				}
				// console.addText("a ver "+clicked+"\n");
			}
		});
		// Rec mensajes consola

		// Start
		setVisible(true);
		// Lo primero que muestra el programa es el asistente
		// initSetupDialog.setVisible(true);

	}
}
