package it.polimi.ingsw.ps19.view.connection;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.security.SecureRandom;
import java.util.concurrent.LinkedBlockingQueue;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.message.Message;
import it.polimi.ingsw.ps19.server.Constants;

/**
 * Class that implements a reader in a RMI interface
 */
public class RMIReader implements RMIReaderIntf, Runnable
{
	private boolean stop;
	private RMIReaderIntf stub;
	private Registry registry;
	private String name;
	private LinkedBlockingQueue<Message> fifo;
	private ClientUI userInterface;
	
	/**
	 * Initializes the RMI interface
	 * @param fifoQueue: fifo in which to write new messages
	 * @param ui: userInterface to notify of proper connection
	 */
	public RMIReader(LinkedBlockingQueue<Message> fifoQueue, ClientUI ui)
	{
		fifo = fifoQueue;
		userInterface = ui;
		try 
		{
			stub = (RMIReaderIntf) UnicastRemoteObject.exportObject(this, 0);
			name = new SecureRandom().toString();
			rmiConnect();
			registry.rebind(name, stub);
		} 
		catch (RemoteException e) 
		{
			ConnectionLogger.log.log(e);
		}
	}
	
	public RMIReaderIntf getRMIReaderStub()
	{
		return stub;
	}

	@Override
	public void setNewMessage(Message m) throws RemoteException 
	{
		fifo.add(m);
	}

	@Override
	public void run() 
	{
		while(!stop);
		try 
		{
			registry.unbind(name);
			UnicastRemoteObject.unexportObject(stub, true);
		} catch (RemoteException | NotBoundException e) 
		{
			ConnectionLogger.log.log(e);
		}
	}
	
	private void rmiConnect() throws RemoteException
	{
		try
		{
			userInterface.showNotification("Creating new register on: localHost:" + Constants.RMI_PORT);
			registry = LocateRegistry.createRegistry(Constants.RMI_PORT);
			userInterface.showNotification("new register created on: localHost:" + Constants.RMI_PORT);
		}catch(ExportException e)
		{				
			userInterface.showNotification("Register on: localHost:" + Constants.RMI_PORT + " already exists");
			ConnectionLogger.log.log(e);
			registry = LocateRegistry.getRegistry(Constants.RMI_PORT);
			userInterface.showNotification("Openede register on: localHost:" + Constants.RMI_PORT);
		}
	}
	
	/**
	 * stops reading, hence closes connection
	 */
	public void stop()
	{
		stop = true;
	}
}
