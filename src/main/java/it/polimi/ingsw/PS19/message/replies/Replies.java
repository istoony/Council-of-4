package it.polimi.ingsw.PS19.message.replies;

import it.polimi.ingsw.PS19.message.ElectCouncillorMessage;
import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.message.MessageType;
import it.polimi.ingsw.PS19.model.Model;

public class Replies 
{
	public static Message createTrueReplies(Message m, Model model)
	{
		if(m.getType() == MessageType.SEND_FULL_GAME)
			return new SendFullGameReply(model);
		
		if(m.getType() == MessageType.MAIN_ELECT_REGION_COUNCILLOR)
		{	
			ElectCouncillorMessage message = (ElectCouncillorMessage) m;
			return new ElectCouncillorReply(model.getMap().getRegionByType(message.getRegion()), model.getPlayerById(message.getID()));
		}
		if(m.getType() == MessageType.MAIN_ELECT_KING_COUNCILLOR)
		{	
			ElectCouncillorMessage message = (ElectCouncillorMessage) m;
			return new ElectCouncillorReply(model.getMap().getKing(), model.getPlayerById(message.getID()));
		}
		return null;
	}
}
