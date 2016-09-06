package udemy.seven;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Tester {

	public static void main(String[] args) {

		BlockingQueue<Integer> q = new ArrayBlockingQueue<Integer>(10);
		
		Thread producer = new Thread(new Producer(q));
		Thread consumer = new Thread(new Consumer(q));
		
		System.out.println("Starting threads......");
		
		producer.start();
		consumer.start();
	}

}
