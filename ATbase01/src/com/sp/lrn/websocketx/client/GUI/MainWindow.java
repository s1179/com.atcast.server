package com.sp.lrn.websocketx.client.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.sp.lrn.websocketx.client.controller.Controller;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {
	private JTabbedPane tabPane;
	private JPanel textInput;
	private ConsolaEnv consola;
	private BarraHtas barraHtas;
	private PanelSonido panelSonido;
	private JFileChooser fileChooser;
	private Controller controller;// Llamar al controlador para que inserte info
									// en la base de datos
	private DialogPrefs dialogPrefs;
	// temporal
	private VentanaInicialTareas ventanaInicial;
	private JButton botonTemp;
	private Preferences prefs;

	/**
	 * Constructor con la estructura de todo
	 */
	public MainWindow() { // aca es donde uno dice inicializando
		super("Ventana Principal");
		/*try {
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			//UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Can't set look and feel.");
		}*/
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() { // Dibuja la ventana principal y pone las demas
								// zonas
		// Cosas de la pantalla principal
		setSize(800, 600);
		setMinimumSize(new Dimension(600, 400));
		setLocation(100, 100);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(new BorderLayout(0, 0));
		// setBounds(100, 100, 450, 300); alternativa para size y location

		// Componentes:
		setJMenuBar(creaMenuBar());
		tabPane= new JTabbedPane();
		consola = new ConsolaEnv(this);
		textInput = new JPanel();
		panelSonido = new PanelSonido();
		barraHtas = new BarraHtas();
		fileChooser = new JFileChooser(); // siempre el mismo, conserva ult dir
		controller = new Controller();
		dialogPrefs = new DialogPrefs(this);
		prefs = Preferences.userRoot().node("conndb");

		//tabbed pane
		tabPane.addTab("Texto", textInput);
		tabPane.addTab("Consola", consola);
		// temp
		botonTemp = new JButton("Enviar");
		ventanaInicial = new VentanaInicialTareas();

		// Mnemonics
		botonTemp.setMnemonic(KeyEvent.VK_A);

		// Config
		fileChooser.addChoosableFileFilter(new ArchivoFileFilter());

		// accionbes (eventos)
		//Para interceptr el cierre de la aplicacin:
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.out.println("cerrando");
				dispose();// mata las ventanas
				System.gc();// garbage colelctor
			}
		});

		barraHtas.setToolbarListener(new ToolbarListener() {
			@Override
			public void tbEvent(String texto) {
				consola.addText(texto);
			}
		});

		// temp:hala los datos de la clase que extiene eventobject
		ventanaInicial.setRecibeParametros(new ParamListener() {
			public void initEventDisparado(InitEvent e) {
				// mostrar y tracear
				String name = e.getInputNombreEvento();
				String hora = e.getInputHora();
				int id = e.getId();
				consola.addText(name + " con id " + id + " inicia a las "
						+ hora + "\n");
				// lo del modelo de datos de verdad
				controller.addEvento(e);
			}
		});

		botonTemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consola.addText("Push once\n");
			}
		});
		// guardando preferencias
		dialogPrefs.setPrefsListener(new PrefsListener() {
			public void prefsSet(String user, String pass, int port) {
				prefs.put("user", user);
				prefs.put("pass", pass);
				prefs.putInt("port", port);
			}
		});
		// cargando preferencias
		String user = prefs.get("user", "");
		String pass = prefs.get("pass", "");
		int port = prefs.getInt("port", 3306);
		dialogPrefs.setDefaults(user, pass, port);

		// pegar todo en el frame pricipal
		//add(consola, BorderLayout.CENTER);
		add(tabPane, BorderLayout.CENTER);
		add(barraHtas, BorderLayout.NORTH);
		add(panelSonido, BorderLayout.WEST);
		// temp
		add(ventanaInicial, BorderLayout.EAST);
		add(botonTemp, BorderLayout.SOUTH);

		// hace visible la ventana y s ocntenido
		setVisible(true);
	}

	private JMenuBar creaMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		// Archivo
		JMenu fileMenu = new JMenu("Archivo");
		//
		JMenuItem exportDataItem = new JMenuItem("Exportar...");
		JMenuItem importDataItem = new JMenuItem("Importar...");
		JMenuItem exitItem = new JMenuItem("Salir");
		//
		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		// Cerrar 2
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				// ShowOptionDialog es el que sirve para inicio
				int action = JOptionPane.showConfirmDialog(MainWindow.this,
						"Desea cerrar la aplicacion?", "Confirmacion cierre",
						JOptionPane.OK_CANCEL_OPTION);// |JOptionPane.WARNING_MESSAGE);
				if (action == JOptionPane.OK_OPTION) {
					WindowListener[] listeners = getWindowListeners();
					
					for(WindowListener listener:listeners){
						listener.windowClosing(new WindowEvent(MainWindow.this,0));
					}
				}
			}
		});
		importDataItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showOpenDialog(MainWindow.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						// tablas.refresh(); //cargar y mostrar info, no impl
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainWindow.this,
								"No se pudo cargar el archivo", "Error",
								JOptionPane.ERROR_MESSAGE);
					}

				}
				// mainwindow.this par saber en que ventana se abre
			}

		});
		exportDataItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (fileChooser.showSaveDialog(MainWindow.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainWindow.this,
								"No se pudo guardar el archivo", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		});

		// //
		// Editar
		JMenu editMenu = new JMenu("Editar");
		//
		JMenu showMenu = new JMenu("Mostrar");
		JCheckBoxMenuItem showPageItem = new JCheckBoxMenuItem("Pagina");
		JMenuItem showPrefItem = new JMenuItem("Preferencias...");
		// Sub mostrar con su adicin
		editMenu.add(showMenu);
		showMenu.add(showPageItem);
		showPageItem.setSelected(true);
		editMenu.add(showPrefItem);
		// Acionlostener
		showPageItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
				ventanaInicial.setVisible(menuItem.isSelected());
				// si showPageItem fuera final lo habia podido usar
				// directamente,
				// como no es asi, toca usar la referencia a traves de ev, que
				// trae la ref de quien disparo el evento
			}
		});
		showPrefItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ev) {
				dialogPrefs.setVisible(true);
			}
		});

		// Mnemonics & accelerators
		fileMenu.setMnemonic(KeyEvent.VK_A);
		exitItem.setMnemonic(KeyEvent.VK_S);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));
		showPrefItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				ActionEvent.CTRL_MASK));

		// agregar todo lo de primer nivel;
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		return menuBar;
	}

}
