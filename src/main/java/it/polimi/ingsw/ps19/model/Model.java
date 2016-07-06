package it.polimi.ingsw.ps19.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.model.map.Map;
import it.polimi.ingsw.ps19.model.map.MapLoader;
import it.polimi.ingsw.ps19.model.parameter.Costants;
import it.polimi.ingsw.ps19.model.parameter.FileNames;

/**
 * Model class
 * contains most of the static data
 */
public class Model extends Observable
{
	
	private Market market;
	private Map map;
	private ArrayList<Player> player;
	private CurrentState currentState;
	
	/**
	 * Constructor
	 * @param playerIdList: list of players
	 */
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
		Player.setStartingItems(player, FileNames.PLAYER_FILE);
	}
	
	public Map getMap() 
	{
		return map;
	}
	
	public Market getMarket() 
	{
		return market;
	}
	
	/**
	 * Get a player from his id
	 * @param i: id of player
	 * @return player with id == i
	 */
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
	
	/**
	 * notifies the view of a new message to send
	 * @param reply: message to send
	 */
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
