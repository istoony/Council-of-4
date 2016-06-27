package it.polimi.ingsw.ps19.message.replies;

import java.util.List;

import it.polimi.ingsw.ps19.model.Player;

public class EndGameReply extends SendFullPlayerReply
{

	public EndGameReply(int activePlayer, String result, List<Player> player) 
	{
		super(activePlayer, result, player);
	}
	
	private static final long serialVersionUID = -4749037445037762674L;

}
