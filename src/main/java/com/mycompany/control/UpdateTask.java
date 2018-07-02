package com.mycompany.control;

import java.util.concurrent.TimeUnit;

public class UpdateTask implements Runnable {
	
	private Updater updater;
	
	public UpdateTask(Updater updater) {
		this.updater = updater;
	}
	
	@Override
	public void run() {
		try {
			while (updater.isRunning()) {
				updater.doStep();
				TimeUnit.MILLISECONDS.sleep ((long)(updater.getDelta()));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
