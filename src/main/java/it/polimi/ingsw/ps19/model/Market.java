package it.polimi.ingsw.ps19.model;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


/**
 * Market
 */
public class Market implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5935605254196710208L;
	private Map<Integer, Order> listoforder;
	private int nullOrder;
	
	/**
	 * Constructor
	 */
	public Market() 
	{
		listoforder = new HashMap<>();
		nullOrder = 0;
	}
	
	/**
	 * Add order to market
	 * @param order
	 * @param playerId: selling player
	 */
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
	
	/**
	 * Get the order sold by the playerId
	 * @param playerId: selling player
	 * @return order of playerId
	 */
	public Order getOrderById(int playerId)
	{
		return listoforder.get(playerId);
	}
	
	/**
	 * Removes the order sold by the playerId
	 * @param playerId
	 * @return
	 */
	public boolean removeOrderById(int playerId)
	{
		return listoforder.remove(playerId)!= null;
		
	}
	
	public int getSize()
	{
		return listoforder.size();
	}
	
	/**
	 * Clears all orders
	 */
	public void reset()
	{
		listoforder = new HashMap<>();
		nullOrder = 0;
	}

	/**
	 * Adds empty order
	 */
	public void addNullOrder() 
	{
		nullOrder++;
		
	}
	public int getNullOrder() 
	{
		return nullOrder;
	}
}
