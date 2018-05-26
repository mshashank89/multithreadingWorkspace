package com.shash.threads.b_useofvolatile;

import java.util.Scanner;


/*
	- Volatile keyword is used to prevent the caching of variables.
	- In the below example, the 'running' variable is not being modified anywhere inside the class Runner
	- ***In some systems, java may try to optimize the code and never check the value of 'running' in the while loop,
	- because it assumes that 'running' is not being modified. 
	- It does not take into account the possibility of 'running' being modified by another thread.
	
	- The below code works even if 'running' is not defined as 'volatile'. 
	But it does not guarantee that the below code works on all systems.
	
	- Where as using 'volatile' guarantees that the below code works on ALL systems. 
 */
public class UseOfVolatile {

	private static class Runner implements Runnable{
		private volatile boolean running = true;
		
		public void run(){
			int count = 0;
			while(running){
				if (count >=10){
					count = 0;
				}
				System.out.println(count++);
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
				}
			}
		}
		
		
		public void shutdown(){
			System.out.println("Stopping thread ...");
			running = false;
		}
	}
	public static void main(String[] args) {
		
		Runner runner = new Runner();
		Thread t1 = new Thread(runner);

		System.out.println("Press return key to stop the thread");
		System.out.println("Starting thread ...");
		t1.start();
		
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
		
		
		runner.shutdown();
		
		System.out.println("End of main");
		
		
	}

}
