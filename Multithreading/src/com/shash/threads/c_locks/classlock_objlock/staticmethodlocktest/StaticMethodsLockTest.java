package com.shash.threads.c_locks.classlock_objlock.staticmethodlocktest;

import java.util.Random;

import com.shash.threads.c_locks.classlock_objlock.ClassLockVsObjectLock;

public class StaticMethodsLockTest {
	
	/*
***************************************************************************************************
* Static synchronized methods will lock on the '.class' object.
* Non-static synchronized methods will lock on the 'this' object.

*************************************************************************************************** 
	 */

	private static int count = 0;
	private static Object lock = StaticMethodsLockTest.class;
	
	public static synchronized void stageOne(){
		
		for (int i=0;i<1E7;i++){
			count ++;
			
			Math.sin(new Random().nextDouble());
		}
	}
	
	public static void stageTwo(){
		
		synchronized(lock){
			for (int i=0;i<1E7;i++){
				count++;
			}
			
		}

	}
	
	
	public static void finalResult(){
		System.out.println("Final value of count : " + count);
	}
}
