package it.polimi.ingsw.ps19.controller.action.serveraction;

import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.support.ActionMessages;
import it.polimi.ingsw.ps19.message.replies.CompleteMarketReply;
import it.polimi.ingsw.ps19.message.replies.PlayerDisconnectedReply;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.parameter.Costants;

public class PlayerDisconnectedAction implements Action {

	private int playerId;
	private String result;
	private boolean newTurn = false;
	
	public PlayerDisconnectedAction(int id) 
	{
		playerId = id;
	}
	@Override
	public Boolean execute(Model model) 
	{
			result = ActionMessages.PLAYER_DISCONNECTED + playerId;
				//guardo il turno attuale
			int actualPlayerId = model.getCurrentState().getPlayerTurnId();
				//capisco di chi è il turno successivo
			int nextTurn = model.getCurrentState().giveNextCorrectId(playerId);
				//azzero il player disconnesso
			model.getPlayerById(playerId).setFastActionCounter(0);
			model.getPlayerById(playerId).setMainActionCounter(0);
				//imposto le starting action per il prossimo player solo se effettivamente cambia
				//nel caso si è disconnesso un player che non stava giocando allora non faccio niente
			if(nextTurn != actualPlayerId)
			{
				//model.getCurrentState().setPlayerTurnId(nextTurn);
				//model.getPlayerById(nextTurn).setStartingAction();
				newTurn = true;
			}
				//aggiungo il player alla lista dei disconnessi
			model.getCurrentState().addDisconnectedPlayer(playerId);
			
				//se rimane solo un player dico che è l'ultimo turno
			if(model.getCurrentState().getNumberOfPlayer() - model.getCurrentState().getNumberOfDisconnectedPlayer() == 1)
				model.getCurrentState().setLastTurn(model.getCurrentState().getPlayerTurnId());
			return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		return true;
	}

	@Override
	public Reply createReplyMessage(Model model) 
	{
		int idTurn;
		if(model.getCurrentState().isTimeToMarket() && model.getMarket().getSize() <= 
				model.getCurrentState().getNumberOfPlayer() - model.getCurrentState().getNumberOfDisconnectedPlayer())
			idTurn = Costants.NO_ACTIVE_PLAYER;
		else
			idTurn = model.getCurrentState().getPlayerTurnId();
		
		Reply r = new PlayerDisconnectedReply(idTurn, model.getPlayer() ,result, newTurn);
		
		if(model.getMarket().getSize() == 
				model.getCurrentState().getNumberOfPlayer() - model.getCurrentState().getNumberOfDisconnectedPlayer())
			return new CompleteMarketReply(model.getMarket(), result + ". Is Time to Buy", idTurn);
			
		return r;
	}

}
