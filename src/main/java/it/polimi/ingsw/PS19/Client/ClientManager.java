/**
 * 
 */
package it.polimi.ingsw.PS19.Client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.view.connection.Connection;
import it.polimi.ingsw.PS19.view.connection.SocketConnection;



/**
 * @author Andrea
 *
 */
public class ClientManager 
{
	private static Socket socket;
	private static ExecutorService executorService = Executors.newFixedThreadPool(2);	//Thread pool used by connections for writing;
	private static PrintWriter out; 
	
	public static void main(String[] args) 
	{
		int tries = 0;
		Connection conn = null;
		Thread t;
		boolean success = false;
		
		String ip = getIP();
		Integer port = getPort();
		do
		{
			t = new waitingWriterThread("Trying to connect..");
			t.start();
			try 
			{				
				socket = new Socket(Inet4Address.getByName(ip), port);
				conn = new SocketConnection(socket, executorService);
				success = true;
				t.interrupt();
				System.out.println("Connection successful");
			} catch (IOException e) 
			{
				success = false;
				t.interrupt();
				System.out.println("Connection Unsuccessful");
				try {Thread.sleep(10000);}
				catch (InterruptedException e1) {
						e1.printStackTrace();
				}
			} 
			finally
			{
				tries++;
			}
		}while(!success && tries < ClientConstants.MAX_CONN_TRIES);
		if(!success)
		{
			System.out.println("Connection Unsuccessful, the program will now close");
			System.exit(0);
		}
		while(true)
		{
			Future<Message> waitMex = conn.read();
			Message mex = waitMex.get();
			if(!(mex == null)) 
				{
					System.out.println("New Message with ID: " + mex.getID());
					System.out.println(mex.getString());
				}
		}
	}
	
	private static String getIP()
	{
		System.out.println("Insert server IP Address: ");
		String ip = System.console().readLine();
		try {
			Inet4Address.getByName(ip);
		} catch (UnknownHostException e) {
			System.out.println("IP Address not valid, using defaul IP: " + ClientConstants.IP_ADDRESS);
			ip = ClientConstants.IP_ADDRESS;
		}
		return ip;
	}
	
	private static Integer getPort()
	{
		Integer port = null;
		System.out.println("Insert server port: ");
		String portString = System.console().readLine();
		try {
			port = Integer.parseUnsignedInt(portString);
			if(port < 0 || port > 65535) throw new NumberFormatException();
		} catch (NumberFormatException e) {
			System.out.println("Port not valid, using defaul Port: " + ClientConstants.REMOTE_PORT);
			port = ClientConstants.REMOTE_PORT;
		}
		return port;
	}
}
