package it.polimi.ingsw.ps19.view.connection;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.security.SecureRandom;
import java.util.concurrent.LinkedBlockingQueue;

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
	
	/**
	 * Initializes the RMI interface
	 * @param fifoQueue: fifo in which to write new messages
	 */
	public RMIReader(LinkedBlockingQueue<Message> fifoQueue)
	{
		fifo = fifoQueue;
		try 
		{
			stub = (RMIReaderIntf) UnicastRemoteObject.exportObject(this, 0);
			name = new SecureRandom().toString();
			try
			{
				System.out.println("Creating new register on: localHost:" + Constants.RMI_PORT);
				registry = LocateRegistry.createRegistry(Constants.RMI_PORT);
				System.out.println("new register created on: localHost:" + Constants.RMI_PORT);
			}catch(ExportException e)
			{				
				System.out.println("Register on: localHost:" + Constants.RMI_PORT + " already exists");
				ConnectionLogger.log.log(e);
				registry = LocateRegistry.getRegistry(Constants.RMI_PORT);
				System.out.println("Openede register on: localHost:" + Constants.RMI_PORT);
			}
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
	
	/**
	 * stops reading, hence closes connection
	 */
	public void stop()
	{
		stop = true;
	}
}
