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

import it.polimi.ingsw.PS19.model.paramiter.ColorManager;

public class DeckFactory 
{
	
	private static final String POLITICSCARD = "politicscard";
	private static final String BUSINESSCARD = "businesscard";
	
	public DeckFactory() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public Deck cardFactory(String pathfile, ColorManager deckcolors) 
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
			return politicscardfactory(numberofcard, deckcolors);
					
		//	System.out.println("----------------------------" + nList.toString());
/*
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
						
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) 
				{

					Element eElement = (Element) nNode;

					System.out.println("id: " + eElement.getAttribute("id"));
					System.out.println("Number of near cities: " + eElement.getElementsByTagName("nOfRoads").item(0).getTextContent());
					int provaint = Integer.parseInt(eElement.getElementsByTagName("nOfRoads").item(0).getTextContent());
					for(int k=0; k<provaint; k++)
					{
						System.out.println("cities: " + eElement.getElementsByTagName("road").item(k).getTextContent());
					}
					//System.out.println("bonus : " + eElement.getElementsByTagName("bonus-more-victory-points").item(0).getTextContent());
				/*	System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
					System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
					System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
*/

				//}
		    } catch (Exception e) {
			e.printStackTrace();
		   }
		return null;
	}
	
	private Deck politicscardfactory(int numberofcard, ColorManager deckcolors)
	{
		int numberofcardpercolor = numberofcard / deckcolors.getSize();
		
		System.out.println(numberofcardpercolor);
		
		Deck deck = new PoliticDeck();
		
		for(int i =0; i<numberofcardpercolor; i++)
		{
			for (Color council : deckcolors.getColors()) 
			{
				Card card = new PoliticsCard(council);
				deck.addToDeck(card);
			}
		}
		return deck;
	}
	
	public static void main(String[] args) throws IOException 
	{
		DeckFactory deck = new DeckFactory();
		ColorManager color = new ColorManager("mapfile/politicscard.xml");
		System.out.println(color.toString());
		Deck d = deck.cardFactory("mapfile/politicscard.xml", color);
		System.out.println(d.toString());
	}
}
