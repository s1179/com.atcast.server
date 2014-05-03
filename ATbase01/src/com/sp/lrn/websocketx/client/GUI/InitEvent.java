package com.sp.lrn.websocketx.client.GUI;

import java.util.EventObject;

@SuppressWarnings("serial")
public class InitEvent extends EventObject {

	private String inputHora;
	private String inputNombreEvento;
	private int id;

	// 2 constructors, one withput the params just in case
	public InitEvent(Object fuenteEv) {
		super(fuenteEv);
	}

	public InitEvent(Object fuenteEv, String inputNombreEvento, String inputHora, int id) {
		super(fuenteEv);
		this.inputHora = inputHora;
		this.inputNombreEvento = inputNombreEvento;
		this.id=id;
	}

	public String getInputHora() {
		return inputHora;
	}

	public void setInputHora(String inputHora) {
		this.inputHora = inputHora;
	}

	public String getInputNombreEvento() {
		return inputNombreEvento;
	}

	public void setInputNombreEvento(String inputNombreEvento) {
		this.inputNombreEvento = inputNombreEvento;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
