package ru.jcode;

import java.util.Timer;
import java.util.TimerTask;

public class OnlineThread extends TimerTask {

	private final API API;
	
	public OnlineThread(String token) {
		API = new API(token);
		
		if(API.isValidToken()) {
			System.out.println("Запускаем поток вечного онлайна");
			
			Timer timer = new Timer();
			timer.schedule(this, 100, 60000*3);
		}
	}
	
	@Override
	public void run() {
		System.out.println(API.getTime() + " Пробуем отправить статус online...");
		
		if(API.setOnlineStatus()) {
			System.out.println(API.getTime()+" - [OK]");
		}else {
			System.out.println(API.getTime()+" - [FAILED]");			
		}
	}
}