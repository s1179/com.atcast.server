package com.atmedios.atcast.utils;

public class Utils {
	public static String getFileExtension(String name){
		int pos = name.lastIndexOf(".");
		if (pos==-1 || (pos == name.length()-1)){
			return null;
		} else {
			return name.substring(pos+1,name.length());
		}
	}

}