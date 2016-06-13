/*
 * @Author Andrea Milanta
 */
package it.polimi.ingsw.PS19.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.PS19.client.ClientCLI;

import java.io.IOException;

/**
 * Main class of the server
 * manages socket connections.
 */
public class ServerManager 
{
	protected static final Logger log = Logger.getLogger("SERVER_LOGGER");
	private static ServerSocket serverSocket;	
	private static boolean stop = false;
	public static final ClientCLI serverCLI =  new ClientCLI();
	
	private ServerManager(){}
	
	/**
	 * Main del server
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Thread t = new StopperThread();
		t.start();
	
		try
		{
			serverSocket = new ServerSocket(Constants.PORT);
			ServerManager.serverCLI.showNotification("The server socket creation has been successful");
		}
		catch(IOException e)
		{
			ServerManager.serverCLI.showNotification("Something went wrong in creating a serversocket");
			log.log(Level.SEVERE, e.toString(), e);
		}
		WaitingRoom.startTimer();
		while(!stop)
		{
			try
			{
				Socket clientSocket = serverSocket.accept();
				ServerManager.serverCLI.showNotification(LocalDateTime.now() + " New client Connected");
				WaitingRoom.addConnection(clientSocket);
			}
			catch(IOException e)
			{
				log.log(Level.SEVERE, e.toString(), e);
			}
		}
		WaitingRoom.quit();
	}
	
	/**
	 * stops the server
	 */
	public static void stop()
	{
		stop = true;
	}
}
