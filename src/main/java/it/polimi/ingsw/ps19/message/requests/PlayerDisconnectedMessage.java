package it.polimi.ingsw.ps19.message.requests;


import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.action.MessageInterpreterVisitor;

public class PlayerDisconnectedMessage extends Request 
{
	private static final long serialVersionUID = 1600487378262939610L;
	
	public PlayerDisconnectedMessage(Integer id) 
	{
		this.id = id;
	}
	
	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) 
	{
		return messageInterpreter.visit(this);
	}

}
