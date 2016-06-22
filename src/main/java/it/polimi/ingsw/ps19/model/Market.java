package it.polimi.ingsw.ps19.model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


public class Market implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5935605254196710208L;
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
		Map<Integer, Order> temp = new HashMap<>();
		for (Entry<Integer, Order> entry : listoforder.entrySet())
			temp.put(entry.getKey(), entry.getValue());
		return temp;
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
