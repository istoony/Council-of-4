package it.polimi.ingsw.PS19.model.card;

import java.util.ArrayList;

import it.polimi.ingsw.PS19.model.bonus.Bonus;
import it.polimi.ingsw.PS19.model.map.City;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class BusinessCard implements Card {

	RegionType type;
	ArrayList<Bonus> bonus;
	ArrayList<City> city;
	
	public BusinessCard(RegionType type, Bonus bonus, City city) 
	{
		this.bonus = new ArrayList<Bonus>();
		this.city = new ArrayList<City>();
		this.type = type;
		this.bonus.add(bonus);
		this.city.add(city);
	}
	
	public BusinessCard(RegionType type) 
	{
		this.bonus = new ArrayList<Bonus>();
		this.city = new ArrayList<City>();
		this.type = type;
	}
	
	public ArrayList<Bonus> getBonus() 
	{
		return bonus;
	}
	public void addBonus(Bonus bonus) 
	{
		this.bonus.add(bonus);
	}
	
	public Boolean addCity(City city) 
	{
		for (City c : this.city) 
		{
			if(c.equals(city))
				return false;
			
		}
		this.city.add(city);
		return true;
	}
	
	@Override
	public String toString() 
	{
		String s = "";
		s = s + "REGION: " + type.name() + "  ";
		for (Bonus b : bonus) 
			s = s + b.getClass().getName() + "  ";
		s += "cities: " + city.size() + "   \n";
		for (City c : city) 
			s = s + c.toString() + "  ";
		s = s + "\n";
		
		return s;
	}
	
}
