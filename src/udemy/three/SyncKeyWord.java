package udemy.three;

public class SyncKeyWord {

	public  int count = 0;
	
	/*
	 * Only one thread per Object can acquire lock on this method at a time.
	 * */
	public synchronized void increment(){
		count++;
	}

	public static void main(String[] args) {
		
		SyncKeyWord skw = new SyncKeyWord();
		
		SyncKeyWord skw1 = new SyncKeyWord();
		
		
		skw.doWork();
		//skw1.doWork();
	}
	
	public void doWork(){
		
		Thread t1 = new Thread(new Runnable() {
						
			public void run() {
			
				for(int i=0 ; i<10000 ; i++){
					increment();
					
					/*try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			
			public void run() {
			
				for(int i=0 ; i<10000 ; i++){
					increment();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			
			// Waits for t1 & t2 threads to die
			//System.out.println(" 1 In betn.........");
			t1.join();
			//
			t2.join();
			//System.out.println(" 3 In betn.........");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(" Count is : "+count);
	}
}


class App{
	
}