package it.polimi.ingsw.PS19.model.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.PS19.exceptions.IllegalFileException;
import it.polimi.ingsw.PS19.model.bonus.Bonus;
import it.polimi.ingsw.PS19.model.bonus.BonusFactory;


public class NobilityPath implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -142212466262863614L;
	private static final String ERROR = "file corrotto/non valido!";
	
	private Map<Integer, ArrayList<Bonus>> nobility;
	
	public NobilityPath(String pathfile) 
	{
		nobility = new HashMap<>();
		NodeList nList = FileReader.XMLReader(pathfile, "cell");
		
		for (int temp = 0; temp < nList.getLength(); temp++) 
		{
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) 
			{
				Element eElement = (Element) nNode;
				
				int position = Integer.parseInt(eElement.getElementsByTagName("position").item(0).getTextContent());
				ArrayList<Bonus> bonusarray = new ArrayList<>();
				String s = eElement.getElementsByTagName("bonus").item(0).getTextContent();
				String[] ss = s.split("\n");
				String s1 = ss[1].trim();
				int k = Integer.parseInt(ss[2].trim());
				Bonus bonus = BonusFactory.getBonus(s1, k);
				if(bonus==null){
					throw new IllegalFileException(ERROR);
				}
				bonusarray.add(bonus);
				ArrayList<Bonus> check = nobility.putIfAbsent(position, bonusarray);
				if(check!=null){
					bonusarray.addAll(check);
					nobility.put(position, bonusarray);
				}
			}
		}
	}
	
	@Override
	public String toString() 
	{
		String s = "\n------------";
		for (int i = 0; i <= this.getMaxKey(); i++) 
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
	
	private int getMaxKey(){
		int max=0;
		for(int k : nobility.keySet()){
			if (k>max){
				max=k;
			}
		}
		return max;
	}
	
		
}
