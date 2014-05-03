package com.atmedios.atcast;

import java.awt.EventQueue;

import com.atmedios.atcast.gui.MainWindow;

/**
 * Entry point.
 * @author sergiopena
 *
 */
public class ATCast {
	public ATCast() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// GUI
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

	public void ServerMode(int Sources) {
		// TODO codigo para tipo de funcionalidad 1
		// listener de cosas del asistent ppal y lanzar los servidoers que
		// corresponde lanzar
		//Server server = new Server();
		//Runnable r = new //launch theeads

	}

	public void TextClientMode(int SourceType) {

	}

	public void SoundClientMode(int SourceType) {

	}

	public void MixedClientMode(int SourceType) {

	}

}
