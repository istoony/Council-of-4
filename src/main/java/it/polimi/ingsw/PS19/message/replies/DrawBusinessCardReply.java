package it.polimi.ingsw.PS19.message.replies;

import it.polimi.ingsw.PS19.client.ReplyVisitor;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class DrawBusinessCardReply extends Reply
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7817867055750090348L;
	
	private BusinessCard firstcard = null;
	private BusinessCard secondcard = null;
	private Player player;
	private RegionType region;
	
	public DrawBusinessCardReply(Player p, RegionType r, String result)
	{
		player = p;
		region = r;
		setResult(result);
	}
	public void setFirstcard(BusinessCard firstcard) {
		this.firstcard = firstcard;
	}
	public void setSecondcard(BusinessCard secondcard) {
		this.secondcard = secondcard;
	}
	public BusinessCard getFirstcard() {
		return firstcard;
	}
	public BusinessCard getSecondcard() {
		return secondcard;
	}
	public RegionType getRegion() {
		return region;
	}
	public Player getPlayer() {
		return player;
	}
	

	@Override
	public void display(ReplyVisitor replyvisitor) 
	{
		return replyvisitor.display(this);
	}


}
