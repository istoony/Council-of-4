package it.polimi.ingsw.ps19.client.clientmodel;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.message.requests.DrawBusinessCardRequest;
import it.polimi.ingsw.ps19.message.requests.Request;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.map.AvailableCouncillor;
import it.polimi.ingsw.ps19.model.map.King;
import it.polimi.ingsw.ps19.model.map.Region;

/**
 * Draw a business card from one of yours (bonus action)
 */
public class GetBusinessToDrawUpdate extends ElectCouncillorUpdate 
{
	private int howMany;
	private List<BusinessCard> chosenCards;
	
	/**
	 * Constructo
	 * @param res
	 * @param r
	 * @param k
	 * @param ac
	 * @param p
	 * @param active
	 * @param hM
	 */
	public GetBusinessToDrawUpdate(String res, List<Region> r, King k, AvailableCouncillor ac, List<Player> p,
			int active, int hM) 
	{
		super(res, r, k, ac, p, active);
		howMany = hM;
	}
	
	@Override
	public Request execute(ClientUI userInterface, ClientModel model) throws InvalidInsertionException
	{
		List<BusinessCard> cards = new ArrayList<>();
		chosenCards = new ArrayList<>();
		for(Region r : model.getRegions())
		{
			cards.add(r.getFirstcard());
			cards.add(r.getSecondcard());
		}
		for(int i = 0; i < howMany && i < 6; i++)
		{
			chosenCards.add(userInterface.getBusiness(cards));
		}
		return new DrawBusinessCardRequest(chosenCards);
	}
	
}
