package it.polimi.ingsw.PS19.model.bonus;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.PS19.model.FileNames;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.FileReader;
import it.polimi.ingsw.PS19.model.map.Region;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class GeneralBonus implements Bonus {

	private ArrayList<RegionType> regionsBonus;
	private int singleRegionBonus;
	private ArrayList<Integer> kingBonus;  //first to achieve
	private ArrayList<CityColorBonus> colorBonus;
	
	
	public GeneralBonus(String xmlfile, ArrayList<Region> regionlist){
		regionsBonus = new ArrayList<RegionType>();
		kingBonus = new ArrayList<Integer>();
		colorBonus = new ArrayList<CityColorBonus>();
		this.inizializeKing(xmlfile);
		this.inizializeCity(xmlfile);
		this.inizializeColors(regionlist);

	}
	
	
	private void inizializeKing(String xmlfile){
		NodeList nList = FileReader.XMLReader(xmlfile, "bonus");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) nNode;
					
				this.singleRegionBonus=Integer.parseInt(e.getElementsByTagName("regionbonus").item(0).getTextContent());
				int n = Integer.parseInt(e.getElementsByTagName("numberOfKingsPrize").item(0).getTextContent());
				for(int i=0; i<n; i++)
				{
					this.kingBonus.add(Integer.parseInt(e.getElementsByTagName("kingprize").item(i).getTextContent()));					
				}
			}
		}	
	}
	
	private void inizializeCity(String xmlfile){
		NodeList nList = FileReader.XMLReader(xmlfile, "citycolor");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) nNode;
					
				int n = Integer.parseInt(e.getElementsByTagName("bonus-more-victory-points").item(0).getTextContent());
				String c = e.getAttribute("id");		
				this.colorBonus.add(new CityColorBonus(c, n));
			}
		}
	}
	
	private void inizializeColors(ArrayList<Region> rlist){
		for(CityColorBonus c : this.colorBonus){
			c.joinBonusToCity(rlist);
		}
	}
	
	
	@Override
	public void giveBonus(Player p) {
		// TODO Auto-generated method stub

	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GeneralBonus [regionsBonus=" + regionsBonus + ", singleRegionBonus=" + singleRegionBonus
				+ ", kingBonus=" + kingBonus + ", colorBonus=" + colorBonus + "]";
	}

	
	
	
}
