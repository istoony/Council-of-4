package it.polimi.ingsw.ps19.server;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * reads server input and waits for stop;
 */
public class StopperThread extends Thread 
{
	protected static final Logger log = Logger.getLogger("SERVER_LOGGER");
	
	@Override
	public void run()
	{
		boolean stop = false;
		while(!stop)
		{
			try 
			{
				String s = ServerManager.serverCLI.read();
				if("quit".equals(s) || "stop".equals(s))
				{
					ServerManager.stop();
					stop = true;
				}
			} 
			catch (IOException e) 
			{
				log.log(Level.SEVERE, e.toString(), e);
			}
		}
	}
}
