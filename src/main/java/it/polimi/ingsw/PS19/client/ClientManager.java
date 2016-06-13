package it.polimi.ingsw.PS19.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.PS19.view.connection.Connection;
import it.polimi.ingsw.PS19.view.connection.SocketConnection;



/**
 * @author Andrea
 *
 */
public class ClientManager 
{
	private static final Logger log = Logger.getLogger("CLIENT_LOGGER");
	private static ExecutorService executorService = Executors.newFixedThreadPool(2);	
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private static ClientUI userInterface;
		private ClientManager(){}
	
	/**
	 * Client main
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException 
	{
		int tries = 0;
		Connection connection = null;
		Thread t;
		boolean success = false;
		
		userInterface = new ClientCLI();
		//Gets IP and port from user
		String ip;
		Integer port;
		try {
			ip = getIP();
			port = getPort();
		} catch (IOException e) 
		{
			log.log(Level.SEVERE, e.toString(), e);
			ip = ClientConstants.IP_ADDRESS;
			port = ClientConstants.REMOTE_PORT;
		}

		//Prova a connettersi
		do
		{
			//Start thread to write string while connecting
			t = new WaitingWriterThread("Trying to connect..", userInterface);
			t.start();
			
			//Tries to connect: First time with player input, after with standard input
			try
			{	
				Socket socket = new Socket(Inet4Address.getByName(ip), port);
				userInterface.showNotification("Socket created");
				connection = new SocketConnection(socket, executorService);
				success = true;
				t.interrupt();
				userInterface.showNotification("Connection successful");
			} catch (IOException e) 
			{
				success = false;
				t.interrupt();
				if(tries == 0)
					userInterface.showNotification("Connection Unsuccessful. Trying again with standard IP (" + ClientConstants.IP_ADDRESS + ") and port (" + ClientConstants.REMOTE_PORT + ")");
				else 
					userInterface.showNotification("Connection Unsuccessful");
				try {
					Thread.sleep(10000);
				}
				catch (InterruptedException e1) 
				{
					log.log(Level.SEVERE, e.toString(), e);
					throw e1; 
				}
			} 
			finally
			{
				tries++;
			}
		}while(!success && tries < ClientConstants.MAX_CONN_TRIES);
		if(!success)
		{
			userInterface.showNotification("Connection Unsuccessful, the program will now close");
			System.exit(0);
		}

		ClientView view = new ClientView(connection);
		ClientInterpreter interpreter = new ClientInterpreter(userInterface);
		view.addObserver(interpreter);
		interpreter.addObserver(view);
		new Thread(view).start();
	}
	
	private static String getIP() throws IOException
	{
		userInterface.showNotification("Insert server IP Address: ");
		String ip = in.readLine();
		try {
			if(ip.length() == 0)
				ip = ClientConstants.IP_ADDRESS;
			Inet4Address.getByName(ip);
		} catch (UnknownHostException e) 
		{
			log.log(Level.SEVERE, e.toString(), e);
			userInterface.showNotification("IP Address not valid, using defaul IP: " + ClientConstants.IP_ADDRESS);
			ip = ClientConstants.IP_ADDRESS;
		}
		return ip;
	}
	
	private static Integer getPort() throws IOException
	{
		Integer port = null;
		userInterface.showNotification("Insert server port: ");
		String portString = in.readLine();
		try {
			port = Integer.parseUnsignedInt(portString);
			if(port < 0 || port > 65535) 
				throw new NumberFormatException();
		} catch (NumberFormatException e) {
			userInterface.showNotification("Port not valid, using defaul Port: " + ClientConstants.REMOTE_PORT);
			port = ClientConstants.REMOTE_PORT;
		}
		return port;
	}
}
