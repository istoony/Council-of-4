package it.polimi.ingsw.PS19.model.map;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MapLoader {
	//fornisce metodi per leggere file, check di integrità dello stesso, csp delle regioni, ritorna le triplette di regioni configurabili.
	
	String type;
	int id;
	int outRight;
	int outLeft;
	
	
	public  static ArrayList<Region[]> wizard(String xmlfile){
		
		ArrayList<MapLoader> maplist = new ArrayList<MapLoader>();
		
		try {

			File fXmlFile = new File(xmlfile);
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
					
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("region");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
		
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element e = (Element) nNode;
					
					String s = e.getElementsByTagName("type"))item(0).getTextContent();
					
					maplist.add(new MapLoader(s, )
					
					System.out.println("id: " + e.getAttribute("id"));
					System.out.println("Number of near cities: " + e.getElementsByTagName("nOfRoads").item(0).getTextContent());
					int provaint = Integer.parseInt(e.getElementsByTagName("nOfRoads").item(0).getTextContent());
					for(int k=0; k<provaint; k++){
					System.out.println("cities: " + e.getElementsByTagName("road").item(k).getTextContent());
					}


				}
			}
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}
	
	
	private Element[] RegionsConstrainResolver(Element[] solution, NodeList nlist){
		
		
		
		
		
	}

	
	private MapLoader(String t, int i, int dx, int sx){
		type=t;
		id=i;
		outRight=dx;
		outLeft=sx;
	}
	
}
