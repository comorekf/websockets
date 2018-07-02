package com.mycompany.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public abstract class BasePage extends WebPage {
	private static final long serialVersionUID = 1L;
	
	protected static final int FAIL = -1;
	
	public BasePage() {
		initPage ();
	}
	
	private void initPage () {
		add(new Label("page_title", "WebSockets counter"));
		add(new Label("footer", "© :-) Using Wicket: " + getApplication().getFrameworkSettings().getVersion()));
	}

}
