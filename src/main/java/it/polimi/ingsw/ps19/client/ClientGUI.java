package it.polimi.ingsw.ps19.client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import it.polimi.ingsw.ps19.client.clientaction.ClientAction;
import it.polimi.ingsw.ps19.client.clientaction.ClientActionChooser;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.client.guicomponents.MainWindow;
import it.polimi.ingsw.ps19.client.guicomponents.MarketFrame;
import it.polimi.ingsw.ps19.client.guicomponents.MarketShow;
import it.polimi.ingsw.ps19.client.guicomponents.Notify;
import it.polimi.ingsw.ps19.client.guicomponents.QuestionFrame;
import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.model.Market;
import it.polimi.ingsw.ps19.model.Order;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

public class ClientGUI extends ClientUI implements ActionListener{
	
	private MainWindow window=null;
	private Notify pop = new Notify("Waiting for messages..");
	private QuestionFrame ask;
	private MarketFrame market;
	private MarketShow showmarket;
	
	private boolean numberflag=false;
	
	
	volatile List<ClientAction> actionTemp = new ArrayList<>();
	volatile List<RegionType> regionTemp = new ArrayList<>();
	volatile List<Color> colorTemp = new ArrayList<>();
	volatile List<PoliticsCard> politicTemp = new ArrayList<>();
	volatile List<BusinessCard> businessTemp = new ArrayList<>();
	volatile List<City> cityTemp = new ArrayList<>();
	volatile Map<City, Integer> mapTemp = new HashMap<>();
	volatile List<Order> orderTemp = new ArrayList<>();
	volatile String stringTemp = new String();
	volatile int lastindex;
	
	static volatile List<Integer> index;

	private static final String START = "Wait the Server to generate the game..";
	
	private static final String MAIN_ACTION_COMMAND = "Take a Main Actions";
	private static final String FAST_ACTION_COMMAND= "Take a Fast Actions";
	
	/**
	 * @param l: language
	 */
	public ClientGUI(Language l) 
	{
		language = l;
		index = new ArrayList<>();
		pop.addMessage(START);
		try {
			SwingUtilities.invokeAndWait(pop);
		} catch (InvocationTargetException | InterruptedException e1) {
			log.log(Level.SEVERE, e1.toString(), e1);
		}
	}

	@Override
	public synchronized ClientActionChooser requestActionType(List<ClientActionChooser> actions) {
		index.clear();
		window.getFrame().getInfobox().getBoxes().get(0).enableActionType();
		window.getFrame().getInfobox().getBoxes().get(0).setListerner(this);
		while(index.isEmpty()){
			//wait button
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
		ask = new QuestionFrame(this, regions, language);
		SwingUtilities.invokeLater(ask);
		while(index.isEmpty()){
			//wait button
		}
		ask.close();
		regionTemp.clear();
		return regions.get(index.get(0));
	}

	@Override
	public Color getColor(List<Color> validColors) throws InvalidInsertionException {
		index.clear();
		colorTemp.addAll(validColors);
		lastindex = colorTemp.size()-2;
		ask = new QuestionFrame(this, validColors, language);
		SwingUtilities.invokeLater(ask);
		while(index.isEmpty()){
			//wait button
		}
		ask.close();
		colorTemp.clear();
		return validColors.get(index.get(0));
	}

	@Override
	public RegionType getRegionAndKing(List<RegionType> regions) throws InvalidInsertionException {
		index.clear();
		regionTemp.addAll(regions);
		ask = new QuestionFrame(this, regions, language);
		SwingUtilities.invokeLater(ask);
		while(index.isEmpty()){
			//wait button
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
			window = new MainWindow(model, language);
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
		index.clear();
		businessTemp.addAll(cards);
		lastindex = businessTemp.size()-1;
		ask = new QuestionFrame(this, cards, language);
		SwingUtilities.invokeLater(ask);
		while(index.isEmpty()){
			//wait button
		}
		businessTemp.clear();
		ask.close();
		return cards.get(index.get(0));
	}

	@Override
	public PoliticsCard getPolitic(List<PoliticsCard> cards) throws InvalidInsertionException {
		index.clear();
		politicTemp.addAll(cards);
		lastindex = politicTemp.size()-1;
		ask = new QuestionFrame(this, cards, language);
		SwingUtilities.invokeLater(ask);
		while(index.isEmpty()){
			//wait button
		}
		politicTemp.clear();
		ask.close();
		return cards.get(index.get(0));
	}

	@Override
	public City getCity(List<City> cities) throws InvalidInsertionException {
		index.clear();
		cityTemp.addAll(cities);
		ask = new QuestionFrame(this, cities, language);
		SwingUtilities.invokeLater(ask);
		while(index.isEmpty()){
			//wait button
		}
		cityTemp.clear();
		ask.close();
		return cities.get(index.get(0));
	}

	@Override
	public City getCity(Map<City, Integer> citiesECost) throws InvalidInsertionException {
		index.clear();
		mapTemp.putAll(citiesECost);
		ask = new QuestionFrame(this, citiesECost, language);
		SwingUtilities.invokeLater(ask);
		while(index.isEmpty()){
			//wait button
		}
		for(Entry<City, Integer> entry: citiesECost.entrySet()){
			if(entry.getKey().getId()==index.get(0)){
				cityTemp.clear();
				ask.close();
				return entry.getKey();
			}
		}
		return null;
	}

	@Override
	public int getNumberOfHelpers(int n) throws InvalidInsertionException {
		showNotification("Market Phase");
		index.clear();
		numberflag=true;
		ask = new QuestionFrame(this, n, language);
		SwingUtilities.invokeLater(ask);
		while(index.isEmpty()){
			//wait button
		}
		ask.close();
		numberflag=false;
		return index.get(0);
	}

	@Override
	public int getPrice() throws InvalidInsertionException {
		index.clear();
		ask = new QuestionFrame(this, "", true, language);
		SwingUtilities.invokeLater(ask);
		while(index.isEmpty()){
			//wait button
		}
		ask.close();
		return index.get(0);
	}

	@Override
	public String getUserString(String title) throws InvalidInsertionException {
		index.clear();
		stringTemp = title;
		ask = new QuestionFrame(this, title, false, language);
		SwingUtilities.invokeLater(ask);
		while(index.isEmpty()){
			//wait button
		}
		String ret = stringTemp;
		stringTemp = new String();
		ask.close();
		return ret;
	}

	@Override
	public Order getOrder(List<Order> orders) throws InvalidInsertionException {
		index.clear();
		orderTemp.addAll(orders);
		lastindex=orders.size()-1;
		market = new MarketFrame(orders, this, language);
		SwingUtilities.invokeLater(market);
		while(index.isEmpty()){
			//wait button
		}
		market.close();
		orderTemp.clear();
		return orders.get(index.get(0));
	}
	
	@Override
	public void showMarket(Market market) {
		showmarket = new MarketShow(market, language);
		SwingUtilities.invokeLater(showmarket);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		index.clear();
		if(nullCheck(e)){
			return;
		}
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
		else if(!politicTemp.isEmpty()){
			politicCheck(e);
		}
		else if(!businessTemp.isEmpty()){
			businessCheck(e);
		}
		else if(!mapTemp.isEmpty()){
			citymapCheck(e);
		}
		else if(!stringTemp.isEmpty()){
			textCheck();
		}
		else if(!orderTemp.isEmpty()){
			marketCheck(e);
		}
		else if(numberflag){
			numberCheck(e);
		}
		else {
			textReader(e);
		}							
	}
	
	private void actionCheck(ActionEvent e){
		for(ClientAction ca : actionTemp){
			if(e.getActionCommand().equals(language.getString(ca))){
				index.add(actionTemp.indexOf(ca));
			}
		}
	}
	
	private void regionCheck(ActionEvent e){
		for(RegionType rt : regionTemp){
			if(e.getActionCommand().equals(language.getString(rt))){
				index.add(regionTemp.indexOf(rt));
			}
		}
	}
	
	private void colorCheck(ActionEvent e){
		for(Color c : colorTemp){
			if(e.getActionCommand().equals(language.getString(c))){
				index.add(colorTemp.indexOf(c));
			}
		}
	}
	
	private boolean nullCheck(ActionEvent e){
		if(e.getActionCommand().equals(language.nothing)){
			index.add(lastindex);
			return true;
		}
		return false;
	}
	
	private void cityCheck(ActionEvent e){
		for(City c : cityTemp){
			if(e.getActionCommand().equals(language.getString(c))){
				index.add(cityTemp.indexOf(c));
			}
		}
	}
	
	private void politicCheck(ActionEvent e){
		for(PoliticsCard p : politicTemp){
			if(e.getActionCommand().equals(language.getString(p))){
				index.add(politicTemp.indexOf(p));
			}
		}
	}
	
	private void businessCheck(ActionEvent e){
		for(BusinessCard b : businessTemp){
			if(e.getActionCommand().equals(language.getString(b))){
				index.add(businessTemp.indexOf(b));
			}
		}
	}
	
	private void citymapCheck(ActionEvent e){
		for(Entry<City, Integer> entry: mapTemp.entrySet()){
			if(e.getActionCommand().equals(language.getString(entry.getKey()) + "(" + entry.getValue().toString() + ")")){
				index.add(entry.getKey().getId());
			}
		}
	}
	
	
	private void textReader(ActionEvent e) {
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
	
	private void marketCheck(ActionEvent e){
		index.add(Integer.parseInt(e.getActionCommand()));
	}
	
	
	private void textCheck() {
		stringTemp = ask.getInput().getText();
		index.add(0);
	}
	
	private void numberCheck(ActionEvent e){
		index.add(Integer.parseInt(e.getActionCommand()));
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

	@Override
	public void showWinner(List<Player> players, String result) {
		String s = "\n" + language.winner.toUpperCase() + ": " + players.get(0).getId() + "\n\n";
		for(Player p : players)
			s = s.concat(((Integer)p.getId()).toString());
		s += "\n\n" + language.result + ": " + result;
		showNotification(s);
	}
	
}
