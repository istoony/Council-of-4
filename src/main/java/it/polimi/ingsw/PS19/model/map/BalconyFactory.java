package it.polimi.ingsw.PS19.model.map;

import org.w3c.dom.NodeList;

import it.polimi.ingsw.PS19.model.parameter.ColorManager;

public class BalconyFactory 
{
	public static void CreateBalcony(/*ArrayList<Region> regions,*/ King king, String pathfile) 
	{
		ColorManager colors = new ColorManager(pathfile);
		
		NodeList nList = FileReader.XMLReader(pathfile, "councillornumber");
		
		int numberofcouncillor = Integer.parseInt(nList.item(0).getTextContent());
		
		AvaibleCouncillor avaiblecouncillor = new AvaibleCouncillor(numberofcouncillor, colors);
		//regions.get(0).addBalcony(new Balcony(avaiblecouncillor, colors));
		
		Balcony b1 = new Balcony(avaiblecouncillor, colors);
		//regions.get(0).addBalcony(new Balcony(avaiblecouncillor, colors));

		Balcony b2 = new Balcony(avaiblecouncillor, colors);
		//regions.get(0).addBalcony(new Balcony(avaiblecouncillor, colors));
		
		Balcony b3 = new Balcony(avaiblecouncillor, colors);
		//regions.get(0).addBalcony(new Balcony(avaiblecouncillor, colors));
		
		Balcony b4 = new Balcony(avaiblecouncillor, colors);
		king.setBalcony(b4);
		
	}
	
	public static void main(String[] args) 
	{
		CreateBalcony(null, "mapfile/politicscard.xml");
	}
}
