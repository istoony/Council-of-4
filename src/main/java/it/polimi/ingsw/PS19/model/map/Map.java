package it.polimi.ingsw.PS19.model.map;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Map 
{
	public Map(String xmlfile) 
	{
		try {

			File fXmlFile = new File(xmlfile);
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
					
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
					
			NodeList nList = doc.getElementsByTagName("city");
					
			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
						
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("id: " + eElement.getAttribute("id"));
					System.out.println("Number of near cities: " + eElement.getElementsByTagName("nOfRoads").item(0).getTextContent());
					int provaint = Integer.parseInt(eElement.getElementsByTagName("nOfRoads").item(0).getTextContent());
					for(int k=0; k<provaint; k++){
					System.out.println("cities: " + eElement.getElementsByTagName("road").item(k).getTextContent());
					}
					//System.out.println("bonus : " + eElement.getElementsByTagName("bonus-more-victory-points").item(0).getTextContent());
				/*	System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
					System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
					System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
*/

				}
			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
	public static void main(String[] args) throws IOException {
		Map map= new Map("mapfile/council.xml");
	}

}
