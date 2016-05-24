package it.polimi.ingsw.PS19.message;

public class GameStartedMessage extends Message 
{
	private static final long serialVersionUID = 8482425746181360920L;
	private int playerNumber;
	private int numberOfPlayer;
	
	public GameStartedMessage(int pNum, int numOfPlayers) 
	{
		type = MessageType.GAME_STARTED;
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
		return null;
	}

}
