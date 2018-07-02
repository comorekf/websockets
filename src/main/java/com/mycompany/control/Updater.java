package com.mycompany.control;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.mycompany.pages.sockets.WebSocketService;

public class Updater implements UpdateInterface {
	
	private static final int MAX_WAITING_TIME = 1;  // in seconds
	public static final int MAX_NUMBER = 10000;     // a random number
	
	private static Updater instance;
	
	private UpdateTask updateTask = null;
	private ScheduledExecutorService threadPool = null;
	
	private int number = 0;
	public Integer DELTA_T = 100; // in miliseconds
	
	private Boolean increase = null;
	
	private Updater() {}
	
	public static Updater getInstance() {
		if (instance == null) {
			instance = new Updater();
		}
		return instance;
	}

	@Override
	public void start() {
		this.increase = true;
	}

	@Override
	public void stop() {
		this.increase = null;
	}

	@Override
	public void reverse() {
		this.increase = false;
	}

	@Override
	public void join() {
		if (updateTask == null) {
			updateTask = new UpdateTask(this);
			threadPool = Executors.newSingleThreadScheduledExecutor();
		}
		threadPool.schedule(updateTask, MAX_WAITING_TIME, TimeUnit.SECONDS);
	}
	
	public void doStep() {
		if (increase != null) {
			number = (increase == true) ? ++number : --number;
			WebSocketService.getInstance().sendMessage(number);
		}
	}
	
	public boolean isRunning() {
		return number < MAX_NUMBER && number > -MAX_NUMBER;
	}
	
	public int getDelta() {
		return this.DELTA_T;
	}
}
