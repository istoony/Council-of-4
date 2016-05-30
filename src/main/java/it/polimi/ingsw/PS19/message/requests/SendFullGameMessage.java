/*
 * @author Andrea Milanta 
 */
package it.polimi.ingsw.PS19.message.requests;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.message.MessageInterpreterVisitor;
import it.polimi.ingsw.PS19.message.MessageType;

/*
 * 
 */
public class SendFullGameMessage extends Message 
{
	private static final long serialVersionUID = -1349912240299946517L;

	public SendFullGameMessage(int iD) 
	{
		id = iD;
	}
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.PS19.message.Message#getString()
	 */
	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) {
		// TODO Auto-generated method stub
		return null;
	}

}
