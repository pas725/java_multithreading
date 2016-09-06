package udemy.twelve;

import java.util.concurrent.Semaphore;

/*
 * Singleton Class
 * */

public class Connection {

	private static Connection connection = new Connection();
	
	// Limiting connections to 10.
	// Only 10 threads at a time can execute connect() method 
	Semaphore semaphore = new Semaphore(10);
	
	int connections = 0;
	
	private Connection(){
		
		
		
	}
	
	public void connect(){
		try {
			semaphore.acquire();
						
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			doConnect();
		}finally{
			semaphore.release();
		}
	}
	
	public void doConnect(){
		
		synchronized (this) {
			connections++;
		}
		
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		synchronized (this) {
			connections--;
		}
		
		System.out.println(" Total connection :"+ connections);
	}
	
	public static Connection getInstance(){
		return connection;
	}
	
}
