package it.polimi.ingsw.ps19.view.connection;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;
import java.security.SecureRandom;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;

import it.polimi.ingsw.ps19.message.Message;
import it.polimi.ingsw.ps19.server.Constants;
import it.polimi.ingsw.ps19.server.Mutex;

/**
 * Class that implements a reader in a RMI interface
 */
public class RMIReader implements RMIReaderIntf, Runnable
{
	Message message = null;
	Mutex mux = new Mutex();
	RMIReaderIntf stub;
	LinkedBlockingQueue<Message> fifo;
	
	/**
	 * Initializes the RMI interface
	 * @param fifoQueue: fifo in which to write new messages
	 */
	public RMIReader(LinkedBlockingQueue<Message> fifoQueue)
	{
		fifo = fifoQueue;
		try 
		{
			/*
			if(System.getSecurityManager() == null)
				System.setSecurityManager(new SecurityManager());
			*/
			//RMIReaderIntf rmiReader = this;
			stub = (RMIReaderIntf) UnicastRemoteObject.exportObject(this, 0);
			String name = new SecureRandom().toString();
			Registry registry;
			try
			{
				registry = LocateRegistry.createRegistry(Constants.RMI_PORT);
			}catch(ExportException e)
			{
				registry = LocateRegistry.getRegistry(Constants.RMI_PORT);
			}
			registry.rebind(name, stub);
		} 
		catch (RemoteException e) 
		{
			ConnectionLogger.log.log(Level.SEVERE, e.toString(), e);
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
		while(true);
	}
}
