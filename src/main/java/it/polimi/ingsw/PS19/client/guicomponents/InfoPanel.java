package it.polimi.ingsw.PS19.client.guicomponents;

import java.awt.Color;

import javax.swing.JPanel;

public class InfoPanel extends JPanel {

	protected InfoPanel(){
		super();
	}
	
	protected void initialize(MainFrame m){
		setSize((int)(m.getWidth()*0.4), (int)(m.getHeight()*0.9));
	}
}
