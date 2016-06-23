package it.polimi.ingsw.ps19.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import it.polimi.ingsw.ps19.model.parameter.Costants;
import it.polimi.ingsw.ps19.server.WaitingRoom;

public class CurrentState 
{
	private int playerTurnId;
	private Boolean timeToMarket;
	private Boolean timeToMarketSended;
	
	private int numberOfPlayer;
	
	private Map<Integer, Boolean> marketState;
	private List<Integer> playerIdList;
	
	
	public CurrentState(List<Integer> playerIdList) 
	{
		playerTurnId = playerIdList.get(0);
		timeToMarket = false;
		this.numberOfPlayer = playerIdList.size();
		marketState = new HashMap<>();
		this.playerIdList = new ArrayList<>();
		for (Integer playerId : playerIdList)
		{
			marketState.put(playerId, false);
			this.playerIdList.add(playerId);
		}
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
		while(!WaitingRoom.isConnected(next))
			next= playerIdList.get((playerIdList.indexOf(next) + 1) % numberOfPlayer);
		return next;
	}
	
	public/*@pure@*/ int giveRandomTurn()
	{
		int randomNumb = Costants.RANDOM_NUMBER.nextInt(numberOfPlayer);
	
		int next= playerIdList.get(randomNumb);
		while(!WaitingRoom.isConnected(next))
			next= playerIdList.get((randomNumb + 1) % numberOfPlayer);
		return next;
	}
	
	@Override
	public String toString() 
	{
		String s = "";
		s += "\nTimeToMarket" + timeToMarket;
		return s;
	}

	public int getNumberOfDisconnectedPlayer() 
	{
		int n = 0;
		for (Integer i : playerIdList)
			if(!WaitingRoom.isConnected(i))
				n++;
		return n;
	} 
	/**
	 * TODO: write me
	 * imposta il model dicendo che questo player ha già comprato
	 * @param id
	 */
	
	public void playerBought(int id)
	{
		marketState.put(id, true);
	}

	public boolean isTimeToEndMarket() 
	{
		for (Integer i : playerIdList)
			if(!marketState.get(i) && WaitingRoom.isConnected(i))
				return false;
		return true;
	}

	public boolean isPlayerBought(int playerId)
	{
		return marketState.get(playerId);
	}

	public void resetPlayerTurnId() 
	{
		int i = 0;
		this.playerTurnId = playerIdList.get(i);
		while(!WaitingRoom.isConnected(playerTurnId))
			playerTurnId= playerIdList.get((i + 1) % numberOfPlayer);
	}
	
	public void resetMarket()
	{
		for (Entry<Integer, Boolean> entry : marketState.entrySet()) 
			entry.setValue(false);
	}
}
