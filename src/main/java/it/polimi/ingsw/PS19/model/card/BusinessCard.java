package it.polimi.ingsw.PS19.model.card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.PS19.model.bonus.Bonus;
import it.polimi.ingsw.PS19.model.map.City;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class BusinessCard implements Card, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RegionType type;
	private List<Bonus> bonus;
	private List<City> city;
	private int id;
	
	public BusinessCard(RegionType type, Bonus bonus, City city) 
	{
		this.bonus = new ArrayList<>();
		this.city = new ArrayList<>();
		this.type = type;
		this.bonus.add(bonus);
		this.city.add(city);
	}
	
	public BusinessCard(RegionType type) 
	{
		this.bonus = new ArrayList<>();
		this.city = new ArrayList<>();
		this.type = type;
	}
	
	public List<Bonus> getBonus() 
	{
		return bonus;
	}
	public void addBonus(Bonus bonus) 
	{
		this.bonus.add(bonus);
	}
	
	public RegionType getType() 
	{
		return type;
	}
	
	protected Boolean addCity(City city) 
	{
		for (City c : this.city) 
		{
			if(c.equals(city))
				return false;
			
		}
		this.city.add(city);
		return true;
	}
	protected void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
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
<<<<<<< HEAD
	
	public List<City> getCity() 
	{
		List<City> cities = new ArrayList<>();
		for (City c : city) 
			cities.add(c);
		return cities;
=======
	@SuppressWarnings("unchecked")
	public ArrayList<City> getCity() 
	{
		return (ArrayList<City>) city.clone();
>>>>>>> branch 'master' of https://bitbucket.org/CoF_ps19/ps19.git
	}
}
