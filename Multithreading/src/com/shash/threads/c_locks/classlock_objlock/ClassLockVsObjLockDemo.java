package com.shash.threads.c_locks.classlock_objlock;

public class ClassLockVsObjLockDemo {

	public static void main(String[] args) {
		
		System.out.println("Starting..");
		
		final ClassLockVsObjectLock testObj = new ClassLockVsObjectLock();
		Thread t1 = new Thread(new Runnable() {
			public void run(){
				testObj.stageOne();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run(){
				testObj.stageTwo();
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
		
		
		testObj.finalResult();
		System.out.println("Finished.");
	}

}
