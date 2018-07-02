package com.mycompany.pages.sockets;

import java.io.Serializable;

import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;

public class WSMessage implements IWebSocketPushMessage, Serializable {
	private static final long serialVersionUID = 1L;
	
	private final int number;
	
	public WSMessage(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}
	

}
