package udemy.practice;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {

	ServerSocket socket;
	ExecutorService threadPool;
	int PORT = 8070;
	boolean isStopped = false;
	
	public WebServer() {
		// TODO Auto-generated constructor stub
		this.threadPool = Executors.newFixedThreadPool(10);
	}
	
	public static void main(String[] args) {
		
		WebServer server = new WebServer();
		
		server.openServerSocket();
		int i = 1;
		while( ! server.isStopped()){
			Socket clientSocket = null;
            try {
            	System.out.println("Waiting for connection... :"+ (i++));
                clientSocket = server.socket.accept();
            } catch (IOException e) {
                if(server.isStopped()) {
                    System.out.println("Server Stopped.") ;
                    return;
                }
                throw new RuntimeException(
                    "Error accepting client connection", e);
            }
            
            // Create thread & run it independently
            /*new Thread(
                new WorkerRunnable(
                    clientSocket, "Multithreaded Server")
            ).start();*/
            
            // Submit a job to thread pool
            server.threadPool.execute(new WorkerRunnable(clientSocket, "Multithreaded Server"));
		}
		
		server.threadPool.shutdown();
			
		
	}
	
	public void openServerSocket(){
		try {
			this.socket = new ServerSocket(PORT);
			System.out.println(" Server started on listening PORT : "+PORT);
		} catch (IOException e) {
			System.out.println(" Cannot start server on Port : "+PORT);
			e.printStackTrace();
		}
	}
	
	public boolean isStopped(){
		return this.isStopped;
	}
	public void stopServer(){
		
		this.isStopped = true;
		try {
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


class WorkerRunnable implements Runnable{

    protected Socket clientSocket = null;
    protected String serverText   = null;

    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
    }
    
	public void run() {
		try {
            InputStream input  = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);
            System.out.println("----- Rcvd : "+input.toString());
            long time = System.currentTimeMillis();
            String str = "HTTP/1.1 200 OK\nCache-Control: private\n\nWorkerRunnable: " + this.serverText + " - " + time + "";
            byte buf[] = (str).getBytes();
            //output.write(buf);
            pw.print(str);
            //output.close();
            pw.close();
            input.close();
            clientSocket.close();
            System.out.println("Request processed: " + time);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}

