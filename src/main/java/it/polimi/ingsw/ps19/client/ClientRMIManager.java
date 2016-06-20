package it.polimi.ingsw.ps19.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;

import it.polimi.ingsw.ps19.server.Constants;
import it.polimi.ingsw.ps19.server.ServerRemoteIntf;
import it.polimi.ingsw.ps19.view.connection.RMIConnection;
import it.polimi.ingsw.ps19.view.connection.RMIReaderIntf;

/**
 * Manages initial connection to the server with RMI
 */
public class ClientRMIManager extends ClientManager
{
	/**
	 * Constructor that actually executes the connection via RMI
	 * @param ui
	 */
	public ClientRMIManager(ClientUI ui)
	{
		userInterface = ui;
		int tries = 0;
		boolean success = false;
		String ip = ClientConstants.IP_ADDRESS;
		Integer port = Constants.RMI_PORT;
		connection = new RMIConnection();
		do
		{
			try
			{
				String name = Constants.RMI_SERVER_STUB_NAME;
				Registry registry = LocateRegistry.getRegistry(ip, port);
				ServerRemoteIntf server = (ServerRemoteIntf) registry.lookup(name);
				RMIReaderIntf writerStub = server.addNewPlayerToWR(((RMIConnection)connection).getReaderStub());
				((RMIConnection)connection).loadWriter(writerStub);
				success = true;
			}
			catch(RemoteException | NotBoundException e)
			{
				ClientLogger.log.log(Level.SEVERE, e.toString(), e);
				success = false;
				ip = getIP();
				port = getPort(Constants.RMI_PORT);
			}
			finally
			{
				tries++;
			}
		}while(!success && tries < ClientConstants.MAX_CONN_TRIES);
		if(!success)
		{
			userInterface.showNotification("Connection Unsuccessful, the program will now close");
			System.exit(0);
		}
		startClient();
	}
}
