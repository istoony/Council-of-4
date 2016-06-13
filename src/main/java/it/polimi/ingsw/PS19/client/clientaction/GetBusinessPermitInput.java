package it.polimi.ingsw.PS19.client.clientaction;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.message.requests.GetBusinessCardMessage;
import it.polimi.ingsw.ps19.message.requests.Request;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
import it.polimi.ingsw.ps19.model.map.Region;
import it.polimi.ingsw.ps19.model.parameter.RegionType;


public class GetBusinessPermitInput extends SatisfyCouncilInput 
{
	private BusinessCard businessCard;
	private RegionType regionType;
	private List<Color> colors;
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
		regionType = userInterface.getRegion(getAvailableRegions());
		Region region = model.getRegionByType(regionType);
		List<Color> balcony = region.getBalcony().getCouncilcolor();
		List<PoliticsCard> politicCards = model.getMyPlayer().getPoliticcard();
		colors = new ArrayList<>();
		PoliticsCard politicCard;
		politicCards = getAvailablePolitics(balcony, politicCards);
		while(count < 4 && !politicCards.isEmpty())
		{
			if(count > 0)
				politicCards.add(null);
			politicCard = userInterface.getPolitic(politicCards);
			if(politicCard == null)
				break;
			politicCards.remove(politicCards.size()-1);
			balcony.remove(politicCard.getColor());
			colors.add(politicCard.getColor());
			politicCards.remove(politicCard);
			count++;
			politicCards = getAvailablePolitics(balcony, politicCards);
		}
		List<BusinessCard> cards = new ArrayList<>();
		cards.add(region.getFirstcard());
		cards.add(region.getSecondcard());
		businessCard = userInterface.getBusiness(cards);
		return buildMessage();
	}

	@Override
	protected Request buildMessage() 
	{
		return new GetBusinessCardMessage(businessCard, regionType, colors);
	}
}
