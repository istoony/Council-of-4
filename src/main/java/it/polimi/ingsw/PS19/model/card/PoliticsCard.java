package it.polimi.ingsw.PS19.model.card;

import java.awt.Color;


public class PoliticsCard implements Card {
	
	private Color color;

	public PoliticsCard(Color color) 
	{
		this.color = color;
	}
	
	public Color getColor() 
	{
		return color;
	}
	

	public boolean equals(PoliticsCard c) 
	{
		if(c.getColor().equals(this.color))
			return true;
		return false;
	}
	
	@Override
	public String toString() 
	{
		return color.toString();
	}

}
