package it.polimi.ingsw.ps19.server;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;

import it.polimi.ingsw.ps19.view.connection.RMIConnection;
import it.polimi.ingsw.ps19.view.connection.RMIReaderIntf;

/**
 * Implementation of server by RMI
 */
public class RMIServer extends RemoteServer implements ServerRemoteIntf 
{
	private static final long serialVersionUID = 1L;

	@Override
	public RMIReaderIntf addNewPlayerToWR(RMIReaderIntf clientStub, boolean newGame, int key) throws RemoteException 
	{
		RMIConnection connection = new RMIConnection(clientStub, ServerManager.serverCLI);
		RMIReaderIntf stub = connection.getReaderStub();
		WaitingRoom.addConnection(connection, newGame, key);
		return stub;
	}
}
