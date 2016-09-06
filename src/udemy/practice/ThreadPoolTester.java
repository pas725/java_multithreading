package udemy.practice;

public class ThreadPoolTester {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		
		ThreadPool pool = new ThreadPool(3);
		int NO_OF_TASK = 50;
		
		Thread.sleep(2000);
		for(int i=0; i <NO_OF_TASK ; i++){
			
			try {
				pool.execute(new Task(i));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

class Task implements Runnable{

	int id;
	public Task(int id) {
		this.id = id;
	}
	public void run() {

		System.out.println("Started task : "+this.id);
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Completed task : "+this.id);
	}
	
}

