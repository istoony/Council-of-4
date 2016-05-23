/*
 *@Author Andrea Milanta 
 */
package it.polimi.ingsw.PS19.view.connection;
import java.util.concurrent.Callable;
import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.server.Constants;
import it.polimi.ingsw.PS19.view.exceptions.SocketWritingException;

/*
 * Abstract class to write a message. 
 * Runs on a different Thread.
 */
public abstract class Writer implements Callable<Integer>
{
	protected Message message;
	
	/*
	 * Implementation of call to write
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public Integer call() throws SocketWritingException
	{
		int numOfTries = 0;
		boolean success = false;
		do
		{
			try
			{
				write();
				success = true;
			}
			catch(Exception e)
			{
				success = false;
			}
			finally
			{
				numOfTries++;
			}
		}while(!success && numOfTries < Constants.MAX_WRITING_TRIES);
		if(!success) 
			throw new SocketWritingException();
		return numOfTries;
	}
	
	/*
	 * Actual writing method which will be different for different type of communication
	 */
	protected abstract void write() throws Exception;
	
	/*
	 * Sets message to be written
	 */
	public void setMessage(Message mex)
	{
		message = mex;
	}

}
