package it.polimi.ingsw.PS19.model.map;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import it.polimi.ingsw.PS19.model.paramiter.CouncilColor;

public class AvaibleCouncillor 
{
	private Map<CouncilColor,Integer> avaiblecouncillor;
	
	/**
	 * Impostare per ogni colore il numero di consiglieri disponibili
	 */
	public AvaibleCouncillor() 
	{
		avaiblecouncillor = new TreeMap<CouncilColor, Integer>();
		int i = 2;
		for (CouncilColor c : CouncilColor.values()) 
		{
			avaiblecouncillor.put(c, i);
			i++;
		}
	}
	
	public boolean decrement(CouncilColor color)
	{
		Integer numberofcouncillor = avaiblecouncillor.get(color);
		if(numberofcouncillor.intValue() == 0)
			return false;
		numberofcouncillor--;
		avaiblecouncillor.put(color, numberofcouncillor);
		return true;
		
	}
	
	public void increment(CouncilColor color)
	{
		Integer numberofcouncillor = avaiblecouncillor.get(color);
		numberofcouncillor++;
		avaiblecouncillor.put(color, numberofcouncillor);
	}
	@Override
	public String toString() 
	{
		String s = "\n+++++++++++++\n";
		for (CouncilColor c : CouncilColor.values()) 
		{
			s = s + c.toString() + " " + avaiblecouncillor.get(c) + "     ";
		}
		return s;
	}
	
}
