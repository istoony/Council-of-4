package it.polimi.ingsw.PS19.model;

import java.util.ArrayList;

import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.card.PoliticsCard;

public class Player {
	int money;
	int helpers;
	int victoryPoints;
	int nobilityPoints;
	int mainActionCounter;
	int fastActionCounter;
	
	ArrayList<BusinessCard> freebusinesscard;
	ArrayList<BusinessCard> usedbusinesscard;
	ArrayList<PoliticsCard> politiccard;
	
	
	
	
	//methods
	
	public void setStartingAction(){
		mainActionCounter = 1;
		fastActionCounter = 1;
	}
	
	
	//getter and setter
	
	public int getMainActionCounter() {
		return mainActionCounter;
	}
	
	public void setMainActionCounter(int mainActionCounter) {
		this.mainActionCounter = mainActionCounter;
	}
	
	public int getFastActionCounter() {
		return fastActionCounter;
	}
	
	public void setFastActionCounter(int fastActionCounter) {
		this.fastActionCounter = fastActionCounter;
	}
	
	public int getNobilityPoints() {
		return nobilityPoints;
	}
	public void setNobilityPoints(int nobilityPoints) {
		this.nobilityPoints = nobilityPoints;
	}
	public int getVictoryPoints() {
		return victoryPoints;
	}
	public void setVictoryPoints(int victoryPoints) {
		this.victoryPoints = victoryPoints;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int m) {
		this.money = m;
	}
	public int getHelpers() {
		return helpers;
	}
	public void setHelpers(int helpers) {
		this.helpers = helpers;
	}
	
	public boolean findPoliticsCard(PoliticsCard card)
	{
		for (PoliticsCard politics : politiccard) 
		{
			if(politics.equals(card))
				return true;
		}
		return false;
		
	}
	
	
}
