package it.polimi.ingsw.PS19.client.guicomponents;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final String NAME = "CO4, GAME ";
	private int idgame;
	
	protected MainFrame(int id){
		super(NAME+id);
		idgame = id;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
	}
	
	
}
