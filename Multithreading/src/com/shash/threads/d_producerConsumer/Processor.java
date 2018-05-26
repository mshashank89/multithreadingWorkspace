package com.shash.threads.d_producerConsumer;

public class Processor {
	
	private int num;
	private final int LIMIT = 10;
	private Object _lock = new Object();
	
	/*
	 * 

****    Demonstrates the use of wait() and norify() methods    ******
        ----------------------------------------------------
        
- Both produce() and consume() are modifying the same variable 'num'.
- When produce() and consume() are being run by different threads(see ProdConsDemo.java),
  there is a need for some synchronization mechanism so that only 1 thread can modify the value of 'num' at a given 
  time. 
- Hence, we are using synchronized blocks which are locking on the same object '_lock'.
- locking on the same object is very important here. 
  So, if Thread-1 has acquired lock on the '_lock' object and it is executing the produce() method, Thread-2 
  cannot execute consume(), because the consume() is synchronized on the '_lock' object, for which 
  Thread-1 has already acquired the lock..  
  
  
	 */
	
	public void produce() throws InterruptedException{
		
		while(true){
			
			synchronized(_lock){
				
				if (num == LIMIT) num = 0;
				
				System.out.println("[" + Thread.currentThread().getName() + "] Prod : " + num++);
				
				_lock.notify();
				Thread.sleep(1000);
				_lock.wait();
			}
		}
		
	}
	
	public void consume() throws InterruptedException{
		
		Thread.sleep(2000);
		
		while(true){
			
			synchronized(_lock){
				
				if (num == LIMIT) num = 0;
				
				System.out.println("Cons : " + num ++);
				
				_lock.notify();
				
				Thread.sleep(1000);
				_lock.wait();
			}
		}
	}

}
