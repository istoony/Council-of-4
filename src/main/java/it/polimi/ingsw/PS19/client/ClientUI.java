package it.polimi.ingsw.PS19.client;

import java.awt.Color;
import java.util.ArrayList;

import it.polimi.ingsw.PS19.client.clientaction.ClientAction;
import it.polimi.ingsw.PS19.client.clientaction.ClientActionChooser;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.model.parameter.RegionType;



public interface ClientUI
{	
	ClientActionChooser requestActionType(ArrayList<ClientActionChooser> actions);
	ClientAction getAction(ArrayList<ClientAction> actions);
	RegionType getRegion() throws InvalidInsertionException;
	Color getAndValidateColor(ArrayList<Color> validColors) throws InvalidInsertionException;
	RegionType getRegionAndKing() throws InvalidInsertionException;
	void showNotification(String s);
	
}
