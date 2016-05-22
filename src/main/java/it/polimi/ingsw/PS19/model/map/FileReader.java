package it.polimi.ingsw.PS19.model.map;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class FileReader {
	
	public static NodeList XMLReader(String nomefile, String nomeNodo){
		try {

			File fXmlFile = new File(nomefile);
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
					
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			
			return doc.getElementsByTagName(nomeNodo);	

		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
}
