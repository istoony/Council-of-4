package it.polimi.ingsw.ps19.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.action.DrawPoliticsCard;
import it.polimi.ingsw.ps19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.ps19.controller.action.MessageInterpreterVisitorImp;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.message.requests.Request;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.Player;

public class GameController implements Observer
{
	private static final int POLITICS_CARD = 7;
	private Model model;
	
	public GameController(Model m) 
	{
		model = m;
		for (Player p : m.getPlayer()) 
		{
			for(int i=1;i<POLITICS_CARD;i++)
			{
				DrawPoliticsCard drawPoliticsCard = new DrawPoliticsCard(p.getId());
				drawPoliticsCard.execute(m);
			}
		}
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
