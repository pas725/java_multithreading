package udemy.nine;

import java.util.LinkedList;
import java.util.Queue;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Queue<Integer> q = new LinkedList<Integer>();
		Object lock = new Object();
		
		Thread p = new Thread(new Producer(q, lock));
		Thread c = new Thread(new Consumer(q, lock));
		
		p.start();
		c.start();

	}

}
