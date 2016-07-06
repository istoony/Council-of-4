package it.polimi.ingsw.ps19.message.replies;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;
import it.polimi.ingsw.ps19.message.Message;

/**
 * OVERVIEW: The Class Reply is the first child of Message class. 
 * 		This class is first standard to communicate from server to client.
 */
public abstract class Reply extends Message
{
	
	/** The result is a string message that provide to send a feedback form server to client. */
	private String result;
	
	/** The active player contains a current player turn Id who must perform the moves*/
	private int activePlayer;
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1984682021445434304L;
	
	/**
	 * Instantiates a new reply message with all parameter.
	 *
	 * @param activePlayer the active player
	 * @param result the result
	 */
	public Reply(int activePlayer, String result)
	{
		this.activePlayer =activePlayer;
		this.result = result;
	}
	
	/**
	 * Display.
	 *
	 * @param replyvisitor the replyvisitor
	 * @return the client update
	 */
	public abstract ClientUpdate display(ReplyVisitor replyvisitor);
	
	/**
	 * Sets the active player.
	 * In a few cases it is later necessary to set the player id.
	 *
	 * @param activePlayer is the new active player
	 */
	public void setActivePlayer(int activePlayer) 
	{
		this.activePlayer = activePlayer;
	}
	
	/**
	 * Gets the active player of the message.
	 *
	 * @return the active player
	 */
	public int getActivePlayer() 
	{
		return activePlayer;
	}
	
	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public String getResult() 
	{
		return result;
	}
}
