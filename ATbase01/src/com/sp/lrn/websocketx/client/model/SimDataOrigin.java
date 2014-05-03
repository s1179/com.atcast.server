package com.sp.lrn.websocketx.client.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimDataOrigin {
	private ArrayList<Evento> listaEventos;
	public SimDataOrigin(){
		listaEventos = new ArrayList<Evento>();
		
	}
	public void addEvento(Evento evento){
		listaEventos.add(evento);
	}
	public List<Evento> getEvento(){
		return listaEventos;
	}
	public void saveToFile(File file) throws IOException { //serializar
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos= new ObjectOutputStream(fos); //wraps fis
		Evento[] eventos = listaEventos.toArray(new Evento[listaEventos.size()]);
		//un arreglo mantiene el tipo, arraylist no tons se guarda arreglo
		oos.writeObject(eventos);
		oos.close();
	}
	public void loadFromFile(File file) throws IOException{
		FileInputStream fis =  new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			Evento[] eventos = (Evento[])ois.readObject();
			listaEventos.clear();//se usa la misma ArrayList, no una nueva v 28 7:20
			listaEventos.addAll(Arrays.asList(eventos)); //addAll no funca con arreglos, toca convertir
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ois.close();
	}
}
