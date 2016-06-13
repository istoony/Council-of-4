package it.polimi.ingsw.PS19.client.clientaction;

import java.awt.Color;

<<<<<<< HEAD
import it.polimi.ingsw.PS19.client.ClientUI;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.message.requests.ElectCouncillorMessage;
import it.polimi.ingsw.PS19.message.requests.Request;
import it.polimi.ingsw.PS19.model.parameter.RegionType;
=======
import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.message.requests.ElectCouncillorMessage;
import it.polimi.ingsw.ps19.message.requests.Request;
import it.polimi.ingsw.ps19.model.parameter.RegionType;
>>>>>>> branch 'master' of https://bitbucket.org/CoF_ps19/ps19.git

public class ElectCouncillorInputs extends ClientAction 
{
	RegionType location;
	boolean mainAction = false;
	Color color;
	
	public ElectCouncillorInputs(ClientModel m, boolean main)
	{
		model = m;
		mainAction = main;
	}
	
	@Override
	public boolean isPossible() 
	{
		return true;
	}

	@Override
	public Request Execute(ClientUI userInterface) throws InvalidInsertionException 
	{
		location = userInterface.getRegionAndKing();
		color = userInterface.getColor(model.getAvailablecouncillor().getAvailableColors());
		
		return buildMessage();
	}

	@Override
	protected Request buildMessage() 
	{
		ElectCouncillorMessage request;
		if(location == null)
			request =  new ElectCouncillorMessage(color, model.getKing());
		else
			request =  new ElectCouncillorMessage(color, location);
		request.setMainAction(mainAction);
		return request;
	}

}
