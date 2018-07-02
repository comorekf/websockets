package com.mycompany.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.ws.api.WebSocketBehavior;
import org.apache.wicket.protocol.ws.api.WebSocketRequestHandler;
import org.apache.wicket.protocol.ws.api.message.ConnectedMessage;
import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;

import com.mycompany.pages.sockets.WSMessage;
import com.mycompany.pages.sockets.WebSocketService;

public class WebSocketCounter extends BasePage {
	private static final long serialVersionUID = 1L;
	
	// wicket
	private Model<Integer> updateModel;
	private Label numberLabel;
	
	public WebSocketCounter() {
	}
	
	@Override
	protected void onInitialize () {
		initPage ();
		super.onInitialize ();
	}
	
	private void initPage () {
		addElements();
		addWebSocketUpdating();
	}
	
	private void addElements() {
		updateModel = new Model<Integer>(0);
		
		numberLabel = new Label ("number_label", updateModel);
		numberLabel.setOutputMarkupId (true);
		add (numberLabel);
		
	}
	
	private void addWebSocketUpdating () {
		add (new WebSocketBehavior() {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onConnect (ConnectedMessage message) {
				super.onConnect (message);
				
				WebSocketService.getInstance().addClient(message);
			}

			@Override
			protected void onPush(WebSocketRequestHandler handler, IWebSocketPushMessage message) {
				super.onPush (handler, message);
				
				if (message instanceof WSMessage) {
					WSMessage msg = (WSMessage) message;
					updateModel.setObject(msg.getNumber());
				}
				handler.add (numberLabel);
			}
		});
	}
	
}
