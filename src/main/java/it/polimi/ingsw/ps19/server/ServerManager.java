/*
 * @Author Andrea Milanta
 */
package it.polimi.ingsw.ps19.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;

import it.polimi.ingsw.ps19.client.ClientCLI;
import it.polimi.ingsw.ps19.client.language.English;
import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.exceptions.LocalLogger;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.view.connection.SocketConnection;

import java.io.IOException;

/**
 * Main class of the server
 * manages socket connections.
 */
public class ServerManager
{
	protected static LocalLogger log = new LocalLogger("ServerLogger");
	private static ServerSocket serverSocket;	
	private static boolean stop = false;
	private static Registry registry;
	private static ServerRemoteIntf rmiServer;
	public static final ClientCLI serverCLI =  new ClientCLI(new English());
	
	private ServerManager(){}
	
	/**
	 * Main del server
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Thread stopperThread = new StopperThread();
		serverCLI.showNotification(Language.START);
		//RMI Init
		try 
		{
			rmiServer = new RMIServer();
			ServerRemoteIntf stub = (ServerRemoteIntf) UnicastRemoteObject.exportObject(rmiServer, 0);
			String name = Constants.RMI_SERVER_STUB_NAME;
			rmiConnect();			
			registry.rebind(name, stub);
			ServerManager.serverCLI.showNotification(Language.RMI_SUCCESS);
		} 
		catch (RemoteException e) 
		{
			ServerManager.serverCLI.showNotification(Language.RMI_INSUCCESS);
			stop = true;
			log.log(e);
		}
		
		//Socket Init
		try
		{
			serverSocket = new ServerSocket(Constants.SOCKET_PORT);
			ServerManager.serverCLI.showNotification(Language.SOCKET_SUCCESS);
		}
		catch(IOException e)
		{
			ServerManager.serverCLI.showNotification(Language.SOCKET_INSUCCESS);
			stop = true;
			log.log(e);
		}
		
		if(!stop)
		{
			Integer maxPlayers = getValue(Language.SET_MAX_P, 2);
			long playerTimeout = getValue(Language.SET_MAX_P_TO, 10);
			Constants.setMaxPlayers(maxPlayers);
			Constants.setPlayerTimeout(playerTimeout);
			WaitingRoom.startTimer();
			stopperThread.start();
		}
		else
		{
			ServerManager.serverCLI.showNotification("Another server is already running!");
		}
		
		while(!stop)
		{
			try
			{
				Socket clientSocket = serverSocket.accept();
				ServerManager.serverCLI.showNotification(LocalDateTime.now() + " - " + Language.NEW_CLIENT_CONN);
				WaitingRoom.addConnection(new SocketConnection(clientSocket));
			}
			catch(IOException | NullPointerException e)
			{
				log.log(e);
			}
		}
		
		try 
		{
			WaitingRoom.quit();
			if(!serverSocket.isClosed())
				serverSocket.close();
			UnicastRemoteObject.unexportObject(rmiServer, true);
		} catch (IOException | NullPointerException e) 
		{
			log.log(e);
		}
		serverCLI.showNotification(Language.SERVER_QUIT);
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
			log.log(e);
		}
	}
	
	private static int getValue(String title, int minValue)
	{
		int value = 0;
		boolean valid;
		do
		{
			try
			{
				value = serverCLI.getInt(title);
				valid = true;
				if(value < minValue)
					valid = false;
			}catch(InvalidInsertionException e)
			{
				log.log(e);
				valid = false;
			}
		}while(!valid);
		return value;
	}
	
	private static void rmiConnect() throws RemoteException
	{
		try
		{
			registry = LocateRegistry.createRegistry(Constants.RMI_PORT);
			serverCLI.showNotification(Language.REG_CREATED + Constants.RMI_PORT);
		}catch(RemoteException e)
		{
			log.log(e);
			registry = LocateRegistry.getRegistry(Constants.RMI_PORT);
			serverCLI.showNotification(Language.REG_ACCESSED + Constants.RMI_PORT);
		}
	}
}
