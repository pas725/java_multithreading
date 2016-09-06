package udemy.nine;

import java.util.Queue;
import java.util.Random;

public class Producer implements Runnable {
	
	Queue<Integer> q;
	Object lock;
	
	public Producer(Queue<Integer> q, Object lock) {
		this.q = q;
		this.lock = lock;
	}
	
	
	public void run() {

		synchronized (lock) {
		
			while(true){
				try {
					//Thread.sleep(400);
					System.out.println("In Producer....");
					
					while(q.size() == 10){
						System.out.println("In Producer : Waiting for Q to be available for push...");
						lock.wait();
					}
					int val = new Random().nextInt(100);
					q.add(val);
					lock.notify();
					System.out.println("Producer : Adding value to Q : "+val);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

}
