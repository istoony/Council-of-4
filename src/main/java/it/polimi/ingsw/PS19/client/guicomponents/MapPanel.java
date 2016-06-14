package it.polimi.ingsw.PS19.client.guicomponents;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;


public class MapPanel extends JPanel{
	
	RegionPanel r1;
	RegionPanel r2;
	RegionPanel r3;
	
	protected MapPanel(){
		super();
	}
	
	protected void initialize(ClientModel m){
		setBackground(Color.green);
		r1 = new RegionPanel(m.getRegions().get(0));
		r2 = new RegionPanel(m.getRegions().get(1));
		r3 = new RegionPanel(m.getRegions().get(2));
		
		
	}
}
