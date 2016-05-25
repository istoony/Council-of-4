package it.polimi.ingsw.PS19.controller.action;

import java.awt.Color;

import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class MainElectRegionCouncillor implements Action 
{
	private static final int MONEY = 4;
	private Color color;
	private int id;
	private RegionType region;

	public MainElectRegionCouncillor(Color color, int id, RegionType region) 
	{
		this.color = color;
		this.id = id;
		this.region = region;
	}
	
	@Override
	public Boolean execute(Model model) 
	{
		System.out.println(model.toString());
		Boolean result = model.getMap().getRegionByType(region).getBalcony().setNewCouncill(color);
		if(result)
			model.getPlayerById(id).addMoney(MONEY);
		System.out.println(model.toString());
		return result;
	}

	@Override
	public Boolean isPossible(Model model) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public static void main(String[] args) 
	{
		
	}

}
