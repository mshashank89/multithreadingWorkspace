package com.shash.threads.h_semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemoApp {
	
	/*

- Scenario:
--------------
- 200 threads created using a CachedThreadPool.
- All threads are requesting for a connection from the Connection class.

*****************************************************************************************************************
* Not using semaphores will result in all 200 connections being formed at a time.
  
  This is not good in a real time scenario, where it will cause an overhead, resulting in performance issues.
  So, it is better to limit the number of threads that can create connections. 
  
* Max number of connections at a time is limited to 10 by the use of semaphores.
* 
* 
* 
* ***********
* Calling doConnect() will resulting all 200 connections being formed together.
* Calling connect() will restrict the maximum connections at a time to 10.
*****************************************************************************************************************
	 */

	public static void main(String[] args) {

		ExecutorService executor = Executors.newCachedThreadPool();
		
		for (int i=0;i<200;i++){
			executor.submit(new Runnable() {
				public void run(){
					Connection.getInstance().connect();				// will restrict the max connections at a time to 10
//					Connection.getInstance().doConnect();				// will result in all 200 connections being formed together.
				}
			});
		}
		
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
