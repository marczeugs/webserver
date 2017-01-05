import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WebServer {
	private ServerSocket server;
	private boolean running = true;
	private Thread serverThread;
	private int port;
	public Map<String, HTMLElement> pages;

	public WebServer(int port) {
		this.pages = new HashMap<String, HTMLElement>();
		
		this.port = port;
	}
	
	public void start() throws IOException {
		this.server = new ServerSocket(port);
		Logger.log("Server was started on 127.0.0.1:" + this.port + ".");
		
		this.serverThread = new Thread(new Runnable() {
			public void run() {
				while(running) {
					try {
						Logger.log("Server thread: Awaiting connection...");
						
						Socket client = server.accept();
						Logger.log("Server thread: Got connection from " + client.getInetAddress() + ":" + client.getPort());
						
						@SuppressWarnings("resource")
						Scanner s = new Scanner(client.getInputStream());
						s.useDelimiter("\r\n\r\n");
						String request = s.next();
						String index = request.split(" ")[1];
						Logger.log("Server thread: Request: \r\n" + request);
						
						
						byte[] response;
						if(pages.get(index) != null) response = ("HTTP/1.1 200 OK\r\n\r\n" + pages.get(index)).getBytes("UTF-8");
						else response = ("HTTP/1.1 404 Not Found").getBytes("UTF-8");
						client.getOutputStream().write(response);
						Logger.log("Server thread: Sent response: \r\n" + new String(response, "UTF-8"));
						
						client.close();
						Logger.log("Server thread: Closed connection.");
					} catch(Exception e) {
						Logger.err("Server thread: An error has ocurred during the execution of the server thread. Stack trace: ");
						e.printStackTrace(System.err);
						Logger.err("Server thread: The server will close now.");
						stop();
					}
				}
			}
		});
		this.serverThread.setDaemon(true);
		this.serverThread.run();
	}
	
	public void stop() {
		try {
			Logger.log("Shutting down server...");
			this.running = false;
    		this.server.close();
    		this.serverThread.join();
    		Logger.log("Server shut down successfully.");
    	} catch(Exception e) {
    		Logger.err("An error has ocurred the shutdown of the server. Stack trace: ");
    		e.printStackTrace(System.err);
    	}
	}
	
	public void addPage(String index, HTMLElement page) {
		this.pages.put(index, page);
	}

	public void removePage(String index) {
		this.pages.remove(index);
	}

}