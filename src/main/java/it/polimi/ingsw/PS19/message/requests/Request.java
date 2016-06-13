/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.PS19.message.requests;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor;
import it.polimi.ingsw.PS19.message.Message;

/**
 * Abstract class that rappresents a message from client to server
 */
public abstract class Request extends Message
{	
	private static final long serialVersionUID = -8039426365908402102L;

	/**
	 * Interpreter method
	 * @param messageInterpreter
	 * @return
	 */
	public abstract Action accept(MessageInterpreterVisitor messageInterpreter);
}
