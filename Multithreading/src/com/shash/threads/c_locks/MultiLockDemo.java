package com.shash.threads.c_locks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MultiLockDemo {

	/*

- This program demonstrates the use of 2 different locks used for modifying 2 different shared resources.

- Disadvantages of using a single lock for modifying 2 different resources
	- If both stageOne() which is used to modify 'list1' and stageTwo() which is used to modify 
	  'list2' use '_lock1' to lock on, then when thread-1 is executing stageOne(), since the '_lock1' is 
	  used in both the methods, it is not possible for 2 threads to execute stageOne() and stageTwo() simultaneously.
	  Thread-2 has to wait for Thread-1 to release lock on '_lock1' to execute the sync block in stageTwo().
	  
- How using 2 locks help:
	- Using 2 separate locks for locking 'list1' and 'list2' allows Thread-2 to execute stageTwo() simultaneously when Thread-1 is executing stageOne().
	- It also make sure that no two threads are allowed to execute stageOne() together simultaneously, and no two threads are allowed to execute stageTwo() simultaneously.
	
	 * 
	 */
	private static class Worker{
		
		private Object _lock1 = new Object();
		private Object _lock2 = new Object();
		
		private Random random = new Random();
		
		private List<Integer> list1 = new ArrayList<Integer>();
		private List<Integer> list2 = new ArrayList<Integer>();
		
		private synchronized void stageOne(){
			synchronized(_lock1){
				
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				list1.add(random.nextInt(100));
			}
		}
		
		private synchronized void stageTwo(){
			synchronized(_lock2){

				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				list2.add(random.nextInt(100));
			}
		}
		
		public void process(){
			stageOne();
			stageTwo();
		}
		
		

		public void main() {
			
			Thread t1 = new Thread(new Runnable(){
				public void run(){
					for(int i=0;i<1000;i++){
						process();
					}
				}
			});
			
			Thread t2 = new Thread(new Runnable(){
				public void run(){
					for(int i=0;i<1000;i++){
						process();
					}
				}
			});
			
			
			
			System.out.println("Starting ...");
			long start = System.currentTimeMillis();
			
			t1.start();
			t2.start();
			
			try {
				t1.join();
				t2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long end = System.currentTimeMillis();
			
			System.out.println("Time taken: " + (end - start));
			System.out.println("List1 : " + list1.size() + " ; List2: " + list2.size());
		}
	}
	
	public static void main(String[] args) {
		new Worker().main();
	}
	

}
