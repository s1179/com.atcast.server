package com.sp.lrn.websocketx.client.controller;

import java.io.File;
import java.io.IOException;

import com.sp.lrn.websocketx.client.GUI.InitEvent;
import com.sp.lrn.websocketx.client.model.Evento;
import com.sp.lrn.websocketx.client.model.SimDataOrigin;

public class Controller {
	SimDataOrigin orig=new SimDataOrigin();
public void addEvento(InitEvent ev){
	String nombre = ev.getInputNombreEvento();
	String hora = ev.getInputHora();
	Evento evento = new Evento(nombre, hora, null);
	orig.addEvento(evento);
}
//ejecuta llamadas desde gui
public void saveToFile (File file) throws IOException{
	orig.saveToFile(file);
}
public void loadFromFile (File file) throws IOException{
	orig.loadFromFile(file);
}
}
