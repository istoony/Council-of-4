package it.polimi.ingsw.PS19.message.replies;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.PS19.client.ReplyVisitor;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.map.Region;

public class SendFullGameReply extends Reply 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4013509319790221079L;
	
	private List<Region> regions;
	private List<Player> player;
	private King king;
	
	public SendFullGameReply(List<Region> regions2, List<Player> p, King k) 
	{
		regions = regions2;
		player = p;
		king = k;
	}
	
	public List<Region> getRegions() {
		return regions;
	}
	public List<Player> getPlayer() {
		return player;
	}
	public King getKing() {
		return king;
	}
	
	@Override
	public void display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
