package it.polimi.ingsw.PS19.server;

import java.util.ArrayList;
import java.util.HashMap;

import it.polimi.ingsw.PS19.controller.GameController;
import it.polimi.ingsw.PS19.exceptions.viewexceptions.WriterException;
import it.polimi.ingsw.PS19.message.replies.GameStartedMessage;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.view.View;
import it.polimi.ingsw.PS19.view.connection.Connection;

public class GameFactory extends Thread 
{
	private HashMap<Integer, Connection> players;
	private View view;
	private Model model;
	private GameController controller;
	
	public GameFactory(ArrayList<Connection> conns) 
	{
		players = new HashMap<>();
		for (Integer i = 0; i < conns.size(); i++) 
		{
			players.put(i, conns.get(i));
		}
		new Thread(this).start();
	}
	
	/*
	 * Sends message of game started to clients
	 * Creates new Game
	 * Configures Observer pattern between view controller and message
	 * @see java.lang.Thread#run()
	 */
	public void run() 
	{
		for(int i = 0; i < players.size(); i++)
		{
			try {
				players.get(i).write(new GameStartedMessage(i, players.size()));
			} catch (WriterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("GameStartedMessages Sent");
		view = new View(players);
		model = new Model(players.size());
		controller = new GameController(model);
		view.addObserver(controller);
		model.addObserver(view);
		new Thread(view).start();
    }

}
