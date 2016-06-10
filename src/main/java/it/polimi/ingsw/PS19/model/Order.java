package it.polimi.ingsw.PS19.model;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.card.PoliticsCard;

public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 692698285944776249L;
	private List<BusinessCard> businesscard;
	private List<PoliticsCard> politicscard;
	private int helper;
	private int price;
	
	public Order() 
	{
		businesscard = new ArrayList<>();
		politicscard = new ArrayList<>();
		helper = 0;
	}
	
	public Order(List<BusinessCard> businesscard, List<PoliticsCard> politicscards, int helper, int price)
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
	public int getPrice() {
		return price;
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
