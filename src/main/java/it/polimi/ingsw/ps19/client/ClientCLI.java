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

import it.polimi.ingsw.ps19.client.clientinput.ClientAction;
import it.polimi.ingsw.ps19.client.clientinput.ClientActionChooser;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.model.Market;
import it.polimi.ingsw.ps19.model.Order;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
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
			strings.add(language.getString(region));
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
			strings.add(language.getString(c));
		return validColors.get(getValues(strings));
	}

	@Override
	public RegionType getRegionAndKing(List<RegionType> regions) throws InvalidInsertionException 
	{ 
		writeln(language.chooseRegionTitle + ":");
		List<String> strings = new ArrayList<>();
		for(RegionType region : regions)
			strings.add(language.getString(region));
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
	public ClientAction getAction(List<ClientAction> actionList) throws InvalidInsertionException
	{
		ClientAction action;
		List<String> strings = new ArrayList<>();
		for(ClientAction a : actionList)
			strings.add(language.getString(a));
		writeln(language.chooseActionTitle + ":");
		int index = getValues(strings);
		action = actionList.get(index);
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
			strings.add(language.getString(card) + "\n");
		int index = getValues(strings);
		return cards.get(index);
	}
	
	@Override
	public PoliticsCard getPolitic(List<PoliticsCard> cards) throws InvalidInsertionException 
	{
		writeln(language.choosePoliticCardTitle + ":");
		List<String> strings = new ArrayList<>();
		for(PoliticsCard card : cards)
			strings.add(language.getString(card));
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
				strings.add(language.getString(city));
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
			strings.add(language.getString(entry.getKey()) + "(" + entry.getValue().toString() + ")");
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
			if(strings.get(0).contains("\n"))
				write("\t0 = " + strings.get(0));
			else
				write("(0 = " + strings.get(0));
			for(i = 1; i < strings.size(); i++)
			{
				if(strings.get(0).contains("\n"))
					write("\t" + i + " = " + strings.get(i));
				else
					write(", " + i + " = " + strings.get(i));
			}
			if(!strings.get(0).contains("\n"))
				writeln(")");
			String s = read();
			n = Integer.parseInt(s);
			if(n >= strings.size() || n < 0)
				throw new NumberFormatException();
		}catch(IOException | NumberFormatException e)
		{
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
		return in.readLine();
	}
	
	private String getFullString(City city)
	{
		String s = city.getName();
		s += ":(";
		s += language.getString(city.getCitycolor());
		s += ", " + language.bonuses + ":";
		if(city.getBonus().isEmpty())
			s += " 0, ";
		else
		{
			s = s.concat("(" + city.getBonus().get(0).toString(language));
			for(int i = 1; i < city.getBonus().size(); i++)
				s = s.concat(", " + city.getBonus().get(i).toString(language));
			s += ")";
		}
		if(city.getEmporia().isEmpty())
			s += language.noEmporia;
		else
		{
			s += language.emporiaOfPlayers + ": " + city.getEmporia().get(0);
			for(int i = 1; i < city.getEmporia().size(); i++)
				s = s.concat(", " + city.getEmporia().get(i).toString());
			s += ")";
		}
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
		s += language.nobilityPath.toUpperCase() + ":\n";
		for(int i = 0; i <= model.getNobilitypath().getMaxKey(); i++)
		{
			s = s.concat(i + ": [");
			if(model.getNobilitypath().getBonusByPosition(i) == null || model.getNobilitypath().getBonusByPosition(i).isEmpty())
				s = s.concat("0");
			else
			{
				s = s.concat(language.getString(model.getNobilitypath().getBonusByPosition(i).get(0)));
				for(int j = 1; j < model.getNobilitypath().getBonusByPosition(i).size(); j++)
					s = s.concat(", " + language.getString(model.getNobilitypath().getBonusByPosition(i).get(j)));
			}
			s = s.concat("]\n");
		}
		s += "\n";
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
			s += "[" + language.getString(p.getPoliticcard().get(0));
			for(int i = 1; i < p.getPoliticcard().size(); i++)
				s = s.concat(", " + language.getString(p.getPoliticcard().get(i)));
			s += "]\n";
		}
		s += language.freeBusiness + ":";
		if(p.getFreebusinesscard().isEmpty())
			s += 0 + "\n";
		else
		{
			for(BusinessCard card : p.getFreebusinesscard())
				s = s.concat("\n\t[" + language.getString(card) + "]");
		}
		s += "\n" + language.usedBusiness + ":";
		if(p.getUsedbusinesscard().isEmpty())
			s += 0 + "\n";
		else
		{
			for(BusinessCard card : p.getUsedbusinesscard())
				s = s.concat("\n\t[" + language.getString(card) + "]");
		}
		return s;
	}
	
	private String getString(King king)
	{
		String s = language.king.toUpperCase() + "\n";
		s += language.currentCity + ": " + language.getString(king.getCurrentcity()) + "\n";
		s += language.getString(king.getBalcony()) + "\n";
		return s;
	}
	
	private String getString(Region region)
	{
		String s = language.region + ": ";
		s += language.getString(region.getType()) + "\n";
		s += language.getString(region.getBalcony());
		s += language.cities + ":\n";
		for(City c : region.getCities())
		{
			s = s.concat("\t" + getFullString(c) + "\n");
		}
		s += language.businessCards + ":\n";
		s += "\t" + language.firstCard + ": ";
		s += language.getString(region.getFirstcard());
		s += "\n\t" + language.secondCard + ": ";
		s += language.getString(region.getSecondcard());
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
			writeln(language.invalidInsertion);
			log.log(Level.SEVERE, e.toString(), e);
			throw new InvalidInsertionException();
		}
		return number;
	}

	@Override
	public int getPrice() throws InvalidInsertionException 
	{
		return getInt(language.setPrice);
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
			ClientStarter.log.log(e);
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
			strings.add(language.getString(order) + "\n");
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
			marketString = marketString.concat("\t" + language.player + ": " + entry.getKey() + " " + language.getString(entry.getValue()) + "\n");
		}
		showNotification(marketString);
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
		showNotification(title + ": ");
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
	
	@Override
	public void showWinner(List<Player> players, String result) 
	{
		String s = "\n" + language.winner.toUpperCase() + ": " + players.get(0).getId() + "\n\n";
		players.forEach(p -> writeln(getString(p)));
		/*
		for(Player p : players)
			s = s.concat(getString(p) + "\n");
		*/
		s += "\n" + language.result + ": " + result;
		showNotification(s);
	}
}
