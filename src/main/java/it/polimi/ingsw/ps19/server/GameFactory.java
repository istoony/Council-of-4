package it.polimi.ingsw.ps19.server;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.ps19.controller.GameController;
import it.polimi.ingsw.ps19.exceptions.viewexceptions.WriterException;
import it.polimi.ingsw.ps19.message.replies.GameStartedMessage;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.view.View;

/**
 * Class the creates a new game
 */
public class GameFactory extends Thread 
{
	protected static final Logger log = Logger.getLogger("GAME_LOGGER");
	
	private List<Integer> players;
	private View view;
	private Model model;
	private GameController controller;
	
	/**
	 * Constructor
	 * @param conns: List of the connections aka players
	 */
	public GameFactory(List<Integer> conns) 
	{
		players = conns;
		this.start();
	}
	
	@Override
	public void run() 
	{
		for (Integer playerNumber : players)
		{
			try {
				WaitingRoom.getConnection(playerNumber).write(new GameStartedMessage(0,"Game start",players.size(), playerNumber));
			} catch (WriterException e) 
			{
				log.log(Level.SEVERE, e.toString(), e);
			}
		}
		view = new View(players);
		//TODO Model intialize with arraylist of integer
		model = new Model(players);
		controller = new GameController(model);
		view.addObserver(controller);
		model.addObserver(view);
		new Thread(view).start();
    }

}
