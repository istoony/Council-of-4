package it.polimi.ingsw.ps19.model;


import java.util.HashMap;
import java.util.Map;


public class Market 
{
	private Map<Integer, Order> listoforder;
	
	public Market() 
	{
		listoforder = new HashMap<>();
	}
	
	public void addOrder(Order order, int playerId)
	{
		listoforder.put(playerId,order);
	}
	
	public Map<Integer, Order> getListoforder() 
	{
		return listoforder;
	}
	
	public Order getOrderById(int playerId)
	{
		return listoforder.get(playerId);
	}
	
	public boolean removeOrderById(int playerId)
	{
		return listoforder.remove(playerId)!= null;
		
	}
	public int getSize()
	{
		return listoforder.size();
	}
}
