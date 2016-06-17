package it.polimi.ingsw.PS19.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.ActionMessages;
import it.polimi.ingsw.PS19.controller.action.DrawPoliticsCard;
import it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitorImp;
import it.polimi.ingsw.PS19.message.replies.GetBusinessCardOrCityBonusReply;
import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.message.replies.TimeToMarketReply;
import it.polimi.ingsw.PS19.message.requests.Request;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.parameter.Costants;;

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
	 * Instantiates a new game controller. The game Started!
	 *
	 * @param m the m
	 */
	public GameController(Model m) 
	{
		model = m;
		
		drawPoliticsCard();
	}
	
	/**
	 * Draw POLITICS_CARD for each player.
	 *
	 * @param m is the model of the game
	 */
	private void drawPoliticsCard() 
	{
		for (Player p : model.getPlayer()) 
		{
			int numberOfPoliticCards = p.getStartingPoliticCard();
			for(int i=0;i<numberOfPoliticCards;i++)
			{
				DrawPoliticsCard drawPoliticsCard = new DrawPoliticsCard(p.getId());
				drawPoliticsCard.execute(model);
			}
		}
		//Fa pescare una carta al primo player
		int numberOfPoliticCards = model.getPlayerById(model.getCurrentState().getPlayerTurnId()).getPoliticCardToDraw();
		
		if( numberOfPoliticCards !=0)
		{
			for(int i=0;i<numberOfPoliticCards;i++)
			{
				DrawPoliticsCard drawPoliticsCard = new DrawPoliticsCard(model.getCurrentState().getPlayerTurnId());
				drawPoliticsCard.execute(model);
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
		
		if(!(message instanceof Request))
			return;
		Request m = (Request) message;
		
		Action action = m.accept(messageInterpreter);
		if(action.isPossible(model))
			action.execute(model);
		reply = action.createReplyMessage(model);		
		
		checkModelStatus();
		
		if(model.getCurrentState().getTimeToMarket())
			reply.setActivePlayer(-1);
		else
			reply.setActivePlayer(model.getCurrentState().getPlayerTurnId());
		reply.setId(-1);		//send to all player
		model.createMessage(reply);

		checkTimeToMarket();
		
	}
	
	/**
	 * Check model status.
	 */
	private void checkModelStatus()
	{
		checkBusinessOrCityBonus();		
		
		setTimeToMarket();
		
		checkAlreadyTurn();
		
	}
	
	/**
	 * Check already turn.
	 * The turn change only if MainAction & FastAction are at 0.
	 * If plyer doesn't play fast action the EndTurn Action set FastAction to 0
	 *	TODO scrivere se player è disconnesso
	 */
	private void checkAlreadyTurn()
	{
		int id = model.getCurrentState().getPlayerTurnId();
		if(model.getPlayerById(id).getMainActionCounter() == 0 && model.getPlayerById(id).getFastActionCounter()==0
				&& !model.getPlayerById(id).isCityBonusRequest() && !model.getPlayerById(id).isBusinessCardRequest())
		{		
			if(id < model.getMaxId())
				model.getCurrentState().setPlayerTurnId(id + 1);
			else
				model.getCurrentState().setPlayerTurnId(model.getMaxId() - model.getCurrentState().getNumberOfPlayer() + 1);
	
			model.getPlayerById(model.getCurrentState().getPlayerTurnId()).setStartingAction();
			
			//draw one card
			DrawPoliticsCard drawPoliticsCard = new DrawPoliticsCard(model.getCurrentState().getPlayerTurnId());
			drawPoliticsCard.execute(model);
			//set active player
			reply.setActivePlayer(model.getCurrentState().getPlayerTurnId());
			
		}
	}
	
	/**
	 * Sets the time to market.
	 * this time is true if all player have main action to zero
	 * TODO tenere conto dei player disconnessi
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
	 * This method was called after was sended reply message. 
	 * This method send TimeToMarket Message.
	 * 
	 */
	private void checkTimeToMarket()
	{
		if(model.getCurrentState().getTimeToMarket())
		{
			Reply r = new TimeToMarketReply(Costants.NO_ACTIVE_PLAYER, ActionMessages.TIME_TO_MARKET);
			r.setId(Costants.BROADCAST_MESSAGE);				//send to all player
			model.createMessage(r);
		}
	}
	
	/**
	 * Check business card & city Bonus.
	 * If first or second are true create a GetBusinessCardOrCityBonusReply
	 * and server send to players all game
	 */
	private void checkBusinessOrCityBonus()
	{
		int playerTurnId = model.getCurrentState().getPlayerTurnId();
		if(model.getPlayerById(playerTurnId).isBusinessCardRequest() || 
				model.getPlayerById(playerTurnId).isCityBonusRequest())
		{
			reply = new GetBusinessCardOrCityBonusReply(
					model.getCurrentState().getPlayerTurnId(), 
					ActionMessages.BUSINESS_CARD_REQUEST, 
					model.getPlayer(), 
					model.getMap().getListaRegioni(), 
					model.getMap().getKing(),
					model.getMap().getAvailableCouncillor(),
					model.getPlayerById(playerTurnId).isBusinessCardRequest(),
					model.getPlayerById(playerTurnId).isCityBonusRequest());
		}
			
	}	
}
