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
				int uiIndex = getValue(typesOfUserInterdace);
				int connIndex = getValue(typesOfConnection);
				int languageIndex =  getValue(languages);
 				if(languageIndex == 0)
 				{
 					language = new Italiano();
 					userInterface = new ClientCLI(language);
 				}
 				startNewGame.add(language.getNewGame());
 				startNewGame.add(language.getReconnect());
 				int newGameIndex = getValue(startNewGame);
 				boolean newGame = true;
 				int key = 0;
 				if(newGameIndex == 1)
 				{
 					newGame = false;
 					do
 					{
	 					try 
	 					{
							key = ((ClientCLI)userInterface).getInt(language.getInsertPassword());
						} catch (InvalidInsertionException e) 
	 					{
							key = -1;
							((ClientCLI)userInterface).showNotification(language.getInvalidInsertion());
							log.log(e);
						}
 					}
	 				while(key < 0);
 				}
 				if(uiIndex == 1)
 					userInterface = new ClientGUI(language);
				if(connIndex == 0)
					new ClientSocketManager(userInterface, newGame, key);
				else
					new ClientRMIManager(userInterface, newGame, key);

	}
	
	private static int getValue(List<String> list)
	{
		int index;
		do{
			try {
				index = ((ClientCLI)userInterface).getValues(list);
			} catch (InvalidInsertionException | NumberFormatException e) 
			{
				((ClientCLI)userInterface).showNotification(userInterface.getLanguage().getInvalidInsertion());
				log.log(e);
				index = -1;
			}
		}while(index < 0 || index >= list.size());
		return index;
	}
}
