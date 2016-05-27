package it.polimi.ingsw.PS19.controller.action;

import java.awt.Color;

import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class MainElectCouncillor implements Action
{
	private static final int MONEY = 4;
	private static final int HELPERS = 1;
	private Boolean MainAction;
	private Color color;
	private int id;
	private RegionType region;
	private King king;

	public MainElectCouncillor(Color color, int id, RegionType region) 
	{
		this.color = color;
		this.id = id;
		this.region = region;
	}
	
	public MainElectCouncillor(Color color, int id, King k) 
	{
		this.color = color;
		this.id = id;
		this.king = k;
	}
	
	@Override
	public Boolean execute(Model model) 
	{
		Boolean result;
		if(king != null)
			result = model.getMap().getKing().getBalcony().setNewCouncill(color);
		else
			result = model.getMap().getRegionByType(region).getBalcony().setNewCouncill(color);
		if(result && MainAction)
			model.getPlayerById(id).addMoney(MONEY);
		else if(result && !MainAction)
			model.getPlayerById(id).setHelpers(model.getPlayerById(id).getHelpers() - HELPERS);
		return result;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		if(MainAction == false)
			return model.getPlayerById(id).getHelpers() >= HELPERS;
		return true;
	}
	
	public static void main(String[] args) 
	{
		
	}

}
