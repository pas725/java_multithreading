package udemy.four;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * Introducing locks for individual methods instead Whole class Object. 
 * */

public class Worker {

	private Random random = new Random();
	
	// Create Object locks for method stage1() and stage2()
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	private List<Integer> l1 = new ArrayList<Integer>();
	private List<Integer> l2 = new ArrayList<Integer>();
	
	public void stage1(){
		
		// Multiple threads needs to acquire lock1 to enter this method of Worker Object
		synchronized (lock1) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			l1.add(random.nextInt(200));
		}
			
		
	}
	
	public void stage2(){
		
		synchronized (lock2) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			l2.add(random.nextInt(200));
		}			
		
	}
	
	public void process(){
		
		for(int i=0 ; i<1000 ; i++){
			
			stage1();
			stage2();
		}
	}
	
	public void main(){
		
		long start,end;
		
		start = System.currentTimeMillis();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				process();
				
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				process();
				
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		end = System.currentTimeMillis();
		
		System.out.println("Time taken : "+ ( end - start));
		System.out.println(" List size : l1 -> "+l1.size()+" , l2 -> "+l2.size());
	}
}
