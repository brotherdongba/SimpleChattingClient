package org.dongba.service;

import java.io.IOException;
import java.io.ObjectOutputStream;

import org.dongba.client.ListenFromServer;
import org.dongba.model.ChatMessage;


public class ClientChatService {
	
	private ListenFromServer listener;

	public void writeMessage(ChatMessage message) throws IOException {
		ObjectOutputStream out = this.listener.getOut();
		out.writeObject(message);
		out.flush();
	}

	public void setListener(ListenFromServer listener) {
		this.listener = listener;
	}
	
	public void startListener() {
		this.listener.start();
	}

	public ListenFromServer getListener() {
		return this.listener;
	}

}
