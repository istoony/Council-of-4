package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.card.BusinessCard;

public class MainBuildEmporium implements Action 
{
	private int cityid;
	private int playerid;
	private BusinessCard businessCard;
	
	public MainBuildEmporium(int city, int playerid, BusinessCard card) 
	{
		this.cityid = city;
		this.playerid = playerid;
		this.businessCard = card;
	}
	
	@Override
	public Boolean execute(Model model) 
	{
		return null;
	}

	@Override
	public Boolean isPossible(Model model) {
		// TODO Auto-generated method stub
		return null;
	}

}
