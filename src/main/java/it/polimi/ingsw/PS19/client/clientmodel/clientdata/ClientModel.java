package it.polimi.ingsw.PS19.client.clientmodel.clientdata;

import java.util.List;

import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.map.Region;
import it.polimi.ingsw.PS19.model.parameter.ColorManager;

public class ClientModel 
{
	private King king;
	private List<Region> regions;
	private ColorManager councilcolors;
	private List<Player> player;
	private int activeplayer;
	
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
	
	
}
