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

import it.polimi.ingsw.PS19.Client.clientAction.ClientAction;
import it.polimi.ingsw.PS19.Client.clientAction.ClientActionChooser;
import it.polimi.ingsw.PS19.Client.clientAction.FastAction;
import it.polimi.ingsw.PS19.Client.clientAction.MainAction;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.NoSuchActionException;
import it.polimi.ingsw.PS19.message.requests.BuyHelperMessage;
import it.polimi.ingsw.PS19.message.requests.ElectCouncillorMessage;
import it.polimi.ingsw.PS19.message.requests.EndTurnMessage;
import it.polimi.ingsw.PS19.message.requests.Request;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

/*
 * 
 */
public class ClientCLI implements ClientUI 
{
	//TO BE PUT IN MODEL
	private ArrayList<Color> councillorsColors = new ArrayList<Color>();
	
	//Necessary
	HashMap<Integer,Supplier<Request>> messageCreator = new HashMap<Integer,Supplier<Request>>();
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	public ClientCLI()
	{
		/*
		councillorsColors.add(new Color(0xFF0000));
		councillorsColors.add(new Color(0x0000FF));
		councillorsColors.add(new Color(0xFF7F00));
		councillorsColors.add(new Color(0x000000));
		councillorsColors.add(new Color(0xFFFFFF));
		councillorsColors.add(new Color(0xFFC0CB));
		
		try {
			Supplier<Request> elCounc = () -> this.electCouncillor();
			Supplier<Request> endTurn = () -> this.endTurn();
			Supplier<Request> buyHelper = () -> this.buyHelper();
			messageCreator.put(0, endTurn);
			messageCreator.put(1, elCounc);
			messageCreator.put(2, buyHelper);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//*/
	}
	
	protected ClientActionChooser requestActionType(ArrayList<ClientActionChooser> actionList) 
	{
		boolean valid = false;
		ClientActionChooser action = null;
		int i = 0;
		while(!valid)
		{
			System.out.println("Decidi che tipo di azione fare:");
			for(int j = 0; j < actionList.size(); j++)
			{
				System.out.println(j + " - " + actionList.get(i).toString());
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
	
	protected ClientAction requestAction(ArrayList<ClientAction> actionList)
	{
		return null;
		
	}
	
	private Request defineAction(int id) throws NoSuchActionException
	{
		Request mex = null;
		try
		{
			Supplier<Request> f = messageCreator.get(id);
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
	
	public RegionType getRegion() throws InvalidInsertionException, IOException
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
	public Color getAndValidateColor(ArrayList<Color> validColors) throws InvalidInsertionException, IOException
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
	public RegionType getRegionAndKing() throws InvalidInsertionException, IOException
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
	public void showNotification(String s) 
	{
		System.out.println(s);
	}

	@Override
	public ClientAction requestActionType() {
		// TODO Auto-generated method stub
		return null;
	}
}
