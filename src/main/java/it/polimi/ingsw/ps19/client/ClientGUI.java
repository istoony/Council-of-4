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
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.Order;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

public class ClientGUI extends ClientUI{
	MainWindow window=null;

	private static final Logger log = Logger.getLogger("GUI_LOGGER");
	
	public ClientGUI() {

	}
	
	public void createGUI(ClientModel m){
		MainWindow window = new MainWindow(m);
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
		Notify pop = new Notify(s);
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
	
	public static void main(String args[]){
		Model mm = new Model(10);
		ClientModel m = new ClientModel(mm);
		m.getMyPlayer().setStartingAction();

		
		PoliticsCard c = new PoliticsCard(Color.decode("#FFFFFF"));
		PoliticsCard d = new PoliticsCard(Color.decode("#FFFFFF"));
		PoliticsCard e = new PoliticsCard(Color.decode("#000000"));
		PoliticsCard f = new PoliticsCard(Color.decode("#0000FF"));
		PoliticsCard g = new PoliticsCard(Color.decode("#FF0000"));
		PoliticsCard h = new PoliticsCard(Color.decode("#000000"));
		PoliticsCard j = new PoliticsCard(Color.decode("#0000FF"));
		PoliticsCard k = new PoliticsCard(Color.decode("#FF0000"));
		BusinessCard aa = new BusinessCard(null, null, m.getAllCities().get(0));
		
		m.getMyPlayer().addCardToHand(c);
		m.getMyPlayer().addCardToHand(d);
		m.getMyPlayer().addCardToHand(e);
		m.getMyPlayer().addCardToHand(f);
		m.getMyPlayer().addCardToHand(g);
		m.getMyPlayer().addCardToHand(h);
		m.getMyPlayer().addCardToHand(j);
		m.getMyPlayer().addCardToHand(k);
		m.getMyPlayer().addCardToHand(g);
		m.getMyPlayer().addCardToHand(h);
		m.getMyPlayer().addCardToHand(j);
		m.getMyPlayer().addCardToHand(k);
		m.getPlayer().get(1).addCardToHand(c);
		m.getPlayer().get(1).addCardToHand(e);
		m.getPlayer().get(1).addCardToHand(f);
		m.getMyPlayer().addCardToHand(aa);
		
		
		MainWindow window = new MainWindow(m);
		try {
			SwingUtilities.invokeAndWait(window);
		} catch (InvocationTargetException | InterruptedException e1) {
			log.log(Level.SEVERE, e1.toString(), e1);
		}
	}


}
