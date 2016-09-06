package udemy.seven;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {

	BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
	
	public static void main(String[] args)  {
		
		ProducerConsumer pc = new ProducerConsumer();
		
		Thread prod = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					pc.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		Thread cons = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					pc.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
		cons.start();
		prod.start();
		
	}
	
	void producer() throws InterruptedException{
		
				
		Random random = new Random();
		
		while(true){
			Thread.sleep(400);
			int val = random.nextInt(100);			
			queue.put(val); // This method wait/blocks here until there is space for new element
			System.out.println("Producer : Added to Q : "+val);
		}		
	}
	
	void consumer() throws InterruptedException{
		
		while(true){
			
			Thread.sleep(600);
			int val = queue.take(); // This method wait/blocks here until there is element in Q
			System.out.println("Consumer : Taken from Q : "+val+", Q Size : "+queue.size());
		}
	}
}
