package it.polimi.ingsw.PS19.model.parameter;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.PS19.model.map.FileReader;

public class ColorManager 
{
	private static final String COLOR_MARKER = "color";
	private static final String COLORS_MARKER = "colors";
	
	
	private ArrayList<Color> colors;
	
	private int size = 0;
	
	public ColorManager(String pathfile) 
	{
		colors = new ArrayList<Color>();
		try {
			
			NodeList numberofcolors = FileReader.XMLReader(pathfile, COLOR_MARKER);
			
			NodeList nList = FileReader.XMLReader(pathfile,COLORS_MARKER);
			Element eElement = (Element) nList.item(0);
						
			for(int k=0; k<numberofcolors.getLength(); k++)
			{
				System.out.println(eElement.getElementsByTagName(COLOR_MARKER).item(k).getTextContent());
				Color c = Color.decode(eElement.getElementsByTagName(COLOR_MARKER).item(k).getTextContent());
				
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
				colors.add(Color.decode(s));
				size++;	
		}
	}
	
	public ColorManager(Color color)
	{
		colors = new ArrayList<Color>();
		colors.add(color);
		size++;	
	}
	
	public void addColor(Color color)
	{
		colors.add(color);
		size++;
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
	public Color getRandomColor()
	{
		Random rand = new Random();
		
		return colors.get(rand.nextInt() % size);
	}
}