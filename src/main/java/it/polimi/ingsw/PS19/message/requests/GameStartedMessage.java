package it.polimi.ingsw.PS19.message.requests;

import it.polimi.ingsw.PS19.controller.action.Action;

public class GameStartedMessage extends Request 
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
	public String getString() 
	{
		String s = "Game has started with " + getNumberOfPlayer() + " players\nYou are player number " + getPlayerNumber();
		return s;
	}

	@Override
	public Action accept(MessageInterpreterVisitor messageInterpreter) {
		// TODO Auto-generated method stub
		return null;
	}

}
