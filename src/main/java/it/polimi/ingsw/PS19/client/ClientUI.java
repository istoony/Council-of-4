package it.polimi.ingsw.PS19.client;

import java.awt.Color;
import java.util.List;

import it.polimi.ingsw.PS19.client.clientaction.ClientAction;
import it.polimi.ingsw.PS19.client.clientaction.ClientActionChooser;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.model.parameter.RegionType;



public interface ClientUI
{	
	public ClientActionChooser requestActionType(List<ClientActionChooser> actions);
	
	public ClientAction getAction(List<ClientAction> actions);
	
	public RegionType getRegion() throws InvalidInsertionException;
	
	public Color getAndValidateColor(List<Color> validColors) throws InvalidInsertionException;
	
	public RegionType getRegionAndKing() throws InvalidInsertionException;
	
	public void showNotification(String s);
	
}
