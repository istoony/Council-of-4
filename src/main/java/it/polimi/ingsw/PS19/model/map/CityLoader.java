package it.polimi.ingsw.PS19.model.map;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CityLoader {
	
	
	static void ReadFile(ArrayList<MapLoader> idlist, String xmlfile){
		
		ArrayList<ArrayList<City>> regioncitylist = new ArrayList<ArrayList<City>>();
		ArrayList<City> citylist = new ArrayList<City>();
			
		NodeList nList = FileReader.XMLReader(xmlfile, "region");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element e = (Element) nNode;
				for(MapLoader elem : idlist){
					if(elem.id==Integer.parseInt(e.getElementsByTagName("type").item(0).getTextContent())){
						for(int i=0; i<Integer.parseInt(e.getElementsByTagName("nOfCities").item(0).getTextContent()); i++){
							citylist.add(new City(Integer.parseInt(e.getElementsByTagName("cityid").item(0).getTextContent())));
						}
					}		
				}					
			}
		}	
	}
	
	
}
