package it.polimi.ingsw.ps19.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.TimeoutException;

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
	 * @param newGame
	 * @param key
	 * @param ui
	 */
	public ClientRMIManager(ClientUI ui, boolean newGame, int key)
	{
		userInterface = ui;
		int tries = 0;
		boolean success = false;
		String ip = ClientConstants.IP_ADDRESS;
		Integer port = Constants.RMI_PORT;
		connection = new RMIConnection(ui);
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
					userInterface.showNotification(userInterface.getLanguage().getReconnected());
				else
					userInterface.showNotification(userInterface.getLanguage().getConnPass() + ": " + reply.getPassword());
				((RMIConnection)connection).loadWriter(writerStub);
				success = true;
			}
			catch(RemoteException | NotBoundException | TimeoutException | InterruptedException | ReaderException e)
			{
				ClientStarter.log.log(e);
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
			userInterface.showNotification(userInterface.getLanguage().getConnInsucces() + "!\n" + userInterface.getLanguage().getKillClient());
			System.exit(0);
		}
		startClient();
	}
}
