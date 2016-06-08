package it.polimi.ingsw.PS19.model.card;

import java.awt.Color;
import java.io.Serializable;


public class PoliticsCard implements Card, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7874520741873353611L;
	private Color color;

	public PoliticsCard(Color color) 
	{
		this.color = color;
	}
	
	public Color getColor() 
	{
		return color;
	}
	
	@Override
	public boolean equals(Card card) 
	{
		PoliticsCard carda = (PoliticsCard) card;
		if(carda!=null && carda.getColor().equals(this.color))
			return true;
		return false;
	}
	
	@Override
	public String toString() 
	{
		return color.toString();
	}

}
