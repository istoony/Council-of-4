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
		boolean valid;
		do
		{
			try 
			{
				valid = true;
				int uiIndex = ((ClientCLI)userInterface).getValues(typesOfUserInterdace);
 				int connIndex = ((ClientCLI)userInterface).getValues(typesOfConnection);
 				if(uiIndex == 1)
 					userInterface = new ClientGUI();
				if(connIndex == 0)
					new ClientSocketManager(userInterface);
				else
					new ClientRMIManager(userInterface);
			} catch (InvalidInsertionException e) 
			{
				valid = false;
				ClientLogger.log.log(Level.SEVERE, e.toString(), e);
			}
		}while(!valid);
	}
}
