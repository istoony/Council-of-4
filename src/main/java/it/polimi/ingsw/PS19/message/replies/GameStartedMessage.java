package it.polimi.ingsw.PS19.message.replies;

import it.polimi.ingsw.PS19.client.ReplyVisitor;
import it.polimi.ingsw.PS19.client.clientmodel.ClientUpdate;

public class GameStartedMessage extends Reply 
{
	private static final long serialVersionUID = 8482425746181360920L;
	private int playerNumber;
	private int numberOfPlayer;
	
	public GameStartedMessage(int pNum, int numOfPlayers) 
	{
		playerNumber = pNum;
		numberOfPlayer = numOfPlayers;
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
