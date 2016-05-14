package it.polimi.ingsw.PS19.model.paramiter;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ColorManager 
{
	private static final String COUNCILCOLOR_MARKER = "councilcolor";
	private static final String COLORS_MARKER = "councilcolors";
	
	
	private ArrayList<Color> colors;
	
	private int size = 0;
	
	public ColorManager(String pathfile) 
	{
		colors = new ArrayList<Color>();
		try {

			File fXmlFile = new File(pathfile);
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
					
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
					
			NodeList nList = doc.getElementsByTagName("mapcolor");
			System.out.println(nList.toString());
				//probabilmente da cambiare mettendo un for
			Element eElement = (Element) nList.item(0);
			
			int numberofelement = Integer.parseInt(eElement.getElementsByTagName("numberofelement").item(0).getTextContent());
			
			for(int k=0; k<numberofelement; k++)
			{
				System.out.println(eElement.getElementsByTagName(COUNCILCOLOR_MARKER).item(k).getTextContent());
				Color c = (Color) Color.class.getField(eElement.getElementsByTagName(COUNCILCOLOR_MARKER).item(k).getTextContent()).get(null);
				
				colors.add(c);
				size++;	
			}

		} catch (Exception e) 
		    	{
		    	e.printStackTrace();
		    	}
	}
	
	public ColorManager(ArrayList<String> listofcolors)
	{
		colors = new ArrayList<Color>();
		for(String s : listofcolors)
		{
			
			Color c;
			try {
				c = (Color) Color.class.getField(s).get(null);
				colors.add(c);
				size++;	
			} catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public String toString() 
	{
		String s = "SIZE: " + size + "\n";
		for (Color color : colors) 
			s = s + color + " ";
		return s;
			
	}
	
	public int getSize() {
		return size;
	}
	public ArrayList<Color> getColors() 
	{
		return colors;
	}
	/*
	public static void main(String[] args)
	{
		ArrayList<String> s = new ArrayList<String>();
		s.add("BLACK");
		s.add("RED");
		s.add("WHITE");
		ColorManager color = new ColorManager(s);
		System.out.println(color.toString());
	}*/
}