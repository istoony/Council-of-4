package it.polimi.ingsw.PS19.client.clientaction;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.PS19.client.ClientUI;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.message.requests.Request;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.card.PoliticsCard;
import it.polimi.ingsw.PS19.model.map.Region;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class GetBusinessPermitInput extends SatisfyCouncilInput 
{
	public GetBusinessPermitInput(ClientModel m) 
	{
		model = m;
	}
	
	@Override
	public boolean isPossible() 
	{
		if(getAvailableRegions() != null)
			return true;
		return false;
	}

	@Override
	public Request Execute(ClientUI userInterface) throws InvalidInsertionException 
	{
		int count = 0;
		RegionType regionType = userInterface.getRegion(getAvailableRegions());
		Region region = model.getRegionByType(regionType);
		List<Color> balcony = region.getBalcony().getCouncilcolor();
		List<PoliticsCard> politicCards = model.getMyPlayer().getPoliticcard();
		
		List<Color> colors = null;
		PoliticsCard politicCard = null;
		while(count < 4 && politicCards.size() > 0)
		{
			politicCard = userInterface.getPolitic(politicCards);
			
			count++;
		}
		List<BusinessCard> cards = new ArrayList<>();
		cards.add(region.getFirstcard());
		cards.add(region.getSecondcard());
		BusinessCard card = userInterface.getBusiness(cards);
		return null;
	}

	@Override
	protected Request buildMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
