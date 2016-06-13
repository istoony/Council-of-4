package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.message.replies.EndTurnReply;
import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.model.Model;

public class EndTurn implements Action 
{
	private int playerId;
	private String result;
	
	public EndTurn(int id) 
	{
		playerId = id;
	}
	
	@Override
	public Boolean execute(Model model) 
	{
		model.getPlayerById(playerId).setFastActionCounter(0);
		model.getPlayerById(playerId).setMainActionCounter(0);
		
		if(playerId < model.getMaxId())
			model.getCurrentState().setPlayerTurnId(playerId + 1);
		else
			model.getCurrentState().setPlayerTurnId(model.getMaxId() - model.getNumberofplayer());
	
		model.getPlayerById(model.getCurrentState().getPlayerTurnId()).setStartingAction();			
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		if(Action.checkPlayerTurn(playerId, model))
		{
			result = ActionMessages.NOT_YOUR_TURN;
			return false;
		}
		
		if(model.getPlayerById(playerId).getMainActionCounter() != 0)
		{
			result = ActionMessages.MAIN_ACTION;
			return false;
		}
		result = ActionMessages.EVERYTHING_IS_OK;
		return true;
	}
	
	@Override
	public String getStringResult() 
	{
		return result;
	}
	
	
	@Override
	public Reply createReplyMessage(Model model) 
	{
		return new EndTurnReply(model.getCurrentState().getPlayerTurnId(), model.getPlayer(), result);
	}

}
