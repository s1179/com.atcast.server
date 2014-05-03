package com.sp.lrn.websocketx.client.GUI;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class ArchivoFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		String name = f.getName();
		if (Utils.getFileExtension(name)=="aef" || f.isDirectory()){//ojo que en mac hay archivos que son directorios
			return true;
		} else {
			return false;
		}

	}

	@Override
	public String getDescription() {
		return "Archivos de evento de ATCast (*.aef)";
	}

}
