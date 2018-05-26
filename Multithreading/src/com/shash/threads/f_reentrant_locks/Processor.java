package com.shash.threads.f_reentrant_locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Processor {

	/*

Outputs without the use of locks:
Final count : 19803
Final count : 16572
Final count : 20000
	.
	.
	
Outputs with the use of locks:
Final count : 20000
Final count : 20000
Final count : 20000
Final count : 20000
	.
	.
	.

	 */
	
	
	private Lock lock = new ReentrantLock();
	private int count = 0;
	
	private void increment(){
		for (int i=0;i<10000;i++){
			count++;
		}
	}
	
	public void firstThread(){
		try
		{
			lock.lock();
			increment();
		}
		finally {
			lock.unlock();
		}
	}
	
	public void secondThread(){
		try
		{
			lock.lock();
			increment();
		}
		finally {
			lock.unlock();
		}
	}
	
	public void finalCount(){
		System.out.println("Final count : " + count);
	}
}
