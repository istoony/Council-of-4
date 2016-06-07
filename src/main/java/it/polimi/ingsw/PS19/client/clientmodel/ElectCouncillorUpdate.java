package it.polimi.ingsw.PS19.client.clientmodel;


import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.model.map.Balcony;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class ElectCouncillorUpdate implements ClientUpdate
{
	
	private Boolean king;
	private RegionType region;
	private Balcony balcony;
	private int activeplayer;
	private String result;
	public ElectCouncillorUpdate(String res, Balcony b, int active) 
	{
		result = res;
		king = true;
		balcony = b;
		activeplayer = active;
				
	}
	public ElectCouncillorUpdate(String res, Balcony b, RegionType r, int active) 
	{
		king = false;
		result = res;
		balcony = b;
		activeplayer = active;
		region = r;
	}
	@Override
	public void update(ClientModel model) 
	{
		model.setActiveplayer(activeplayer);
		model.setResult(result);
		if(king == true)
			model.getKing().setBalcony(balcony);
		else
			model.getRegionByType(region).setBalcony(balcony);
	}

}
