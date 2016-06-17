package it.polimi.ingsw.PS19.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.PS19.controller.GameController;
import it.polimi.ingsw.PS19.exceptions.viewexceptions.WriterException;
import it.polimi.ingsw.PS19.message.replies.GameStartedMessage;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.view.View;
import it.polimi.ingsw.PS19.view.connection.Connection;

/**
 * Class the creates a new game
 */
public class GameFactory extends Thread 
{
	protected static final Logger log = Logger.getLogger("GAME_LOGGER");
	
	private Map<Integer, Connection> players;
	private View view;
	private Model model;
	private GameController controller;
	
	/**
	 * Constructor
	 * @param conns: List of the connections aka players
	 */
	public GameFactory(List<Connection> conns) 
	{
		players = new HashMap<>();
		for (Integer i = 0; i < conns.size(); i++) 
		{
			players.put(i, conns.get(i));
		}
		this.start();
	}
	
	@Override
	public void run() 
	{
		for(int i = 0; i < players.size(); i++)
		{
			try {
				players.get(i).write(new GameStartedMessage(0,"Game start",players.size(), i));
			} catch (WriterException e) 
			{
				log.log(Level.SEVERE, e.toString(), e);
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
