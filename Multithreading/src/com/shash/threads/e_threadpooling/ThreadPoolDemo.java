package com.shash.threads.e_threadpooling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {
	
	/*
	 * 
**********************************************************************************************************************
* Executors.newFixedThreadPool(int numOfThreads) will create numOfThreads number of threads in the threadPool.
* 
* executor.submit() will submit all the runnable tasks to the threadPool. 
       All the submitted tasks will be picked up by the threads in the threadPool and will be executed one by one.
       
* executor.shutdown() : Terminates the managerial thread when the threads in the threadPools are done 
  					    executing all the runnable tasks.
  					    Initiates an orderly shutdown in which previously submitted tasks are executed, but no new 
  					    tasks will be accepted. Invocation has no additional effect if already shut down. 
						This method does not wait for previously submitted tasks to complete execution. Use awaitTermination to do that

  
  
* executor.awaitTermination : Blocks until all tasks have completed execution after a shutdown request, or the timeout 
                              occurs, or the current thread is interrupted, whichever happens first.


**********************************************************************************************************************

	 */
	
	private static class Worker implements Runnable{
		public void run(){
			
			System.out.println(Thread.currentThread().getName() + " : Started");
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(Thread.currentThread().getName() + " : End");
		}
	}

	public static void main(String[] args) {
		/*
		 			Use Case
		 			----------
		 	1. Create 5 Worker instances
		 	2. Create a thread pool with 2 threads
		 	3. Execute the runnable instances using the threads in the threadpool
		 	
		 	
		 */
		
		ExecutorService executor = Executors.newFixedThreadPool(2);    //Creates a threadpool with 2 threads
		
		for (int i=0;i<5;i++){    					//  Creates 5 Worker instances
			executor.submit(new Worker());
		}
		
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);		//similar to thread.join(), waits for the termination of all threads in the threadpool
																//Blocks until all tasks have completed execution after a shutdown request,
																//or the timeout occurs, or the current thread is interrupted, whichever happens first.
		} catch (InterruptedException e) {
		}
		
		System.out.println("End of main");
	}

}
