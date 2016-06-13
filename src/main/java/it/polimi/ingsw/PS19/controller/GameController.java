package it.polimi.ingsw.PS19.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.DrawPoliticsCard;
import it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitorImp;
import it.polimi.ingsw.PS19.message.replies.GetBusinessCardBonusReply;
import it.polimi.ingsw.PS19.message.replies.GetCityBonusReply;
import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.message.replies.TimeToMarketReply;
import it.polimi.ingsw.PS19.message.requests.Request;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.Player;


public class GameController implements Observer
{
	
	private Model model;
	
	
	public GameController(Model m) 
	{
		model = m;
		drawPoliticsCard(m);
	}
	
	/**
	 * Draw POLITICS_CARD for each player
	 * @param m is the model of the game
	 */
	private void drawPoliticsCard(Model m) 
	{
		for (Player p : m.getPlayer()) 
		{
			for(int i=0;i<p.getPoliticCardToDraw();i++)
			{
				DrawPoliticsCard drawPoliticsCard = new DrawPoliticsCard(p.getId());
				drawPoliticsCard.execute(m);
			}
		}
	}
	
	@Override
	public void update(Observable view, Object message) 
	{
		/*
		 *the messageInterpreter implements vistior pattern to read all message
		 *See MessageInterpreterVisitor
		 */
		MessageInterpreterVisitor messageInterpreter = new MessageInterpreterVisitorImp();
	
		if(!(message instanceof Request))
			return;
		Request m = (Request) message;
		
		Action action = m.accept(messageInterpreter);
		if(action.isPossible(model))
			action.execute(model);
		Reply reply = action.createReplyMessage(model);
		
		checkModelStatus();
		
		reply.setActivePlayer(model.getCurrentState().getPlayerTurnId());
		model.createMessage(reply);
	}
	
	private void checkModelStatus()
	{
		checkBusinessCardBonus();
		checkCityBonus();		
		
		checkAlreadyTurn();
		
		setTimeToMarket();
		checkTimeToMarket();
			
		return;
	}
	private void checkAlreadyTurn()
	{
		int id = model.getCurrentState().getPlayerTurnId();
		if(model.getPlayerById(id).getMainActionCounter() == 0 && model.getPlayerById(id).getFastActionCounter()==0
				&& !model.getCurrentState().getCityBonusRequest() && !model.getCurrentState().getBusinessCardRequest())
		{		
			if(id + 1 < model.getMaxId())
				model.getCurrentState().setPlayerTurnId(id + 1);
			else
				model.getCurrentState().setPlayerTurnId(model.getMaxId() - model.getNumberofplayer());
	
			model.getPlayerById(model.getCurrentState().getPlayerTurnId()).setFastActionCounter(1);
			model.getPlayerById(model.getCurrentState().getPlayerTurnId()).setMainActionCounter(1);
			
		}
	}
	
	private void setTimeToMarket()
	{
		for (Player p : model.getPlayer()) 
			if(p.getMainActionCounter()!=0)
			{
				model.getCurrentState().setTimeToMarket(false);
				return;
			}
		model.getCurrentState().setTimeToMarket(true);
	}
	
	private void checkTimeToMarket()
	{
		if(model.getCurrentState().getTimeToMarket())
		{
			Reply r = new TimeToMarketReply();
			model.createMessage(r);
		}
	}
	
	private void checkBusinessCardBonus()
	{
		if(model.getCurrentState().getBusinessCardRequest())
			model.createMessage(new GetBusinessCardBonusReply(model.getCurrentState().getPlayerTurnId()));
	}
	
	private void checkCityBonus()
	{
		if(model.getCurrentState().getCityBonusRequest())
			model.createMessage(new GetCityBonusReply(model.getCurrentState().getPlayerTurnId()));
	}
	
	
}
