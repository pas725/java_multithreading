package udemy.ten;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReEntrantLock {
	
	public static void main(String[] args) {
		Runner r = new Runner();
		
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					r.m1();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					r.m2();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		
				
		r.finished();
	}

}

class Runner{
	
	int count = 0;
	
	// Use lock Interface, Same working as synchronized block
	Lock lock =  new ReentrantLock();
	
	// Can be used as wait() & notify() 
	Condition cond = lock.newCondition();
	
	public void increment(){
		for(int i=0;i<1000;i++){
			count++;
		}
	}
	
	public void m1() throws InterruptedException{
		
		lock.lock();
		System.out.println(" Waiting .. m1()");
		cond.await();
		System.out.println("Resumed .... m1()");
		
		try{
			increment();
		}finally{
			lock.unlock();
		}		
	}
	
	public void m2() throws InterruptedException{
		
		Thread.sleep(2000);
		lock.lock();
		System.out.println("Started m2()...");		
		cond.signal();
		
		try{
			increment();
		}finally{
			lock.unlock();
		}		
	}
	
	public void finished(){
		System.out.println(" Value of count : "+count);
	}
}
