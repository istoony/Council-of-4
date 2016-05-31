package it.polimi.ingsw.PS19.controller.action;

import java.util.ArrayList;

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
			result = Action.GENERIC_ERROR;
			return false;
		}
		result = Action.EVERYTHING_IS_OK;
		return true;
	}

	@Override
	public String getStringResult() 
	{
		return result;
	}

	@Override
<<<<<<< Upstream, based on branch 'master' of https://bitbucket.org/CoF_ps19/ps19
	public Reply createReplyMessage(Model model) 
	{
		ArrayList<Region> regions = model.getMap().getListaRegioni();
		King king = model.getMap().getKing();
		ArrayList<Player> player = model.getPlayer();
		SendFullGameReply reply = new SendFullGameReply(regions, player, king);
		reply.setId(-1);
		return reply;
	}

	@Override
	public void checkAlreadyTurn() 
	{
		
=======
	public void createReplyMessage() 
	{
		
	}

	@Override
	public void checkAlreadyTurn() 
	{
>>>>>>> 0ccb890 Refactor Messages
		return;		
	}

}
