package it.polimi.ingsw.PS19.model.map;

import java.util.ArrayList;

import org.w3c.dom.NodeList;

import it.polimi.ingsw.PS19.model.parameter.ColorManager;

public class BalconyFactory 
{
	public static ColorManager createBalcony(ArrayList<Region> regions, King king, String pathfile) 
	{
		ColorManager colors = new ColorManager(pathfile);
		
		NodeList nList = FileReader.XMLReader(pathfile, "councillornumber");
		
		int numberofcouncillor = Integer.parseInt(nList.item(0).getTextContent());
		
		AvaibleCouncillor avaiblecouncillor = new AvaibleCouncillor(numberofcouncillor, colors);
		System.out.println(avaiblecouncillor.toString());
		regions.get(0).setBalcony(new Balcony(avaiblecouncillor, colors));
		
		//Balcony b1 = new Balcony(avaiblecouncillor, colors);
		regions.get(1).setBalcony(new Balcony(avaiblecouncillor, colors));

		//Balcony b2 = new Balcony(avaiblecouncillor, colors);
		regions.get(2).setBalcony(new Balcony(avaiblecouncillor, colors));
		
		//Balcony b3 = new Balcony(avaiblecouncillor, colors);
		//regions.get(0).addBalcony(new Balcony(avaiblecouncillor, colors));
		
		//Balcony b4 = new Balcony(avaiblecouncillor, colors);
		king.setBalcony(new Balcony(avaiblecouncillor, colors));
		
		return colors;
	}
	
}
