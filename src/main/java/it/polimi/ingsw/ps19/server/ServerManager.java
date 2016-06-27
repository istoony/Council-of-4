/*
 * @Author Andrea Milanta
 */
package it.polimi.ingsw.ps19.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.ps19.client.ClientCLI;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.view.connection.SocketConnection;

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
	private static Registry registry;
	private static ServerRemoteIntf rmiServer;
	public static final ClientCLI serverCLI =  new ClientCLI();
	
	private ServerManager(){}
	
	/**
	 * Main del server
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Thread stopperThread = new StopperThread();
		serverCLI.showNotification("started");
		boolean valid;
		Integer maxPlayers = null;
		do
		{
			try
			{
				maxPlayers = serverCLI.getInt("Max players per turn");
				valid = true;
				if(maxPlayers < 1)
					valid = false;
			}catch(InvalidInsertionException e)
			{
				log.log(Level.OFF, e.toString(), e);
				valid = false;
			}
		}while(!valid);
		Constants.setMaxPlayers(maxPlayers);
		//RMI Init
		try 
		{
			rmiServer = new RMIServer();
			ServerRemoteIntf stub = (ServerRemoteIntf) UnicastRemoteObject.exportObject(rmiServer, 0);
			String name = Constants.RMI_SERVER_STUB_NAME;
			try
			{
				registry = LocateRegistry.createRegistry(Constants.RMI_PORT);
				serverCLI.showNotification("New Registry created at: localhost:" + Constants.RMI_PORT);
			}catch(RemoteException e)
			{
				log.log(Level.OFF, e.toString(), e);
				registry = LocateRegistry.getRegistry(Constants.RMI_PORT);
				serverCLI.showNotification("Accessing Registry at: localhost:" + Constants.RMI_PORT);
			}
			registry.rebind(name, stub);
			ServerManager.serverCLI.showNotification("The RMI server creation has been successful");
		} 
		catch (RemoteException e) 
		{
			ServerManager.serverCLI.showNotification("Something went wrong in creating a RMI Server");
			log.log(Level.SEVERE, e.toString(), e);
		}
		
		//Socket Init
		try
		{
			serverSocket = new ServerSocket(Constants.SOCKET_PORT);
			ServerManager.serverCLI.showNotification("The server socket creation has been successful");
		}
		catch(IOException e)
		{
			ServerManager.serverCLI.showNotification("Something went wrong in creating a serversocket");
			log.log(Level.SEVERE, e.toString(), e);
		}
		
		//Waiting Room Init
		WaitingRoom.startTimer();
		stopperThread.start();
		while(!stop)
		{
			try
			{
				Socket clientSocket = serverSocket.accept();
				ServerManager.serverCLI.showNotification(LocalDateTime.now() + " New client Connected");
				WaitingRoom.addConnection(new SocketConnection(clientSocket));
			}
			catch(IOException e)
			{
				log.log(Level.SEVERE, e.toString(), e);
			}
		}
		WaitingRoom.quit();
		try {
			UnicastRemoteObject.unexportObject(rmiServer, true);
		} catch (NoSuchObjectException e) 
		{
			log.log(Level.OFF, e.toString(), e);
		}
		serverCLI.showNotification("server quits");
		System.exit(0);
	}
	
	/**
	 * stops the server
	 */
	public static void stop()
	{
		stop = true;
		try {
			serverSocket.close();
		} catch (IOException e) 
		{
			log.log(Level.SEVERE, e.toString(), e);
		}
	}
}
