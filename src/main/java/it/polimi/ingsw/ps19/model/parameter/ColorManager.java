package it.polimi.ingsw.ps19.model.parameter;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Class to manage colors
 */
/*
 * 
 */
public class ColorManager implements Serializable
{
	private static final long serialVersionUID = 477874054352268861L;
	private static final String COLOR_MARKER = "color";
	private static final String COLORS_MARKER = "colors";
	
	private static final Logger log = Logger.getLogger("COLOR_LOGGER");
	
	private ArrayList<Color> colors;
	
	private int size = 0;
	
	/**
	 * Constructor from file
	 * @param pathfile: file path to get colors
	 */
	public ColorManager(String pathfile) 
	{
		colors = new ArrayList<>();
		try {
			
			NodeList numberofcolors = FileReader.xMLReader(pathfile, COLOR_MARKER);
			
			NodeList nList = FileReader.xMLReader(pathfile,COLORS_MARKER);
			Element eElement = (Element) nList.item(0);
						
			for(int k=0; k<numberofcolors.getLength(); k++)
			{
				Color c = Color.decode(eElement.getElementsByTagName(COLOR_MARKER).item(k).getTextContent());
				
				colors.add(c);
				size++;	
			}

		} catch (Exception e) 
		    	{
					log.log(Level.SEVERE, e.toString(), e);
		    	}
	}
	
<<<<<<< HEAD
	/**
	 * Constructor with a given list of colors
	 * @param listofcolors
	 */
	public ColorManager(List<String> listofcolors)
	{
		colors = new ArrayList<>();
		for(String s : listofcolors)
		{
				colors.add(Color.decode(s));
				size++;	
		}
	}
	
	
	/**
	 * Constructor with a single color
	 * @param color
	 */
	public ColorManager(Color color)
	{
		colors = new ArrayList<>();
		colors.add(color);
		size++;	
	}
	
	/**
	 * adds a color
	 * @param color
	 */
=======
>>>>>>> branch 'master' of https://bitbucket.org/CoF_ps19/ps19
	public void addColor(Color color)
	{
		colors.add(color);
		size++;
	}

	public int getSize() {
		return size;
	}
	
	public List<Color> getColors() 
	{
		return colors;
	}
	
	public Color getRandomColor()
	{
		Random rand = new Random();
		
		return colors.get(rand.nextInt(size));
	}
}