package it.polimi.ingsw.ps19.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import it.polimi.ingsw.ps19.model.parameter.Costants;

/**
 * Class that contains the current state of the model
 */
public class CurrentState 
{
	private int playerTurnId;
	private Boolean timeToMarket;
	private Boolean timeToMarketSended;
	
	private int numberOfPlayer;
	private Integer lastTurn;
	
	private Map<Integer, Boolean> marketState;
	private List<Integer> playerIdList;
	
	private List<Integer> disconnectedPlayersId;
	
	
	/**
	 * Constructor
	 * @param playerIdList: list of players
	 */
	public CurrentState(List<Integer> playerIdList) 
	{
		playerTurnId = playerIdList.get(0);
		timeToMarket = false;
		lastTurn = Costants.INVALID_ID;
		this.numberOfPlayer = playerIdList.size();
		marketState = new HashMap<>();
		this.playerIdList = new ArrayList<>();
		for (Integer playerId : playerIdList)
		{
			marketState.put(playerId, false);
			this.playerIdList.add(playerId);
		}
		disconnectedPlayersId = new ArrayList<>();
	}
		
	public void setPlayerTurnId(int playerTurnId) 
	{
		this.playerTurnId = playerTurnId;
	}
	public int getPlayerTurnId() 
	{
		return playerTurnId;
	}
	public void setTimeToMarket(Boolean timeToMarket) 
	{
		this.timeToMarket = timeToMarket;
	}
	public Boolean isTimeToMarket() 
	{
		return timeToMarket;
	}
	public Boolean isTimeToMarketSended() 
	{
		return timeToMarketSended;
	}
	public void setTimeToMarketSended(Boolean timeToMarketSended) 
	{
		this.timeToMarketSended = timeToMarketSended;
	}
	
	public int getNumberOfPlayer() 
	{
		return numberOfPlayer;
	}
	public List<Integer> getPlayerIdList() 
	{
		return Costants.clone(playerIdList);
	}
	
	/**
	 * Questa funzione ritorna il prossimo player se gli passo
	 * il player che sta giocando adesso
	 * altrimenti non cambia il turno
	 * @param i
	 * @return
	 */
	public/*@pure@*/ int giveNextCorrectId(int i)
	{
		int next = playerTurnId;
		if(playerTurnId == i)
			next= playerIdList.get((playerIdList.indexOf(i) + 1) % numberOfPlayer);
		while(!isConnected(next))
			next= playerIdList.get((playerIdList.indexOf(next) + 1) % numberOfPlayer);
		return next;
	}
	
	/**
	 * Randomly gets the id of the next active player
	 * @return
	 */
	public/*@pure@*/ int giveRandomTurn()
	{
		int randomNumb = Costants.RANDOM_NUMBER.nextInt(numberOfPlayer);
	
		int next= playerIdList.get(randomNumb);
		while(!isConnected(next))
		{
			randomNumb++;
			next= playerIdList.get((randomNumb) % numberOfPlayer);
		}
		return next;
	}
	
	public int getNumberOfDisconnectedPlayer() 
	{
		int n = 0;
		for (Integer i : playerIdList)
			if(!isConnected(i))
				n++;
		return n;
	} 
	
	/**
	 * set player state in market as "true",  meaning that the player has bought something (or nothing)
	 * @param id
	 */
	public void playerBought(int id)
	{
		marketState.put(id, true);
	}

	public boolean isTimeToEndMarket() 
	{
		for (Integer i : playerIdList)
			if(!marketState.get(i) && isConnected(i))
				return false;
		return true;
	}
	
	/**
	 * Checks whether a player has bought or not
	 * @param playerId
	 * @return true <==> player has bougth
	 */
	public boolean isPlayerBought(int playerId)
	{
		return marketState.get(playerId);
	}

	/**
	 * Reset the active player to the lowest id connected
	 */
	public void resetPlayerTurnId() 
	{
		int i = 0;
		this.playerTurnId = playerIdList.get(i);
		while(!isConnected(playerTurnId))
			playerTurnId= playerIdList.get((i + 1) % numberOfPlayer);
	}
	
	/**
	 * Empties the market
	 */
	public void resetMarket()
	{
		for (Entry<Integer, Boolean> entry : marketState.entrySet()) 
			entry.setValue(false);
	}
	
	/**
	 * Add a new player to the list of disconnected players
	 * @param playerId: new disconnected player id
	 */
	public void addDisconnectedPlayer(Integer playerId)
	{
		if(!disconnectedPlayersId.contains(playerId))
			disconnectedPlayersId.add(playerId);
	}
	/**
	 * Removes a player from the list of disconnected players
	 * @param playerId
	 */
	public void reconnectPlayer(Integer playerId)
	{
		if(playerId>0)
			disconnectedPlayersId.remove(playerId);
	
	}
	public List<Integer> getDisconnectedPlayersId() {
		return Costants.clone(disconnectedPlayersId);
	}
	
	/**
	 * Checks whether a player with given id is connected or not
	 * @param id: player id to check
	 * @return true <==> connected
	 */
	public boolean isConnected(int id)
	{
		for (Integer i : disconnectedPlayersId)
			if(i == id)
				return false;
		return true;
	}
	
	public void setLastTurn(Integer lastTurn) 
	{
		this.lastTurn = lastTurn;
	}
	
	public Integer getLastTurn() {
		return lastTurn;
	}
	
}
