package it.polimi.ingsw.PS19.model;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.card.PoliticsCard;
import it.polimi.ingsw.PS19.model.map.City;
import it.polimi.ingsw.PS19.model.map.FileReader;

public class Player {
	private int id;
	private int money;
	private int helpers;
	private int victoryPoints;
	private int nobilityPoints;
	private int mainActionCounter;
	private int fastActionCounter;
	private int startingPoliticCard;
	
	ArrayList<BusinessCard> freebusinesscard;
	ArrayList<BusinessCard> usedbusinesscard;
	ArrayList<PoliticsCard> politiccard;
	
	private ArrayList<City> myEmporia;
	
	
	public Player(int id) 
	{
		this.id = id;
		myEmporia = new ArrayList<City>();
		freebusinesscard = new ArrayList<>();
		usedbusinesscard = new ArrayList<>();
		politiccard = new ArrayList<>();
	}
	
	
	//methods
	
	public void setStartingAction(){
		mainActionCounter = 1;
		fastActionCounter = 1;
	}
	
	
	public static ArrayList<Player> setStartingItems(ArrayList<Player> p, String xmlfile){
		
		NodeList nList = FileReader.XMLReader(xmlfile, "starting");
		Node nNode = nList.item(0);
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element e = (Element) nNode;
			int money=Integer.parseInt(e.getElementsByTagName("money").item(0).getTextContent());
			int helpers = Integer.parseInt(e.getElementsByTagName("helpers").item(0).getTextContent());
			int politiccards = Integer.parseInt(e.getElementsByTagName("politiccards").item(0).getTextContent());		
			
			for(Player player : p){
				player.startingPoliticCard=politiccards;
				player.money=money+p.indexOf(player);
				player.helpers=helpers+p.indexOf(player);
			}
		}
		return p;
	}
	
	public void addCardToHand(PoliticsCard c){
		politiccard.add(c);
	}
	public void removeCardToHand(PoliticsCard c)
	{
		for (PoliticsCard card : politiccard) 
			if(card.equals(c))
			{
				politiccard.remove(card);
				return;
			}
	}
	
	public void addCardToHand(BusinessCard c){
		freebusinesscard.add(c);
	}
	public ArrayList<PoliticsCard> getPoliticcard() 
	{
		return politiccard;
	}
	public void addMoney(int m)
	{
		money = money + m;
	}
	
	//getter and setter
	public int getId() 
	{
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}	
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
	
	@Override
	public String toString() {
		String s = "";
		s += "ID:  " + id + "\n";
		s += "Money:  " + money + "\n";
		s += "Helpers:  " + helpers + "\n";
		s += "victoryP:  " + victoryPoints + "\n";
		s += "NobilityP:  " + nobilityPoints + "\n";
		s += "POLITICS CARD: ";
		for (PoliticsCard p : politiccard) 
		{
			s += p.toString() + "   ";
		}
		s += "\nFREE BUSINESS CARD: ";
		for (BusinessCard businessCard : freebusinesscard) {
			s += businessCard.toString() + "   ";
		}
		
		s += "\nUSED BUSINESS CARD: ";
		for (BusinessCard businessCard : usedbusinesscard) {
			s += businessCard.toString() + "   ";
		}
		return s;
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

	public void addToMyEmporia(City c) {
		this.myEmporia.add(c);
	}

	/**
	 * @return the myEmporia
	 */
	public ArrayList<City> getMyEmporia() {
		return myEmporia;
	}


	/**
	 * @param myEmporia the myEmporia to set
	 */
	public void setMyEmporia(ArrayList<City> myEmporia) {
		this.myEmporia = myEmporia;
	}
	
	public Boolean findMyEmporiaById(int id)
	{
		for (City c : myEmporia) 
			if(c.getId() == id)
				return true;
		return false;
	}
}
