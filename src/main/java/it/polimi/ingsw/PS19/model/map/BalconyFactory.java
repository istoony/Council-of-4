package it.polimi.ingsw.PS19.model.map;

import java.util.List;

import org.w3c.dom.NodeList;

import it.polimi.ingsw.ps19.model.parameter.ColorManager;

public class BalconyFactory 
{
	private BalconyFactory() {}
	public static AvailableCouncillor createBalcony(List<Region> regions, King king, String pathfile) 
	{
		ColorManager colors = new ColorManager(pathfile);
		
		NodeList nList = FileReader.XMLReader(pathfile, "councillornumber");
		
		int numberofcouncillor = Integer.parseInt(nList.item(0).getTextContent());
		
		AvailableCouncillor avaiblecouncillor = new AvailableCouncillor(numberofcouncillor, colors);
		
		regions.get(0).setBalcony(new Balcony(avaiblecouncillor, colors));
		
		regions.get(1).setBalcony(new Balcony(avaiblecouncillor, colors));

		regions.get(2).setBalcony(new Balcony(avaiblecouncillor, colors));
		
		king.setBalcony(new Balcony(avaiblecouncillor, colors));
		
		return avaiblecouncillor;
	}
	
}
