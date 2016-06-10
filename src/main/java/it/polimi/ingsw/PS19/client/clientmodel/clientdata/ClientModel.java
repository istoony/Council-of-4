package it.polimi.ingsw.PS19.client.clientmodel.clientdata;

import java.util.List;

import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.map.AvailableCouncillor;
import it.polimi.ingsw.ps19.model.map.King;
import it.polimi.ingsw.ps19.model.map.NobilityPath;
import it.polimi.ingsw.ps19.model.map.Region;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

public class ClientModel 
{
	private King king;
	private List<Region> regions;
	private AvailableCouncillor availablecouncillor;
	private List<Player> player;
	private int activeplayer;
	private String result;
	private NobilityPath nobilitypath;
	private int currentId;
	
	public ClientModel(int id) 
	{
		currentId = id;
	}
	
	public void setKing(King king) {
		this.king = king;
	}
	public void setRegions(List<Region> regions) {
		this.regions = regions;
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
	public void setAvailablecouncillor(AvailableCouncillor availablecouncillor) {
		this.availablecouncillor = availablecouncillor;
	}
	public void setNobilitypath(NobilityPath nobilitypath) {
		this.nobilitypath = nobilitypath;
	}
	public AvailableCouncillor getAvailablecouncillor() {
		return availablecouncillor;
	}
	public NobilityPath getNobilitypath() {
		return nobilitypath;
	}
	
	public Player getMyPlayer()
	{
		for (Player p : player)
		{
			if(p.getId() == currentId)
				return p;
		}
		return null;
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
		s += availablecouncillor.toString();
		s += result;
		s += "\n------\n";
		s += king.toString();
		s += "\n-----\n";
		for (Region region : regions) 
			s += region.toString() + "\n";
		s += "\n-----\n";
		s += player.get(activeplayer).toString();
		return s;
	}
	
	
}
