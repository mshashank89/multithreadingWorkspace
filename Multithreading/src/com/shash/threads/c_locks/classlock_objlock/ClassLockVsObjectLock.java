package com.shash.threads.c_locks.classlock_objlock;

import java.util.Random;

public class ClassLockVsObjectLock {

	/*
	 * **************************************************************************************************
	 * Static synchronized methods will lock on the '.class' object. Non-static
	 * synchronized methods will lock on the 'this' object.
	 * 
	 * **************************************************************************
	 * ************************
	 */

	private int count = 0;

	public synchronized void stageOne() {

		for (int i = 0; i < 1E7; i++) {
			count++;

			Math.sin(new Random().nextDouble());
		}
	}

/*	public synchronized void stageTwo() {

		for (int i = 0; i < 1E7; i++) {
			count++;
		}
	}*/

	public void stageTwo() {

//		synchronized (ClassLockVsObjectLock.class) {    // synchronization NOT achieved because stageOne() method is locked on the current object 
		synchronized (this) {							// synchronization achieved when locked on "this" object.
			for (int i = 0; i < 1E7; i++) {
				count++;
			}

		}

	}

	public void finalResult() {
		System.out.println("Final value of count : " + count);
	}
}
