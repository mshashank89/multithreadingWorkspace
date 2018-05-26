package com.shash.threadpool.example;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ECommApp {
	
/*
 * Simulate an app where there are a million requests and only 1000 phones.
 * Smulate an app to show how only the first 1000 users are assigned the phones.
 */
	
	private static int NO_OF_USERS = 1000;

	public static void main(String[] args) throws InterruptedException {
		
		ArrayList<UserRequest> users = new ArrayList<UserRequest>(NO_OF_USERS);
		
		for (int i=1;i<=NO_OF_USERS;i++) {
			UserRequest ur = new UserRequest();
			ur.id = i;
			ur.isAssigned = false;
			
			users.add(ur);
		}
		
		ArrayBlockingQueue<UserRequest> userQueue = new ArrayBlockingQueue<UserRequest>(NO_OF_USERS);
		
		for (UserRequest ur : users) {
			userQueue.add(ur);
		}
		
		
//		if (1 < 2) return;
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		/*for (UserRequest ur : users) {
			AssignProductWorker apw = new AssignProductWorker(ur);
			executor.submit(apw);
		}*/
		
		while(userQueue.size() > 0) {
			UserRequest ur = userQueue.poll();
			AssignProductWorker apw = new AssignProductWorker(ur);
			executor.submit(apw);
		}
		
		
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.DAYS);
		
		int c = 0;
		System.out.println("===================================");
		for (UserRequest ur : users) {
			System.out.println(ur.id + " " + ur.isAssigned);
			if (ur.isAssigned) c++;
		}
		System.out.println("++++" + c);
		System.out.println("===================================");
	}

}
