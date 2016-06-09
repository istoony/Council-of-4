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
import it.polimi.ingsw.PS19.client.clientaction.ClientAction;
import it.polimi.ingsw.PS19.client.clientaction.ClientActionChooser;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.model.bonus.Bonus;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.card.PoliticsCard;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

/*
 * 
 */
public class ClientCLI extends ClientUI 
{
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
				e.printStackTrace();
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
		writeln("Definisci il colore del consigliere da aggiungere");
		List<String> strings = new ArrayList<>();
		for(Color c : validColors)
			strings.add(getString(c));
		System.out.println("):");
		return validColors.get(getValues(strings));
	}

	/*
	 * Gets region or king from user
	 * if is king returns null;
	 */
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
		System.out.println(s);
	}

	@Override
	public ClientAction getAction(List<ClientAction> actionList) 
	{
		boolean valid = false;
		ClientAction action = null;
		int i = 0;
		List<String> strings = new ArrayList<>();
		for(ClientAction a : actionList)
			strings.add(a.toString());
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
		return(cards.get(index));
	}
	
	@Override
	public PoliticsCard getPolitic(List<PoliticsCard> cards) throws InvalidInsertionException 
	{
		writeln("Scegli Carta");
		List<String> strings = new ArrayList<>();
		for(PoliticsCard card : cards)
			strings.add(getString(card));
		int index = getValues(strings);
		return(cards.get(index));
	}
	
	private int getValues(List<String> strings) throws InvalidInsertionException
	{
		int i, n;
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
	
	private String read() throws IOException
	{
		return in.readLine();
	}
	
	private String getString(BusinessCard card)
	{
		String s = null;
		for(Bonus b : card.getBonus())
		{
			s += b.toString();
			s += ", ";
		}
		return s;
	}

	private String modelToString(ClientModel model)
	{
		return model.toString();
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
	
}
