package it.polimi.ingsw.ps19.client;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.client.language.English;
import it.polimi.ingsw.ps19.client.language.Italiano;
import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.exceptions.LocalLogger;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;

/**
 * class with main to start the client
 */
public class ClientStarter 
{
	private static ClientUI userInterface;
	static LocalLogger log;
	
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
		log = new LocalLogger("ClientStarter");
		
		List<String> typesOfConnection = new ArrayList<>();
		typesOfConnection.add(Language.SOCKET);
		typesOfConnection.add(Language.RMI);
		
		List<String> typesOfUserInterdace = new ArrayList<>();
		typesOfUserInterdace.add(Language.CLI);
		typesOfUserInterdace.add(Language.GUI);
		
		List<String> languages = new ArrayList<>();
		languages.add(Language.ITALIANO);
		languages.add(Language.ENGLISH);
		
		List<String> startNewGame = new ArrayList<>();
		
		boolean valid;
		int uiIndex = 0;
		int connIndex = 0;
		int languageIndex = 0;
		do{
			try {
				uiIndex = ((ClientCLI)userInterface).getValues(typesOfUserInterdace);
			} catch (InvalidInsertionException e) 
			{
				log.log(e);
				((ClientCLI)userInterface).showNotification(userInterface.getLanguage().invalidInsertion);
				uiIndex = -1;
			}
		}while(uiIndex != 0 && uiIndex != 1);
		
		do{
			try {
 				connIndex = ((ClientCLI)userInterface).getValues(typesOfConnection);
			} catch (InvalidInsertionException e) 
			{
				log.log(e);
				((ClientCLI)userInterface).showNotification(userInterface.getLanguage().invalidInsertion);
				connIndex = -1;
			}
		}while(connIndex != 0 && connIndex != 1);
		
		do{
			try {
 				languageIndex = ((ClientCLI)userInterface).getValues(languages);
			} catch (InvalidInsertionException e) 
			{
				log.log(e);
				((ClientCLI)userInterface).showNotification(userInterface.getLanguage().invalidInsertion);
				languageIndex = -1;
			}
		}while(languageIndex != 0 && languageIndex != 1);
		
		do
		{
			try 
			{
				valid = true;
 				if(languageIndex == 0)
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
				log.log(e);
			}
		}while(!valid);
	}
}
