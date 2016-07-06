package it.polimi.ingsw.ps19.model.parameter;

import it.polimi.ingsw.ps19.exceptions.LocalLogger;

/**
 * Logger related to file reading
 */
public class FileLogger 
{
	public static final LocalLogger log = new LocalLogger("file reader");
	private FileLogger()
	{}
}
