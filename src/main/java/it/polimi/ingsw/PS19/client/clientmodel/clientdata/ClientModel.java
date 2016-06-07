package it.polimi.ingsw.PS19.client.clientmodel.clientdata;

import java.util.List;

import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.map.Region;
import it.polimi.ingsw.PS19.model.parameter.ColorManager;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class ClientModel 
{
	private King king;
	private List<Region> regions;
	private ColorManager councilcolors;
	private List<Player> player;
	private int activeplayer;
	private String result;
	
	public void setKing(King king) {
		this.king = king;
	}
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}
	public void setCouncilcolors(ColorManager cuncilcolors) {
		this.councilcolors = cuncilcolors;
	}
	public void setActiveplayer(int activeplayer) {
		this.activeplayer = activeplayer;
	}
	public void setPlayer(List<Player> player) {
		this.player = player;
	}
	public int getActiveplayer() {
		return activeplayer;
	}
	public ColorManager getCouncilcolors() {
		return councilcolors;
	}
	public King getKing() {
		return king;
	}
	public List<Player> getPlayer() {
		return player;
	}
	public List<Region> getRegions() {
		return regions;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public Region getRegionByType(RegionType r)
	{
		for (Region region : regions) 
		{
			if(region.getType() == r)
				return region;
		}
		return null;
	}
	
	@Override
	public String toString() 
	{
		String s = "";
		//s += councilcolors.toString();
		s += "\n-----\n";
		s += king.toString();
		s += "\n-----\n";
		for (Region region : regions) 
			s += region.toString() + "\n";
		s += "\n-----\n";
		s += player.get(activeplayer).toString();
		return s;
	}
	
	
}
