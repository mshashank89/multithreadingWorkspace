package com.shash.threads.h_semaphores;

import java.util.concurrent.Semaphore;

public class Connection {
	
	/*
- Connection is a singleton.
- Maximum connections that is possible at any given time is defined as 10.

********************************************************************************************************************
*  semaphore is a lock that can be locked more than once.
*
*  Semaphore is used to limit the number of connections at a given time.
*  		semaphore.acquire() is analogous to lock.lock()
*  		semaphore.release() is analogous to lock.unlock()
*  
*  
*  Constructor:  Semaphore(int permit, boolean fair)
*  @int permits: defines the initial number of permits. i.e., the number of times the semaphore can be acquired 
  				 before it is released. 
  				 In the below example, 10 threads can acquire the semaphore. After the 10th thread has acquired the
  				 semaphore, the 11th thread has to wait for one of the 10 threads to release() the semaphore in order
  				 to be given the permit.
*  
*  @ boolean fair: When fairness parameter is set to true, it means that, the thread that calls the acquire() first,
*  				   will be the first to be given the permit.  
*
*
*******************************************************************************************************************
	 */
	
	private final static Connection instance = new Connection();
	
	private static final int MAX_CONNECTIONS_AT_ANY_TIME = 10;
	
	private int connections = 0;
	
	private Semaphore semaphore = new Semaphore(MAX_CONNECTIONS_AT_ANY_TIME, true);
	
	private Connection(){
	}
	
	public static Connection getInstance(){
		return instance;
	}
	
	public void connect(){
		
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		try {
			doConnect();
		}
		finally {
			semaphore.release();
		}
		
	}
	
	public void doConnect(){
		
		synchronized(this)
		{
			connections++;
			System.out.println("No. of connections : " + connections);
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		synchronized(this)
		{
			connections--;
		}
	}

}
