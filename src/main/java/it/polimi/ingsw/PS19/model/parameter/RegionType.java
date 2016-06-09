package it.polimi.ingsw.PS19.model.parameter;

public enum RegionType 
{
	MOUNTAIN, HILL, PLAIN;
	
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
}
//INCOERENTE, da rivedere con file XML