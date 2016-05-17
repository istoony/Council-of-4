package it.polimi.ingsw.PS19.model.map;

import java.awt.Color;
import java.util.ArrayList;

import it.polimi.ingsw.PS19.model.paramiter.ColorManager;


public class Balcony 
{
	private ArrayList<Color> councilcolor;
	private static final int NUMBER_OF_COUNCILLOR = 4;
	
	private AvaibleCouncillor avaiblecouncillor;
		
	public Balcony(AvaibleCouncillor avaiblecouncillor, ColorManager colormanager) 
	{
		this.avaiblecouncillor = avaiblecouncillor;
		for(int i = 0; i< NUMBER_OF_COUNCILLOR; i++)
		{
			Color color = colormanager.getRandomColor();
			if(avaiblecouncillor.decrement(color))
				councilcolor.add(color);
			else
				i--;
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
	
	@Override
	public String toString() 
	{
		String s="*************\n";
		for ( Color c : councilcolor) 
		{
			s = s + " " + c.toString();
		}
		s = s + avaiblecouncillor.toString();
		
		return s;
	}
}
