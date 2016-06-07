package it.polimi.ingsw.PS19.controller.action;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.message.replies.SendFullGameReply;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.map.Region;

public class SendFullGame implements Action 
{

	private String result;
	@Override
	public Boolean execute(Model model) 
	{
		model.getCurrentState().setSendfullgame(true);
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		if(model == null)
		{
			result = ActionMessages.GENERIC_ERROR;
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
		List<Region> regions = model.getMap().getListaRegioni();
		King king = model.getMap().getKing();
		List<Player> player = model.getPlayer();
		SendFullGameReply reply = new SendFullGameReply(regions, player, king);
		reply.setId(-1);
		return reply;
	}

	@Override
	public void checkAlreadyTurn() 
	{
		
	}

}
