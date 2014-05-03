package com.sp.lrn.websocketx.client.model;

import java.io.Serializable;


public class Evento implements Serializable{
	
	private static final long serialVersionUID = -6254728634261232873L;
	private static int count; //oculto a todos
	private int id;
	private String nombre;
	private String hora;
	private String[] options;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String[] getOptions() {
		return options;
	}
	public void setOptions(String[] options) {
		this.options = options;
	}
	
	public Evento(String nombre, String hora, String[] options){
		this.nombre = nombre;
		this.hora = hora;
		this.options = options;
		this.id=count;
		count ++;
	}
	public int getId() {//overrides count gen. ids
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
