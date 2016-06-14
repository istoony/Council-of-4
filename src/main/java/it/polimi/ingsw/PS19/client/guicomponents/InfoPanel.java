package it.polimi.ingsw.PS19.client.guicomponents;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {

	protected InfoPanel(){
		super();
	}
	
	protected void initialize(MainFrame m){
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setSize((int)(m.getWidth()*0.4), (int)(m.getHeight()*0.9));
	}
}
