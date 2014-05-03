package com.sp.lrn.websocketx.client.GUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class PanelSonido extends JPanel implements ActionListener{
	private Timer timer;
	private JTextField textf;
	private int i=0;
	//constructor
	public PanelSonido(){
		Dimension dim = getPreferredSize();
		dim.width=90;
		setPreferredSize(dim);
		//Bordes
		Border bordExt=BorderFactory.createEmptyBorder(5,5,5,5);
		Border bordInt=BorderFactory.createTitledBorder("Audio");
		setBorder(BorderFactory.createCompoundBorder(bordExt, bordInt));
		
		//cosasd el timer
		//init
		timer= new Timer(500, this);
		timer.start();
		textf= new JTextField(5);
		add(textf);
		//si es sobre otros objetos, el objeto debe ser focusable y tener focus:
		/*KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher(){
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				int key = e.getKeyCode();
				//ej: flecha arriba es KeyEvent.VK_UP; y es int
				return false;
			}
		});*/
		textf.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
System.out.println("entró un caracter"+e.getKeyChar());
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				//System.out.println("sueltatecla");
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.println("presstecla");
				
			}
		});
		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				super.componentResized(e);
			}
		});
		

	}
//Timer
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(i>=1){
			timer.stop();
		}
		i++;
		System.out.println(i);
		textf.setText(textf.getText()+" y otro" + i);
		
	}

}
