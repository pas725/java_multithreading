package udemy.eight;

import java.util.Scanner;

public class WaitnNotifyDemo {

	public static void main(String[] args) {

		WaitnNotifyDemo wd = new WaitnNotifyDemo();
		Thread p = new Thread(new Runnable() {
			
			public void run() {
				try {
					wd.producer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread c = new Thread(new Runnable() {
			
			public void run() {
				try {
					wd.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		p.start();
		c.start();
	}
	
	public void producer() throws InterruptedException{
		
		synchronized (this) {
			System.out.println("Producer running...");
			wait();
			System.out.println("Producer resumed.");
		}
	}

	public void consumer() throws InterruptedException{
		
		Thread.sleep(2000);
		Scanner scanner = new Scanner(System.in);
		
		synchronized (this) {
			
			System.out.println("In consumer : press Enter .... ");
			scanner.nextLine();
			
			notify();
			
		}
	}
}
