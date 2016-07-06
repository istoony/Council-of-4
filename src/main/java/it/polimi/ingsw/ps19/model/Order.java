package it.polimi.ingsw.ps19.model;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.model.card.BusinessCard;

/**
 * Order
 */
public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 692698285944776249L;
	private List<BusinessCard> businesscard;
	private List<Color> politicscard;
	private int helper;
	private int price;
	
	/**
	 * Constructor
	 */
	public Order() 
	{
		businesscard = new ArrayList<>();
		politicscard = new ArrayList<>();
		helper = 0;
	}
	
	/**
	 * Constructor for a full order
	 * @param businesscard: business cards to sell
	 * @param politicscards: politic cards to sell
	 * @param helper: helpers to sell
	 * @param price
	 */
	public Order(List<BusinessCard> businesscard, List<Color> politicscards, int helper, int price)
	{
		this.businesscard = businesscard;
		this.politicscard = politicscards;
		this.helper = helper;
		this.price = price;
	}
	
	/**
	 * Add a business card to the order
	 * @param card
	 */
	public void addBusinessCard(BusinessCard card)
	{
		businesscard.add(card);
	}
	
	/**
	 * add politic card to the order
	 * @param card
	 */
	public void addPoliticsCard(Color card)
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
	public List<Color> getPoliticscard() {
		return politicscard;
	}
	
}
