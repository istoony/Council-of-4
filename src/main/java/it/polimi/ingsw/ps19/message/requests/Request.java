/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.ps19.message.requests;

import it.polimi.ingsw.ps19.controller.action.Action;
import it.polimi.ingsw.ps19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.ps19.message.Message;

/*
 * Abstract class that implements a message
 */
public abstract class Request extends Message
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8039426365908402102L;

	public abstract Action accept(MessageInterpreterVisitor messageInterpreter);
}
