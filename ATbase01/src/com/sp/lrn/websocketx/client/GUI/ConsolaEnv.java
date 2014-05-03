package com.sp.lrn.websocketx.client.GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import com.sp.lrn.websocketx.client.controller.Message;
import com.sp.lrn.websocketx.client.controller.MessageServer;

/**
 * JPanel custom que maneja la salida de la aplicacion. Tiene funciones para
 * agregar, borrar todo, etc.
 * 
 * @author sergiopena
 * 
 */
@SuppressWarnings("serial")
public class ConsolaEnv extends JPanel implements ProgressDialogListener {
	private JTextArea txtConsola;
	private JPanel minipanelInf;
	private JButton borrar;
	
	private MessageServer messageServer;
	private ProgressDialog progressDialog;	
	private SwingWorker<List<Message>, Integer> worker;

	// Constructor
	public ConsolaEnv(Window parent) {
		setLayout(new BorderLayout());
		txtConsola = new JTextArea();
		txtConsola.setEditable(false);
		
		minipanelInf= new JPanel();
		minipanelInf.setLayout(new FlowLayout(FlowLayout.LEFT,5,3));
		//MSG Server
		messageServer= new MessageServer();
		progressDialog = new ProgressDialog(parent, "Procesando...");
		progressDialog.setListener(this);
		
		//esto es un panel entonces no pasa ocmo window. Parent es un container y aunque no pasa como window, es subtipo y se eude castear
		borrar = new JButton("Clear");
		borrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				clear();
				printMsg();
			}
		});
		
		minipanelInf.add(borrar);
		add(new JScrollPane(txtConsola), BorderLayout.CENTER);//add(txtConsola);
		add(minipanelInf,BorderLayout.SOUTH);
		
		txtConsola.setText("init");
		
		
	}
	//Se demora obteniendo repsuesta:multithread
	private void printMsg(){
		progressDialog.setMaximum(messageServer.getMessageCount());
		
		progressDialog.setVisible(true);//Aparece la barra de progreso

		//Dialogo con barra. Se puede lanzar de aca como sigue pero tambien puede lanzarse desde adentro, ver ?renosu
		/*SwingUtilities.invokeLater(new Runnable(){//al poner dialogo modal aqui, la appsigue corriendo al fondo
			@Override
			public void run() {
				progressDialog.setVisible(true);//Aparece la barra de progreso
			}
			
		});*/
		
		worker= new SwingWorker<List<Message>, Integer>(){
			//SwingWorker is a Template class (?)
			
			@Override
			protected void process(List<Integer> counts) {
				int retrieved= counts.get(counts.size()-1);
				//System.out.println(retrieved +" mensajes");
				progressDialog.setValue(retrieved);
			}
			@Override
			protected void done() {
				progressDialog.setVisible(false);//Desaparece la barra
				if(isCancelled()) return;
				try {
					List<Message> retrievedMessages = get();
					System.out.println("se obtuvieron"+ retrievedMessages.size());
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				
			}
			@Override
			protected List<Message> doInBackground() throws Exception {
				//usando msg server para barras
				List<Message> retrievedMessages = new ArrayList<Message>();
				int count =0;
				for(Message message: messageServer){
					if(isCancelled())break;//flag set by thread.cancel, cancelling a thread doesnt kill it, just sets this flag so you can shut things down
					System.out.println(message.getTitle());
					System.out.println(message.getTitle());
					retrievedMessages.add(message);
					count++;
					publish(count);//llama process
				}
				
				return retrievedMessages;
			}
		};
		
		worker.execute();
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
	@Override
	public void progressDialogCancelled() {
if(worker !=null){
	worker.cancel(true);
}
	}

}
