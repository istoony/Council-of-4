package it.polimi.ingsw.PS19.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;

public class CLIClientStarter 
{
	private static ClientCLI userInterface = new ClientCLI();
	
	public static void main(String[] args) 
	{
		List<String> typesOfConnection = new ArrayList<>();
		typesOfConnection.add("Socket");
		typesOfConnection.add("RMI");
		boolean valid;
		do
		{
			try {
				valid = true;
				int index = userInterface.getValues(typesOfConnection);
				if(index == 0)
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
