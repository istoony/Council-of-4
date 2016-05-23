package it.polimi.ingsw.PS19.server;

import java.util.ArrayList;

import it.polimi.ingsw.PS19.controller.GameController;
import it.polimi.ingsw.PS19.message.GameStartedMessage;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.view.View;
import it.polimi.ingsw.PS19.view.connection.Connection;

public class GameFactory extends Thread 
{
	private ArrayList<Connection> players;
	private View view;
	private Model model;
	private GameController controller;
	
	public GameFactory(ArrayList<Connection> conns) 
	{
		players = conns;
	}
	
	public void run() 
	{
		for(int i = 0; i < players.size(); i++)
		{
			players.get(i).write(new GameStartedMessage(i, players.size()));
		}
		view = new View(players);
		model = new Model(players.size());
		controller = new GameController(model);
		view.addObserver(controller);
		model.addObserver(view);
    }

}
