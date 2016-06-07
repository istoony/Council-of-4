/**
 * 
 */
package it.polimi.ingsw.PS19.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) 
	{
		int tries = 0;
		Connection connection = null;
		Thread t;
		boolean success = false;
		
		//Gets IP and port from user
		String ip;
		Integer port;
		try {
			ip = getIP();
			port = getPort();
		} catch (IOException e) {
			ip = ClientConstants.IP_ADDRESS;
			port = ClientConstants.REMOTE_PORT;
		}

		//Prova a connettersi
		do
		{
			//Start thread to write string while connecting
			t = new waitingWriterThread("Trying to connect..");
			t.start();
			
			//Tries to connect: First time with player input, after with standard input
			try 
			{				
				socket = new Socket(Inet4Address.getByName(ip), port);
				System.out.println("Socket created");
				connection = new SocketConnection(socket, executorService);
				success = true;
				t.interrupt();
				System.out.println("Connection successful");
			} catch (IOException e) 
			{
				success = false;
				t.interrupt();
				if(tries == 0)
					System.out.println("Connection Unsuccessful. Trying again with standard IP (" + ClientConstants.IP_ADDRESS + ") and port (" + ClientConstants.REMOTE_PORT + ")");
				else 
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
		
		//Start new thread for the actual game;
		ClientView view = new ClientView(connection);
		ClientUI userInterface = new ClientCLI();
		ClientInterpreter interpreter = new ClientInterpreter(userInterface);
		view.addObserver(interpreter);
		interpreter.addObserver(view);

		/*/
		ClientInterpreter interpreter = new ClientInterpreter();
		view.addObserver(interpreter);
		ClientMessageCreator messageCreator = new ClientMessageCreator();
		ClientCLI..addObserver(view);
		//*/
		new Thread(view).start();
	}
	
	private static String getIP() throws IOException
	{
		System.out.println("Insert server IP Address: ");
		String ip = in.readLine();
		try {
			Inet4Address.getByName(ip);
		} catch (UnknownHostException e) {
			System.out.println("IP Address not valid, using defaul IP: " + ClientConstants.IP_ADDRESS);
			ip = ClientConstants.IP_ADDRESS;
		}
		return ip;
	}
	
	private static Integer getPort() throws IOException
	{
		Integer port = null;
		System.out.println("Insert server port: ");
		String portString = in.readLine();
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
