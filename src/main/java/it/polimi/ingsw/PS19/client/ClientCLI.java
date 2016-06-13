/*
 * @author Andrea Milanta 
 */
package it.polimi.ingsw.PS19.client;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import it.polimi.ingsw.PS19.client.clientaction.BuyHelperInputs;
import it.polimi.ingsw.PS19.client.clientaction.ClientAction;
import it.polimi.ingsw.PS19.client.clientaction.ClientActionChooser;
import it.polimi.ingsw.PS19.client.clientaction.ElectCouncillorInputs;
import it.polimi.ingsw.PS19.client.clientaction.EndTurnInput;
import it.polimi.ingsw.PS19.client.clientaction.GetBusinessPermitInput;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.bonus.Bonus;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.card.PoliticsCard;
import it.polimi.ingsw.PS19.model.map.Balcony;
import it.polimi.ingsw.PS19.model.map.City;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.map.Region;
import it.polimi.ingsw.PS19.model.parameter.RegionType;
import it.polimi.ingsw.PS19.server.Mutex;

/**
 * CLI User Interface
 */
public class ClientCLI extends ClientUI 
{
	private static Mutex mux = new Mutex();
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	@Override
	public ClientActionChooser requestActionType(List<ClientActionChooser> actionList) 
	{
		boolean valid = false;
		ClientActionChooser action = null;
		int i = 0;
		List<String> strings = new ArrayList<>();
		for(ClientActionChooser a : actionList)
			strings.add(a.toString());
		while(!valid)
		{
			writeln("Decidi che tipo di azione fare");
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
		writeln("Definisci il balcolcino da shiftare");
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
		writeln("Definisci il colore del consigliere da aggiungere:");
		List<String> strings = new ArrayList<>();
		for(Color c : validColors)
			strings.add(getString(c));
		return validColors.get(getValues(strings));
	}

	@Override
	public RegionType getRegionAndKing(List<RegionType> regions) throws InvalidInsertionException 
	{ 
		writeln("Definisci il balcolcino da shiftare");
		List<String> strings = new ArrayList<>();
		for(int i = 0; i < regions.size(); i++)
			strings.add(regions.toString());
		strings.add("KING");
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
			writeln("Decidi che azione fare");
			try 
			{
				int index = getValues(strings);
				action = actionList.get(index);
				valid = true;
			} catch (InvalidInsertionException e) 
			{
				valid = false;
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
		writeln("Scegli Carta");
		List<String> strings = new ArrayList<>();
		for(BusinessCard card : cards)
			strings.add(getString(card));
		int index = getValues(strings);
		return cards.get(index);
	}
	
	@Override
	public PoliticsCard getPolitic(List<PoliticsCard> cards) throws InvalidInsertionException 
	{
		writeln("Scegli Carta");
		List<String> strings = new ArrayList<>();
		for(PoliticsCard card : cards)
			strings.add(getString(card));
		int index = getValues(strings);
		return cards.get(index);
	}
	
	@Override
	public City getCity(List<City> cities) throws InvalidInsertionException
	{
		writeln("Scegli Città");
		List<String> strings = new ArrayList<>();
		for(City city : cities)
			strings.add(getString(city));
		int index = getValues(strings);
		return cities.get(index);
	}
	
	private int getValues(List<String> strings) throws InvalidInsertionException
	{
		int i;
		int n;
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
			writeln("Invalid Insertion");
			log.log(Level.SEVERE, e.toString(), e);
			throw new InvalidInsertionException();
		}
		return n;
	}
	
	private void write(String s)
	{
		mux.lock();
		System.out.print(s);
		mux.unlock();
	}
	
	private void writeln(String s)
	{
		mux.lock();
		System.out.println(s);
		mux.unlock();
	}
	
	/**
	 * Read from CLI
	 * @return string read
	 * @throws IOException
	 */
	public String read() throws IOException
	{
		mux.lock();
		String s = in.readLine();
		mux.unlock();
		return s;
	}
	
	private String getString(BusinessCard card)
	{
		String s = "[";
		s = s.concat("Cities: ");
		for(City city: card.getCity())
		{
			s = s.concat(getString(city));
			s = s.concat(", ");
		}
		s += "Bonus: ";
		for(Bonus b : card.getBonus())
		{
			s = s.concat(getString(b));
			s = s.concat(", ");
		}
		s += "]";
		return s;
	}
	
	private String getString(Bonus b)
	{
		return b.toString().substring(b.toString().lastIndexOf('.') + 1, b.toString().lastIndexOf('@'));
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
			s += "No Emporia";
		else
		{
			s += "Emporia of players: ";
			for(Integer playerId : city.getEmporia())
				s = s.concat(playerId.toString() + ", ");
		}
		s += ")";
		return s;
	}
	
	private String modelToString(ClientModel model)
	{
		String s = "----------------------------------- \n MODEL\n \n \n";
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
		return s;
	}
	
	private String getString(Player p)
	{
		String s = "Player: "+ p.getId() + "\n";
		s += "\nNumber of Emporia Left: " + p.getMyEmporia() + "\n";
		s += "Money: " + p.getMoney() + "\n";
		s += "Victory Points: " + p.getVictoryPoints() + "\n";
		s += "Nobility Points: " + p.getNobilityPoints() + "\n";
		s += "Number of helpers: " + p.getHelpers() + "\n";
		s += "PoliticCards: [";
		for(PoliticsCard card : p.getPoliticcard())
		{
			s = s.concat(getString(card) + ", ");
		}	
		s += "]\n";
		s += "Free Business Cards:";
		if(p.getFreebusinesscard().isEmpty())
			s += 0 + "\n";
		else
		{
			s += "\n";
			for(BusinessCard card : p.getFreebusinesscard())
				s = s.concat(getString(card) + "\n");
		}
		s += "\nUsed Business Cards:";
		if(p.getUsedbusinesscard().isEmpty())
			s += 0 + "\n";
		else
		{
			s += "\n";
			for(BusinessCard card : p.getUsedbusinesscard())
				s = s.concat(getString(card) + "\n");
		}
		s += "\nPolitic Cards: ";
		if(p.getPoliticcard().isEmpty())
			s += 0 + "\n";
		else
		{
			s += "[";
			for(PoliticsCard card : p.getPoliticcard())
				s = s.concat(getString(card) + ", ");
			s += "]\n";
		}
		return s;
	}
	
	private String getString(King king)
	{
		String s = "King\n";
		s += "Current City: " + getString(king.getCurrentcity()) + "\n";
		s += getString(king.getBalcony()) + "\n";
		return s;
	}

	private String getString(Color c)
	{
		return "#" + Integer.toHexString(c.getRGB()).substring(2).toUpperCase() + ", ";
	}

	private String getString(PoliticsCard card)
	{
		if(card == null)
			return "no more cards";
		return getString(card.getColor());
	}
	
	private String getString(RegionType region)
	{
		return region.toString();
	}
	
	private String getString(ClientAction input)
	{
		if(input instanceof GetBusinessPermitInput)
			return "Get Business Permit";
		if(input instanceof ElectCouncillorInputs)
			return "Elect Councillor";
		if(input instanceof BuyHelperInputs)
			return "Buy Helper";
		if(input instanceof EndTurnInput)
			return "EndTurn";
		return null;
	}

	private String getString(Balcony b)
	{
		String s = "Balcony: [";
		for(Color c : b.getCouncilcolor())
		{
			s = s.concat(getString(c) + ", ");
		}
		s += "]\n";
		return s;
	}
	
	private String getString(Region region)
	{
		String s = "Region: ";
		s += getString(region.getType()) + "\n";
		s += getString(region.getBalcony());
		s += "Cities:\n";
		for(City c : region.getCities())
		{
			s = s.concat("\t" + getFullString(c) + "\n");
		}
		s += "\n Business Cards:\n";
		s += "\tFirst Card: ";
		s += getString(region.getFirstcard());
		s += "\n\tSecond Card: ";
		s += getString(region.getSecondcard());
		s += "\n";
		return s;
	}

}
