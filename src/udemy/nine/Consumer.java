package udemy.nine;

import java.util.Queue;

public class Consumer implements Runnable {

	Queue<Integer> q;
	Object lock;
	
	public Consumer(Queue<Integer> q, Object lock) {
		this.q = q;
		this.lock = lock;
	}
	
	
	public void run() {

		synchronized (lock) {
		
			while(true){
				try {					
					System.out.println("In Consumer....");

					while(q.size() == 0){
						System.out.println("In Consumer : Waiting for Q to be filled ...");
						lock.wait();
					}
					
					int val = q.poll();
					lock.notify();
					System.out.println("Consumer : Taken value from Q : "+val+", Q size : "+q.size());
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}

}
