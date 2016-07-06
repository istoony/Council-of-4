package it.polimi.ingsw.ps19.model.map;

import java.io.Serializable;

/**
 * Class that rappresents the king
 */
public class King implements Serializable
{

	private static final long serialVersionUID = 4394670612565992362L;
	
	City currentcity;
	Balcony balcony;
	
	/**
	 * Constructor of king in the given city
	 * @param city
	 */
	public King(City city) 
	{
		currentcity = city;
	}
	public void setBalcony(Balcony balcony) 
	{
		this.balcony = balcony;
	}
	
	public City getCurrentcity() 
	{
		return currentcity;
	}
	
	public Balcony getBalcony() 
	{
		return balcony;
	}
	/**
	 * @param currentcity the currentcity to set
	 */
	public void setCurrentcity(City currentcity) {
		this.currentcity = currentcity;
	}
}
