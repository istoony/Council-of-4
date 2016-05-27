package it.polimi.ingsw.PS19.message;

import java.awt.Color;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.MainElectCouncillor;
import it.polimi.ingsw.PS19.message.requests.SendFullGameMessage;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class MessageInterpreterVisitorImp implements MessageInterpreterVisitor {

	@Override
	public Action visit(ElectCouncillorMessage message) 
	{
		int playerId = message.getId();
		RegionType region = message.getRegion();
		Color councicolor =  message.getColor();
		
		if(region == null)
		{
			King king = message.getKing();
			return new MainElectCouncillor(councicolor, playerId, king);	
		}
		
		return new MainElectCouncillor(councicolor, playerId, region);
	}

	@Override
	public Action visit(SendFullGameMessage message) 
	{
		// TODO Auto-generated method stub
		return null;
	}

}
