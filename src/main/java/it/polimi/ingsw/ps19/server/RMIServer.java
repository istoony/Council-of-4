package it.polimi.ingsw.ps19.server;

import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;

import it.polimi.ingsw.ps19.view.connection.Connection;
import it.polimi.ingsw.ps19.view.connection.RMIConnection;
import it.polimi.ingsw.ps19.view.connection.RMIReaderIntf;

public class RMIServer extends RemoteServer implements ServerRemoteIntf 
{
	@Override
	public RMIReaderIntf addNewPlayerToWR(RMIReaderIntf clientStub) throws RemoteException 
	{
		Connection connection = new RMIConnection(clientStub);
		RMIReaderIntf stub = ((RMIConnection)connection).getReaderStub();
		WaitingRoom.addConnection(connection);
		return stub;
	}

}
