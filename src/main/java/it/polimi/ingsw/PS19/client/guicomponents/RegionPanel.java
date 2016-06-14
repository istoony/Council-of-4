package it.polimi.ingsw.PS19.client.guicomponents;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import it.polimi.ingsw.PS19.model.map.City;
import it.polimi.ingsw.PS19.model.map.Region;

public class RegionPanel extends JPanel{

	private List<DrawCity> citylist;
	private Region myregion;
	
	protected RegionPanel(Region re){
		super();
		citylist = new ArrayList<>();
		myregion = re;
	}
	
	protected void initialize(){
		for(City c : myregion.getCities()){
			citylist.add(new DrawCity(c));
		}
	}
	
	
}
