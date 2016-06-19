package it.polimi.ingsw.PS19.model.card;

import java.awt.Color;
import java.io.Serializable;

import it.polimi.ingsw.PS19.model.parameter.Costants;


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
		String s = "";
		if(color.equals(Color.decode("#FF0000"))){
			s= "Red";
		}
		else if(color.equals(Color.decode("#0000FF"))){
			s= "Blue";
		}
		else if(color.equals(Color.decode("#FF7F00"))){
			s= "Orange";
		}
		else if(color.equals(Color.decode("#000000"))){
			s= "White";
		}
		else if(color.equals(Color.decode("#FFFFFF"))){
			s=  "Black";
		}
		else if(color.equals(Color.decode("#FFC0CB"))){
			s= "Pink";
		}
		else if(color.equals(Color.decode(Costants.JOKERCOLOR))){
			s= "Joker";
		}
		return s;
	}

}
