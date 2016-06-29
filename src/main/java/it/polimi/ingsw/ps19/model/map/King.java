package it.polimi.ingsw.ps19.model.map;

import java.io.Serializable;

public class King implements Serializable
{

	private static final long serialVersionUID = 4394670612565992362L;
	
	City currentcity;
	Balcony balcony;
	
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
