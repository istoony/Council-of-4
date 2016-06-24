package it.polimi.ingsw.ps19.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;

import it.polimi.ingsw.ps19.exceptions.viewexceptions.ReaderException;
import it.polimi.ingsw.ps19.message.replies.ConnectionReply;
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
	public ClientRMIManager(ClientUI ui, boolean newGame, int key)
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
				RMIReaderIntf writerStub = server.addNewPlayerToWR(((RMIConnection)connection).getReaderStub(), newGame, key);
				ConnectionReply reply = (ConnectionReply) connection.read(30);
				playerId = reply.getPassword();
				if(reply.getSuccessful())
					userInterface.showNotification("You have reconnected to previous game");
				else
					userInterface.showNotification("New Game! your password to regain access is: " + reply.getPassword());
				((RMIConnection)connection).loadWriter(writerStub);
				success = true;
			}
			catch(RemoteException | NotBoundException | TimeoutException | InterruptedException | ReaderException e)
			{
				ClientLogger.log.log(Level.OFF, e.toString(), e);
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
