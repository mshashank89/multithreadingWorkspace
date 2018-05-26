package com.shash.threads.c_locks.classlock_objlock.staticmethodlocktest;

public class StaticMethodsLockTestDemo {

	public static void main(String[] args) {
		
		System.out.println("Starting..");
		
		
		Thread t1 = new Thread(new Runnable() {
			public void run(){
				StaticMethodsLockTest.stageOne();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run(){
				StaticMethodsLockTest.stageTwo();
			}
		});
		
		
		t1.start();
		t2.start();
		
		try{
			t1.join();
			t2.join();
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		
		
		StaticMethodsLockTest.finalResult();
		System.out.println("Finished.");
	}

}
