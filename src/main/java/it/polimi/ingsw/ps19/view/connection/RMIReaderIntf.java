package it.polimi.ingsw.ps19.view.connection;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.ps19.message.Message;

/**
 * Remote interface to create the method of the reader to be remotely called
 */
@FunctionalInterface
public interface RMIReaderIntf extends Remote 
{
	/**
	 * Message to set a new message to be read
	 * @param message
	 * @throws RemoteException
	 */
	public void setNewMessage(Message message) throws RemoteException;
}
