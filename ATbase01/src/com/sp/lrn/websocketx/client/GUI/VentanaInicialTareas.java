package com.sp.lrn.websocketx.client.GUI;



import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class VentanaInicialTareas extends JPanel {
	// declaracin decomponentes
	private JLabel secc1;
	private JLabel secc2;
	private JLabel secc3;
	private JTextField inputNomEvento;
	private JTextField inputHora;
	// private JButton bot1;
	// private JButton bot2;
	private JButton botOk;
	private JList<IdsLista> listaOpciones;
	private JComboBox<String> selec;
	private JCheckBox check;
	private JTextField num;
	private JLabel numL;
	private JRadioButton radio1;
	private JRadioButton radio2;
	private ButtonGroup grupoRadio;
	private JPopupMenu popup;
	// decl otras cosas
	private ParamListener recibeParametros;

	public VentanaInicialTareas() {
		// Inicializacin de componentes
		secc1 = new JLabel("Qu\u00e9 operaci\u00f3n desea ejecutar?");
		secc2 = new JLabel("Texto 2:");
		secc3 = new JLabel("Texto 3:");
		inputNomEvento = new JTextField(10);
		inputHora = new JTextField(8);
		// bot1 = new JButton("Sel. 1");
		// bot1 = new JButton("Sel. 2");
		botOk = new JButton("Aceptar");
		listaOpciones = new JList<IdsLista>();
		selec = new JComboBox<String>();
		check = new JCheckBox("Enviar automaticamente");
		num = new JTextField(15);
		numL = new JLabel("Cantidad de letras");
		radio1 = new JRadioButton("uno");
		radio2 = new JRadioButton("dos");
		grupoRadio = new ButtonGroup();
		popup=new JPopupMenu();

		//Mnemonics
		botOk.setMnemonic(KeyEvent.VK_E);
		secc2.setDisplayedMnemonic(KeyEvent.VK_X);//es para un campo pero va aca
		secc2.setLabelFor(inputNomEvento); //Asocia label y campo
		
		// accionesd e los componenetes que se acaban de inicializar
		botOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombreEv = inputNomEvento.getText();
				String hora = inputHora.getText();
				IdsLista opt = (IdsLista) listaOpciones.getSelectedValue();
				int id = opt.getId();
				//String sel = (String) selec.getSelectedItem();
				String resRadio = grupoRadio.getSelection().getActionCommand();
				System.out.println(resRadio + "\n");

				InitEvent ev = new InitEvent(this, nombreEv, hora, id);
				if (recibeParametros != null) {
					recibeParametros.initEventDisparado(ev);
				}
			}
		});
		// para la lista
		DefaultListModel<IdsLista> modeloLista = new DefaultListModel<IdsLista>();
		modeloLista.addElement(new IdsLista(0, "Alt 1"));
		modeloLista.addElement(new IdsLista(1, "Alt 2"));
		modeloLista.addElement(new IdsLista(2, "Alt 3"));
		listaOpciones.setModel(modeloLista);
		listaOpciones.setPreferredSize(new Dimension(110, 66));
		listaOpciones.setBorder(BorderFactory.createEtchedBorder());
		listaOpciones.setSelectedIndex(2);
		// para combo box
		DefaultComboBoxModel<String> empModel = new DefaultComboBoxModel<String>();
		empModel.addElement("Alt1");
		empModel.addElement("Alt2");
		empModel.addElement("Alt3");
		selec.setModel(empModel);
		selec.setSelectedIndex(1);
		selec.setEditable(true);

		// Para config checckbox
		numL.setEnabled(false);
		num.setEnabled(false);
		check.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean isTicked=check.isSelected();
				numL.setEnabled(isTicked);
				num.setEnabled(isTicked);
			}	
		}
		);
		
		//para radio
		grupoRadio.add(radio1);
		grupoRadio.add(radio2);
		radio1.setActionCommand("opcionUno");
		radio2.setActionCommand("opcionDos");
		radio1.setSelected(true);
		
		//Popup menu
		JMenuItem pop2 = new JMenuItem("Bot Izq");
		popup.add(pop2);
		selec.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3){
					popup.show(selec, e.getX(), e.getY());
				}

			}
			
		});
		//se le pone a la combo box, se podria poner en este panel pniendo implements mouselistener
		//se usa mouse adapter, que ya tiene toda la interfaz y permite override solo los metodos necesarios
		
		
		// Dibujar todo
		setLayout();

	}

	public void setLayout() {
		// Cuadrar Apariencia (layout)
		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		// Fila 1
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridwidth = 2; // ocupa las 2 columnas
		gc.gridx = 0;
		gc.gridy = 0; // se puede incrementar con gc.gridy++
		gc.anchor = GridBagConstraints.LINE_END;

		add(secc1, gc);

		// Fila 2
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridwidth = 1; // apaga el ocupacolumnas
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 10);// indentar el elemento

		add(secc2, gc);

		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);// toca quitar la indentacin

		add(inputNomEvento, gc);

		// Fila 3
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.insets = new Insets(0, 0, 0, 10);// indentar el elemento, se puede
											// obj inset

		add(secc3, gc);

		gc.gridx = 1;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);// toca quitar la indentacin

		add(inputHora, gc);

		// Fila 4
		gc.weightx = 1;
		gc.weighty = 2;

		gc.gridx = 1;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(listaOpciones, gc);

		// Fila 5
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 1;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(selec, gc);

		// Fila 6
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.gridy = 5;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Cfg Envio"), gc);
		
		gc.gridx = 1;
		gc.gridy = 5;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(check, gc);
		
		// Fila 7
		gc.weightx = 1;
		gc.weighty = 0.2;

		gc.gridx = 0;
		gc.gridy = 6;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(numL, gc);
		
		gc.gridx = 1;
		gc.gridy = 6;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(num, gc);
		
		// Fila 7
				gc.weightx = 1;
				gc.weighty = 0.2;

				gc.gridx = 0;
				gc.gridy = 7;
				gc.anchor = GridBagConstraints.FIRST_LINE_END;
				add(radio1, gc);
				
				gc.gridx = 1;
				gc.gridy = 7;
				gc.anchor = GridBagConstraints.FIRST_LINE_START;
				add(radio2, gc);
		
		// Fila fin
		gc.weightx = 1;
		gc.weighty = 2;

		gc.gridx = 1;
		gc.gridy = 8;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;

		add(botOk, gc);
	}

	public void setRecibeParametros(ParamListener recibe) {
		this.recibeParametros = recibe;
	}

}

class IdsLista {
	private int id;
	private String texto;

	public IdsLista(int id, String texto) {
		this.id = id;
		this.texto = texto;
	}

	public String toString() {
		return texto;
	}

	public int getId() {
		return id;
	}
}
