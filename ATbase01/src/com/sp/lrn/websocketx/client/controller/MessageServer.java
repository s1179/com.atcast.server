package com.sp.lrn.websocketx.client.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MessageServer implements Iterable<Message>{
	private Map<Integer, List<Message>> messages;
	private List<Message> selected;
	//tr
	private Set<Integer> selectedServers;
	
	public MessageServer(){
		
		selected = new ArrayList<Message>();
//creando mensajes artificialmente
		messages= new TreeMap<Integer, List<Message>>();
		
		List<Message> lista = new ArrayList<Message>();
		lista.add(new Message("tit1","msg1"));
		lista.add(new Message("tit2","msg2"));
		messages.put(0, lista);
		
		lista = new ArrayList<Message>();//clr lista
		lista.add(new Message("tita","msga"));
		lista.add(new Message("titb","msgb"));
		messages.put(1, lista);
//seleccionandolos chambonamente
		selectedServers= new TreeSet<Integer>();
		selectedServers.add(0);
		selectedServers.add(1);
		//System.out.println(selectedServers);
		setSelectedServers(selectedServers);
	}
public void setSelectedServers(Set<Integer> servers){
	//selected.clear();
	for(Integer id: servers) {
		if(messages.containsKey(id)){
			selected.addAll(messages.get(id));
		}
	}
}
public int getMessageCount(){
	return selected.size();
}
@Override
public Iterator<Message> iterator() {
	return new MessageIterator(selected);
}

}

class MessageIterator implements Iterator{
	private Iterator<Message> iterator;
	public MessageIterator (List<Message> messages){
		iterator=messages.iterator();
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return iterator.hasNext();
	}

	@Override
	public Object next() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO quitado para que termine el hilo silenciosamente
			//e.printStackTrace();
		}
		return iterator.next();
	}

	@Override
	public void remove() {
		iterator.remove();
	}
	
}

