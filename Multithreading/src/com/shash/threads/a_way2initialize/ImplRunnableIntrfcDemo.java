package com.shash.threads.a_way2initialize;



public class ImplRunnableIntrfcDemo {
	
	
	private static class Runner implements Runnable {
		private int sleeptime;
		public Runner(int sleeptime){
			this.sleeptime = sleeptime;
		}
		public void run() {

			for (int i = 0; i < 10; i++) {
				System.out.println(i);

				try {
					Thread.sleep(sleeptime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Thread execution ends");
		}
	}

	public static void main(String[] args) {

		Thread t1 = new Thread(new Runner(1000));
		Thread t2 = new Thread(new Runner(2000));
		
		t1.start();
		t2.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
		}
		
		System.out.println("End of main");
	}

}
