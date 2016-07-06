package it.polimi.ingsw.ps19.model.card;

import java.awt.Color;
import java.io.Serializable;

/**
 * Class to rapresent a politic card
 */
public class PoliticsCard implements Card, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7874520741873353611L;
	private Color color;

	/**
	 * Constructor for a card of the specified color
	 * @param color
	 */
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
}
