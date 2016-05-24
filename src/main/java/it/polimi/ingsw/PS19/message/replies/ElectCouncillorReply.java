package it.polimi.ingsw.PS19.message.replies;


import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.map.Region;

public class ElectCouncillorReply extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Region region = null;
	private Player player;
	private King king = null;
	
	public ElectCouncillorReply(Region region, Player player) 
	{
		this.region = region;
		this.player = player;
	}
	
	public ElectCouncillorReply(King king, Player player) 
	{
		this.king = king;
		this.player = player;
	}
	
	public Region getRegion() 
	{
		return region;
	}
	
	public King getKing() 
	{
		return king;
	}
	
	public Player getPlayer() 
	{
		return player;
	}
	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}

}
