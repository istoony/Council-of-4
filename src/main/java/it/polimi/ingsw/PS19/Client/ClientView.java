/*
 * @author Andrea Milanta 
 */
package it.polimi.ingsw.PS19.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import it.polimi.ingsw.PS19.exceptions.viewexceptions.WriterException;
import it.polimi.ingsw.PS19.message.requests.Request;
import it.polimi.ingsw.PS19.view.connection.Connection;

/*
 * 
 */
public class ClientView extends Observable implements Observer, Runnable
{
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	private boolean stop = false;
	Connection connection = null;
		
	public ClientView(Connection conn)
	{
		connection = conn;
	}

	@Override
	public void run() 
	{
		while(!stop)
		{
			Future<Request> waitMex = connection.read();
			try 
			{
				//Message recMex = waitMex.get(ClientConstants.MAX_SERVER_TIMEOUT_s, TimeUnit.SECONDS);
				Request recMex = waitMex.get();
				setChanged();
				notifyObservers(recMex);
			} 
			//General Error. Exit
			catch (InterruptedException | ExecutionException e) 
			{
				e.printStackTrace();
				System.exit(0);
			} 
			/*
			catch (TimeoutException e) {
				System.out.println("Server Timeout Error!");
				e.printStackTrace();
				System.exit(0);
			}//*/
		}
		notifyObservers(null);
		/*
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
			notifyObservers(null);
			stop = true;
		}
		//*/
	}

	@Override
	public void update(Observable o, Object arg)
	{
		//Checks whether the object passed is a message or not
		if(!(arg instanceof Request))
			return;
		Request mex = (Request) arg;

		//The message is forwarded to the clients
		forwardMessage(mex);
	}
	
	/*Forwards message on connection
	 * 
	 */
	public void forwardMessage(Request mex)
	{
		try {
			connection.write(mex);
		} catch (WriterException e) 
		{
			e.printStackTrace();
		}
	}

}
