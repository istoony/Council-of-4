package it.polimi.ingsw.PS19.client.guicomponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;


public class MapPanel extends JPanel{
	
	private List<RegionPanel> regionPanelList;
	private List<String> positions; 

	
	protected MapPanel(){
		super();
		regionPanelList = new ArrayList<>();
		positions = new ArrayList<>();
		positions.add("West");
		positions.add("Center");
		positions.add("East");
	}
	
	protected void initialize(ClientModel m){
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setLayout(new GridLayout(1, 3));
		
		for(int i=0; i<m.getRegions().size(); i++){
			regionPanelList.add(new RegionPanel(m.getRegions().get(i)));
		}
		for(RegionPanel rp : regionPanelList){
			this.add(rp);
			rp.initialize();
			rp.draw();
		}
		
		
	}
}
