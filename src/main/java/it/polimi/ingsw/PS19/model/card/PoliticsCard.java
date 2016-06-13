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
	public boolean compare(Card card) 
	{
		PoliticsCard c = (PoliticsCard) card;
		if(c!=null && c.getColor().equals(this.color))
			return true;
		return false;
	}
	
	@Override
	public String toString() 
	{
		return color.toString();
	}

}
