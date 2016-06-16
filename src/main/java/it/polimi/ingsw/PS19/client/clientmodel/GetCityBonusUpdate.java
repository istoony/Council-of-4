package it.polimi.ingsw.PS19.client.clientmodel;

import java.util.List;

import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.AvailableCouncillor;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.map.Region;

public class GetCityBonusUpdate extends ElectCouncillorUpdate {

	public GetCityBonusUpdate(String res, List<Region> r, King k, AvailableCouncillor ac, List<Player> p, int active) {
		super(res, r, k, ac, p, active);
	}

}
