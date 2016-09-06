package udemy.one;

public class ImplementThread {

	public static void main(String[] args) {
		
		Thread t1 = new Thread(new App1());
		
		t1.start();
	}
}

class App1 implements Runnable{
	
	public void run() {
		
		for(int i=0;i<8;i++){
			
			System.out.println("  : "+i);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}