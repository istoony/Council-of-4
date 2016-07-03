package it.polimi.ingsw.ps19.model.map;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class FileReader {
	
	private static final Logger log = Logger.getLogger("FILE_READER_LOGGER");
	
	private FileReader(){
		
	}
	
	public static NodeList xMLReader(String nomefile, String nomeNodo){
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
			log.log(Level.SEVERE, e.toString(), e);
			return null;
		}

	}
	
}
