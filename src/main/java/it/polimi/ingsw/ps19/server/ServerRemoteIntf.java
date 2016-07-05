package it.polimi.ingsw.ps19.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import it.polimi.ingsw.ps19.view.connection.RMIReaderIntf;

/**
 * Public interface for RMI on server manager
 */
@FunctionalInterface
public interface ServerRemoteIntf extends Remote
{
	/**
	 * @param clientStub: stub of client
	 * @param newGame: false if you want to reconnect to an old game
	 * @param key: key for reconnection
	 * @return stub to write to
	 * @throws RemoteException
	 */
	public RMIReaderIntf addNewPlayerToWR(RMIReaderIntf clientStub, boolean newGame, int key) throws RemoteException ;
}
