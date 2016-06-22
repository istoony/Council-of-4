package it.polimi.ingsw.ps19.view;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.ps19.exceptions.viewexceptions.WriterException;
import it.polimi.ingsw.ps19.message.Message;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.message.replies.StringMessage;
import it.polimi.ingsw.ps19.message.requests.PlayerDisconnectedMessage;
import it.polimi.ingsw.ps19.message.requests.SendFullGameMessage;
import it.polimi.ingsw.ps19.server.Constants;
import it.polimi.ingsw.ps19.server.ServerManager;
import it.polimi.ingsw.ps19.server.WaitingRoom;
import it.polimi.ingsw.ps19.view.connection.Connection;
import it.polimi.ingsw.ps19.view.connection.ConnectionStatus;

/**
 * Class that manages the connections towards the internet
 */
public class View extends Observable implements Observer, Runnable
{
	protected static final Logger log = Logger.getLogger("SERVER_LOGGER");
	
	private List<Integer> playerConnection;
	private int activeId = 0;
	private boolean stop = false;
	boolean firstRun = true;
	
	/**
	 * Constructor
	 * @param conns: HashMap to associate players ids and connections
	 */
	public View(List<Integer> conns) 
	{
		playerConnection = conns;
	}
	
	/**
	 * Set Active Player
	 */
	private void setActive(int n) 
	{
		if(n < playerConnection.size() && n >= 0)
		{
			if(WaitingRoom.getConnection(playerConnection.get(n)).getStatus() == ConnectionStatus.DISCONNECTED)
			{
				setChanged();
				notifyObservers(new PlayerDisconnectedMessage(n));
				return;
			}
			for (Integer playerIndex : playerConnection)
			{
				if(playerIndex == n)
				{
					WaitingRoom.getConnection(playerConnection.get(n)).setActive();
					activeId = n;
				}
				else if(WaitingRoom.getConnection(playerConnection.get(n)).getStatus() != ConnectionStatus.DISCONNECTED) 
					WaitingRoom.getConnection(playerConnection.get(n)).setInactive();
			}
		}
		else return;
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		if(!(arg instanceof Reply))
			return;
		setActive(((Reply)arg).getActivePlayer());
		//TODO togli i println
		System.out.println("ID: " + ((Reply) arg).getActivePlayer());
		System.out.println("RESULT: " + ((Reply) arg).getResult());
		Reply mex = (Reply) arg;
		forwardMessage(mex);
	}

	@Override
	public void run() 
	{
		setChanged();
		notifyObservers(new SendFullGameMessage(-1));
		while(!stop)
		{
			Connection activeConn = WaitingRoom.getConnection(activeId);
			try 
			{
				Message recMex = activeConn.read(Constants.PLAYER_TIMEOUT_TIME_S);
				if(recMex instanceof StringMessage)
				{
					ServerManager.serverCLI.showNotification(recMex.toString());
				}
				else
				{
					setChanged();
					notifyObservers(recMex);
				}
			} 
			//Timeout error => Player set disconnected
			catch (TimeoutException | InterruptedException e) 
			{
				log.log(Level.SEVERE, e.toString(), e);
				activeConn.setDisconnected();
				setChanged();
				notifyObservers(new PlayerDisconnectedMessage(activeId));
			}
		}
	}
	
	
	/**
	 * Forwards message to right player
	 * @param mex: message to be sent
	 */
	public void forwardMessage(Reply mex)
	{
		Integer id = mex.getId();
		// Broadcast
		if(id < 0)
		{
			for (Integer player : playerConnection) 
			{
				try {
					WaitingRoom.getConnection(player).write(mex);
				} catch (WriterException e) 
				{
					log.log(Level.SEVERE, e.toString(), e);
					setChanged();
					notifyObservers(new PlayerDisconnectedMessage(player));
				}
			}
		}
		
		// To specific client
		else
		{
			try {
				WaitingRoom.getConnection(playerConnection.get(id)).write(mex);
			} catch (WriterException e) 
			{
				log.log(Level.SEVERE, e.toString(), e);
				setChanged();
				notifyObservers(new PlayerDisconnectedMessage(id));
			}		
		}
	}
}
