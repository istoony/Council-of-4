package it.polimi.ingsw.PS19.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.ActionFactory;
import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.message.MessageType;
import it.polimi.ingsw.PS19.model.Model;

public class GameController implements Observer
{
	private Model model;
	
	
	public GameController(Model m) 
	{
		model = m;
	}
	
	public void update(Observable view, Object message) 
	{
		if(!(message instanceof Message))
			return;
		Message m = (Message) message;
		
		readMessage(m);
		
		//model.createFalseMessage(message);
	}
	
	protected void readMessage(Message message)
	{
		switch (message.getType()) 
		{
			case GAME_STARTED:
			{
				model.createMessage(MessageType.GAME_STARTED);
				break;
			}

			default:
				break;
		}
	}

}
