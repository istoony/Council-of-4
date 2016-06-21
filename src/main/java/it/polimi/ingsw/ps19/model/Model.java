package it.polimi.ingsw.ps19.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.model.map.Map;
import it.polimi.ingsw.ps19.model.map.MapLoader;
import it.polimi.ingsw.ps19.model.parameter.FileNames;

public class Model extends Observable
{
	
	private Market market;
	private Map map;
	private ArrayList<Player> player;
	private CurrentState currentState;
	
	public Model(int numberofPlayer) 
	{
		currentState = new CurrentState(numberofPlayer);
		
		playerFactory();
		
		map = MapLoader.builder();
		
		market = new Market();
		
		
	}
	
	private void playerFactory()
	{
		player = new ArrayList<>();
		
		for(int i = 0; i < currentState.getNumberOfPlayer(); i++)
		{
			player.add(new Player(i));
			currentState.addPlayer(i);
		}
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
		return player.get(i);
	}
	
	public List<Player> getPlayer() 
	{
		return player;
	}
	
	@Override
	public String toString() 
	{
		String s = "++++++++++++++++++++\n";
		s = s + "number of player: " + currentState.getNumberOfPlayer() + "\n";
		s = s + map.toString() + "\n*************PLAYERSSSS************\n";
		for (Player p : player) 
		{
			s = s + p.toString() + "\n";
		}
		
		s += "\n++++++++++++++++++++\n";
		return s;
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
