/*
 * @author Andrea Milanta 
 */
package it.polimi.ingsw.PS19.Client;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import it.polimi.ingsw.PS19.controller.action.Action;
import it.polimi.ingsw.PS19.controller.action.MainElectRegionCouncillor;
import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.message.SendActionMessage;
import it.polimi.ingsw.PS19.message.requests.PlayerDisconnectedMessage;
import it.polimi.ingsw.PS19.model.parameter.RegionType;
import it.polimi.ingsw.PS19.view.connection.Connection;

/*
 * 
 */
public class ClientView extends Observable implements Observer, Runnable
{
	private boolean stop = false;
	Connection connection = null;
	ClientInterpreter interpreter = new ClientInterpreter();
	
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	public ClientView(Connection conn)
	{
		connection = conn;
	}

	@Override
	public void run() 
	{
		//notifyObservers(new GetInputMessage());
		//notifyObservers(new SendFullGameMessage(-1));
		/*
		while(!stop)
		{
			Future<Message> waitMex = connection.read();
			try 
			{
				Message recMex = waitMex.get();
				notifyObservers(recMex);
			} 
			//General Error. Exit
			catch (InterruptedException | ExecutionException e) 
			{
				e.printStackTrace();
				System.exit(0);
			} 
		}//*/
		System.out.println("Inserisci numero player");
		String iD;
		try {
			iD = in.readLine();
		} catch (IOException e1) {
			iD = "0";
			e1.printStackTrace();
		}
		int id = Integer.parseInt(iD);
		
		while(!stop)
		{
			try
			{
				System.out.println("Inserisci codice azione(0)");
				String s = in.readLine();
				int ac = Integer.parseInt(s);
				RegionType r = null;
				System.out.println("Inserisci Regione (p, h, m)");
				String loc = in.readLine();
				if (loc.equals("p")) 
					r = RegionType.PLAIN;
				else if (loc.equals("h")) 
					r = RegionType.HILL;
				else if (loc.equals("m")) 
					r = RegionType.MOUNTAIN;
				//else throw new Exception();
				System.out.println("Inserisci colore (#FF0000, #0000FF, #FF7F00, #000000, #FFFFFF, #FFC0CB)");
				String col = in.readLine();
				Color c = Color.decode(col);
				
				Action a = new MainElectRegionCouncillor(c, id, r);
				Message m = new SendActionMessage(a, id);
				Future<Integer> f = connection.write(m);
				f.get();
				System.out.println("Mex mandato!");
			} 
			catch(Exception e)
			{
				System.out.println("NON VALIDO!!");
			}
			
		}
	}

	@Override
	public void update(Observable o, Object arg)
	{
		//Checks whether the object passed is a message or not
		if(!(arg instanceof Message))
			return;
		Message mex = (Message) arg;

		//The message is forwarded to the clients
		forwardMessage(mex);
	}
	
	/*Forwards message on connection
	 * 
	 */
	public void forwardMessage(Message mex)
	{
		Future<Integer> writeFeedback = connection.write(mex);
		
		//Verify writing success
		try {
			writeFeedback.get();
		} catch (InterruptedException | ExecutionException e) 
		{
			e.printStackTrace();
			notifyObservers(new PlayerDisconnectedMessage(-1));
		}
	}
}
