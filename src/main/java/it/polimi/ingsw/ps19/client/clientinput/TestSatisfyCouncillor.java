package it.polimi.ingsw.ps19.client.clientinput;

import java.awt.Color;
import java.util.List;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.message.requests.Request;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

public class TestSatisfyCouncillor extends SatisfyCouncilInput
{
	public TestSatisfyCouncillor(ClientModel model)
	{
		this.model = model;
	}
	@Override
	public List<RegionType> getAvailableRegions()
	{
		return super.getAvailableRegions();
	}
	@Override
	public List<PoliticsCard> getAvailablePolitics(List<Color> balcony, List<PoliticsCard> cards)
	{
		return super.getAvailablePolitics(balcony, cards);
	}
	

	@Override
	public boolean isPossible() {
		return false;
	}
	@Override
	public Request execute(ClientUI userInterface) throws InvalidInsertionException {
		return null;
	}
	@Override
	protected Request buildMessage() {
		return null;
	}
	@Override
	public String toString(Language l) {
		return null;
	}
}
