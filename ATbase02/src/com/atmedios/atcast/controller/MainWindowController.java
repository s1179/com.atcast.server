package com.atmedios.atcast.controller;

import com.atmedios.atcast.net.WebSocketServer;

public class MainWindowController {

	public MainWindowController() {
		// TODO Auto-generated constructor stub
	}
	public void startStreamingServer(int port){//TODO creo que debe retornar una referencia otr objeto
		//int port = 8080;
		try {
			WebSocketServer textServer = new WebSocketServer(port);
			textServer.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void sendMessage(String message){
		
	}
	

}
