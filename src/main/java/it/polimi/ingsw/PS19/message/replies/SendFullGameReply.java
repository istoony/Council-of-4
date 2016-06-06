package it.polimi.ingsw.PS19.message.replies;

import java.util.ArrayList;

import it.polimi.ingsw.PS19.Client.ReplyVisitor;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.map.Region;

public class SendFullGameReply extends Reply 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4013509319790221079L;
	
	private ArrayList<Region> regions;
	private ArrayList<Player> player;
	private King king;
	
	public SendFullGameReply(ArrayList<Region> r, ArrayList<Player> p, King k) 
	{
		regions = r;
		player = p;
		king = k;
	}
	
	public ArrayList<Region> getRegions() {
		return regions;
	}
	public ArrayList<Player> getPlayer() {
		return player;
	}
	public King getKing() {
		return king;
	}
	
	@Override
	public void display(ReplyVisitor replyvisitor) 
	{
		replyvisitor.display(this);
		
	}

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}
}
