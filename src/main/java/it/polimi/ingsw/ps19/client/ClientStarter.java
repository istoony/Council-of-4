package it.polimi.ingsw.ps19.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import it.polimi.ingsw.ps19.client.language.English;
import it.polimi.ingsw.ps19.client.language.Italiano;
import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;

/**
 * class with main to start the client
 */
public class ClientStarter 
{
	private static ClientUI userInterface;
	
	private ClientStarter()
	{}
	
	/**
	 * starting main of the client
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Language language = new English();
		userInterface = new ClientCLI(language);
		List<String> typesOfConnection = new ArrayList<>();
		typesOfConnection.add(Language.SOCKET);
		typesOfConnection.add(Language.RMI);
		List<String> typesOfUserInterdace = new ArrayList<>();
		typesOfUserInterdace.add(Language.CLI);
		typesOfUserInterdace.add(Language.GUI);
		List<String> languages = new ArrayList<>();
		languages.add(new English().toString());
		languages.add(new Italiano().toString());
		List<String> startNewGame = new ArrayList<>();
		
		boolean valid;
		do
		{
			try 
			{
				valid = true;
				int uiIndex = ((ClientCLI)userInterface).getValues(typesOfUserInterdace);
 				int connIndex = ((ClientCLI)userInterface).getValues(typesOfConnection);
 				int languageIndex = ((ClientCLI)userInterface).getValues(languages);
 				if(languageIndex == 1)
 				{
 					language = new Italiano();
 					userInterface = new ClientCLI(language);
 				}
 				startNewGame.add(language.newGame);
 				startNewGame.add(language.reconnect);
 				int newGameIndex = ((ClientCLI)userInterface).getValues(startNewGame);
 				boolean newGame = true;
 				int key = 0;
 				if(newGameIndex == 1)
 				{
 					newGame = false;
 					key = ((ClientCLI)userInterface).getInt(language.insertPassword);
 				}
 				if(uiIndex == 1)
 					userInterface = new ClientGUI(language);
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
