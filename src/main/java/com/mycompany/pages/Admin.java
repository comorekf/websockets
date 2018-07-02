package com.mycompany.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;

import com.mycompany.control.Updater;

public class Admin<T> extends BasePage {
	private static final long serialVersionUID = 1L;
	
	// wicket
	private AjaxFallbackLink<String> start;
	private AjaxFallbackLink<String> stop;
	private AjaxFallbackLink<String> reverse;
	
	@Override
	protected void onInitialize () {
		initPage ();
		super.onInitialize ();
	}
	
	private void initPage () {
		addElements();
	}
	
	
	private void addElements() {
		start = new AjaxFallbackLink<String> ("start") {
			private static final long serialVersionUID = 1L;

			public void onClick (AjaxRequestTarget target) {
				Updater.getInstance().start();
			}
		};
		start.setOutputMarkupId (true);
		add (start);
		
		stop = new AjaxFallbackLink<String> ("stop") {
			private static final long serialVersionUID = 1L;

			public void onClick (AjaxRequestTarget target) {
				Updater.getInstance().stop();
			}
		};
		stop.setOutputMarkupId (true);
		add (stop);
		
		reverse = new AjaxFallbackLink<String> ("reverse") {
			private static final long serialVersionUID = 1L;

			public void onClick (AjaxRequestTarget target) {
				Updater.getInstance().reverse();
			}
		};
		reverse.setOutputMarkupId (true);
		add (reverse);
	}

}
