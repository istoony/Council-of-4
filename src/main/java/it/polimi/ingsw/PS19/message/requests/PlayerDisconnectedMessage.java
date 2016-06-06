package it.polimi.ingsw.PS19.message.requests;


import it.polimi.ingsw.PS19.controller.action.Action;

public class PlayerDisconnectedMessage extends Request 
{
	private static final long serialVersionUID = 1600487378262939610L;
	
	public PlayerDisconnectedMessage(Integer id) 
	{
		this.id = id;
	}
	
	@Override
	public String toString() 
	{
		return null;
	}

	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) {
		// TODO Auto-generated method stub
		return null;
	}

}
