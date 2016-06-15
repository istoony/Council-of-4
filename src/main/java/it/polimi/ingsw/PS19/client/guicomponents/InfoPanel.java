package it.polimi.ingsw.PS19.client.guicomponents;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;

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
				boxes.get(i).setInfo(m.getModel().getPlayer().get(i-1));
			}
		}
	}
	
	protected void update(ClientModel model){
		//TODO
	}
}
