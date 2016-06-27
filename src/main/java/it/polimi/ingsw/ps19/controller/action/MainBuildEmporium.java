package it.polimi.ingsw.ps19.controller.action;

import it.polimi.ingsw.ps19.message.replies.ElectCouncillorReply;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.parameter.Costants;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

public class MainBuildEmporium extends SupportMethod implements Action 
{
	private int cityid;
	private int playerId;
	private BusinessCard businessCard;
	
	private String result;
	
	public MainBuildEmporium(City city, int playerid, BusinessCard card) 
	{
		this.cityid = city.getId();
		this.playerId = playerid;
		this.businessCard = card;
	}
	
	@Override
	public Boolean execute(Model model) 
	{
		RegionType region = businessCard.getType();
		Player player = model.getPlayerById(playerId);
		
		int numberOfHelpers = getNumberOfHelper(model, region);
		
		player.setHelpers(model.getPlayerById(playerId).getHelpers() - numberOfHelpers);
		
		model.getMap().getRegionByType(region).getCityById(cityid).buildEmporium(player);
		model.getPlayerById(playerId).addToMyEmporia(model.getMap().getRegionByType(region).getCityById(cityid));
		
		//Assegno i bonus al player, per ogni città trovata e
		//per ogni bonus presente nella città applico il bonus e controllo il 
		//percorso della nobiltà
		
		giveBonusToPlayer(model, region, player, cityid);
		
		//remove to my hand business card
		player.addUsedBusinessCard(player.removeFreebusinesscardById(businessCard.getId()));
		player.setMainActionCounter(player.getMainActionCounter() - Costants.N_OF_ACTION_TO_ADD);
		
		checkPlayerVictory(model, player, result);
		
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
		if(model.getPlayerById(playerId).getMainActionCounter() < Costants.N_OF_ACTION_TO_ADD)
		{
			result = ActionMessages.NO_ACTION_TO_DO_IT;
			return false;
		}
		RegionType region = businessCard.getType();
		if(model.getPlayerById(playerId).findMyEmporiaById(cityid))
		{
			result = ActionMessages.BUILD_EMPORIA;
			return false;
		}
		if(getNumberOfHelper(model, region) > model.getPlayerById(playerId).getHelpers())
		{
			result = ActionMessages.NO_HELPERS;
			return false;
		}
		result = ActionMessages.EVERYTHING_IS_OK;
		return true;
	}
	
	private int getNumberOfHelper(Model model, RegionType region)
	{
		return model.getMap().getRegionByType(region).getCityById(cityid).calculateMalusEmporium();
	}

	@Override
	public Reply createReplyMessage(Model model) 
	{
		return new ElectCouncillorReply(model.getCurrentState().getPlayerTurnId(), result, 
				model.getPlayer(), model.getMap().getRegionList(), model.getMap().getKing(),
				model.getMap().getAvailableCouncillor());
	}

}
