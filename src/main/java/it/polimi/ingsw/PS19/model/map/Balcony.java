package it.polimi.ingsw.PS19.model.map;

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
	    //make temp variable to hold last element
	    Color temp = councilcolor.get(NUMBER_OF_COUNCILLOR-1); 
	    
	    //make a loop to run through the array list
	    for(int i = NUMBER_OF_COUNCILLOR-1; i > 0; i--)
	    {
	        //set the last element to the value of the 2nd to last element
	    	councilcolor.set(i,councilcolor.get(i-1)); 
	    }
	    return temp;
	}
	public List<Color> getCouncilcolor()
	{
		List<Color> colors = new ArrayList<>();
		colors.addAll(councilcolor);
		return colors;
	}
	
	@Override
	public String toString() 
	{
		String s="*************\n";
		for ( Color c : councilcolor) 
		{
			s = s + " " + c.toString();
		}		
		return s;
	}
	
}
