package it.polimi.ingsw.PS19.client.clientaction;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.PS19.model.card.PoliticsCard;
import it.polimi.ingsw.PS19.model.map.Balcony;
import it.polimi.ingsw.PS19.model.map.Region;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public abstract class SatisfyCouncilInput extends ClientAction 
{
	protected List<RegionType> getAvailableRegions()
	{
		List<RegionType> availableRegions = new ArrayList<>();
		for(Region r: model.getRegions())
		{
			
			if(getCost(r.getBalcony(), model.getMyPlayer().getPoliticcard()) <= 10 && getCost(r.getBalcony(), model.getMyPlayer().getPoliticcard()) <= model.getMyPlayer().getMoney());
				availableRegions.add(r.getType());
		}
		return availableRegions;
	}
	
	protected boolean kingAvailable()
	{
		return false;
	}
	
	private int getCost(Balcony balcony, List<PoliticsCard> cards)
	{
		int cost = 13;
		List<Boolean> catched = new ArrayList<>();
		for(int j = 0; j < balcony.getCouncilcolor().size(); j++)
		{
			catched.add(false);
			Color balconyColor = balcony.getCouncilcolor().get(j); 
			for(int i = 0; i < cards.size(); i++)
			{
				
				if(balconyColor.equals(cards.get(i).getColor()))
				{
					cost -= 3;
					if(cost == 1)
						cost = 0;
					catched.set(j, true);
					cards.remove(i);
					break;
				}
			}
		}
		return cost;
	}
}
