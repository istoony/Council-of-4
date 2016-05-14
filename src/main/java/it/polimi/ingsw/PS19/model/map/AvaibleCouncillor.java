package it.polimi.ingsw.PS19.model.map;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.PS19.model.paramiter.ColorManager;

public class AvaibleCouncillor 
{
	private Map<Color,Integer> avaiblecouncillor;
	private ColorManager listofcolors;
	
	/**
	 * Impostare per ogni colore il numero di consiglieri disponibili
	 */
	public AvaibleCouncillor(int numberoffreecouncillor, ColorManager colors) 
	{
		listofcolors = colors;
		avaiblecouncillor = new HashMap<Color, Integer>();
		
		for (Color color : listofcolors.getColors())
		{
			avaiblecouncillor.put(color, numberoffreecouncillor);
		}
	}
	
	public boolean decrement(Color color)
	{
		Integer numberofcouncillor = avaiblecouncillor.get(color);
		if(numberofcouncillor.intValue() == 0)
			return false;
		numberofcouncillor--;
		avaiblecouncillor.put(color, numberofcouncillor);
		return true;
		
	}
	
	public void increment(Color color)
	{
		Integer numberofcouncillor = avaiblecouncillor.get(color);
		numberofcouncillor++;
		avaiblecouncillor.put(color, numberofcouncillor);
	}
	@Override
	public String toString() 
	{
		String s = "\n+++++++++++++\n";
		for (Color c : listofcolors.getColors()) 
		{
			s = s + c.toString() + " " + avaiblecouncillor.get(c) + "     ";
		}
		return s;
	}
	
}
