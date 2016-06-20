package it.polimi.ingsw.ps19.message.replies;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;

public class GameStartedMessage extends Reply 
{
	private static final long serialVersionUID = 8482425746181360920L;
	private int playerNumber;
	private int numberOfPlayer;
	
	public GameStartedMessage(int activePlayer, String result, int numberOfPlayer, int playerNumber) 
	{
		super(activePlayer, result);
		this.numberOfPlayer = numberOfPlayer;
		this.playerNumber = playerNumber;
		
	}
	
	public int getNumberOfPlayer() 
	{
		return numberOfPlayer;
	}
	
	public int getPlayerNumber() 
	{
		return playerNumber;
	}
	
	
	@Override
	public String toString() 
	{
		return "Game has started with " + getNumberOfPlayer() + " players\nYou are player number " + getPlayerNumber();
	}
	
	@Override
	public ClientUpdate display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}

}
