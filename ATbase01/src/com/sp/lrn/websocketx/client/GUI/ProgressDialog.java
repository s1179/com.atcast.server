package com.sp.lrn.websocketx.client.GUI;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class ProgressDialog extends JDialog {
	private static final long serialVersionUID = 4149309050107789193L;
	private JButton cancelButton;
	private JProgressBar progressBar;
	private ProgressDialogListener listener;

	public ProgressDialog(Window parent, String title) {
		super(parent, title, ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		cancelButton = new JButton("Cancel");
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setString("Recibiendo...");
		//progressBar.setIndeterminate(false);
		setSize(400, 200);
		setLayout(new FlowLayout());
		setLocationRelativeTo(parent);
		Dimension size = cancelButton.getPreferredSize();
		size.width=400;
		progressBar.setPreferredSize(size);
		
		cancelButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (listener != null){
					listener.progressDialogCancelled();
				}
			}
		});
		
		add(progressBar);
		add(cancelButton);

		pack();
	}
	public void setMaximum(int value){
		progressBar.setMaximum(value);
	}
	public void setValue(int value){
		if (progressBar.getMaximum()>0){
			int progress = 100*value/progressBar.getMaximum();
			progressBar.setString(String.format("%d%%",progress));
		}
		progressBar.setValue(value);
	}
	//?renosu implementacion
	@Override
	public void setVisible(final boolean visible) { //final para que pueda ser usado arriba?
		SwingUtilities.invokeLater(new Runnable(){
		@Override
		public void run() {
			if(!visible){
				setCursor(Cursor.getDefaultCursor());
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				progressBar.setValue(0);
			}
			ProgressDialog.super.setVisible(visible);//Aparece la barra de progreso
		}
	});
	}
	public void setListener(ProgressDialogListener listener){
		this.listener = listener;
	}

}
