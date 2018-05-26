package com.shash.threads.a_way2initialize;

public class AnonymousClassDeclDemo {

	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable(){
			public void run(){
				for (int i=0;i<10;i++){
					System.out.println(i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				for (int i=0;i<10;i++){
					System.out.println(i);
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
//			t2.join();
		} catch (InterruptedException e) {
		}
		
		System.out.println("End of main");
	}

}
