package it.polimi.ingsw.PS19.model.map;

import java.awt.Color;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.PS19.model.parameter.ColorManager;

public class AvaibleCouncillor implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<Color,Integer> councillor;
	private ColorManager listofcolors;
	
	/**
	 * Impostare per ogni colore il numero di consiglieri disponibili
	 */
	public AvaibleCouncillor(int numberoffreecouncillor, ColorManager colors) 
	{
		listofcolors = colors;
		councillor = new HashMap<>();
		
		for (Color color : listofcolors.getColors())
			councillor.put(color, numberoffreecouncillor);
	}
	
	public boolean decrement(Color color)
	{
		Integer numberofcouncillor = councillor.get(color);
		if(numberofcouncillor.intValue() == 0)
			return false;
		numberofcouncillor--;
		councillor.put(color, numberofcouncillor);
		return true;
		
	}
	
	public void increment(Color color)
	{
		Integer numberofcouncillor = councillor.get(color);
		numberofcouncillor++;
		councillor.put(color, numberofcouncillor);
	}
	
	@Override
	public String toString() 
	{
		String s = "\n+++++++++++++\n          ";
		for (Color c : listofcolors.getColors()) 
		{
			s = s + c.toString() + "-->" + councillor.get(c) + "\n          ";
		}
		return s;
	}
	
}
