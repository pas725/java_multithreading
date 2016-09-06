package udemy.fourteen;

public class Interrupt {

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println("Started");
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {

				for(int i=0;i<1E10;i++){
					
					//System.out.println(" --- Loop : "+i);
					
					// Method 1 : Check if thread is interrupted
					if(Thread.currentThread().isInterrupted()){
						System.out.println("Interrupted...");
						break;
					}
					
					// Method 2 : Check if thread is interrupted
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						//e.printStackTrace();
						System.out.println("Interrupted in catch...");
						break;
					}
				}
			}
		});
		
		t1.start();
		
		Thread.sleep(1000);
		t1.interrupt();
		
		t1.join();
		
		
		System.out.println("Finished");
	}
}
