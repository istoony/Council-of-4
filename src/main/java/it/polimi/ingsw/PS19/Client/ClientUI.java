package it.polimi.ingsw.PS19.Client;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import it.polimi.ingsw.PS19.Client.clientAction.ClientAction;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

<<<<<<< Upstream, based on branch 'master' of https://bitbucket.org/CoF_ps19/ps19
<<<<<<< Upstream, based on branch 'master' of https://bitbucket.org/CoF_ps19/ps19
import it.polimi.ingsw.PS19.message.requests.GameStartedMessage;
import it.polimi.ingsw.PS19.message.requests.Request;
import it.polimi.ingsw.PS19.message.requests.NewTurnMessage;
import it.polimi.ingsw.PS19.message.requests.StringMessage;

public abstract class ClientUI extends Observable implements Observer
=======
=======
<<<<<<< Upstream, based on branch 'milanta' of https://bitbucket.org/CoF_ps19/ps19
>>>>>>> 0ccb890 Refactor Messages
public abstract class ClientUI 
<<<<<<< Upstream, based on branch 'master' of https://bitbucket.org/CoF_ps19/ps19
>>>>>>> ad0c516 Client update
=======
=======
import it.polimi.ingsw.PS19.message.requests.GameStartedMessage;
import it.polimi.ingsw.PS19.message.requests.Request;
import it.polimi.ingsw.PS19.message.requests.NewTurnMessage;
import it.polimi.ingsw.PS19.message.requests.StringMessage;

public abstract class ClientUI extends Observable implements Observer
>>>>>>> 501e0b6 Refactor Messages
>>>>>>> 0ccb890 Refactor Messages
{
<<<<<<< Upstream, based on branch 'master' of https://bitbucket.org/CoF_ps19/ps19
<<<<<<< Upstream, based on branch 'master' of https://bitbucket.org/CoF_ps19/ps19
	Integer playerId;
	@Override
	public void update(Observable o, Object arg) 
	{
		if(arg instanceof GameStartedMessage)
		{
			System.out.println(((GameStartedMessage) arg).getString());
			playerId = ((GameStartedMessage) arg).getPlayerNumber();
			setChanged();
			notifyObservers(new StringMessage("Hello this is player " + playerId));
		}
		else if(arg instanceof NewTurnMessage)
		{
			NewTurnMessage mex = (NewTurnMessage) arg;
			if(mex.getActivePlayer() == playerId)
				activatePlayer();
			else
				System.out.println(mex.getString());
		}
		else if(arg instanceof Request)
		{
			System.out.println(((Request) arg).getString());
			
		}
		else
			System.out.println("Invalid Object received");
			
	}
=======
=======
<<<<<<< Upstream, based on branch 'milanta' of https://bitbucket.org/CoF_ps19/ps19
>>>>>>> 0ccb890 Refactor Messages
	protected abstract ClientAction requestActionType();
<<<<<<< Upstream, based on branch 'master' of https://bitbucket.org/CoF_ps19/ps19
>>>>>>> ad0c516 Client update
=======
=======
	Integer playerId;
	@Override
	public void update(Observable o, Object arg) 
	{
		if(arg instanceof GameStartedMessage)
		{
			System.out.println(((GameStartedMessage) arg).getString());
			playerId = ((GameStartedMessage) arg).getPlayerNumber();
			setChanged();
			notifyObservers(new StringMessage("Hello this is player " + playerId));
		}
		else if(arg instanceof NewTurnMessage)
		{
			NewTurnMessage mex = (NewTurnMessage) arg;
			if(mex.getActivePlayer() == playerId)
				activatePlayer();
			else
				System.out.println(mex.getString());
		}
		else if(arg instanceof Request)
		{
			System.out.println(((Request) arg).getString());
			
		}
		else
			System.out.println("Invalid Object received");
			
	}
>>>>>>> 501e0b6 Refactor Messages
>>>>>>> 0ccb890 Refactor Messages
	
	protected abstract RegionType getRegion() throws InvalidInsertionException, IOException;
	protected abstract Color getAndValidateColor(ArrayList<Color> validColors) throws InvalidInsertionException, IOException;
	protected abstract RegionType getRegionAndKing() throws InvalidInsertionException, IOException;
	protected abstract void showNotification(String s);

	
}
