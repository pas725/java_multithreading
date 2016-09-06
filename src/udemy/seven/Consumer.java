package udemy.seven;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

	BlockingQueue<Integer> q;
	public Consumer(BlockingQueue<Integer> q) {
		this.q = q;
	}

	public void run() {
		while(true){
			
			try {
				Thread.sleep(900);
				int val = q.take();
				
				System.out.println(" Consumer : Taken : "+val+" , Q Size : "+q.size());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	
}
