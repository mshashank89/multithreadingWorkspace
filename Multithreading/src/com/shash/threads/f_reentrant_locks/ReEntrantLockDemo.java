package com.shash.threads.f_reentrant_locks;

public class ReEntrantLockDemo {

	public static void main(String[] args) {

		final Processor processor = new Processor();
		
		Thread t1 = new Thread(new Runnable(){
			public void run(){
				processor.firstThread();
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				processor.secondThread();
			}
		});
		
		t1.start();
		t2.start();
		
		try{
			t1.join();
			t2.join();
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		
		processor.finalCount();
		
		
	}

}
