package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.message.ElectCouncillorMessage;
import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.message.MessageType;

public class ActionFactory 
{
	public static Action createAction(Message message)
	{
		if(message.getType() == MessageType.MAIN_ELECT_REGION_COUNCILLOR)
		{
			ElectCouncillorMessage m = (ElectCouncillorMessage) message;
			return new MainElectRegionCouncillor(m.getColor(), m.getID(), m.getRegion());
		}
		return null;
		
	}
}
