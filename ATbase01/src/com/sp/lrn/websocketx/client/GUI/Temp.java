package com.sp.lrn.websocketx.client.GUI;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Temp {

	public Temp() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
			System.out.println(info);
		}
	}

}
