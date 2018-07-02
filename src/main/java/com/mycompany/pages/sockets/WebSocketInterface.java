package com.mycompany.pages.sockets;

import org.apache.wicket.protocol.ws.api.message.ConnectedMessage;

public interface WebSocketInterface {

	public void addClient (ConnectedMessage message);
	
	public void sendMessage (int number);
	
	
}
