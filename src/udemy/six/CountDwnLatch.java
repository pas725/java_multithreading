package udemy.six;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processor implements Runnable{
	
	CountDownLatch latch;
	public Processor(CountDownLatch latch) {
		// TODO Auto-generated constructor stub
		this.latch = latch;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Started thread");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Decrement the latch 
		latch.countDown();
	}
}

public class CountDwnLatch {

	
	
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(3);
		
		ExecutorService service = Executors.newFixedThreadPool(3);
		
		for(int i=0; i<3 ;i++){
			service.submit(new Processor(latch));
		}
		
		try {
			// Causes the current Thread to wait until latch become Zero
			// This method returns once latch is 0.
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Completed.");
	}
}
