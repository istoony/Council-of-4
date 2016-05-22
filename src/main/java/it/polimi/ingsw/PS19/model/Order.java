package it.polimi.ingsw.PS19.model;

import java.util.List;
import java.util.ArrayList;

import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.card.PoliticsCard;

public class Order {
	private ArrayList<BusinessCard> businesscard;
	private ArrayList<PoliticsCard> politicscard;
	private int helper;
	private int price;
	
	public Order() 
	{
		businesscard = new ArrayList<BusinessCard>();
		politicscard = new ArrayList<PoliticsCard>();
		helper = 0;
	}
	
	public Order(ArrayList<BusinessCard> businesscard, ArrayList<PoliticsCard> politicscards, int helper, int price)
	{
		this.businesscard = businesscard;
		this.politicscard = politicscards;
		this.helper = helper;
		this.price = price;
	}
	
	public void addBusinessCard(BusinessCard card)
	{
		businesscard.add(card);
	}
	
	public void addPoliticsCard(PoliticsCard card)
	{
		politicscard.add(card);
	}
	public void setHelper(int helper) 
	{
		this.helper = helper;
	}
	public void setPrice(int price) 
	{
		this.price = price;
	}
	
	public List<BusinessCard> getBusinesscard() {
		return businesscard;
	}
	public int getHelper() {
		return helper;
	}
	public List<PoliticsCard> getPoliticscard() {
		return politicscard;
	}
	
}
