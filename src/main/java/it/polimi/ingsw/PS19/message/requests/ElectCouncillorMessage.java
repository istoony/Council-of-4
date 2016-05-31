/*
 * @author Andrea Milanta 
 */
package it.polimi.ingsw.PS19.message.requests;

import java.awt.Color;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

/*
 * 
 */
public class ElectCouncillorMessage extends Request 
{
	private static final long serialVersionUID = 8630705435374774862L;
	private Color color;
	private RegionType region = null;
	private King king = null;
	private Boolean mainAction;

	public ElectCouncillorMessage(Color c, RegionType r) 
	{
		color = c;
		region = r;
	}
	public ElectCouncillorMessage(Color c, King k) 
	{
		color = c;
		king = k;
	}
	
	public ElectCouncillorMessage(Color c) 
	{
		color = c;
	}
	
	public void setMainAction(Boolean mainAction) 
	{
		this.mainAction = mainAction;
	}
	
	public Boolean getMainAction() {
		return mainAction;
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
