package it.polimi.ingsw.PS19.model;

import java.util.ArrayList;
import java.util.Observable;

import it.polimi.ingsw.PS19.message.requests.NewTurnMessage;
import it.polimi.ingsw.PS19.model.map.Map;
import it.polimi.ingsw.PS19.model.map.MapLoader;

public class Model extends Observable
{
	
	private Market market;
	private Map map;
	private ArrayList<Player> player;
	private int numberofplayer;
	private CurrentState currentState;
	
	public Model(int numberofplayer) 
	{
		this.numberofplayer = numberofplayer;
		
		playerFactory();
		
		map = MapLoader.builder();
		
		market = new Market();
		
		currentState = new CurrentState();
	}
	
	private void playerFactory()
	{
		player = new ArrayList<>();
		
		for(int i = 0; i < numberofplayer; i++)
		{
			player.add(new Player(i));
		}
		Player.setStartingItems(player, "mapfile/playerconfig.xml");
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
	
	public int getNumberofplayer() 
	{
		return numberofplayer;
	}
	
	public ArrayList<Player> getPlayer() 
	{
		return player;
	}
	
	@Override
	public String toString() 
	{
		String s = "++++++++++++++++++++\n";
		s = s + "number of player: " + numberofplayer + "\n";
		s = s + map.toString() + "\n*************PLAYERSSSS************\n";
		for (Player p : player) 
		{
			s = s + p.toString() + "\n";
		}
		
		s += "\n++++++++++++++++++++\n";
		return s;
	}
	
	public void createMessage(String result)
	{
		setChanged();
		if(currentState.getSendfullgame())
		{
			NewTurnMessage message = new NewTurnMessage(currentState.getPlayerTurnId());
			notifyObservers(message);
		}
	}
	
	public CurrentState getCurrentState() 
	{
		return currentState;
	}
}
