package it.polimi.ingsw.ps19.view;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeoutException;

import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.exceptions.LocalLogger;
import it.polimi.ingsw.ps19.exceptions.viewexceptions.ReaderException;
import it.polimi.ingsw.ps19.exceptions.viewexceptions.WriterException;
import it.polimi.ingsw.ps19.message.Message;
import it.polimi.ingsw.ps19.message.replies.EndGameReply;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.message.replies.StringMessage;
import it.polimi.ingsw.ps19.message.requests.PlayerDisconnectedMessage;
import it.polimi.ingsw.ps19.message.requests.SendFullGameMessage;
import it.polimi.ingsw.ps19.model.parameter.Costants;
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
	protected static final LocalLogger log = new LocalLogger("ServerView");
	
	private List<Integer> playerConnection;
	private int activeId;
	private boolean stop = false;
	boolean firstRun = true;
	
	/**
	 * Constructor
	 * @param conns: HashMap to associate players ids and connections
	 */
	public View(List<Integer> conns) 
	{
		playerConnection = conns;
		activeId = conns.get(0);
	}
	
	/**
	 * Set Active Player
	 */
	private void setActive(int n) 
	{
		if(playerConnection.contains(n))
		{
			for (Integer playerIndex : playerConnection)
			{
				if(playerIndex == n)
				{
					WaitingRoom.getConnection(n).setActive();
					activeId = n;
				}
				else if(WaitingRoom.getConnection(n).getStatus() != ConnectionStatus.DISCONNECTED) 
					WaitingRoom.getConnection(n).setInactive();
			}
		}
		else return;
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		if(!(arg instanceof Reply))
			return;
		else if(arg instanceof EndGameReply)
		{
			forwardMessage((Reply)arg);
			stop();
		}
		else
		{
			setActive(((Reply)arg).getActivePlayer());
			Reply mex = (Reply) arg;
			forwardMessage(mex);
		}
	}

	@Override
	public void run() 
	{
		setChanged();
		notifyObservers(new SendFullGameMessage(Costants.BROADCAST_MESSAGE));
		while(!stop)
		{
			Connection activeConn = WaitingRoom.getConnection(activeId);
			try 
			{
				Message recMex = activeConn.read(Constants.getPlayerTimeout());
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
			catch (TimeoutException | InterruptedException | ReaderException e) 
			{
				log.log(e);
				activeConn.setDisconnected();
				ServerManager.serverCLI.showNotification(Language.playerDisconnected(activeId));
				setChanged();
				notifyObservers(new PlayerDisconnectedMessage(activeId));
			}
		}
		ServerManager.serverCLI.showNotification("game has stopped");
	}
	
	private void stop()
	{
		stop = true;
		WaitingRoom.removeMany(playerConnection);
	}
	
	/**
	 * Forwards message to right player
	 * @param mex: message to be sent
	 */
	public void forwardMessage(Reply mex)
	{
		Integer id = mex.getId();
		for (Integer player : playerConnection) 
		{
			try 
			{
				if(WaitingRoom.getConnection(player).getStatus() != ConnectionStatus.DISCONNECTED
						&& (id == player || id < 0))
					WaitingRoom.getConnection(player).write(mex);
			} catch (WriterException e) 
			{
				log.log(e);
				WaitingRoom.getConnection(player).setDisconnected();
				ServerManager.serverCLI.showNotification(Language.playerDisconnected(player));
				setChanged();
				notifyObservers(new PlayerDisconnectedMessage(player));
			}
		}
	}
}
