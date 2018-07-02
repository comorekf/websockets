package com.mycompany.pages.sockets;

import java.io.Serializable;

import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;

public class TextWSMessage implements IWebSocketPushMessage, Serializable {
	private static final long serialVersionUID = 1L;
	
	private final String message;
	
	public TextWSMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}

}
