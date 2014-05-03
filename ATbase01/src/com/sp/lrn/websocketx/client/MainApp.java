package com.sp.lrn.websocketx.client;

import java.awt.EventQueue;

import com.sp.lrn.websocketx.client.GUI.MainWindow;

/**
 * @author sergiopena Tratar de usar el cliente dentro de una ventana, reportar
 *         y enviar desde aqu
 */
public class MainApp {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new MainWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
