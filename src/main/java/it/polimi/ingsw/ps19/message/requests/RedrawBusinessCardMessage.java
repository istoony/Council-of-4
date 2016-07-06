package it.polimi.ingsw.ps19.message.requests;

import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

/**
 * Message to Redraw the shown business cards on the region
 */
public class RedrawBusinessCardMessage extends Request
{
	private static final long serialVersionUID = -1320917359987159094L;
	private RegionType region;
	
	/**
	 * Constructor for message relative to region r
	 * @param r
	 */
	public RedrawBusinessCardMessage(RegionType r) 
	{
		region = r;
	}
	public RegionType getRegion() 
	{
		return region;
	}

	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) 
	{
		return messageInterpreter.visit(this);
	}

}
