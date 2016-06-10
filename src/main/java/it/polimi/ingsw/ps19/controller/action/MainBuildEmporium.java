package it.polimi.ingsw.ps19.controller.action;

import java.util.ArrayList;

import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

public class MainBuildEmporium implements Action 
{
	private int cityid;
	private int playerId;
	private BusinessCard businessCard;
	
	private String result;
	
	public MainBuildEmporium(int city, int playerid, BusinessCard card) 
	{
		this.cityid = city;
		this.playerId = playerid;
		this.businessCard = card;
	}
	
	@Override
	public Boolean execute(Model model) 
	{
		RegionType region = businessCard.getType();
		Player player = model.getPlayerById(playerId);
			//CHECK SE NON C'E' GIA L'EMPORIO DEL PLAYER SU QUESTA CITTA'
		
				//WRITE ME!
		
			//RETURN = numero di empori dentro la città di altri player
		int numberOfHelpers = getNumberOfHelper(model, region);
		
		model.getPlayerById(playerId).setHelpers(model.getPlayerById(playerId).getHelpers() - numberOfHelpers);
		
		model.getMap().getRegionByType(region).getCityById(cityid).buildEmporium(player);
		
		model.getMap().getRegionByType(region).getCityById(cityid).applyBonus(player);
		
		model.getMap().getRegionByType(region).getCityById(cityid).applyNetBonus(player, new ArrayList<City>());
		
		//CONTROLLO SE HO ALTRI BONUS DA APPLICARE
		
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
	public String getStringResult() {
		return result;
	}

	@Override
	public Reply createReplyMessage(Model model) {
		// TODO Auto-generated method stub
		return null;
	}

}
