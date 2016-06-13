package it.polimi.ingsw.PS19.view;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

<<<<<<< HEAD
import it.polimi.ingsw.PS19.exceptions.viewexceptions.WriterException;
import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.message.requests.NewTurnMessage;
import it.polimi.ingsw.PS19.message.requests.PlayerDisconnectedMessage;
import it.polimi.ingsw.PS19.message.requests.SendFullGameMessage;
import it.polimi.ingsw.PS19.view.connection.Connection;
import it.polimi.ingsw.PS19.view.connection.ConnectionStatus;
=======
import it.polimi.ingsw.ps19.exceptions.viewexceptions.WriterException;
import it.polimi.ingsw.ps19.message.Message;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.message.requests.NewTurnMessage;
import it.polimi.ingsw.ps19.message.requests.PlayerDisconnectedMessage;
import it.polimi.ingsw.ps19.message.requests.SendFullGameMessage;
import it.polimi.ingsw.ps19.view.connection.Connection;
import it.polimi.ingsw.ps19.view.connection.ConnectionStatus;
>>>>>>> branch 'master' of https://bitbucket.org/CoF_ps19/ps19.git

public class View extends Observable implements Observer, Runnable
{
	private Map<Integer, Connection> playerConnection;
	private int activeId = 0;
	private boolean stop = false;
	boolean firstRun = true;
	
	public View(Map<Integer, Connection> conns) 
	{
		playerConnection = conns;
	}
	
	/*
	 * Set Active Player
	 */
	private void setActive(int n) 
	{
		if(n < playerConnection.size() && n >= 0)
		{
			if(playerConnection.get(n).getStatus() == ConnectionStatus.DISCONNECTED)
			{
				setChanged();
				notifyObservers(new PlayerDisconnectedMessage(n));
				return;
			}
			for(int i = 0; i < playerConnection.size(); i++)
			{
				if(i == n)
				{
					playerConnection.get(n).setActive();
					activeId = n;
				}
				else
				{
					if(playerConnection.get(n).getStatus() != ConnectionStatus.DISCONNECTED) 
						playerConnection.get(n).setInactive();
				}
			}
		}
	/*	if(!firstRun)
		{
			Message m = new RequestActionMessage();
			m.setId(activeId);
			update(this, m);
			System.out.println("New Action requested to player " + activeId);
		} */
		else return;
	}

	private void setNextActive()
	{
		if(playerConnection.get(activeId + 1) != null)
			setActive(activeId + 1);
		else
			setActive(0);
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		//Checks whether the object passed is a message or not and if so gets the id;
		if(!(arg instanceof Reply))
			return;
		if(arg instanceof NewTurnMessage)
			setActive(((NewTurnMessage) arg).getActivePlayer());
		Reply mex = (Reply) arg;
		//Checks if message is to set new turn, and if so changes the active connection
		//If no action is required by the view the message is forwarded to the clients
		forwardMessage(mex);
	}

	/*
	 * "Main of the game"
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() 
	{
		setChanged();
		notifyObservers(new SendFullGameMessage(-1));
		while(!stop)
		{
			Connection activeConn = playerConnection.get(activeId);
			Future<Message> waitMex = activeConn.read();
			try 
			{
				//Message recMex = waitMex.get(Constants.PLAYER_TIMEOUT_TIME_s, TimeUnit.SECONDS);
				Message recMex = waitMex.get();
				/*
				if(recMex instanceof StringMessage)
				{
					System.out.println(recMex.toString());
				}
				else */ 
				{
					setChanged();
					notifyObservers(recMex);
				}
			} 
			//Timeout error => Player set disconnected
			/*
			catch (TimeoutException e) 
			{
				activeConn.setDisconnected();
				setNextActive();
				setChanged();
				notifyObservers(new PlayerDisconnectedMessage(activeId));
			}	
			//*///General Error. Exit
			catch (InterruptedException | ExecutionException e) 
			{
				e.printStackTrace();
				System.exit(0);
			}
		}
	}
	
	
	public void forwardMessage(Reply mex)
	{
		Integer id = mex.getId();
		
		// Broadcast
		if(id < 0)
		{
			for (Entry<Integer, Connection> player : playerConnection.entrySet()) 
			{
				try {
					player.getValue().write(mex);
				} catch (WriterException e) {
					setChanged();
					notifyObservers(new PlayerDisconnectedMessage(player.getKey()));
					e.printStackTrace();
				}
			}
		}
		
		// To specific client
		else
		{
			try {
				playerConnection.get(id).write(mex);
			} catch (WriterException e) {
				setChanged();
				notifyObservers(new PlayerDisconnectedMessage(id));
				e.printStackTrace();
			}		
		}
		
		/*
		//Verify writing success
		for (int i = 0; i < writeFeedback.size(); i++) 
		{
			try 
			{
				writeFeedback.get(i).get();
			} catch (InterruptedException | ExecutionException e) 
			{
				e.printStackTrace();
				playerConnection.get(i).setDisconnected();
				notifyObservers(new PlayerDisconnectedMessage(i));
			}
		}//*/
	}
}
