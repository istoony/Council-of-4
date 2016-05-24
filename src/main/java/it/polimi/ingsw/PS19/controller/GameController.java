package it.polimi.ingsw.PS19.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.ActionFactory;
import it.polimi.ingsw.PS19.message.Message;
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
		
		Action action = ActionFactory.createAction(m);
		
		Boolean result = action.execute(model);
		
		if(result)
			model.createTrueMessage(m);
		//model.createFalseMessage(message);
	}

}
