package it.polimi.ingsw.PS19.client;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.PS19.client.clientaction.ClientAction;
import it.polimi.ingsw.PS19.client.clientaction.ClientActionChooser;
import it.polimi.ingsw.PS19.client.clientaction.FastAction;
import it.polimi.ingsw.PS19.client.clientaction.MainAction;
import it.polimi.ingsw.PS19.client.clientmodel.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.message.replies.StringMessage;
import it.polimi.ingsw.PS19.message.requests.EndTurnMessage;
import it.polimi.ingsw.PS19.message.requests.GameStartedMessage;
import it.polimi.ingsw.PS19.message.requests.NewTurnMessage;
import it.polimi.ingsw.PS19.message.requests.Request;

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
		if(arg instanceof Reply)
		{
			Reply reply = ((Reply) arg);
			userInterface.showNotification(reply.toString());
			if(reply.getActivePlayer() == playerId)
				activatePlayer();
		}
		else
			userInterface.showNotification("Invalid Object received");
			
	}

	private void activatePlayer() 
	{
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