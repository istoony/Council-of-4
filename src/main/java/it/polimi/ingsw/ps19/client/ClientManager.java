package it.polimi.ingsw.ps19.client;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.server.Constants;
import it.polimi.ingsw.ps19.view.connection.Connection;

public abstract class ClientManager 
{
	protected static ExecutorService executorService = Executors.newFixedThreadPool(2);	
	protected static Connection connection;
	protected static ClientUI userInterface;
	
	protected void startClient()
	{
		ClientView view = new ClientView(connection);
		ClientInterpreter interpreter = new ClientInterpreter(userInterface);
		view.addObserver(interpreter);
		interpreter.addObserver(view);
		new Thread(view).start();
	}
	
	protected static String getIP()
	{
		String ip = null;
		try 
		{
			ip = userInterface.getUserString("Insert server IP Address: ");
			if(ip.isEmpty())
				throw new UnknownHostException();
			Inet4Address.getByName(ip);
		} catch (UnknownHostException | InvalidInsertionException e) 
		{
			ip = ClientConstants.IP_ADDRESS;
			ClientLogger.log.log(Level.SEVERE, e.toString(), e);
			userInterface.showNotification("Invalid Insertion, using standard IP address: " + ClientConstants.IP_ADDRESS);
		}
		return ip;
	}
	
	protected static Integer getPort(Integer standardPort)
	{
		Integer port = null;
		try 
		{
			String portString = userInterface.getUserString("Insert server Port: ");
			if(portString.isEmpty())
				throw new NumberFormatException();
			port = Integer.parseUnsignedInt(portString);
			if(port < 0 || port > 65535) 
				throw new NumberFormatException();
		} catch (NumberFormatException | InvalidInsertionException e) 
		{
			port = standardPort;
			ClientLogger.log.log(Level.SEVERE, e.toString(), e);
			userInterface.showNotification("Invalid Insertion, using standard port: " + standardPort);
		}
		return port;
	}
}
