package it.polimi.ingsw.ps19.model;

import java.util.List;

<<<<<<< HEAD
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.card.PoliticsCard;
=======
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;

import java.io.Serializable;
import java.util.ArrayList;
>>>>>>> branch 'master' of https://bitbucket.org/CoF_ps19/ps19.git

import java.io.Serializable;
import java.util.ArrayList;

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
