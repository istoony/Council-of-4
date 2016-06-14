package it.polimi.ingsw.PS19.message.requests;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class RedrawBusinessCardMessage extends Request {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1320917359987159094L;
	private RegionType region;
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
