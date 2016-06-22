package it.polimi.ingsw.ps19.client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import org.hamcrest.core.IsInstanceOf;

import it.polimi.ingsw.ps19.client.clientaction.BuildEmporiumInputs;
import it.polimi.ingsw.ps19.client.clientaction.BuildWithKingInputs;
import it.polimi.ingsw.ps19.client.clientaction.BuyHelperInputs;
import it.polimi.ingsw.ps19.client.clientaction.BuyMainActionInput;
import it.polimi.ingsw.ps19.client.clientaction.ClientAction;
import it.polimi.ingsw.ps19.client.clientaction.ClientActionChooser;
import it.polimi.ingsw.ps19.client.clientaction.ElectCouncillorInputs;
import it.polimi.ingsw.ps19.client.clientaction.EndTurnInput;
import it.polimi.ingsw.ps19.client.clientaction.FastAction;
import it.polimi.ingsw.ps19.client.clientaction.GetBusinessPermitInput;
import it.polimi.ingsw.ps19.client.clientaction.MainAction;
import it.polimi.ingsw.ps19.client.clientaction.MarketSell;
import it.polimi.ingsw.ps19.client.clientaction.RedrawBusinessCardInput;
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

public class ClientGUI extends ClientUI implements ActionListener{
	
	MainWindow window=null;
	Notify pop = new Notify("Waiting for messages..");
	Boolean flagTypeAction = true;
	Boolean flagAction = true;
	ClientActionChooser actiontype;
	ClientAction action;

	private static final Logger LOG = Logger.getLogger("GUI_LOGGER");
	private static final String START = "Wait the Server to generate the game..";
	
	private static final String MAIN_ACTION_COMMAND = "Take a Main Actions";
	private static final String FAST_ACTION_COMMAND= "Take a Fast Actions";
	
	public ClientGUI() {
		pop.addMessage(START);
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
		flagTypeAction=true;
		window.getFrame().getInfobox().getBoxes().get(0).enableActionType();
		while(flagTypeAction){
			//wait the button to be pressed
		}
		window.getFrame().getInfobox().getBoxes().get(0).disableActionType();
		return actiontype;
	}

	@Override
	public ClientAction getAction(List<ClientAction> actions) {
		flagAction=true;
		window.getFrame().getInfobox().getBoxes().get(0).getActions().generateActions(actions);
		window.getFrame().getInfobox().getBoxes().get(0).getActions().setListener(this);
		while(flagTypeAction){
			//wait the button to be pressed
		}
		window.getFrame().getInfobox().getBoxes().get(0).getActions().disableButtons();
		return action;
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
				LOG.log(Level.SEVERE, e1.toString(), e1);
			}
			registerListerner();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(MAIN_ACTION_COMMAND)){
			actiontype = new MainAction(window.getFrame().getModel());
			flagTypeAction=false;
		}
		else if(e.getActionCommand().equals(FAST_ACTION_COMMAND)){
			actiontype = new FastAction(window.getFrame().getModel());
			flagTypeAction=false;
		}
		action = actionChooser(e);
		if(action!=null){
			flagAction=false;
		}
		
		
		// TODO Auto-generated method stub
		
	}
	
	public ClientAction actionChooser(ActionEvent e){
		action = new BuildEmporiumInputs(window.getFrame().getModel());
		if(actionCheck(action, e)){
			return action;
		}
		action = new BuildWithKingInputs(window.getFrame().getModel());
		if(actionCheck(action, e)){
			return action;
		}
		action = new BuyHelperInputs(window.getFrame().getModel());
		if(actionCheck(action, e)){
			return action;
		}
		action = new BuyMainActionInput(window.getFrame().getModel());
		if(actionCheck(action, e)){
			return action;
		}
		if(actiontype instanceof MainAction){
			action = new ElectCouncillorInputs(window.getFrame().getModel(), true);
			if(actionCheck(action, e)){
				return action;
			}
		}
		else if(actiontype instanceof FastAction){
			action = new ElectCouncillorInputs(window.getFrame().getModel(), false);
			if(actionCheck(action, e)){
				return action;
			}
		}
		action = new EndTurnInput(window.getFrame().getModel());
		if(actionCheck(action, e)){
			return action;
		}
		action = new GetBusinessPermitInput(window.getFrame().getModel());
		if(actionCheck(action, e)){
			return action;
		}
		action = new MarketSell(window.getFrame().getModel());
		if(actionCheck(action, e)){
			return action;
		}
		action = new RedrawBusinessCardInput(window.getFrame().getModel());
		if(actionCheck(action, e)){
			return action;
		}
		return null;
	}
	
	public boolean actionCheck(ClientAction ca, ActionEvent e){
		if(e.getActionCommand().equals(ca.toString())){
			return true;
		}
		return false;
	}
	
	
	private void registerListerner(){
		window.getFrame().getInfobox().getBoxes().get(0).setListerner(this);
	}
	

	/**
	 * @return the mainActionCommand
	 */
	public static String getMainActionCommand() {
		return MAIN_ACTION_COMMAND;
	}

	/**
	 * @return the fastActionCommand
	 */
	public static String getFastActionCommand() {
		return FAST_ACTION_COMMAND;
	}
	
}
