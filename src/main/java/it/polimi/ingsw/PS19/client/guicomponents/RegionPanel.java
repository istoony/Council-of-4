package it.polimi.ingsw.PS19.client.guicomponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
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
		setVisible(true);
	}
	
	protected void initialize(){
		setBackground(Color.green);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setLayout(new GridLayout(3, 2));
		
		for(City c : myregion.getCities()){
			citylist.add(new DrawCity(c));
		}
		
		for(DrawCity dc : citylist){
			add(dc);
		}
	}
	
	protected void draw(){
		for(DrawCity c : citylist){
			c.paintComponent(c.getGraphics());
		}
	}
	
	
}
