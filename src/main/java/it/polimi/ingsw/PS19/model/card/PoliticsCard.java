package it.polimi.ingsw.PS19.model.card;

import it.polimi.ingsw.PS19.model.paramiter.CouncilColor;

public class PoliticsCard implements Card {
	
	CouncilColor color;
	
	
	/**
	 * Set card color
	 * @param color
	 */
	public void setColor(CouncilColor color) 
	{
		this.color = color;
	}
	
	public CouncilColor getColor() 
	{
		return color;
	}

}
