package udemy.five;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

	
	public static void main(String[] args) {
		
		ExecutorService service = Executors.newFixedThreadPool(2);
		
		for(int i=0 ; i<5 ;i++){
			
			service.submit(new Processor(i));
		}
		
		System.out.println(" Submitted all tasks.");
		
		service.shutdown();
		try {
			service.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(" Completed all tasks.");
	}
}


class Processor implements Runnable{
	
	int id;
	
	public Processor(int id) {
		// TODO Auto-generated constructor stub
		this.id = id;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Started : "+id);
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Completed : "+id);
	}
}
