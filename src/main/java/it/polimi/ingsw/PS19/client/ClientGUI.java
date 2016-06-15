package it.polimi.ingsw.PS19.client;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import it.polimi.ingsw.PS19.client.clientaction.ClientAction;
import it.polimi.ingsw.PS19.client.clientaction.ClientActionChooser;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.client.guicomponents.MainWindow;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.card.PoliticsCard;
import it.polimi.ingsw.PS19.model.map.City;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class ClientGUI extends ClientUI{

	
	@Override
	public ClientActionChooser requestActionType(List<ClientActionChooser> actions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientAction getAction(List<ClientAction> actions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegionType getRegion(List<RegionType> regions) throws InvalidInsertionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getColor(List<Color> validColors) throws InvalidInsertionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegionType getRegionAndKing(List<RegionType> regions) throws InvalidInsertionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showNotification(String s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawModel(ClientModel model) {
		MainWindow view = new MainWindow(model);
		// TODO Auto-generated method stub
		
	}

	@Override
	public BusinessCard getBusiness(List<BusinessCard> cards) throws InvalidInsertionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PoliticsCard getPolitic(List<PoliticsCard> cards) throws InvalidInsertionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public City getCity(List<City> cities) throws InvalidInsertionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public City getCity(Map<City, Integer> citiesECost) throws InvalidInsertionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfHelpers(int n) throws InvalidInsertionException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPrice() throws InvalidInsertionException {
		// TODO Auto-generated method stub
		return 0;
	}

}
