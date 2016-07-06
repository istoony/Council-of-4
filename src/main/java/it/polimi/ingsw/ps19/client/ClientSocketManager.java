package it.polimi.ingsw.ps19.client;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;

import it.polimi.ingsw.ps19.exceptions.viewexceptions.WriterException;
import it.polimi.ingsw.ps19.message.requests.ConnectionMessage;
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
	 * @param newGame: request for a new game or rconnection to a prevoius game
	 * @param key: password to use to recconect to an old game
	 * @throws InterruptedException 
	 */
	public static void connect(ClientUI ui, boolean newGame, int key) 
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
			t = new WaitingWriterThread(userInterface.getLanguage().getWaiting(), userInterface);
			
			//Tries to connect: First time with player input, after with standard input
			try
			{	
				Socket socket = new Socket(Inet4Address.getByName(ip), port);
				userInterface.showNotification(userInterface.getLanguage().getSocketCreated());
				t.start();
				connection = new SocketConnection(socket, executorService);
				connection.write(new ConnectionMessage(newGame, key));
				success = true;
				t.interrupt();
				userInterface.showNotification(userInterface.getLanguage().getConnSuccess());
			} catch (IOException | WriterException e) 
			{
				ClientStarter.log.log(e);
				success = false;
				t.interrupt();
				userInterface.showNotification(userInterface.getLanguage().getConnInsucces());
				ip = getIP();
			} 
			finally
			{
				tries++;
			}
		}while(!success && tries < ClientConstants.MAX_CONN_TRIES);
		if(!success)
		{
			userInterface.showNotification(userInterface.getLanguage().getConnInsucces() + "!\n" + userInterface.getLanguage().getKillClient());
			System.exit(0);
		}
		startClient();
	}
	
	
	
}
