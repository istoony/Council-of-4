package it.polimi.ingsw.PS19.client.guicomponents;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;

public class MainFrame extends JFrame {

	private static final int MWIDTH = 1000;
	private static final int MHEIGHT = 600;
	
	private static final String NAME = "CO4, GAME ";
	private int idgame;
	private ClientModel model;
	
	protected MainFrame(int id, ClientModel model){
		super(NAME+id);
		idgame = id;
		this.model=model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setBounds(100, 60, MWIDTH, MHEIGHT);
	}
	
	protected void initialize(){
		MapPanel mpanel = new MapPanel();
		mpanel.initialize(model);

		add(mpanel);
		mpanel.setSize(new Dimension(2*MWIDTH/3, MHEIGHT));

		
		InfoPanel infobox = new InfoPanel();
		infobox.initialize(this);
		add(infobox);
		infobox.setSize(new Dimension(MWIDTH/3, MHEIGHT));
		infobox.setMaximumSize(new Dimension(MWIDTH/3, MHEIGHT));
	}
	
	
}
