package com.mycompany;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.mycompany.pages.Admin;
import com.mycompany.pages.WebSocketCounter;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;
	
	private AjaxFallbackLink<String> ClientLink;
	private AjaxFallbackLink<String> AdminLink;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		ClientLink = new AjaxFallbackLink<String> ("sockets_link") {
			private static final long serialVersionUID = 1L;

			public void onClick (AjaxRequestTarget target) {
				throw new RestartResponseAtInterceptPageException (new WebSocketCounter());
			}
		};
		add (ClientLink);
		
		AdminLink = new AjaxFallbackLink<String> ("admin_link") {
			private static final long serialVersionUID = 1L;

			public void onClick (AjaxRequestTarget target) {
				throw new RestartResponseAtInterceptPageException (new Admin<Object>());
			}
		};
		add (AdminLink);
    }
}
