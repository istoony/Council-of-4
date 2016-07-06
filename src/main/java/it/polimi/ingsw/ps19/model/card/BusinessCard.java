package it.polimi.ingsw.ps19.model.card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.model.bonus.Bonus;
import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.parameter.Costants;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

/**
 * Class rapresenting a business card
 */
public class BusinessCard implements Card, Serializable
{
	private static final long serialVersionUID = 1L;
	private RegionType type;
	private List<Bonus> bonus;
	private List<City> city;
	private int id;
	
	/**
	 * Create a card of the given region
	 * @param type: region
	 */
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
	
	/**
	 * adds bonus to business card
	 * @param bonus
	 */
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
	
	public List<City> getCity() 
	{
		return Costants.clone(city);
	}

	@Override
	public boolean compare(Card card) 
	{
		BusinessCard b = (BusinessCard) card;
		if(b.getId() == id)
			return true;
		return false;
	}
}
