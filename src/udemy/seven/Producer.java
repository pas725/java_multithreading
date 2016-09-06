package udemy.seven;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

	BlockingQueue<Integer> q;
	public Producer(BlockingQueue<Integer> q) {
		this.q = q;
	}

	public void run() {
		while(true){
			
			try {
				Thread.sleep(600);
				Random random = new Random();				
				int val = random.nextInt(100);				
				q.put(val);
				System.out.println(" Producer : Added : "+val);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
