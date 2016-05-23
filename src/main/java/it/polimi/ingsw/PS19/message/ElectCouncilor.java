package it.polimi.ingsw.PS19.message;

import java.awt.Color;

import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class ElectCouncilor extends Message 
{
	private static final long serialVersionUID = 8630705435374774862L;
	private Color color;
	private RegionType region = null;

	public ElectCouncilor(Color c, RegionType r) 
	{
		color = c;
		region = r;
		type = MessageType.MAIN_ELECT_REGION_COUNCILLOR;
	}
	
	public ElectCouncilor(Color c) 
	{
		color = c;
		type = MessageType.MAIN_ELECT_KING_COUNCILLOR;
	}
	
	public RegionType getRegion() 
	{
		return region;
	}
	
	public Color getColor() 
	{
		return color;
	}
	
	
	@Override
	public String getString() 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
