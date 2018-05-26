import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class TestSingleThreadExecutor {

	private static class Worker implements Runnable {
		int id;
		int num;
		
		public Worker(int n) {
			id = n;
			num = 0;
		}

		@Override
		public void run() {

//			System.out.println(id + " | Start " + Thread.currentThread().getId() + " " + Thread.currentThread().getName());

			if (id % 3 == 0) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				num++;
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			System.out.println(id + " | Num Val : " + num);
			System.out.println(id + " | End   " + Thread.currentThread().getId() + " " + Thread.currentThread().getName());
		}
		
	}
	public static void main(String[] args) {

//		ExecutorService executor = Executors.newSingleThreadExecutor();
//		ExecutorService executor = Executors.newFixedThreadPool(3);
//		ExecutorService executor = Executors.newCachedThreadPool();
		
		ExecutorService executor = new ThreadPoolExecutor(3, 2147483647, 60L, TimeUnit.SECONDS,
				new SynchronousQueue());
		
		for (int i=0;i<10;i++) {
			Worker r = new Worker(i);
//			System.out.println("			Submitting " + (i+1));
			executor.submit(r);
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		executor.shutdown();
		try {
			executor.awaitTermination(1L, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("---------\nEnd of Main");
		
	}

}