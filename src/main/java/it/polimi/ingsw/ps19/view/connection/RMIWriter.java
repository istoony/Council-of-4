package it.polimi.ingsw.ps19.view.connection;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Class that implements a writer in a RMI interface
 */
public class RMIWriter extends Writer 
{
	RMIReaderIntf readerStub;
	
	/**
	 * Constructor
	 * @param stub: stub to remote reader
	 */
	public RMIWriter(RMIReaderIntf stub) 
	{
		readerStub = stub;
	}

	@Override
	protected void write() throws IOException, RemoteException
	{
		readerStub.setNewMessage(message);
	}

}
