package it.polimi.ingsw.PS19.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.message.MessageInterpreterVisitor;
import it.polimi.ingsw.PS19.message.MessageInterpreterVisitorImp;
import it.polimi.ingsw.PS19.model.Model;

public class GameController implements Observer
{
	private Model model;
	
	
	public GameController(Model m) 
	{
		model = m;
	}
	
	@Override
	public void update(Observable view, Object message) 
	{
		MessageInterpreterVisitor messageInterpreter = new MessageInterpreterVisitorImp();
	
		if(!(message instanceof Message))
			return;
		Message m = (Message) message;
		
		Action action = m.accept(messageInterpreter);
		if(action.isPossible(model))
			action.execute(model);
		String result = action.getStringResult();
		model.createMessage(result);
	}

}
