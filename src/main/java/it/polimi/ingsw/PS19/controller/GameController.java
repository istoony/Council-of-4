package it.polimi.ingsw.PS19.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitorImp;
import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.message.requests.Request;
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
	
		if(!(message instanceof Request))
			return;
		Request m = (Request) message;
		
		Action action = m.accept(messageInterpreter);
		if(action.isPossible(model))
			action.execute(model);
		Reply reply = action.createReplyMessage(model);
		reply.setActivePlayer(model.getCurrentState().getPlayerTurnId());
		model.createMessage(reply);
	}

}
