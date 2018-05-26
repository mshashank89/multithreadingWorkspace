package com.shash.threads.d_producerConsumer;

public class ProdConsDemo {

	
	/*

- The below method 2 threads print numbers alternatively. The 'producer' prints 1, next 
  the 'consumer' prints 2, and so on..
  
- The producer calls the produce() method and the consumer calls the consume() method of the Processor object.

- However, the above functionality can also be achieved by calling only the produce() method from both the threads. 
	 */
	public static void main(String[] args) {

		final Processor processor = new Processor();
		Thread producer = new Thread(new Runnable(){
			
			public void run(){
				try {
					processor.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		Thread consumer = new Thread(new Runnable(){
			
			public void run(){
				try {
//					processor.produce();
					processor.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		producer.start();
		consumer.start();
		
		try {
			producer.join();
			consumer.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println("Press ");
		
		System.out.println("demo end");
	}

}
