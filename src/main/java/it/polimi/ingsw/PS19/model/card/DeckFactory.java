package it.polimi.ingsw.PS19.model.card;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.PS19.model.bonus.Bonus;
import it.polimi.ingsw.PS19.model.bonus.BonusFactory;
import it.polimi.ingsw.PS19.model.parameter.ColorManager;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class DeckFactory 
{
	
	private static final String POLITICSCARD = "politicscard";
	private static final String BUSINESSCARD = "businesscard";
	private static final String JOKERCOLOR = "#FEFEFE";
	
	public static BusinessDeck businessDeckFactory(String pathfile, RegionType type) 
	{
		try {

			File fXmlFile = new File(pathfile);
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
					
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			
			BusinessDeck businessdeck = new BusinessDeck();
			if(doc.getElementsByTagName(BUSINESSCARD).getLength()!=1)
				return null;		//potrei lanciare un eccezione
			
			NodeList nList = doc.getElementsByTagName("card");
			for (int temp = 0; temp < nList.getLength(); temp++) 
			{
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) 
				{
					Element eElement = (Element) nNode;
					
					RegionType region = RegionType.valueOf(eElement.getElementsByTagName("region").item(0).getTextContent());
					int numberofcities = Integer.parseInt(eElement.getElementsByTagName("numberofcities").item(0).getTextContent());
					if(region == type)
					{
						BusinessCard card = new BusinessCard(region);
						int numberofbonus = eElement.getElementsByTagName("bonus").getLength();
						for(int i = 0; i < numberofbonus; i++)
						{
							Bonus bonus = BonusFactory.getBonus(eElement.getElementsByTagName("type").item(i).getTextContent(), Integer.parseInt(eElement.getElementsByTagName("parameter").item(i).getTextContent()));
							card.addBonus(bonus);
						}
					
						businessdeck.addToDeck(card);
					}
				}
			}
			return businessdeck;
		    } catch (Exception e) {
			e.printStackTrace();
		   }
		return null;
	}
	
	public static Deck politicsDeckFactory(String pathfile, ColorManager deckcolors) 
	{
		try {

			File fXmlFile = new File(pathfile);
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
					
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName(POLITICSCARD);
			
			Node node = nList.item(0);
			Element element = (Element) node;
			int numberofcard = Integer.parseInt(element.getElementsByTagName("numberofcard").item(0).getTextContent());
			int numberofjoker = Integer.parseInt(element.getElementsByTagName("numberofjoker").item(0).getTextContent());
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
				Card card = new PoliticsCard(Color.decode(JOKERCOLOR));
				deck.addToDeck(card);
		}
		
		return deck;
	}
	
	public static void main(String[] args) throws IOException 
	{
		ColorManager color = new ColorManager("mapfile/politicscard.xml");
		System.out.println(color.toString());
		Deck politic = DeckFactory.politicsDeckFactory("mapfile/politicscard.xml", color);
		System.out.println(JOKERCOLOR);
		System.out.println(politic.toString());
		Deck businessMountain = DeckFactory.businessDeckFactory("mapfile/politicscard.xml",RegionType.MOUNTAIN);
		Deck businessPlain = DeckFactory.businessDeckFactory("mapfile/politicscard.xml",RegionType.PLAIN);
		Deck businessHill = DeckFactory.businessDeckFactory("mapfile/politicscard.xml",RegionType.HILL);
	}
}
