package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.message.ElectCouncillorMessage;
import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.message.MessageType;
import it.polimi.ingsw.PS19.message.SendActionMessage;

public class ActionFactory 
{
	public static Action createAction(Message message)
	{
		/***CONFIGURATION MESSAGES**
		if(message.getType() == MessageType.SEND_FULL_GAME)
			return new SendFullGame();
		if(message.getType() == MessageType.)
			return new SendFullGame();
		
		/***ACTION MESSAGES**
		if(message.getType() == MessageType.ACTION)
		{
			SendActionMessage m = (SendActionMessage) message;
			return m.getAction();
		}
		
		/***SECOND ACTION MESSAGES***/
		/*if(message.getType() == MessageType.ID_ACTIVE_PLAYER)
		{
			ElectCouncillorMessage m = (ElectCouncillorMessage) message;
			return new MainElectRegionCouncillor(m.getColor(), m.getID(), m.getRegion());
		} */
		return null;
		
	}
}
