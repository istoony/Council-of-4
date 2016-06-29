package it.polimi.ingsw.ps19.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.model.map.Map;
import it.polimi.ingsw.ps19.model.map.MapLoader;
import it.polimi.ingsw.ps19.model.parameter.Costants;
import it.polimi.ingsw.ps19.model.parameter.FileNames;

public class Model extends Observable
{
	
	private Market market;
	private Map map;
	private ArrayList<Player> player;
	private CurrentState currentState;
	
	public Model(List<Integer> playerIdList) 
	{
		currentState = new CurrentState(playerIdList);
		
		playerFactory(playerIdList);
		
		map = MapLoader.builder();
		
		market = new Market();
		
		
	}
	
	private void playerFactory(List<Integer> playerIdList)
	{
		player = new ArrayList<>();
		
		for (Integer p : playerIdList)
			player.add(new Player(p));
		Player.setStartingItems(player, FileNames.MAPFILE_PLAYERCONFIG_XML);
	}
	
	public Map getMap() 
	{
		return map;
	}
	
	public Market getMarket() 
	{
		return market;
	}
	
	public Player getPlayerById(int i)
	{
		for (Player p : player) 
			if(p.getId() == i)
				return p;
		return null;
	}
	
	public List<Player> getPlayer() 
	{
		return Costants.clone(player);
	}
	
	public void sendMessage(Reply reply)
	{
		setChanged();
		notifyObservers(reply);
	}
	
	public CurrentState getCurrentState() 
	{
		return currentState;
	}
	
	public int getMaxId()
	{
		int maxId = -1;
		for (Player p : player) 
			if(maxId < p.getId())
				maxId = p.getId();
		return maxId;
	}
}
