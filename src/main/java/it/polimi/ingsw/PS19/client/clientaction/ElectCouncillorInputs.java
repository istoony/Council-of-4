package it.polimi.ingsw.PS19.client.clientaction;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.PS19.client.ClientUI;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.message.requests.ElectCouncillorMessage;
import it.polimi.ingsw.PS19.message.requests.Request;
import it.polimi.ingsw.PS19.model.parameter.RegionType;
import it.polimi.ingsw.PS19.model.map.King;

public class ElectCouncillorInputs extends ClientAction 
{
	private ArrayList<Color> councillorsColors = new ArrayList<Color>();
	RegionType location;
	boolean mainAction = false;
	Color color;
	
	public ElectCouncillorInputs(boolean main)
	{
		mainAction = main;
		councillorsColors.add(new Color(0xFF0000));
		councillorsColors.add(new Color(0x0000FF));
		councillorsColors.add(new Color(0xFF7F00));
		councillorsColors.add(new Color(0x000000));
		councillorsColors.add(new Color(0xFFFFFF));
		councillorsColors.add(new Color(0xFFC0CB));
	}
	
	@Override
	public boolean isPossible(ClientModel model) 
	{
		return true;
	}

	@Override
	public Request Execute(ClientUI userInterface, ClientModel model) throws InvalidInsertionException 
	{
		location = userInterface.getRegionAndKing();
		color = userInterface.getAndValidateColor(councillorsColors);
		
		return buildMessage(model);
	}

	@Override
	protected Request buildMessage(ClientModel model) 
	{
		ElectCouncillorMessage request;
		if(location == null)
			request =  new ElectCouncillorMessage(color, (King)getUsefulInfo(model).get(0));
		else
			request =  new ElectCouncillorMessage(color, location);
		request.setMainAction(mainAction);
		return request;
	}
	
	@Override
	public String toString()
	{
		return "Elect Councillor";
	}

	@Override
	public List<Object> getUsefulInfo(ClientModel model) 
	{
		List<Object> list = new ArrayList<>();
		list.add(model.getKing());
		return list;
	}

}
