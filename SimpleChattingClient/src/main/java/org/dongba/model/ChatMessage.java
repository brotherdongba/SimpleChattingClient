package org.dongba.model;

import java.io.Serializable;

public class ChatMessage implements Serializable {
	
	private static final long serialVersionUID = -5438669792709105439L;
	private int type;
	private String message;
	
	public ChatMessage(int type, String message) {
		this.type = type;
		this.message = message;
	}
	
	public int getType() {
		return this.type;
	}
	
	public String getMessage() {
		return this.message;
	}

}
