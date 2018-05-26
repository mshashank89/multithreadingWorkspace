package com.shash.threads.a_way2initialize;

public class ExtndThreadClassDemo {
	
	
	private static class Runner extends Thread{
		public void run(){
			for(int i=0;i<10;i++){
				System.out.println(i);
				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			System.out.println("Thread execution ends");
		}
	}
	
	
	public static void main(String[] args) {

		Runner runner = new Runner();
		runner.start();
		
		try {
			runner.join();
		} catch (InterruptedException e) {
		}
		System.out.println("End of main");
	}

}
