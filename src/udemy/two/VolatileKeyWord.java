package udemy.two;

import java.util.Scanner;


/*
 * Volatile use : It prevents threads caching the value of variable 
 * */

public class VolatileKeyWord {	
	
	public static void main(String[] args) {
		
		App app = new App();
		app.start();
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter code :");
		String str = scan.nextLine();
		
		
		app.shutDown();
		
		/*for( int i =0;i<1 ;i++){
			
				System.out.println("Enter code :");
				String str = scan.nextLine();
				
				System.out.println(" ++++++ "+str);
				
				if( str.equals("s")){
					app.startUp();
				}else if( str.equals("p")){
					app.shutDown();
				}
		}*/
	}
}

class App extends Thread{
	
	private volatile boolean flag = true;
	@Override
	public void run() {
		
		while(flag){
			
			System.out.println(" hello...");
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void startUp(){
		flag = true;
	}
	
	public void shutDown(){
		flag = false;
	}
}
