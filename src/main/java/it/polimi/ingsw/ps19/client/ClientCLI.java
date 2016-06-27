/*
/ * @author Andrea Milanta 
 */
package it.polimi.ingsw.ps19.client;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;

import it.polimi.ingsw.ps19.client.clientaction.ClientAction;
import it.polimi.ingsw.ps19.client.clientaction.ClientActionChooser;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.model.Market;
import it.polimi.ingsw.ps19.model.Order;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.bonus.Bonus;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
import it.polimi.ingsw.ps19.model.map.Balcony;
import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.map.King;
import it.polimi.ingsw.ps19.model.map.Region;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

/**
 * CLI User Interface
 */
/*
 * 
 */
public class ClientCLI extends ClientUI 
{
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	/**
	 * @param l: language
	 */
	public ClientCLI(Language l)
	{
		language = l;
	}
	@Override
	public ClientActionChooser requestActionType(List<ClientActionChooser> actionList) 
	{
		boolean valid = false;
		ClientActionChooser action = null;
		int i = 0;
		List<String> strings = new ArrayList<>();
		for(ClientActionChooser a : actionList)
			strings.add(a.toString(language));
		while(!valid)
		{
			writeln(language.chooseActionTypeTitle + ":");
			try {
				int index = getValues(strings);
				action = actionList.get(index);
				valid = true;
			} catch (InvalidInsertionException e) 
			{
				valid = false;
				log.log(Level.SEVERE, e.toString(), e);
			}
			finally
			{
				i++;
				if(i >= ClientConstants.MAX_INPUT_ERRORS)
					valid = true;
			}
		}
		return action;
	}
	
	@Override
	public RegionType getRegion(List<RegionType> regions) throws InvalidInsertionException
	{
		writeln(language.chooseRegionTitle + ":");
		List<String> strings = new ArrayList<>();
		for(RegionType region : regions)
			strings.add(getString(region));
		int index = getValues(strings);
		return regions.get(index);
	}
	
	/*
	 * Gets a valid color from user wrt the parameter array
	 */
	@Override
	public Color getColor(List<Color> validColors) throws InvalidInsertionException
	{
		writeln(language.chooseColor + ":");
		List<String> strings = new ArrayList<>();
		for(Color c : validColors)
			strings.add(getString(c));
		return validColors.get(getValues(strings));
	}

	@Override
	public RegionType getRegionAndKing(List<RegionType> regions) throws InvalidInsertionException 
	{ 
		writeln(language.chooseRegionTitle + ":");
		List<String> strings = new ArrayList<>();
		for(RegionType region : regions)
			strings.add(getString(region));
		strings.add(language.king.toUpperCase());
		int index = getValues(strings);
		if(index == regions.size())
			return null;
		return regions.get(index);
	}

	@Override
	public void showNotification(String s) 
	{
		writeln(s);
	}

	@Override
	public ClientAction getAction(List<ClientAction> actionList) 
	{
		boolean valid = false;
		ClientAction action = null;
		int i = 0;
		List<String> strings = new ArrayList<>();
		for(ClientAction a : actionList)
			strings.add(getString(a));
		while(!valid)
		{
			writeln(language.chooseActionTitle + ":");
			try 
			{
				int index = getValues(strings);
				action = actionList.get(index);
				valid = true;
			} catch (InvalidInsertionException e) 
			{
				valid = false;
				showNotification(ClientConstants.INVALID_INSERTION);
				log.log(Level.SEVERE, e.toString(), e);
				strings.clear();
			}
			finally
			{
				i++;
				if(i >= ClientConstants.MAX_INPUT_ERRORS)
				valid = true;
			}
		}
		return action;
	}

	@Override
	public void drawModel(ClientModel model) 
	{
		writeln(modelToString(model));
	}

	@Override
	public BusinessCard getBusiness(List<BusinessCard> cards) throws InvalidInsertionException
	{
		writeln(language.chooseBusinessCardTitle + ":");
		List<String> strings = new ArrayList<>();
		for(BusinessCard card : cards)
			strings.add(getString(card));
		int index = getValues(strings);
		return cards.get(index);
	}
	
	@Override
	public PoliticsCard getPolitic(List<PoliticsCard> cards) throws InvalidInsertionException 
	{
		writeln(language.choosePoliticCardTitle + ":");
		List<String> strings = new ArrayList<>();
		for(PoliticsCard card : cards)
			strings.add(getString(card));
		int index = getValues(strings);
		return cards.get(index);
	}
	
	@Override
	public City getCity(List<City> cities) throws InvalidInsertionException
	{
		writeln(language.chooseCityTitle + ":");
		List<String> strings = new ArrayList<>();
		for(City city : cities)
			if(city != null)
				strings.add(getString(city));
		int index = getValues(strings);
		return cities.get(index);
	}
	
	@Override
	public City getCity(Map<City, Integer> citiesECosts) throws InvalidInsertionException 
	{
		writeln(language.chooseCityTitle + ":");
		List<String> strings = new ArrayList<>();
		List<City> cities = new ArrayList<>();
		for(Entry<City, Integer> entry : citiesECosts.entrySet())
		{
			strings.add(getString(entry.getKey()) + "(" + entry.getValue().toString() + ")");
			cities.add(entry.getKey());
		}
		int index = getValues(strings);
		return cities.get(index);
	}
	
	public int getValues(List<String> strings) throws InvalidInsertionException
	{
		int i;
		int n;
		if (strings.isEmpty())
			throw new InvalidInsertionException();
		try
		{
			write("(");
			for(i = 0; i < strings.size()-1; i++)
				write(i + " = " + strings.get(i) + ", ");
			writeln(i + " = " + strings.get(i) + ")");
			String s = read();
			n = Integer.parseInt(s);
			if(n >= strings.size() || n < 0)
				throw new IOException();
		}catch(IOException | NumberFormatException e)
		{
			writeln(ClientConstants.INVALID_INSERTION);
			log.log(Level.SEVERE, e.toString(), e);
			throw new InvalidInsertionException();
		}
		return n;
	}
	
	private void write(String s)
	{
		System.out.print(s);
	}
	
	private void writeln(String s)
	{
		System.out.println(s);
	}
	
	/**
	 * Read from CLI
	 * @return string read
	 * @throws IOException
	 */
	public String read() throws IOException
	{
		if(in.ready())
			in.readLine();
		String s = in.readLine();
		return s;
	}
	
	private String getString(BusinessCard card)
	{
		if(card == null)
			return language.nothing;
		String s = "[";
		s = s.concat(language.cities + ": ");
		for(City city: card.getCity())
		{
			s = s.concat(getString(city));
			s = s.concat(", ");
		}
		s += language.bonuses + ": ";
		if(card.getBonus().isEmpty())
			s += "0";
		else
		{
			for(Bonus b : card.getBonus())
			{
				s = s.concat(getString(b));
				s = s.concat(", ");
			}
		}
		s += "]\n";
		return s;
	}
	
	private String getString(Bonus b)
	{
		//TODO visitor on bonus for strings
		return b.getClass().toString().substring(b.getClass().toString().lastIndexOf('.') + 1);
	}
	
	private String getString(City city)
	{
		return city.getName();
	}
	
	private String getFullString(City city)
	{
		String s = city.getName();
		s += ":(";
		s += getString(city.getCitycolor());
		s += ",";
		if(city.getEmporia().isEmpty())
			s += language.noEmporia;
		else
		{
			s += language.emporiaOfPlayers + ": ";
			for(Integer playerId : city.getEmporia())
				s = s.concat(playerId.toString() + ", ");
		}
		s += ")";
		return s;
	}
	
	private String modelToString(ClientModel model)
	{
		String s = "----------------------------------- \n " + language.map.toUpperCase() + "\n \n \n";
		for(Region region: model.getRegions())
		{
			s = s.concat(getString(region));
			s = s.concat("\n\n");
		}
		s += getString(model.getKing());
		s += "\n";
		for(Player p : model.getPlayer())
		{
			s = s.concat(getString(p));
			s = s.concat("\n\n");
		}
		s += language.result + ": " + model.getResult();
		s += "\n\n" + language.activePlayerId + ": " + model.getActiveplayer();
		s += "\n--------------------------------------\n";
		s += language.youArePlayer + ": " + model.getMyPlayer().getId() + "\n\n";
		return s;
	}
	
	private String getString(Player p)
	{
		String s = language.player + ": "+ p.getId() + "\n";
		s += language.numEmporiaLeft + ": " + p.getMaxemporia() + "\n";
		s += language.money + ": " + p.getMoney() + "\n";
		s += language.victoryPoints + ": " + p.getVictoryPoints() + "\n";
		s += language.nobilityPoints + ": " + p.getNobilityPoints() + "\n";
		s += language.numOfHelpers + ": " + p.getHelpers() + "\n";
		s += language.numberOf + " " + language.main + " " + language.available + ": " + p.getMainActionCounter() + "\n";
		s += language.numberOf + " " + language.quick + " " + language.available + ": " + p.getFastActionCounter() + "\n";
		s += language.politicCards + ": ";
		if(p.getPoliticcard().isEmpty())
			s += 0 + "\n";
		else
		{
			s += "[";
			for(PoliticsCard card : p.getPoliticcard())
				s = s.concat(getString(card) + ", ");
			s += "]\n";
		}
		s += language.freeBusiness + ":";
		if(p.getFreebusinesscard().isEmpty())
			s += 0 + "\n";
		else
		{
			s += "\n";
			for(BusinessCard card : p.getFreebusinesscard())
				s = s.concat(getString(card));
		}
		s += language.usedBusiness + ":";
		if(p.getUsedbusinesscard().isEmpty())
			s += 0 + "\n";
		else
		{
			s += "\n";
			for(BusinessCard card : p.getUsedbusinesscard())
				s = s.concat(getString(card));
		}
		return s;
	}
	
	private String getString(King king)
	{
		String s = language.king + "\n";
		s += language.currentCity + ": " + getString(king.getCurrentcity()) + "\n";
		s += getString(king.getBalcony()) + "\n";
		return s;
	}

	private String getString(Color c)
	{
		if(c == null)
			return language.nothing;
		return "#" + Integer.toHexString(c.getRGB()).substring(2).toUpperCase();
	}

	private String getString(PoliticsCard card)
	{
		if(card == null)
			return language.nothing;
		return getString(card.getColor());
	}
	
	private String getString(RegionType region)
	{
		return language.getString(region).toUpperCase();
	}
	
	private String getString(ClientAction input)
	{
		return input.toString(language);
	}

	private String getString(Balcony b)
	{
		String s = language.balcony + ": [";
		for(Color c : b.getCouncilcolor())
		{
			s = s.concat(getString(c) + ", ");
		}
		s += "]\n";
		return s;
	}
	
	private String getString(Region region)
	{
		String s = language.region + ": ";
		s += getString(region.getType()) + "\n";
		s += getString(region.getBalcony());
		s += language.cities + ":\n";
		for(City c : region.getCities())
		{
			s = s.concat("\t" + getFullString(c) + "\n");
		}
		s += language.businessCards + ":\n";
		s += "\t" + language.firstCard + ": ";
		s += getString(region.getFirstcard());
		s += "\t" + language.secondCard + ": ";
		s += getString(region.getSecondcard());
		return s;
	}
	
	@Override
	public int getNumberOfHelpers(int n) throws InvalidInsertionException 
	{
		int number;
		writeln(language.howManyHelpersToSell + "(" + language.available + ": " + n + ")\n");
		try
		{
			String s = read();
			number = Integer.parseInt(s);
			if(number > n || number < 0)
				throw new IOException();
		}catch(IOException | NumberFormatException e)
		{
			writeln(ClientConstants.INVALID_INSERTION);
			log.log(Level.SEVERE, e.toString(), e);
			throw new InvalidInsertionException();
		}
		return number;
	}

	@Override
	public int getPrice() throws InvalidInsertionException 
	{
		int number;
		writeln(language.setPrice + ":");
		try
		{
			String s = read();
			number = Integer.parseInt(s);
			if(number < 0)
				throw new IOException();
		}catch(IOException | NumberFormatException e)
		{
			writeln(ClientConstants.INVALID_INSERTION);
			log.log(Level.SEVERE, e.toString(), e);
			throw new InvalidInsertionException();
		}
		return number;
	}

	@Override
	public String getUserString(String title) throws InvalidInsertionException 
	{
		writeln(title);
		String s;
		try {
			s = read();
		} catch (IOException e) 
		{
			ClientLogger.log.log(Level.SEVERE, e.toString(), e);
			throw new InvalidInsertionException();
		}
		return s;
	}

	@Override
	public Order getOrder(List<Order> orders) throws InvalidInsertionException 
	{
		writeln(language.chooseOrder + ": \n");
		List<String> strings = new ArrayList<>();
		for(Order order : orders)
			strings.add(getString(order) + "\n");
		int index = getValues(strings);
		return orders.get(index);
	}

	@Override
	public void showMarket(Market market) 
	{
		String marketString = language.market.toUpperCase() + "\n";
		if(market.getListoforder().isEmpty())
		{
			showNotification(marketString + language.orders + ": " + language.nothing);
			return;
		}
		marketString += language.orders + ":\n";
		for(Entry<Integer, Order> entry : market.getListoforder().entrySet())
		{
			marketString = marketString.concat("\t" + language.player + ": " + entry.getKey() + " " + getString(entry.getValue()));
		}
		showNotification(marketString);
	}
	
	private String getString(Order order)
	{
		if(order == null)
			return language.nothing;
		String s = "[" + language.helpers + ": " + order.getHelper();
		s += ", " + language.politicCards + ": ";
		if(order.getPoliticscard().isEmpty())
			s += "0,";
		else
			for(Color card : order.getPoliticscard())
				s = s.concat(getString(card) + ",");
		s += " " + language.businessCards + ": ";
		if(order.getBusinesscard().isEmpty())
			s += "0,";
		else
			for(BusinessCard card : order.getBusinesscard())
				s = s.concat(getString(card) + ",");
		s += " " + language.price + ": " + order.getPrice() + "]";
		return s;
	}

	/**
	 * get integer from user
	 * @param title
	 * @return
	 * @throws InvalidInsertionException
	 */
	public int getInt(String title) throws InvalidInsertionException
	{
		int number;
		showNotification(title);
		try 
		{
			String s = read();
			number = Integer.parseInt(s);
		} catch (IOException | NumberFormatException e) 
		{
			log.log(Level.SEVERE, e.toString(), e);
			throw new InvalidInsertionException();
		}
		return number;
	}
}
