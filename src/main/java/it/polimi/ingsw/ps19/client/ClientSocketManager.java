package it.polimi.ingsw.ps19.client;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.logging.Level;

import it.polimi.ingsw.ps19.server.Constants;
import it.polimi.ingsw.ps19.view.connection.SocketConnection;



/**
 * @author Andrea
 *
 */
public class ClientSocketManager extends ClientManager
{	
	/**
	 * Constructor
	 * @param ui = userInterface
	 * @throws InterruptedException 
	 */
	public ClientSocketManager(ClientUI ui) 
	{
		userInterface = ui;
		int tries = 0;
		Thread t;
		boolean success = false;
		String ip = ClientConstants.IP_ADDRESS;
		Integer port = Constants.SOCKET_PORT;

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
				ClientLogger.log.log(Level.SEVERE, e.toString(), e);
				success = false;
				t.interrupt();
				userInterface.showNotification("Connection Unsuccessful");
				ip = getIP();
				port = getPort(Constants.SOCKET_PORT);
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
		startClient();
	}
	
	
	
}
