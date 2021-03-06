package it.polimi.ingsw.ps19.model.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.ps19.exceptions.IllegalFileException;
import it.polimi.ingsw.ps19.model.bonus.Bonus;
import it.polimi.ingsw.ps19.model.bonus.BonusFactory;
import it.polimi.ingsw.ps19.model.parameter.FileReader;

/**
 * Class to rappresent the nobility path
 */
public class NobilityPath implements Serializable
{
	private static final long serialVersionUID = -142212466262863614L;
	private static final String ERROR = "file corrotto/non valido!";
	
	private Map<Integer, List<Bonus>> nobility;
	
	/**
	 * Creates the nobility path from the pathfile
	 * @param pathfile
	 */
	public NobilityPath(String pathfile) 
	{
		nobility = new HashMap<>();
		NodeList nList = FileReader.xMLReader(pathfile, "cell");
		
		for (int temp = 0; temp < nList.getLength(); temp++) 
		{
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) 
			{
				Element eElement = (Element) nNode;
				
				int position = Integer.parseInt(eElement.getElementsByTagName("position").item(0).getTextContent());
				List<Bonus> bonusarray = new ArrayList<>();
				String s = eElement.getElementsByTagName("bonus").item(0).getTextContent();
				String[] ss = s.split("\n");
				String s1 = ss[1].trim();
				int k = Integer.parseInt(ss[2].trim());
				Bonus bonus = null;
				if(k!=0)
					bonus = BonusFactory.getBonus(s1, k);
				else
					bonus = BonusFactory.getBonus(s1);
				if(bonus==null){
					throw new IllegalFileException(ERROR);
				}
				bonusarray.add(bonus);
				List<Bonus> check = nobility.putIfAbsent(position, bonusarray);
				if(check!=null){
					bonusarray.addAll(check);
					nobility.put(position, bonusarray);
				}
			}
		}
	}
	
	/**
	 * Gets a bonus in the given position
	 * @param key: position
	 * @return bonus in position(key)
	 */
	public List<Bonus> getBonusByPosition(int key)
	{
		return nobility.get(key);
	}
	
	public int getMaxKey(){
		int max=0;
		for(int k : nobility.keySet()){
			if (k>max){
				max=k;
			}
		}
		return max;
	}

	/**
	 * @return the nobility
	 *
	public Map<Integer, List<Bonus>> getNobility() 
	{
		Map<Integer, List<Bonus>> clone = new HashMap<>();
		for(Entry<Integer, List<Bonus>> entry : nobility.entrySet())
		{
			List<Bonus> cloneList = null;
			if(entry.getValue() != null && entry.getValue().isEmpty())
				cloneList = Costants.clone(entry.getValue());
			clone.put(entry.getKey(), cloneList);
		}
		return clone;
	}
	*/
		
}
