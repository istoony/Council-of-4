/*
 * @author Andrea Milanta 
 */
package it.polimi.ingsw.ps19.message.requests;

import java.awt.Color;

import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.ps19.model.map.King;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

/**
 * Message to notify of a new councillor elected
 */
public class ElectCouncillorMessage extends Request 
{
	private static final long serialVersionUID = 8630705435374774862L;
	private Color color;
	private RegionType region = null;
	private King king = null;
	private Boolean mainAction;

	/**
	 * Constructor if election on region balcony
	 * @param c
	 * @param r
	 */
	public ElectCouncillorMessage(Color c, RegionType r) 
	{
		color = c;
		region = r;
	}
	
	/**
	 * Constructor if election on king balcony
	 * @param c
	 * @param k
	 */
	public ElectCouncillorMessage(Color c, King k) 
	{
		color = c;
		king = k;
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

}
