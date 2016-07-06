package it.polimi.ingsw.ps19.controller;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.action.DrawPoliticsCard;
import it.polimi.ingsw.ps19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.ps19.controller.action.MessageInterpreterVisitorImp;
import it.polimi.ingsw.ps19.controller.support.ActionMessages;
import it.polimi.ingsw.ps19.controller.support.SupportMethod;
import it.polimi.ingsw.ps19.message.replies.EndGameReply;
import it.polimi.ingsw.ps19.message.replies.GetBusinessCardOrCityBonusReply;
import it.polimi.ingsw.ps19.message.replies.GetBusinessToDrawReply;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.message.replies.SendFullGameReply;
import it.polimi.ingsw.ps19.message.replies.SendFullPlayerReply;
import it.polimi.ingsw.ps19.message.replies.TimeToMarketReply;
import it.polimi.ingsw.ps19.message.requests.Request;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.parameter.Costants;
import it.polimi.ingsw.ps19.server.WaitingRoom;

/**
 * The Class GameController.
 */
public class GameController implements Observer
{
	
	/** The model. */
	private Model model;
	
	/** The reply. */
	private Reply reply;
	private Request m;
	
	private Action action;
	
	/**
	 * Instantiates a new game controller. The game Started!
	 * @param model: model on which the controller will operate on
	 */
	public GameController(Model model) 
	{
		this.model = model;
		drawStartingPoliticsCard();
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
		//TODO: capire se serve
		//if(!model.getCurrentState().isTimeToMarket())
			checkReconnectedPlayer();
		
		if(!(message instanceof Request))
			return;
		m = (Request) message;
		
		action = m.accept(messageInterpreter);
		
		boolean isPossible = action.isPossible(model);
		if(isPossible)
			action.execute(model);
		
		reply = createReply();
		
		if(model.getCurrentState().isTimeToMarket() && model.getCurrentState().getLastTurn() == Costants.INVALID_ID)
			checkModelStatusInMarketTime();
		else if(model.getCurrentState().getLastTurn()  == Costants.INVALID_ID)
			checkModelStatus();
		else
			checkModelStatusLastTurn();
		
		model.sendMessage(reply);
		
		checkTimeToMarket();
		
	}
	
	/**
	 * Draw POLITICS_CARD for each player.
	 *
	 * @param m is the model of the game
	 */
	private void drawStartingPoliticsCard() 
	{
		for (Player p : model.getPlayer()) 
		{
			DrawPoliticsCard drawPoliticsCard = new DrawPoliticsCard(p.getId(), p.getStartingPoliticCard());
			drawPoliticsCard.execute(model);
		}
		SupportMethod.politicCardToDrawToCurrentPlayer(model);
	}
	
	private void checkModelStatusInMarketTime() 
	{
		if(model.getCurrentState().isTimeToEndMarket())
		{
			model.getCurrentState().setTimeToMarket(false);
			model.getCurrentState().resetPlayerTurnId();
			
			for(Player player : model.getPlayer())
				player.setStartingAction();
			
			SupportMethod.politicCardToDrawToCurrentPlayer(model);
			
			reply = new SendFullPlayerReply(model.getCurrentState().getPlayerTurnId(), 
					reply.getResult(), model.getPlayer());
			reply.setId(Costants.BROADCAST_MESSAGE);
			
			model.getCurrentState().resetMarket();
			model.getMarket().reset();
		}
		
	}
	private void checkModelStatusLastTurn() 
	{
		if(model.getCurrentState().getLastTurn() != Costants.INVALID_ID)
		{
			int currentPlayer = model.getCurrentState().getPlayerTurnId();
				//
				//Se il player ha ancora mosse da fare return
				//
			
			if((model.getPlayerById(currentPlayer).getMainActionCounter() != 0 || model.getPlayerById(currentPlayer).getFastActionCounter() !=0) 
					&& model.getCurrentState().isConnected(currentPlayer))
				return;
				
				//
				//Else: se il player corrente ha finito le mosse lo cambio
				//
			model.getCurrentState().setPlayerTurnId(
				model.getCurrentState().giveNextCorrectId(
					model.getCurrentState().getPlayerTurnId()));
				//
				//se il player corrente è quello che ha vinto allora invio il messaggio
				//di end game
				//
			if(model.getCurrentState().getPlayerTurnId() == model.getCurrentState().getLastTurn())
			{
					//
					//se il gioco è finito sovrascrivo la risposta inserendo i player in ordine di punteggio
					//e inviando il messaggio di end game
					//
				
				SupportMethod.calculateLastPoints(model);
				List<Player> orderList = SupportMethod.sortByVictoryPoints(model.getPlayer());
				reply = new EndGameReply(Costants.NO_ACTIVE_PLAYER,ActionMessages.END_GAME, orderList);
				reply.setId(Costants.BROADCAST_MESSAGE);
				return;
			}
			SupportMethod.politicCardToDrawToCurrentPlayer(model);
				
			reply.setActivePlayer(model.getCurrentState().getPlayerTurnId());
		}
	}

	public Reply getReply() 
	{
		return reply;
	}
	
	/**
	 * Check model status.
	 */
	private void checkModelStatus()
	{
		int id = model.getCurrentState().getPlayerTurnId();
		if(!model.getPlayerById(id).isBusinessCardOrCityBonusRequest() && !model.getPlayerById(id).isBusinessCardToDraw())
		{
			setTimeToMarket();
			checkAlreadyTurn();
		}
	}
	
	private void checkReconnectedPlayer()
	{
			int idReconnectedPlayer = checkDisconnectedPlayer();
			if(idReconnectedPlayer > 0)
			{
				if(!model.getCurrentState().isTimeToMarket())
				{
					model.getPlayerById(idReconnectedPlayer).setStartingAction();
					model.getCurrentState().reconnectPlayer(idReconnectedPlayer);
				}
				Reply game = new SendFullGameReply(model.getCurrentState().getPlayerTurnId(), "Player Reconnected", 
						model.getPlayer(), model.getMap().getRegionList(), model.getMap().getKing(),
						model.getMap().getAvailableCouncillor(), model.getMap().getNobilityPath());
				game.setId(idReconnectedPlayer);
				model.sendMessage(game);
			}
	}
	
	/**
	 * Check already turn.
	 * The turn change only if MainAction & FastAction are at 0.
	 * If plyer doesn't play fast action the EndTurn Action set FastAction to 0
	 */
	private void checkAlreadyTurn()
	{
		int id = model.getCurrentState().getPlayerTurnId();
		if(model.getPlayerById(id).getMainActionCounter() == 0 && model.getPlayerById(id).getFastActionCounter()==0
				&& !model.getCurrentState().isTimeToMarket())
		{
			model.getCurrentState().setPlayerTurnId(model.getCurrentState().giveNextCorrectId(model.getCurrentState().getPlayerTurnId()));
			model.getPlayerById(model.getCurrentState().getPlayerTurnId()).setStartingAction();
			
			SupportMethod.politicCardToDrawToCurrentPlayer(model);
			
			reply.setActivePlayer(model.getCurrentState().getPlayerTurnId());
		}
		
	}
	
	private int checkDisconnectedPlayer()
	{
		for (Integer  id : model.getCurrentState().getDisconnectedPlayersId())
			if(WaitingRoom.isConnected(id))
				return id;
		return -1;
				
	}
	
	/**
	 * Sets the time to market.
	 * this time is true if all player have main and fast action to zero
	 */
	private void setTimeToMarket()
	{
		for (Player p : model.getPlayer()) 
			if((p.getMainActionCounter()!=0 || p.getFastActionCounter()!=0) && 
					model.getCurrentState().isConnected(p.getId()))
			{
				model.getCurrentState().setTimeToMarket(false);
				model.getCurrentState().setTimeToMarketSended(false);
				return;
			}
		model.getCurrentState().setTimeToMarket(true);
		model.getCurrentState().setTimeToMarketSended(false);
		reply.setActivePlayer(model.getCurrentState().giveRandomTurn());
	}
	
	/**
	 * Check time to market.
	 * This method was called after was sended reply message. 
	 * This method send TimeToMarket Message.
	 * 
	 */
	private void checkTimeToMarket()
	{
		if(model.getCurrentState().isTimeToMarket() && !model.getCurrentState().isTimeToMarketSended())
		{
			model.getCurrentState().setTimeToMarketSended(true);
			Reply r = new TimeToMarketReply(Costants.NO_ACTIVE_PLAYER, ActionMessages.TIME_TO_MARKET);
			r.setId(Costants.BROADCAST_MESSAGE);				//send to all player
			model.sendMessage(r);
			model.getMarket().reset();
		}
	}
	
	/**
	 * Check business card & city Bonus.
	 * If first or second are true create a GetBusinessCardOrCityBonusReply
	 * and server send to players all game
	 */
	private Reply createReply()
	{
		int playerTurnId = model.getCurrentState().getPlayerTurnId();
		Reply r;
		if(model.getPlayerById(playerTurnId).isBusinessCardOrCityBonusRequest())
		{
			r = new GetBusinessCardOrCityBonusReply(
					model.getCurrentState().getPlayerTurnId(), 
					ActionMessages.BUSINESS_CARD_REQUEST, 
					model.getPlayer(), 
					model.getMap().getRegionList(), 
					model.getMap().getKing(),
					model.getMap().getAvailableCouncillor(),
					model.getPlayerById(playerTurnId).isBusinessCardRequest(),
					model.getPlayerById(playerTurnId).isCityBonusRequest());
		}
		else if(model.getPlayerById(playerTurnId).isBusinessCardToDraw())
			r = new GetBusinessToDrawReply(
					model.getCurrentState().getPlayerTurnId(), 
					ActionMessages.BUSINESS_CARD_TO_DRAW_REQUEST, 
					model.getPlayer(), 
					model.getMap().getRegionList(), 
					model.getMap().getKing(),
					model.getMap().getAvailableCouncillor(),
					model.getPlayerById(playerTurnId).getDrawBusinessCard());
		else	
			r = action.createReplyMessage(model);
		r.setId(Costants.BROADCAST_MESSAGE);
		return r;
			
	}	
}
