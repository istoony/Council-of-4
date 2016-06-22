package it.polimi.ingsw.ps19.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;

public class ClientStarter 
{
	private static ClientUI userInterface = new ClientCLI();
	
	public static void main(String[] args) 
	{
		List<String> typesOfConnection = new ArrayList<>();
		typesOfConnection.add("Socket");
		typesOfConnection.add("RMI");
		List<String> typesOfUserInterdace = new ArrayList<>();
		typesOfUserInterdace.add("CLI");
		typesOfUserInterdace.add("GUI");
		List<String> startNewGame = new ArrayList<>();
		startNewGame.add("New Game");
		startNewGame.add("Recconect to old game");
		boolean valid;
		do
		{
			try 
			{
				valid = true;
				int uiIndex = ((ClientCLI)userInterface).getValues(typesOfUserInterdace);
 				int connIndex = ((ClientCLI)userInterface).getValues(typesOfConnection);
 				int newGameIndex = ((ClientCLI)userInterface).getValues(startNewGame);
 				boolean newGame = true;
 				int key = 0;
 				if(newGameIndex == 1)
 				{
 					newGame = false;
 					key = ((ClientCLI)userInterface).getInt("Insert password for old game: ");
 				}
 				if(uiIndex == 1)
 					userInterface = new ClientGUI();
				if(connIndex == 0)
					new ClientSocketManager(userInterface, newGame, key);
				else
					new ClientRMIManager(userInterface, newGame, key);
			} catch (InvalidInsertionException e) 
			{
				valid = false;
				ClientLogger.log.log(Level.SEVERE, e.toString(), e);
			}
		}while(!valid);
	}
}
