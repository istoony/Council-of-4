package it.polimi.ingsw.PS19.message;

public class ActivePlayerMessage extends Message 
{
	private static final long serialVersionUID = 6744987619447786564L;
	
	public ActivePlayerMessage(int activePlayer) 
	{
		type = MessageType.ID_ACTIVE_PLAYER;
		id = activePlayer;
	}
	
	@Override
	public String getString() 
	{
		return null;
	}

}
