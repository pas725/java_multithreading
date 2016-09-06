package udemy.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ThreadPool {

	private BlockingQueue<Runnable> taskQ = null;
	private List<PoolThread> threads = new ArrayList<PoolThread>();
	private boolean isStopped = false;
	
	public ThreadPool(int MAX_THREADS) {
	
		taskQ = new ArrayBlockingQueue<Runnable>(100);
		for(int i=0 ; i<MAX_THREADS ;i++){
			
			PoolThread pt = new PoolThread(taskQ);
			pt.setName("Worker_"+ (i+1) );
			threads.add(pt);
		}
		
		for(PoolThread t : threads){
			t.start();
	
		}
	}
	
	public synchronized void execute(Runnable task) throws Exception{
		
		if(this.isStopped){
			throw new Exception("Pool is stopped...");
		}
		this.taskQ.add(task);
	}
	
	public synchronized void stop() {
		
		this.isStopped = true;
		for(PoolThread t : threads){
			t.doStop();
		}
		System.out.println("Pool is stopped now.");
	}
}

class PoolThread  extends Thread{

	private BlockingQueue<Runnable> taskQ = null;
	private boolean isStopped = false;
	
	public PoolThread(BlockingQueue<Runnable> q) {

		this.taskQ = q;
	}
	
	public void run() {

		while( !isStopped()){
			
			Runnable r;
			try {
				System.out.println("*** Worker : "+this.getName()+ ", is waiting for task");
				r = taskQ.take();
				r.run();
				System.out.println("*** Worker : "+this.getName()+ ", completed the task");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void doStop(){
		this.isStopped = true;
		this.interrupt(); //break pool thread out of dequeue() call.
	}
	
	public synchronized boolean isStopped(){
        return isStopped;
    }
	
}
