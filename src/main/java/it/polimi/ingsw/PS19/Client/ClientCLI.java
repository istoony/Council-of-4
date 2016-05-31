/*
 * @author Andrea Milanta 
 */
package it.polimi.ingsw.PS19.Client;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;

import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.NoSuchActionException;
import it.polimi.ingsw.PS19.message.BuyHelperMessage;
import it.polimi.ingsw.PS19.message.ElectCouncillorMessage;
import it.polimi.ingsw.PS19.message.EndTurnMessage;
import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

/*
 * 
 */
public class ClientCLI extends ClientUI 
{
	//TO BE PUT IN MODEL
	private ArrayList<Color> councillorsColors = new ArrayList<Color>();
	
	//Necessary
	HashMap<Integer,Supplier<Message>> messageCreator = new HashMap<Integer,Supplier<Message>>();
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public ClientCLI()
	{
		councillorsColors.add(new Color(0xFF0000));
		councillorsColors.add(new Color(0x0000FF));
		councillorsColors.add(new Color(0xFF7F00));
		councillorsColors.add(new Color(0x000000));
		councillorsColors.add(new Color(0xFFFFFF));
		councillorsColors.add(new Color(0xFFC0CB));
		
		try {
			Supplier<Message> elCounc = () -> this.electCouncillor();
			Supplier<Message> endTurn = () -> this.endTurn();
			Supplier<Message> buyHelper = () -> this.buyHelper();
			messageCreator.put(0, endTurn);
			messageCreator.put(1, elCounc);
			messageCreator.put(2, buyHelper);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void requestAction() 
	{
		boolean valid = false;
		int actionId;
		Message mex = null;

		while(!valid)
		{
			System.out.print("Decidi che azione fare (");
			for(int i = 0; i < messageCreator.size(); i++)
			{
				String actionName = messageCreator.get(i).toString();
				System.out.print(i + "-" + actionName + ", ");
			}
			System.out.println("):");
			try {
				String s = in.readLine();
				actionId = Integer.parseInt(s);
				mex = defineAction(actionId);
				mex.setId(playerId);
				setChanged();
				notifyObservers(mex);
				valid = true;
			} catch (IOException | NumberFormatException | NoSuchActionException e) 
			{
				valid = false;
				e.printStackTrace();
				System.out.println("Inserimento non valido");
			}
		}
	}
	
	private Message defineAction(int id) throws NoSuchActionException
	{
		Message mex = null;
		try
		{
			Supplier<Message> f = messageCreator.get(id);
			if(f == null) throw new IndexOutOfBoundsException();
			mex = f.get();
			if(mex == null) throw new NoSuchActionException();
		} catch(Exception e)
		{
			e.printStackTrace();
			throw new NoSuchActionException();
		}
		return mex;
	}
	
	private ElectCouncillorMessage electCouncillor()
	{
		ElectCouncillorMessage mex = null;
		RegionType loc = null;
		Color col;
		System.out.println();
		System.out.println("SHIFTA BALCONCINO");
		try 
		{
			loc = getRegionAndKing();
			System.out.println("Got the region");
			col = getAndValidateColor(councillorsColors);
			System.out.println("Got the color");
			if(loc == null) 
				mex = new ElectCouncillorMessage(col);
			else 
				mex = new ElectCouncillorMessage(col, loc);
			mex.setMainAction(true);
		} catch (IOException | InvalidInsertionException e) 
		{
			e.printStackTrace();
			return null;
		}
		
		return mex;
	}
	
	private BuyHelperMessage buyHelper()
	{
		return new BuyHelperMessage();
	}
	
	private EndTurnMessage endTurn()
	{
		EndTurnMessage mex = new EndTurnMessage();
		mex.setId(playerId);
		System.out.println("Your turn has ended!");
		return mex;
	}
	
	private RegionType getRegion() throws InvalidInsertionException, IOException
	{
		System.out.print("Definisci il balcolcino da shiftare");
		for(RegionType reg : RegionType.values())
		{
			System.out.print(reg.toString().substring(0, 1).toLowerCase() + "=" + reg.toString() + ", ");
		}
		System.out.println("):");
		String s = in.readLine();
		for(RegionType reg : RegionType.values())
		{
			if(reg.toString().substring(0, 1).toLowerCase().equals(s))
				return reg;
		}
		System.out.println("Invalid Region");
		throw new InvalidInsertionException();
	}
	
	/*
	 * Gets a valid color from user wrt the parameter array
	 */
	private Color getAndValidateColor(ArrayList<Color> validColors) throws InvalidInsertionException, IOException
	{
		
		System.out.print("Definisci il colore del consigliere da aggiungere (");
		for(Color c : validColors)
		{
			System.out.print("#" + Integer.toHexString(c.getRGB()).substring(2).toUpperCase() + ", ");
		}
		System.out.println("):");
		String s = in.readLine();
		Color color;
		try
		{
			color = Color.decode(s);
			for(Color c : validColors)
			{
				if(color.equals(c))
					return color;
			}
			throw new InvalidInsertionException();
		} catch(Exception e)
		{
			System.out.println("Invalid Color");
			throw new InvalidInsertionException();
		}
	}

	/*
	 * Gets region or king from user
	 * if is king returns null;
	 */
	private RegionType getRegionAndKing() throws InvalidInsertionException, IOException
	{
		System.out.print("Definisci il balcolcino da shiftare (");
		for(RegionType reg : RegionType.values())
		{
			System.out.print(reg.toString().substring(0, 1).toLowerCase() + "=" + reg.toString() + ", ");
		}
		System.out.println("k=KING):");
		String s = in.readLine();
		for(RegionType reg : RegionType.values())
		{
			if(reg.toString().toLowerCase().substring(0, 1).equals(s))
				return reg;
		}
		if(s.equals("k"))
			return null;
		System.out.println("Invalid Region");
		throw new InvalidInsertionException();
	}

	
	@Override
	protected void activatePlayer() 
	{
		System.out.println("It is your turn!");
		requestAction();
	}
}
