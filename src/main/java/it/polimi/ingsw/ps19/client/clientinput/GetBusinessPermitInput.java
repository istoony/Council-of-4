package it.polimi.ingsw.ps19.client.clientinput;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.message.requests.GetBusinessCardMessage;
import it.polimi.ingsw.ps19.message.requests.Request;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.map.Region;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

/**
 * Class that creates a new {@link GetBusinessCardMessage} from user inputs and local model
 */
public class GetBusinessPermitInput extends SatisfyCouncilInput 
{
	private BusinessCard businessCard;
	private RegionType regionType;
	private List<Color> colors;
	
	/**
	 * Constructor
	 * @param m
	 */
	public GetBusinessPermitInput(ClientModel m) 
	{
		model = m;
	}
	
	@Override
	public boolean isPossible() 
	{
		if(!getAvailableRegions().isEmpty())
			return true;
		return false;
	}

	@Override
	public Request execute(ClientUI userInterface) throws InvalidInsertionException 
	{
		regionType = userInterface.getRegion(getAvailableRegions());
		Region region = model.getRegionByType(regionType);
		colors = satisfyCouncil(region.getBalcony(), userInterface);
		List<BusinessCard> cards = new ArrayList<>();
		if(region.getFirstcard() != null)
			cards.add(region.getFirstcard());
		if(region.getSecondcard() != null)
			cards.add(region.getSecondcard());
		businessCard = userInterface.getBusiness(cards);
		return buildMessage();
	}

	@Override
	protected Request buildMessage() 
	{
		return new GetBusinessCardMessage(businessCard, regionType, colors);
	}

	@Override
	public String toString(Language l) 
	{
		return l.getString(this);
	}
}
