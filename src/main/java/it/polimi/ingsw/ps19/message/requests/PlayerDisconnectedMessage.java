package it.polimi.ingsw.ps19.message.requests;


import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.action.MessageInterpreterVisitor;

/**
 * Message to notify of a disconnected player
 */
public class PlayerDisconnectedMessage extends Request 
{
	private static final long serialVersionUID = 1600487378262939610L;
	
	/**
	 * Constructor for message where player "id" has disconnected
	 * @param id
	 */
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
