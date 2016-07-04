package it.polimi.ingsw.ps19.controller.action;

import java.awt.Color;

import it.polimi.ingsw.ps19.controller.support.ActionMessages;
import it.polimi.ingsw.ps19.controller.support.SupportMethod;
import it.polimi.ingsw.ps19.message.replies.ElectCouncillorReply;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.map.King;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

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
		if(king != null)
			model.getMap().getKing().getBalcony().setNewCouncill(color);
		else
			model.getMap().getRegionByType(region).getBalcony().setNewCouncill(color);
		
		if(mainAction)
		{
			model.getPlayerById(playerId).setMoney(model.getPlayerById(playerId).getMoney() + MONEY);
			model.getPlayerById(playerId).setMainActionCounter(model.getPlayerById(playerId).getMainActionCounter() - SupportMethod.N_OF_ACTION_TO_ADD);	
		}
		else
		{
			model.getPlayerById(playerId).setHelpers(model.getPlayerById(playerId).getHelpers() - HELPERS);
			model.getPlayerById(playerId).setFastActionCounter(model.getPlayerById(playerId).getFastActionCounter() - SupportMethod.N_OF_ACTION_TO_ADD);
		}
		return null;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		if((mainAction && !SupportMethod.checkPlayerTurnAndAction(model, playerId, SupportMethod.MAIN_ACTION))
				|| (!mainAction && !SupportMethod.checkPlayerTurnAndAction(model, playerId, SupportMethod.FAST_ACTION)))
		{
			result = ActionMessages.GENERIC_ERROR;
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
				model.getPlayer(), model.getMap().getRegionList(), model.getMap().getKing(),
				model.getMap().getAvailableCouncillor());
	}
	
}
