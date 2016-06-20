package it.polimi.ingsw.ps19.controller.action;

import it.polimi.ingsw.ps19.message.replies.PlayerDisconnectedReply;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.model.Model;

public class PlayerDisconnectedAction implements Action {

	private int playerId;
	private String result;
	
	public PlayerDisconnectedAction(int id) 
	{
		playerId = id;
	}
	@Override
	public Boolean execute(Model model) 
	{
		model.getCurrentState().disconnectPlayer(playerId);
		int nextTurn = model.getCurrentState().giveNextCorrectId(playerId);
		model.getCurrentState().setPlayerTurnId(nextTurn);
		model.getPlayerById(nextTurn).setStartingAction();
		
		//draw one card
		DrawPoliticsCard drawPoliticsCard = new DrawPoliticsCard(model.getCurrentState().getPlayerTurnId());
		drawPoliticsCard.execute(model);
		
		result = ActionMessages.PLAYER_DISCONNECTED + playerId;
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
		return new PlayerDisconnectedReply(playerId, result);
	}

}
