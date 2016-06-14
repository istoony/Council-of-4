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

/**
 * The Class GameController.
 */
public class GameController implements Observer
{
	
	/** The model. */
	private Model model;
	
	/** The reply. */
	private Reply reply;
	
	/**
	 * Instantiates a new game controller.
	 *
	 * @param m the m
	 */
	public GameController(Model m) 
	{
		model = m;
	}
	
	/**
	 * Draw POLITICS_CARD for each player.
	 *
	 * @param m is the model of the game
	 */
	private void drawPoliticsCard(Model m) 
	{
		for (Player p : m.getPlayer()) 
		{
			int numberOfPoliticCards = p.getPoliticCardToDraw();
			for(int i=0;i<numberOfPoliticCards;i++)
			{
				DrawPoliticsCard drawPoliticsCard = new DrawPoliticsCard(p.getId());
				drawPoliticsCard.execute(m);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable view, Object message) 
	{
		/*
		 *the messageInterpreter implements vistior pattern to read all message
		 *See MessageInterpreterVisitor
		 */
		reply = null;
		MessageInterpreterVisitor messageInterpreter = new MessageInterpreterVisitorImp();
		
		drawPoliticsCard(model);
	
		if(!(message instanceof Request))
			return;
		Request m = (Request) message;
		
		Action action = m.accept(messageInterpreter);
		if(action.isPossible(model))
			action.execute(model);
		reply = action.createReplyMessage(model);		
		
		checkModelStatus();
		reply.setId(-1);
		
		if(model.getCurrentState().getTimeToMarket())
			reply.setActivePlayer(-1);
		else
			reply.setActivePlayer(model.getCurrentState().getPlayerTurnId());
		model.createMessage(reply);

		checkTimeToMarket();
		
	}
	
	/**
	 * Check model status.
	 */
	private void checkModelStatus()
	{
		checkBusinessCardBonus();
		checkCityBonus();		
		
		setTimeToMarket();
		
		checkAlreadyTurn();
		
	}
	
	/**
	 * Check already turn.
	 */
	private void checkAlreadyTurn()
	{
		int id = model.getCurrentState().getPlayerTurnId();
		if(model.getPlayerById(id).getMainActionCounter() == 0 && model.getPlayerById(id).getFastActionCounter()==0
				&& !model.getCurrentState().getCityBonusRequest() && !model.getCurrentState().getBusinessCardRequest())
		{		
			if(id < model.getMaxId())
				model.getCurrentState().setPlayerTurnId(id + 1);
			else
				model.getCurrentState().setPlayerTurnId(model.getMaxId() - model.getNumberofplayer() + 1);
	
			model.getPlayerById(model.getCurrentState().getPlayerTurnId()).setStartingAction();
			reply.setActivePlayer(model.getCurrentState().getPlayerTurnId());
			
		}
	}
	
	/**
	 * Sets the time to market.
	 */
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
	
	/**
	 * Check time to market.
	 */
	private void checkTimeToMarket()
	{
		if(model.getCurrentState().getTimeToMarket())
		{
			Reply r = new TimeToMarketReply();
			model.createMessage(r);
		}
	}
	
	/**
	 * Check business card bonus.
	 */
	private void checkBusinessCardBonus()
	{
		if(model.getCurrentState().getBusinessCardRequest())
			model.createMessage(new GetBusinessCardBonusReply(model.getCurrentState().getPlayerTurnId()));
	}
	
	/**
	 * Check city bonus.
	 */
	private void checkCityBonus()
	{
		if(model.getCurrentState().getCityBonusRequest())
			model.createMessage(new GetCityBonusReply(model.getCurrentState().getPlayerTurnId()));
	}
	
	
}
