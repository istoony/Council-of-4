package it.polimi.ingsw.PS19.model;

import java.util.ArrayList;

public class Market 
{
	private ArrayList<Order> listoforder;
	
	public Market() 
	{
		listoforder = new ArrayList<Order>();
	}
	
	public void addOrder(Order order)
	{
		listoforder.add(order);
	}
	public ArrayList<Order> getListoforder() 
	{
		return listoforder;
	}
}
