package it.polimi.ingsw.ps19.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.map.FileReader;
import it.polimi.ingsw.ps19.model.parameter.Costants;

/**
 * The Class Player.
 * OVERVIEW: Classe che rappresenta il singolo giocatore in una partita.
 */
public class Player implements Serializable
{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -505551466312267551L;
	
	/**Identificate a player in a game. */
	private int id;
	
	/** Game parameter: points, money etc. */
	private int money;
	private int helpers;
	private int victoryPoints;
	private int nobilityPoints;
	private int startingPoliticCard;
		
	/** Number of fast or main action this player have */
	private int mainActionCounter;
	private int fastActionCounter;
	
	/** The politic card to draw. */
	private int politicCardToDraw;
	
	/** Deck of free Business Permit Card, Usede Business Permit Card and
	 * Politics card*/
	private List<BusinessCard> freebusinesscard;
	private List<BusinessCard> usedbusinesscard;
	private List<PoliticsCard> politiccard;
	
	/** The my emporia. */
	private List<City> myEmporia;
	
	/** The business card bonus and city token bonus */
	private boolean businessCardRequest;
	private boolean cityBonusRequest;
	private int drawBusinessCard;
	
	/**
	 * Instantiates a new player.
	 *
	 * @param id the id
	 */
	public Player(int id) 
	{
		this.id = id;
		myEmporia = new ArrayList<>();
		freebusinesscard = new ArrayList<>();
		usedbusinesscard = new ArrayList<>();
		politiccard = new ArrayList<>();
		mainActionCounter = 1;
		fastActionCounter = 1;
		businessCardRequest = false;
		cityBonusRequest = false;
		drawBusinessCard = 0;
	}
	
	/**
	 * Sets the starting action. 
	 * To reset the player for a new turn
	 */
	public void setStartingAction()
	{
		mainActionCounter = 1;
		fastActionCounter = 1;
		politicCardToDraw = 1;
	}
	
	/**
	 * Sets the starting items. This items are readed from file
	 *
	 * @param p the list of Player.
	 * @param xmlfile the path to find configuration file.
	 * @return the updated list of player.
	 */
	public static List<Player> setStartingItems(List<Player> p, String xmlfile){
		
		NodeList nList = FileReader.XMLReader(xmlfile, "starting");
		Node nNode = nList.item(0);
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element e = (Element) nNode;
			int money=Integer.parseInt(e.getElementsByTagName("money").item(0).getTextContent());
			int helpers = Integer.parseInt(e.getElementsByTagName("helpers").item(0).getTextContent());
			int politiccards = Integer.parseInt(e.getElementsByTagName("politiccards").item(0).getTextContent());		
			
			for(Player player : p)
			{
				player.startingPoliticCard=politiccards;
				player.money=money+p.indexOf(player);
				player.helpers=helpers+p.indexOf(player);
				player.politicCardToDraw = 1;
			}
		}
		return p;
	}
	
	/**
	 * Adds the card to hand.
	 *
	 * @param c the c
	 */
	public void addCardToHand(PoliticsCard c)
	{
		politiccard.add(c);
	}
	
	/**
	 * Removes the card to hand.
	 *
	 * @param c the c
	 */
	public void removeCardToHand(PoliticsCard c)
	{
		for (PoliticsCard card : politiccard) 
			if(card.getColor().equals(c.getColor()))
			{
				politiccard.remove(card);
				return;
			}
	}
	
	/**
	 * Adds the card to hand.
	 *
	 * @param c the c
	 */
	public void addCardToHand(BusinessCard c)
	{
		freebusinesscard.add(c);
	}
	
	/**
	 * Gets the politiccard.
	 *
	 * @return the politiccard
	 */
	public List<PoliticsCard> getPoliticcard() 
	{
		return Costants.clone(politiccard);
	}
	
	/**
	 * Get player Id.
	 *
	 * @return the id of this player
	 */
	public /*@pure*/ int getId() 
	{
		return id;
	}	
	
	/**
	 * Gets the main action counter.
	 *
	 * @return the main action counter
	 */
	/*
	 * START GETTER AND SETTER OF MAIN AND FAST ACTION
	 */
	public int getMainActionCounter() 
	{
		return mainActionCounter;
	}
	
	/**
	 * Sets the main action counter.
	 *
	 * @param mainActionCounter the new main action counter
	 */
	public void setMainActionCounter(int mainActionCounter) 
	{
		this.mainActionCounter = mainActionCounter;
	}
	
	/**
	 * Gets the fast action counter.
	 *
	 * @return the fast action counter
	 */
	public int getFastActionCounter() 
	{
		return fastActionCounter;
	}
	
	/**
	 * Sets the fast action counter.
	 *
	 * @param fastActionCounter the new fast action counter
	 */
	public void setFastActionCounter(int fastActionCounter) 
	{
		this.fastActionCounter = fastActionCounter;
	}
	/*
	 * END GETTER AND SETTER OF MAIN AND FAST ACTION
	 * 
	 * START GETTER AND SETTER OF MONEY - NOBILITY POINTS - VICTORY POINTS - HELPERS
	 */
	
	/**
	 * Gets the nobility points.
	 *
	 * @return the nobility points
	 */
	public int getNobilityPoints() 
	{
		return nobilityPoints;
	}
	
	/**
	 * Sets the nobility points.
	 *
	 * @param nobilityPoints the new nobility points
	 */
	public void setNobilityPoints(int nobilityPoints) 
	{
		this.nobilityPoints = nobilityPoints;
	}
	
	/**
	 * Gets the victory points.
	 *
	 * @return the victory points
	 */
	public int getVictoryPoints() 
	{
		return victoryPoints;
	}
	
	/**
	 * Sets the victory points.
	 *
	 * @param victoryPoints the new victory points
	 */
	public void setVictoryPoints(int victoryPoints) 
	{
		this.victoryPoints = victoryPoints;
	}
	
	/**
	 * Gets the money.
	 *
	 * @return the money
	 */
	public int getMoney()
	{
		return money;
	}
	
	/**
	 * Sets the money.
	 *
	 * @param money the new money
	 */
	public void setMoney(int money) 
	{
		this.money = money;
	}
	
	/**
	 * Gets the helpers.
	 *
	 * @return the helpers
	 */
	public int getHelpers() 
	{
		return helpers;
	}
	
	/**
	 * Sets the helpers.
	 *
	 * @param helpers the new helpers
	 */
	public void setHelpers(int helpers) 
	{
		this.helpers = helpers;
	}
	
	/*
	 * END GETTER AND SETTER OF MONEY - VICTORY POINTS - NOBILITY POINTS - HELPERS
	 */
	
	/**
	 * Find politics card.
	 *
	 * @param card the card
	 * @return true, if successful
	 */
	public boolean findPoliticsCard(PoliticsCard card)
	{
		for (PoliticsCard politics : politiccard) 
		{
			if(politics.getColor().equals(card.getColor()))
				return true;
		}
		return false;
		
	}

	/**
	 * Adds the to my emporia.
	 *
	 * @param c the c
	 */
	public void addToMyEmporia(City c)
	{
		myEmporia.add(c);
	}

	/**
	 * Gets the my emporia.
	 *
	 * @return the myEmporia
	 */
	public List<City> getMyEmporia() 
	{
		return Costants.clone(myEmporia);
	}

	/**
	 * Find my emporia by id.
	 *
	 * @param id the id
	 * @return the boolean
	 */
	public Boolean findMyEmporiaById(int id)
	{
		for (City c : myEmporia) 
			if(c.getId() == id)
				return true;
		return false;
	}

	/**
	 * Gets the freebusinesscard.
	 *
	 * @return the freebusinesscard
	 */
	public List<BusinessCard> getFreebusinesscard() 
	{
		return Costants.clone(freebusinesscard);
	}
	
	/**
	 * Removes the freebusinesscard by id.
	 *
	 * @param id the id
	 * @return the business card
	 */
	public BusinessCard removeFreebusinesscardById(int id) 
	{
		BusinessCard b = null;
		for (BusinessCard card : freebusinesscard)
			if(card.getId() == id)
				b = card;
		freebusinesscard.remove(b);
		return b;
	}
	
	
	/**
	 * Gets the usedbusinesscard.
	 *
	 * @return the usedbusinesscard
	 */
	public List<BusinessCard> getUsedbusinesscard() 
	{
		return Costants.clone(usedbusinesscard);
	}
	
	/**
	 * Adds the used business card.
	 *
	 * @param card the card
	 */
	public void addUsedBusinessCard(BusinessCard card)
	{
		usedbusinesscard.add(card);
	}


	/**
	 * @return Number of emporia left to win the game
	 */
	public int getMaxemporia() 
	{
		return Costants.EMPORIUM_SIZE - myEmporia.size();
	}

	/**
	 * Get Number of Politic card to Draw AFTER SET
	 * THIS NUMBER AT 0.
	 *
	 * @return the politicCardToDraw
	 */
	public int getPoliticCardToDraw() 
	{
		int app = politicCardToDraw;
		politicCardToDraw = 0;
		return app;
	}

	/**
	 * Sets the politic card to draw.
	 *
	 * @param politicCardToDraw the politicCardToDraw to set
	 */
	public void setPoliticCardToDraw(int politicCardToDraw) 
	{
		this.politicCardToDraw = politicCardToDraw;
	}
	
	/**
	 * Gets the starting politic card.
	 *
	 * @return the starting politic card
	 */
	public int getStartingPoliticCard() 
	{
		return startingPoliticCard;
	}
	
	
	/**
	 * Sets the business card request.
	 *
	 * @param businessCardRequest the new business card request
	 */
	public void setBusinessCardRequest(boolean businessCardRequest) {
		this.businessCardRequest = businessCardRequest;
	}
	
	/**
	 * Sets the city bonus request.
	 *
	 * @param cityBonusRequest the new city bonus request
	 */
	public void setCityBonusRequest(boolean cityBonusRequest) {
		this.cityBonusRequest = cityBonusRequest;
	}
	
	/**
	 * Checks if is business card request.
	 *
	 * @return true, if is business card request
	 */
	public boolean isBusinessCardRequest() {
		return businessCardRequest;
	}
	
	/**
	 * Checks if is city bonus request.
	 *
	 * @return true, if is city bonus request
	 */
	public boolean isCityBonusRequest() {
		return cityBonusRequest;
	}

	public boolean isBusinessCardOrCityBonusRequest() 
	{
		return isCityBonusRequest() || isBusinessCardRequest();
	}
	public boolean isBusinessCardToDraw()
	{
		if(drawBusinessCard > 0)
			return true;
		return false;
	}
	public void setDrawBusinessCard(int drawBusinessCard) 
	{
		this.drawBusinessCard = drawBusinessCard;
	}
	
}
