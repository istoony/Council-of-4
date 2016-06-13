package it.polimi.ingsw.PS19.message.requests;


import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor;

/**
 * Message to notify that a player had disconnected
 */
public class PlayerDisconnectedMessage extends Request 
{
	private static final long serialVersionUID = 1600487378262939610L;
	
	/**
	 * Constructor
	 * @param id: id of disconnected player
	 */
	public PlayerDisconnectedMessage(Integer id) 
	{
		this.id = id;
	}
	
	@Override
	public String toString() 
	{
		return new String();
	}

	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) 
	{
		return null;
	}

}
