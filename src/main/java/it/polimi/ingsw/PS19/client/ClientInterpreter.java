package it.polimi.ingsw.PS19.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import it.polimi.ingsw.PS19.client.clientaction.ClientAction;
import it.polimi.ingsw.PS19.client.clientaction.ClientActionChooser;
import it.polimi.ingsw.PS19.client.clientaction.FastAction;
import it.polimi.ingsw.PS19.client.clientaction.MainAction;
import it.polimi.ingsw.PS19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.message.replies.GameStartedMessage;
import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.message.replies.StringMessage;
import it.polimi.ingsw.PS19.message.requests.EndTurnMessage;
import it.polimi.ingsw.PS19.message.requests.Request;

public class ClientInterpreter extends Observable implements Observer
{
	ClientUI userInterface;
	ClientModel model;
	Integer playerId;
	MainAction mainAction;
	FastAction fastAction;
	List<ClientActionChooser> typesOfAction;
	ReplyVisitor visitor;
	
	public ClientInterpreter(ClientUI ui) 
	{
		userInterface = ui;
	}
	
	private void loadInterpreter()
	{
		visitor = new ReplyVisitorImpl();
		model = new ClientModel(playerId);
		mainAction = new MainAction(model);
		fastAction = new FastAction(model);
		typesOfAction = new ArrayList<ClientActionChooser>();
		typesOfAction.add(mainAction);
		typesOfAction.add(fastAction);
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		if(arg instanceof GameStartedMessage)
		{
			playerId = ((GameStartedMessage)arg).getPlayerNumber();
			loadInterpreter();
			userInterface.showNotification(((GameStartedMessage)arg).toString());
		}
		else if(arg instanceof StringMessage)
		{
			userInterface.showNotification(((StringMessage)arg).toString());
			return;
		}
		else if(arg instanceof Reply)
		{
			Reply reply = ((Reply) arg);
			updateModel(reply);
			userInterface.drawModel(model);
			if(reply.getActivePlayer() == playerId)
				activatePlayer();
		}
		else
			userInterface.showNotification("Invalid Object received");
			
	}
	
	private void updateModel(Reply reply)
	{
		ClientUpdate updateAction = reply.display(visitor);
		updateAction.update(model);
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
				ClientAction action = actionType.getAction(userInterface);
				try 
				{
					mex = action.Execute(userInterface);
					actionType.subAvail();
					//mex.setId(playerId);
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