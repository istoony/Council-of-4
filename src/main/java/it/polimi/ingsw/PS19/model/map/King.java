package it.polimi.ingsw.PS19.model.map;

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
	
	@Override
	public String toString() 
	{
		String s = "";
		s += balcony.toString() + "\n";
		s += "CITY:  " + currentcity.toString() + "\n";
		return s;
	}
	/**
	 * @param currentcity the currentcity to set
	 */
	public void setCurrentcity(City currentcity) {
		this.currentcity = currentcity;
	}
}
