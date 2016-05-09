package it.polimi.ingsw.PS19.model.card;

import java.util.ArrayList;

import it.polimi.ingsw.PS19.model.bonus.Bonus;
import it.polimi.ingsw.PS19.model.paramiter.RegionType;

public class BusinessCard implements Card {

	RegionType type;
	ArrayList<Bonus> bonus;
	ArrayList<City> city;
	
	public BusinessCard(RegionType type, Bonus bonus, City city) 
	{
		this.type = type;
		this.bonus.add(bonus);
		this.city.add(city);
	}
	
	public ArrayList<Bonus> getBonus() 
	{
		return bonus;
	}
	
}
