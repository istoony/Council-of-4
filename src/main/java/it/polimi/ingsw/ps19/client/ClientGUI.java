package it.polimi.ingsw.ps19.client;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import it.polimi.ingsw.ps19.client.clientaction.ClientAction;
import it.polimi.ingsw.ps19.client.clientaction.ClientActionChooser;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.client.guicomponents.MainWindow;
import it.polimi.ingsw.ps19.client.guicomponents.Notify;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.model.Market;
import it.polimi.ingsw.ps19.model.Order;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

public class ClientGUI extends ClientUI{
	MainWindow window=null;
	Notify pop = new Notify("Waiting for messages..");

	private static final Logger log = Logger.getLogger("GUI_LOGGER");
	private static final String start = "Wait the Server to generate the game..";
	
	public ClientGUI() {
		pop.addMessage(start);
		try {
			SwingUtilities.invokeAndWait(pop);
		} catch (InvocationTargetException | InterruptedException e1) {
			log.log(Level.SEVERE, e1.toString(), e1);
		}
	}
	
	public void createGUI(ClientModel m){
		window = new MainWindow(m);
		try {
			SwingUtilities.invokeAndWait(window);
		} catch (InvocationTargetException | InterruptedException e) {
			log.log(Level.SEVERE, e.toString(), e);
		}
	}
	
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
		pop.addMessage(s);
		try {
			SwingUtilities.invokeAndWait(pop);
		} catch (InvocationTargetException | InterruptedException e1) {
			log.log(Level.SEVERE, e1.toString(), e1);
		}
		
	}

	@Override
	public void drawModel(ClientModel model) {	
		if(window==null){
			window = new MainWindow(model);
			try {
				SwingUtilities.invokeAndWait(window);
			} catch (InvocationTargetException | InterruptedException e1) {
				log.log(Level.SEVERE, e1.toString(), e1);
			}
		}
		else{
			window.update(model);
		}
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

	@Override
	public String getUserString(String title) throws InvalidInsertionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getOrder(List<Order> orders) throws InvalidInsertionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showMarket(Market market) {
		// TODO Auto-generated method stub
		
	}
	
}
