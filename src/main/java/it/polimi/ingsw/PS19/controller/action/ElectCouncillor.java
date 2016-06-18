package it.polimi.ingsw.PS19.controller.action;

import java.awt.Color;

import it.polimi.ingsw.PS19.message.replies.ElectCouncillorReply;
import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class ElectCouncillor implements Action
{
	private static final int MONEY = 4;
	private static final int HELPERS = 1;
	private Boolean mainAction;
	private Color color;
	private int playerId;
	private RegionType region;
	private King king = null;
	
	private String result;

	public ElectCouncillor(Color color, int id, RegionType region, Boolean mainAction) 
	{
		this.color = color;
		this.playerId = id;
		this.region = region;
		this.mainAction = mainAction;
	}
	
	public ElectCouncillor(Color color, int id, King k, Boolean mainAction) 
	{
		this.color = color;
		this.playerId = id;
		this.king = k;
		this.mainAction = mainAction;
	}
	
	@Override
	public Boolean execute(Model model) 
	{
		Boolean res;
		if(king != null)
			res = model.getMap().getKing().getBalcony().setNewCouncill(color);
		else
			res = model.getMap().getRegionByType(region).getBalcony().setNewCouncill(color);
		if(res && mainAction)
		{
			model.getPlayerById(playerId).setMoney(model.getPlayerById(playerId).getMoney() + MONEY);
			model.getPlayerById(playerId).setMainActionCounter(model.getPlayerById(playerId).getMainActionCounter() - 1);	
		}
		else if(res)
		{
			model.getPlayerById(playerId).setHelpers(model.getPlayerById(playerId).getHelpers() - HELPERS);
			model.getPlayerById(playerId).setFastActionCounter(model.getPlayerById(playerId).getFastActionCounter() - 1);
		}
		
		return res;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		if(Action.checkPlayerTurn(playerId, model))
		{
			result = ActionMessages.NOT_YOUR_TURN;
			return false;
		}
		if(!model.getMap().getAvailableCouncillor().findColor(color))
		{
			result = ActionMessages.COLOR_NOT_AVAILABLE;
			return false;
		}
		if(!mainAction && model.getPlayerById(playerId).getHelpers() < HELPERS)
		{
			result = ActionMessages.NO_HELPERS;
			return false;
		}
		result = ActionMessages.EVERYTHING_IS_OK;
		return true;
	}

	@Override
	public Reply createReplyMessage(Model model) 
	{
		return new ElectCouncillorReply(model.getCurrentState().getPlayerTurnId(), result, 
				model.getPlayer(), model.getMap().getListaRegioni(), model.getMap().getKing(),
				model.getMap().getAvailableCouncillor());
	}
	
}
