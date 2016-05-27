/*
 * @author Andrea Milanta 
 */
package it.polimi.ingsw.PS19.message;

import java.awt.Color;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

/*
 * 
 */
public class ElectCouncillorMessage extends Message 
{
	private static final long serialVersionUID = 8630705435374774862L;
	private Color color;
	private RegionType region = null;
	private King king = null;

	public ElectCouncillorMessage(Color c, RegionType r) 
	{
		color = c;
		region = r;
		type = MessageType.MAIN_ELECT_REGION_COUNCILLOR;
	}
	public ElectCouncillorMessage(Color c, King k) 
	{
		color = c;
		king = k;
		type = MessageType.MAIN_ELECT_REGION_COUNCILLOR;
	}
	
	public ElectCouncillorMessage(Color c) 
	{
		color = c;
		type = MessageType.MAIN_ELECT_KING_COUNCILLOR;
	}
	
	public King getKing() 
	{
		return king;
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
	public Action accept(MessageInterpreterVisitor messageInterpreter) 
	{
		return messageInterpreter.visit(this);
	}
	
	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}

}
