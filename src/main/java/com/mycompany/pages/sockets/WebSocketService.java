package com.mycompany.pages.sockets;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.protocol.ws.WebSocketSettings;
import org.apache.wicket.protocol.ws.api.WebSocketPushBroadcaster;
import org.apache.wicket.protocol.ws.api.message.ConnectedMessage;
import org.apache.wicket.protocol.ws.api.registry.IWebSocketConnectionRegistry;

import com.mycompany.control.Updater;

public class WebSocketService implements WebSocketInterface {
	
	private List<ConnectedMessage> connections = new ArrayList<ConnectedMessage> ();
	private WebSocketPushBroadcaster broadcaster;
	
	private static WebSocketService instance;
	
	private WebSocketService() {}
	
	public static WebSocketService getInstance() {
		if(instance == null) {
			instance = new WebSocketService();
		}
		return instance;
	}

	public void addClient (ConnectedMessage message) {
		ConnectedMessage conMsg = new ConnectedMessage (message.getApplication(), message.getSessionId(), message.getKey());
		connections.add(conMsg);
		
		if (null == broadcaster) {
			WebSocketSettings webSocketSettings = WebSocketSettings.Holder.get(message.getApplication());
			IWebSocketConnectionRegistry webSocketConnectionRegistry = webSocketSettings.getConnectionRegistry();
			broadcaster = new WebSocketPushBroadcaster (webSocketConnectionRegistry);
		}
		
		Updater.getInstance().join();
	}

	@Override
	public void sendMessage (int number) {
		if (null != broadcaster) {
			WSMessage message = new WSMessage(number);
			broadcaster.broadcastAll(connections.listIterator().next().getApplication(), message);
		} else {
			throw new RuntimeException ("WebSockets can not send message");
		}
	}
	
}
