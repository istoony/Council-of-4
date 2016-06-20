package it.polimi.ingsw.PS19.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.PS19.view.connection.RMIReaderIntf;

/**
 * Public interface for RMI on server manager
 */
@FunctionalInterface
public interface ServerRemoteIntf extends Remote
{
	/**
	 * Adds new player to waiting Room
	 */
	RMIReaderIntf addNewPlayerToWR(RMIReaderIntf clientStub) throws RemoteException;
}
