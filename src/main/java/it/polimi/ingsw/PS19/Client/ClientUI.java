package it.polimi.ingsw.PS19.Client;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import it.polimi.ingsw.PS19.Client.clientAction.ClientAction;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.model.parameter.RegionType;



public interface ClientUI
{	
	ClientAction requestActionType();
	
	RegionType getRegion() throws InvalidInsertionException, IOException;
	Color getAndValidateColor(ArrayList<Color> validColors) throws InvalidInsertionException, IOException;
	RegionType getRegionAndKing() throws InvalidInsertionException, IOException;
	void showNotification(String s);

	
}
