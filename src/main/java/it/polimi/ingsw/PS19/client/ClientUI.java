package it.polimi.ingsw.PS19.client;

import java.awt.Color;
import java.util.List;
import java.util.logging.Logger;

import it.polimi.ingsw.ps19.client.clientaction.ClientAction;
import it.polimi.ingsw.ps19.client.clientaction.ClientActionChooser;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
import it.polimi.ingsw.ps19.model.parameter.RegionType;



public abstract class ClientUI
{	
	protected static final Logger log = Logger.getLogger("CLIENT_LOGGER");
	
	public abstract ClientActionChooser requestActionType(List<ClientActionChooser> actions);
	
	public abstract ClientAction getAction(List<ClientAction> actions);
	
	public RegionType getRegion() throws InvalidInsertionException
	{
		return(getRegion(RegionType.getValues()));
	}
	
	public abstract RegionType getRegion(List<RegionType> regions) throws InvalidInsertionException;
	
	public abstract Color getColor(List<Color> validColors) throws InvalidInsertionException;
	
	public RegionType getRegionAndKing() throws InvalidInsertionException
	{
		return(getRegionAndKing(RegionType.getValues()));
	}

	public abstract RegionType getRegionAndKing(List<RegionType> regions) throws InvalidInsertionException;

	public abstract void showNotification(String s);
	
	public abstract void drawModel(ClientModel model);
	
	public abstract BusinessCard getBusiness(List<BusinessCard> cards) throws InvalidInsertionException;
	
	public abstract PoliticsCard getPolitic(List<PoliticsCard> cards) throws InvalidInsertionException;

}
