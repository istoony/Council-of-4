package it.polimi.ingsw.PS19.model.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.PS19.model.bonus.Bonus;
import it.polimi.ingsw.PS19.model.bonus.BonusFactory;
import it.polimi.ingsw.PS19.model.map.FileReader;

public class NobilityPath 
{
	private Map<Integer, ArrayList<Bonus>> nobility;
	
	public NobilityPath(String pathfile) 
	{
		nobility = new HashMap<Integer, ArrayList<Bonus>>();
		NodeList nList = FileReader.XMLReader(pathfile, "position");
		
		for (int temp = 0; temp < nList.getLength(); temp++) 
		{
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) 
			{
				Element eElement = (Element) nNode;
				
				int numberofposition = Integer.parseInt(eElement.getElementsByTagName("numberofposition").item(0).getTextContent());
				int numberofbonus = eElement.getElementsByTagName("bonus").getLength();
				ArrayList<Bonus> bonusarray = new ArrayList<Bonus>();
				for(int i = 0; i < numberofbonus; i++)
				{
					Bonus bonus = BonusFactory.getBonus(eElement.getElementsByTagName("type").item(i).getTextContent(), Integer.parseInt(eElement.getElementsByTagName("parameter").item(i).getTextContent()));
					bonusarray.add(bonus);
				}
				nobility.put(numberofposition, bonusarray);
			}
		}
	}
	
	@Override
	public String toString() 
	{
		String s = "\n------------";
		for (int i = 0; i < 6; i++) 
		{
			s = s + "\nposition: " + i + "\n";
			if(nobility.get(i) != null)
			{
				for (Bonus b : nobility.get(i)) 
				{
					s = s + " -----bonus: " + b + "\n";
				}
			}
		}
		return s;
	}
	
	public static void main(String[] args) 
	{
		NobilityPath np = new NobilityPath("mapfile/politicscard.xml");
		System.out.println(np.toString());
	}
}
