package udemy.thirteen;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/*
 * 
 * 
 * */
public class CallableNFuture {

	public static void main(String[] args) {
		
		ExecutorService service = Executors.newCachedThreadPool();
		int [] arr = new int[10];
		
		for(int i=0;i<10;i++){
			
			Future<Integer> future = service.submit(new Callable<Integer>() {
				
				public Integer call() throws Exception {
					
					Thread.sleep(700);
					return 23;
				}
								
			});
			
			try {
				
				System.out.println("  Value is : "+ future.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		service.shutdown();
		
		try {
			service.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
