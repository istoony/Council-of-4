/*
 * @author Andrea Milanta 
 */
package it.polimi.ingsw.PS19.message;

/*
 * 
 */
public class SendFullGameMessage extends Message 
{
	private static final long serialVersionUID = -1349912240299946517L;

	public SendFullGameMessage(int iD) 
	{
		id = iD;
		type = MessageType.SEND_FULL_GAME;
	}
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.PS19.message.Message#getString()
	 */
	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}

}
