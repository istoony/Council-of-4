package it.polimi.ingsw.PS19.client.guicomponents;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {

	private int nplayer;
	private List<InfoCell> boxes;
	
	protected InfoPanel(){
		super();
		boxes= new ArrayList<>();
	}
	
	protected void initialize(MainFrame m){
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		nplayer = m.getModel().getPlayer().size();
		setLayout(new GridLayout(nplayer+1, 0));
		
		for(int i=0; i<=nplayer; i++){
			boxes.add(new InfoCell());
		}
		for(int i=0; i<boxes.size(); i++){
			add(boxes.get(i));
			if(i==0){
				boxes.get(i).setInfo(m.getModel());
			}
			else{
				if(m.getModel().getMyPlayer().getId()==m.getModel().getPlayer().get(i-1).getId()){
					boxes.get(i).setMyInfo(m.getModel().getPlayer().get(i-1));
				}	
				else{
					boxes.get(i).setInfo(m.getModel().getPlayer().get(i-1));
				}
			}
		}
	}
	
	protected void update(MainFrame m){
		for(int i=0; i<boxes.size(); i++){
			if(i==0){
				boxes.get(i).updateInfo(m.getModel());
			}
			else{
				if(m.getModel().getMyPlayer().getId()==m.getModel().getPlayer().get(i-1).getId()){
					boxes.get(i).updateMyInfo(m.getModel().getPlayer().get(i-1));
				}
				else{
					boxes.get(i).updateInfo(m.getModel().getPlayer().get(i-1));
				}
			}
		}
	}
}
