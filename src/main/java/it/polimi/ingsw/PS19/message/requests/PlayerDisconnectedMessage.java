package it.polimi.ingsw.PS19.message.requests;

import java.util.Queue;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.message.MessageInterpreterVisitor;
import it.polimi.ingsw.PS19.message.MessageType;

public class PlayerDisconnectedMessage extends Message 
{
	private static final long serialVersionUID = 1600487378262939610L;
	
	public PlayerDisconnectedMessage(Integer id) 
	{
		this.id = id;
	}
	
	@Override
	public String getString() 
	{
		return null;
	}

	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) {
		// TODO Auto-generated method stub
		return null;
	}

}
