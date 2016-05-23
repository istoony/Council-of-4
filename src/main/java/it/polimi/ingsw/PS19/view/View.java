package it.polimi.ingsw.PS19.view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.PS19.view.connection.Connection;

public class View extends Observable implements Observer
{
	private ArrayList<Connection> playerConnection;
	
	public View(ArrayList<Connection> conns) 
	{
		playerConnection = conns;
	}
	
	public void update(Observable arg0, Object arg1) 
	{
		
	}

}
