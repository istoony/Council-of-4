package it.polimi.ingsw.PS19.Client;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import it.polimi.ingsw.PS19.Client.clientAction.ClientAction;
import it.polimi.ingsw.PS19.Client.clientAction.ClientActionChooser;
import it.polimi.ingsw.PS19.Client.clientAction.FastAction;
import it.polimi.ingsw.PS19.Client.clientAction.MainAction;
import it.polimi.ingsw.PS19.Client.clientModel.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.message.requests.EndTurnMessage;
import it.polimi.ingsw.PS19.message.requests.GameStartedMessage;
import it.polimi.ingsw.PS19.message.requests.NewTurnMessage;
import it.polimi.ingsw.PS19.message.requests.Request;
import it.polimi.ingsw.PS19.message.requests.StringMessage;

public class ClientInterpreter extends Observable implements Observer
{
	ClientUI userInterface;
	ClientModel model;
	Integer playerId;
	MainAction mainAction = new MainAction();
	FastAction fastAction = new FastAction();
	ArrayList<ClientActionChooser> typesOfAction = new ArrayList<ClientActionChooser>();
	
	public ClientInterpreter(ClientUI ui) 
	{
		userInterface = ui;
		typesOfAction.add(mainAction);
		typesOfAction.add(fastAction);
	}
	
	public void setModel(ClientModel model) 
	{
		this.model = model;
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		if(arg instanceof GameStartedMessage)
		{
			playerId = ((GameStartedMessage) arg).getPlayerNumber();
			userInterface.showNotification(((GameStartedMessage) arg).getString());
			setChanged();
			notifyObservers(new StringMessage("Hello this is player " + playerId));
		}
		else if(arg instanceof NewTurnMessage)
		{
			NewTurnMessage mex = (NewTurnMessage) arg;
			if(mex.getActivePlayer() == playerId)
				activatePlayer(mex);
			else
				userInterface.showNotification(mex.getString());
		}
		else if(arg instanceof Reply)
			userInterface.showNotification(((Reply) arg).getString());
		else
			userInterface.showNotification("Invalid Object received");
			
	}

	private void activatePlayer(NewTurnMessage mex) 
	{
		for(ClientActionChooser c : typesOfAction)
			c.resetAvail();
		requestAction();
	}
	
	private void requestAction()
	{
		Request mex;
		boolean valid;
		do
		{
			ClientActionChooser actionType = getActionType();
			if(actionType == null)
			{
				mex = new EndTurnMessage(); 
				valid = true;
			}
			else
			{
				ClientAction action = actionType.getAction(userInterface, model);
				try 
				{
					mex = action.Execute(userInterface);
					actionType.subAvail();
					notify(mex);
					valid = true;
				} catch (InvalidInsertionException e) 
				{
					valid = false;
				}
			}
		}while(!valid);
	}
	
	private ClientActionChooser getActionType()
	{
		boolean choose = true;
		
		for(ClientActionChooser c : typesOfAction) 
			choose &= c.available();
		if(choose)
			return(userInterface.requestActionType(typesOfAction));
		else if(mainAction.available())
			return mainAction;
		else if(fastAction.available())
			return fastAction;
		else
			return null;
	}
	
	private void notify(Message mex)
	{
		if(mex == null)
			return;
		mex.setId(playerId);
		setChanged();
		notifyObservers(mex);
	}
}