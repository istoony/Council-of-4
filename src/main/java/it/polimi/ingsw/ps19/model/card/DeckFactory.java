package it.polimi.ingsw.ps19.model.card;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.ps19.model.bonus.Bonus;
import it.polimi.ingsw.ps19.model.bonus.BonusFactory;
import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.map.FileReader;
import it.polimi.ingsw.ps19.model.parameter.ColorManager;
import it.polimi.ingsw.ps19.model.parameter.Costants;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

public class DeckFactory 
{
	private static final String NUMBEROFJOKER = "numberofjoker";
	private static final String NUMBEROFCARD = "numberofcard";
	private static final String NUMBEROFCITIES = "numberofcities";
	private static final String TYPE = "type";
	private static final String BONUS = "bonus";
	private static final String REGION = "region";
	private static final String CARD = "card";
	private static final String POLITICSCARD = "politicscard";
	private static final String BUSINESSCARD = "businesscard";
	
	
	private DeckFactory ()
	{}
	
	
	public static BusinessDeck businessDeckFactory(String pathfile, RegionType type, List<City> cities)
	{
		try {
			Random rand = new Random();
						
			BusinessDeck businessdeck = new BusinessDeck();
			if(FileReader.XMLReader(pathfile, BUSINESSCARD).getLength()!=1)
				return null;		//potrei lanciare un eccezione
			
			NodeList nList = FileReader.XMLReader(pathfile, CARD);
			for (int temp = 0; temp < nList.getLength(); temp++) 
			{
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element eElement = (Element) nNode;
					
					RegionType region = RegionType.valueOf(eElement.getElementsByTagName(REGION).item(0).getTextContent());				
					if(region == type)
					{
						BusinessCard card = new BusinessCard(region);
						int numberofbonus = eElement.getElementsByTagName(BONUS).getLength();
						for(int i = 0; i < numberofbonus; i++)
						{
							Bonus bonus = BonusFactory.getBonus(eElement.getElementsByTagName(TYPE).item(i).getTextContent(), Integer.parseInt(eElement.getElementsByTagName("parameter").item(i).getTextContent()));
							card.addBonus(bonus);
						}
						int numberofcities = Integer.parseInt(eElement.getElementsByTagName(NUMBEROFCITIES).item(0).getTextContent());
						int i=0;
						while(i < numberofcities)
						{
							if(!card.addCity(cities.get(Math.abs(rand.nextInt(cities.size())))))
								i--;
							i++;
						}
					
						businessdeck.addToDeckRandom(card);
					}
				}
			}
			businessdeck.shuffle();
			businessdeck.setCardsId();
			return businessdeck;
		    } catch (Exception e) {
			e.printStackTrace();
		   }
		return null;
	}
	
	public static Deck politicsDeckFactory(String pathfile, ColorManager deckcolors) 
	{
		try {

			NodeList nList = FileReader.XMLReader(pathfile, POLITICSCARD);
			
			Node node = nList.item(0);
			Element element = (Element) node;
			int numberofcard = Integer.parseInt(element.getElementsByTagName(NUMBEROFCARD).item(0).getTextContent());
			int numberofjoker = Integer.parseInt(element.getElementsByTagName(NUMBEROFJOKER).item(0).getTextContent());
			return politicscardfactory(numberofcard, numberofjoker, deckcolors);

		    } catch (Exception e) {
			e.printStackTrace();
		   }
		return null;
	}

	private static Deck politicscardfactory(int numberofcard, int numberofjoker, ColorManager deckcolors)
	{
		int numberofcardpercolor = numberofcard / deckcolors.getSize();
		
		Deck deck = new PoliticDeck();
		
		for(int i =0; i<numberofcardpercolor; i++)
		{
			for (Color council : deckcolors.getColors()) 
			{
				Card card = new PoliticsCard(council);
				deck.addToDeck(card);
			}
		}
		for(int i =0; i<numberofjoker; i++)
		{
				Card card = new PoliticsCard(Color.decode(Costants.JOKERCOLOR));
				deck.addToDeck(card);
		}
		deck.shuffle();
		return deck;
	}
}
