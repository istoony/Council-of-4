/*
 * @author Andrea Milanta 
 */
package it.polimi.ingsw.PS19.client;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import it.polimi.ingsw.PS19.client.clientaction.ClientAction;
import it.polimi.ingsw.PS19.client.clientaction.ClientActionChooser;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.model.map.Region;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

/*
 * 
 */
public class ClientCLI implements ClientUI 
{
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public ClientActionChooser requestActionType(List<ClientActionChooser> actionList) 
	{
		boolean valid = false;
		ClientActionChooser action = null;
		int i = 0;
		while(!valid)
		{
			System.out.println("Decidi che tipo di azione fare:");
			for(int j = 0; j < actionList.size(); j++)
			{
				System.out.println(j + " - " + actionList.get(j).toString());
			}
			try {
				String s = in.readLine();
				int actionId = Integer.parseInt(s);
				action = actionList.get(actionId);
				valid = true;
			} catch (IOException | NumberFormatException | IndexOutOfBoundsException e) 
			{
				valid = false;
				e.printStackTrace();
				System.out.println("Inserimento non valido");
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
	
	public RegionType getRegion() throws InvalidInsertionException
	{
		System.out.print("Definisci il balcolcino da shiftare");
		for(RegionType reg : RegionType.values())
		{
			System.out.print(reg.toString().substring(0, 1).toLowerCase() + "=" + reg.toString() + ", ");
		}
		System.out.println("):");
		try
		{
			String s = in.readLine();
			for(RegionType reg : RegionType.values())
			{
				if(reg.toString().substring(0, 1).toLowerCase().equals(s))
					return reg;
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("Invalid Region");
		throw new InvalidInsertionException();
	}
	
	/*
	 * Gets a valid color from user wrt the parameter array
	 */
	public Color getAndValidateColor(List<Color> validColors) throws InvalidInsertionException
	{
		
		System.out.print("Definisci il colore del consigliere da aggiungere (");
		for(Color c : validColors)
		{
			System.out.print("#" + Integer.toHexString(c.getRGB()).substring(2).toUpperCase() + ", ");
		}
		System.out.println("):");
		Color color;
		try
		{
			String s = in.readLine();
			color = Color.decode(s);
			for(Color c : validColors)
			{
				if(color.equals(c))
					return color;
			}
		} catch(IOException | NumberFormatException e)
		{
			e.printStackTrace();
		}
		System.out.println("Invalid Color");
		throw new InvalidInsertionException();
	}

	/*
	 * Gets region or king from user
	 * if is king returns null;
	 */
	public RegionType getRegionAndKing() throws InvalidInsertionException 
	{
		System.out.print("Definisci il balcolcino da shiftare (");
		for(RegionType reg : RegionType.values())
		{
			System.out.print(reg.toString().substring(0, 1).toLowerCase() + "=" + reg.toString() + ", ");
		}
		System.out.println("k=KING):");
		String s;
		try 
		{
			s = in.readLine();
			for(RegionType reg : RegionType.values())
			{
				if(reg.toString().toLowerCase().substring(0, 1).equals(s))
					return reg;
			}
			if(s.equals("k"))
				return null;
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		System.out.println("Invalid Region");
		throw new InvalidInsertionException();
	}

	public void showNotification(String s) 
	{
		System.out.println(s);
	}

	public ClientAction getAction(List<ClientAction> actionList) 
	{
		boolean valid = false;
		ClientAction action = null;
		int i = 0;
		while(!valid)
		{
			System.out.println("Decidi che azione fare:");
			for(int j = 0; j < actionList.size(); j++)
			{
				System.out.println(j + " - " + actionList.get(i).toString());
			}
			System.out.println("Q to start again");
			try {
				String s = in.readLine();
				if(s.equals("Q") || s.equals("q"))
					return null;
				int actionId = Integer.parseInt(s);
				action = actionList.get(actionId);
				valid = true;
			} catch (IOException | NumberFormatException | IndexOutOfBoundsException e) 
			{
				valid = false;
				e.printStackTrace();
				System.out.println("Inserimento non valido");
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
		System.out.println(model.toString());
	}
}
