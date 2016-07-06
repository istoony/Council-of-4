package it.polimi.ingsw.ps19.model.parameter;

import java.util.ArrayList;
import java.util.List;

/**
 * Type of region enum
 */
public enum RegionType 
{
	MOUNTAIN, HILL, PLAIN;
	
	/**
	 * return enum correleted to number
	 * @param n
	 * @return
	 */
	public static RegionType valueOf(int n)
	{
		if(n == 0)
			return PLAIN;
		if(n == 1) 
			return HILL;
		if(n == 2) 
			return MOUNTAIN;
		return null;
	}
	
	public static List<RegionType> getValues()
	{
		List<RegionType> regions = new ArrayList<>();
		for(int i = 0; i < RegionType.values().length; i++)
			regions.add(RegionType.values()[i]);
		return regions;
	}
}
//INCOERENTE, da rivedere con file XML