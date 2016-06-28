package it.polimi.ingsw.ps19.client.guicomponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.client.language.Language;


public class MapPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 114631944693966503L;
	
	private List<RegionPanel> regionPanelList;
	private List<String> positions; 
	private List<Point> starts;
	private List<Point> ends;
	Language language;
	
	protected MapPanel(Language l)
	{
		super();
		language = l;
		regionPanelList = new ArrayList<>();
		positions = new ArrayList<>();
		starts = new ArrayList<>();
		ends = new ArrayList<>();
		positions.add("West");
		positions.add("Center");
		positions.add("East");
	}
	
	protected void initialize(ClientModel m){
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setLayout(new GridLayout(1, 3));
		
		for(int i=0; i<m.getRegions().size(); i++){
			regionPanelList.add(new RegionPanel(m.getRegions().get(i), language));
		}
		for(RegionPanel rp : regionPanelList){
			this.add(rp);
			rp.initialize();
		}
	}
	
	protected void drawRoads(){
		for(RegionPanel rp : regionPanelList){
			rp.drawRoads();
		}
		for(int i=0; i<regionPanelList.size()-1; i++){
			drawRoads2(regionPanelList.get(i), regionPanelList.get(i+1));
		}
	}
	
	/*
	 * draw roads between this and the next (right) panel
	 */
	protected void drawRoads2(RegionPanel sx, RegionPanel dx){
		for(DrawCity dc : sx.getCitylist()){
			for(DrawCity dc2 : dx.getCitylist()){
				if(dc.getMycity().getNeighbours().contains(dc2.getMycity())){
					Rectangle r = dc.getBounds();
					Rectangle r2 = dc2.getBounds();
					starts.add(SwingUtilities.convertPoint(sx, RegionPanel.centerOf(r), this));
					ends.add(SwingUtilities.convertPoint(dx, RegionPanel.centerOf(r2), this));
					
				}
			}
		}
	}
	
    @Override
	public void paint(Graphics g) {
    	super.paint(g);
    	for(int i=0; i<starts.size(); i++){
    		g.drawLine(starts.get(i).x, starts.get(i).y, ends.get(i).x, ends.get(i).y);
    		g.setColor(Color.decode("#7F5217"));
    	}
    }
    
    protected void update(MainFrame mf){
    	for(int i=0; i<mf.getModel().getRegions().size(); i++){
    		regionPanelList.get(i).updateInfo(mf.getModel().getRegions().get(i));  		
    	}
    }
}
