package udemy.eleven;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Dedlock {

	public static void main(String[] args) {
		
		Processor p = new Processor();
		
		Thread to = new Thread(new Runnable() {
			
			public void run() {
				try {
					p.t1();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread tt = new Thread(new Runnable() {
			
			public void run() {
				try {
					p.t2();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		to.start();
		tt.start();
		
		try {
			to.join();
			tt.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		p.finished();
		
	}
	
}

class Processor{
	
	Account acc1 = new Account(10000,"John");
	Account acc2 = new Account(10000,"Roman");
	
	Lock lock1 = new ReentrantLock();
	Lock lock2 = new ReentrantLock();
	
	void acquireLock(Lock l1, Lock l2) throws InterruptedException{		
		
		while(true){
			
			boolean firstLock = false, secondLock = false;
			try{
				
				firstLock = l1.tryLock();
				secondLock = l2.tryLock();
			}finally{
				
				if( firstLock && secondLock){
					return;
				}
				
				if(firstLock){
					l1.unlock();
				}
				
				if(secondLock){
					l2.unlock();
				}
			}
			
			Thread.sleep(5);
		}
	}
	
	public void finished(){
		System.out.println("Account 1 balance : "+acc1.balance());
		System.out.println("Account 2 balance : "+acc2.balance());
		System.out.println("Total balance : "+ (acc1.balance() + acc2.balance()) );
	}
	
	public void t1() throws InterruptedException{
				
			for(int i=0;i<1000;i++){
				//System.out.println(" ++ In t1");
				acquireLock(lock1, lock2);	
				
				try{	
					Account.transfer(acc1, acc2, new Random().nextInt(500));
				}finally{
					lock1.unlock();
					lock2.unlock();
				}
				//Thread.sleep(5);
			}
		
	}
	
	public void t2() throws InterruptedException{
		
		Thread.sleep(1000);
		for(int i=0;i<1000;i++){
			//System.out.println(" In t2");
			acquireLock(lock1, lock2);
			
			//lock2.lock(); // Causes deadlock , coz T1 has locked lock1 & t2 has locked lock2
			//lock1.lock();
			
			try{	
				Account.transfer(acc2, acc1, new Random().nextInt(500));
			}finally{
				lock1.unlock();
				lock2.unlock();
			}
			//Thread.sleep(2);
		}
	}
}
