package it.polimi.ingsw.ps19.client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import it.polimi.ingsw.ps19.client.clientaction.ClientAction;
import it.polimi.ingsw.ps19.client.clientaction.ClientActionChooser;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.client.guicomponents.MainWindow;
import it.polimi.ingsw.ps19.client.guicomponents.Notify;
import it.polimi.ingsw.ps19.client.guicomponents.QuestionFrame;
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
	QuestionFrame ask;
	
	volatile List<ClientAction> actionTemp = new ArrayList<>();
	volatile List<RegionType> regionTemp = new ArrayList<>();
	volatile List<Color> colorTemp = new ArrayList<>();
	volatile List<PoliticsCard> politicTemp = new ArrayList<>();
	volatile List<BusinessCard> businessTemp = new ArrayList<>();
	volatile List<City> cityTemp = new ArrayList<>();
	
	static volatile List<Integer> index;

	private static final String START = "Wait the Server to generate the game..";
	
	private static final String MAIN_ACTION_COMMAND = "Take a Main Actions";
	private static final String FAST_ACTION_COMMAND= "Take a Fast Actions";
	
	public ClientGUI() {
		index = new ArrayList<>();
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
	public synchronized ClientActionChooser requestActionType(List<ClientActionChooser> actions) {
		index.clear();
		window.getFrame().getInfobox().getBoxes().get(0).enableActionType();
		while(index.isEmpty()){
			//wait the button to be pressed
		}
		window.getFrame().getInfobox().getBoxes().get(0).disableActionType();
		return actions.get(index.get(0));
	}

	@Override
	public ClientAction getAction(List<ClientAction> actions) {
		index.clear();
		actionTemp.addAll(actions);
		window.getFrame().getInfobox().getBoxes().get(0).getActions().generateActions(actions);
		window.getFrame().getInfobox().getBoxes().get(0).getActions().setListener(this);
		window.getFrame().revalidate();
		window.getFrame().repaint();
		while(index.isEmpty()){
			//wait button
		}
		window.getFrame().getInfobox().getBoxes().get(0).getActions().disableButtons();
		actionTemp.clear();
		return actions.get(index.get(0));
	}

	@Override
	public RegionType getRegion(List<RegionType> regions) throws InvalidInsertionException {
		index.clear();
		regionTemp.addAll(regions);
		ask = new QuestionFrame(this, regions);
		SwingUtilities.invokeLater(ask);
		while(index.isEmpty()){
			//wait the button to be pressed
		}
		ask.close();
		regionTemp.clear();
		return regions.get(index.get(0));
	}

	@Override
	public Color getColor(List<Color> validColors) throws InvalidInsertionException {
		index.clear();
		colorTemp.addAll(validColors);
		ask = new QuestionFrame(this, validColors);
		SwingUtilities.invokeLater(ask);
		while(index.isEmpty()){
			//wait the button to be pressed
		}
		ask.close();
		return validColors.get(index.get(0));
	}

	@Override
	public RegionType getRegionAndKing(List<RegionType> regions) throws InvalidInsertionException {
		index.clear();
		regionTemp.addAll(regions);
		ask = new QuestionFrame(this, regions);
		ask.run();
		SwingUtilities.invokeLater(ask);
		while(index.isEmpty()){
			//wait the button to be pressed
		}
		ask.close();
		regionTemp.clear();
		return regions.get(index.get(0));
	}

	@Override
	public void showNotification(String s) {
		pop.addMessage(s);
		SwingUtilities.invokeLater(pop);
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
			registerListerner();
		}
		else{
			window.update(model);
		}
	}

	@Override
	public BusinessCard getBusiness(List<BusinessCard> cards) throws InvalidInsertionException {
		return null;
	}

	@Override
	public PoliticsCard getPolitic(List<PoliticsCard> cards) throws InvalidInsertionException {
		index.clear();
		politicTemp.addAll(cards);
		ask = new QuestionFrame(this, cards);
		SwingUtilities.invokeLater(ask);
		while(index.isEmpty()){
			//wait the button to be pressed
		}
		politicTemp.clear();
		ask.close();
		return cards.get(index.get(0));
	}

	@Override
	public City getCity(List<City> cities) throws InvalidInsertionException {
		index.clear();
		cityTemp.addAll(cities);
		ask = new QuestionFrame(this, cities);
		SwingUtilities.invokeLater(ask);
		while(index.isEmpty()){
			//wait the button to be pressed
		}
		cityTemp.clear();
		ask.close();
		return cities.get(index.get(0));
	}

	@Override
	public City getCity(Map<City, Integer> citiesECost) throws InvalidInsertionException {
		return null;
	}

	@Override
	public int getNumberOfHelpers(int n) throws InvalidInsertionException {
		index.clear();
		ask = new QuestionFrame(this, n);
		SwingUtilities.invokeLater(ask);
		while(index.isEmpty()){
			//wait the button to be pressed
		}
		ask.close();
		return index.get(0);
	}

	@Override
	public int getPrice() throws InvalidInsertionException {
		index.clear();
		ask = new QuestionFrame(this);
		SwingUtilities.invokeLater(ask);
		while(index.isEmpty()){
			//wait the button to be pressed
		}
		ask.close();
		return index.get(0);
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
			index.add(0);
		}
		else if(e.getActionCommand().equals(FAST_ACTION_COMMAND)){
			index.add(1);
		}
		else if(!actionTemp.isEmpty()){
			actionCheck(e);
		}
		else if(!regionTemp.isEmpty()){
			regionCheck(e);	
		}
		else if(!colorTemp.isEmpty()){
			colorCheck(e);
		}
		else if(!cityTemp.isEmpty()){
			cityCheck(e);
		}
		else {
			numberCheck(e);
			textReader(e);
		}					
		// TODO Auto-generated method stub
		
	}
	
	private void actionCheck(ActionEvent e){
		for(ClientAction ca : actionTemp){
			if(e.getActionCommand().equals(ca.toString())){
				index.add(actionTemp.indexOf(ca));
			}
		}
	}
	
	private void regionCheck(ActionEvent e){
		for(RegionType rt : regionTemp){
			if(e.getActionCommand().equals(rt.toString())){
				index.add(regionTemp.indexOf(rt));
			}
		}
	}
	
	private void colorCheck(ActionEvent e){
		for(Color c : colorTemp){
			if(e.getActionCommand().equals(c.toString())){
				index.add(colorTemp.indexOf(c));
			}
		}
	}
	
	private void cityCheck(ActionEvent e){
		for(City c : cityTemp){
			if(e.getActionCommand().equals(c.getName())){
				index.add(cityTemp.indexOf(c));
			}
		}
	}
	
	private void textReader(ActionEvent e){
		if(e.getSource() instanceof JTextField){
			try{
				int n = Integer.parseInt(ask.getInput().getText());
				index.add(n);
			}
			catch(Exception e1){
				log.log(Level.SEVERE, e1.toString(), e1);
				textReader(e);
			}
		}
	}
	
	private void numberCheck(ActionEvent e){
		try{
			int n = Integer.parseInt(e.getActionCommand());
			index.add(n);
		}
		catch(Exception ex){
			log.log(Level.SEVERE, ex.toString(), ex);
		}
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
