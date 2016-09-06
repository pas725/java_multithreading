package udemy.one;

public class ExtendThread {	
	
	public static void main(String[] args) {
		App app = new App();
		app.start();
	}
}


class App extends Thread{
		
	public void run() {
		
		for(int i=0;i<10;i++){
			
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
