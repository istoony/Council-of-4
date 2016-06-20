package it.polimi.ingsw.ps19.model.map;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.model.parameter.ColorManager;


public class Balcony implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2160537846072709310L;
	private List<Color> councilcolor;
	private static final int NUMBER_OF_COUNCILLOR = 4;
	
	private AvailableCouncillor avaiblecouncillor;
		
	public Balcony(AvailableCouncillor avaiblecouncillor, ColorManager colormanager) 
	{
		councilcolor = new ArrayList<>();
		this.avaiblecouncillor = avaiblecouncillor;
		int i = 0;
		while (i< NUMBER_OF_COUNCILLOR)
		{
			Color color = colormanager.getRandomColor();
			if(avaiblecouncillor.decrement(color))
			{
				councilcolor.add(color);
				i++;
			}
		}
	}
	
	public boolean setNewCouncill(Color color) 
	{
		if(!avaiblecouncillor.decrement(color))
			return false;
		avaiblecouncillor.increment(shiftRight());
		councilcolor.set(0,color);
		return true;
	}
	
	private Color shiftRight() 
	{
	    Color temp = councilcolor.get(NUMBER_OF_COUNCILLOR-1); 

	    for(int i = NUMBER_OF_COUNCILLOR-1; i > 0; i--)
	    	councilcolor.set(i,councilcolor.get(i-1)); 
	    return temp;
	}
	/**
	 * Return a list of council colors
	 * @return
	 */
	public List<Color> getCouncilcolor()
	{
		List<Color> colors = new ArrayList<>();
		for (Color c : councilcolor) 
		{
			colors.add(c);
		}
		return colors;
	}
	
	@Override
	public String toString() 
	{
		String s="";
		for ( Color c : councilcolor) 
		{
			if(c.equals(Color.decode("#FF0000"))){
				s+= "Red ";
			}
			else if(c.equals(Color.decode("#0000FF"))){
				s+= "Blue ";
			}
			else if(c.equals(Color.decode("#FF7F00"))){
				s+= "Orange ";
			}
			else if(c.equals(Color.decode("#000000"))){
				s+= "White ";
			}
			else if(c.equals(Color.decode("#FFFFFF"))){
				s+=  "Black ";
			}
			else if(c.equals(Color.decode("#FFC0CB"))){
				s+= "Pink ";
			}
		}		
		return s;
	}
	
}
