package it.polimi.ingsw.PS19.view.connection;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;

import it.polimi.ingsw.PS19.exceptions.viewexceptions.SocketWritingException;
import it.polimi.ingsw.PS19.exceptions.viewexceptions.WriterException;
import it.polimi.ingsw.PS19.message.Message;

/**
 * Class for RMI connection
 */
public class RMIConnection extends Connection 
{
	RMIReader reader;
	LinkedBlockingQueue<Message> fifo = new LinkedBlockingQueue<>();
	/**
	 * Constructo
	 */
	public RMIConnection() 
	{
		reader = new RMIReader(fifo);
		Thread t = new Thread(reader);
		t.start();
	}
	
	public RMIConnection(RMIReaderIntf stub) 
	{
		loadWriter(stub);
		reader = new RMIReader(fifo);
		Thread t = new Thread(reader);
		t.start();
	}
	
	public RMIReaderIntf getReaderStub()
	{
		return (reader).getRMIReaderStub();
	}
	
	public void loadWriter(RMIReaderIntf stub)
	{
		writer = new RMIWriter(stub);
	}

	@Override
	public Integer write(Message message) throws WriterException 
	{
		if(status == ConnectionStatus.DISCONNECTED)
			return -1;
		writer.setMessage(message);
		Integer result = null;
		try {
			result = writer.call();
		} catch (SocketWritingException e) 
		{
			log.log(Level.SEVERE, e.toString(), e);
			throw new WriterException();
		}
		return result;
	}

	@Override
	public Message read(long timeOut) throws TimeoutException
	{
		Message mex;
			try 
			{
				//if timeout < 0 waits virtually forever
				if(timeOut < 0)
					mex = fifo.poll(10, TimeUnit.DAYS);
				else
					mex = fifo.poll(timeOut, TimeUnit.SECONDS);
			} catch (InterruptedException e) 
			{
				ConnectionLogger.log.log(Level.SEVERE, e.toString(), e);
				throw new TimeoutException();
			}
		return mex;
	}

}
