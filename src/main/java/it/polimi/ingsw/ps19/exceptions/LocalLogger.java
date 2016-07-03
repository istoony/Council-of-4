package it.polimi.ingsw.ps19.exceptions;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocalLogger
{
	private final Logger log;
	FileHandler fh;
	
	public LocalLogger(String logName)
	{
		log = Logger.getLogger(logName.toUpperCase());
		String path = "C:\\Users\\Andrea\\Desktop" + logName + ".log";
		try {
			File logFile = new File(path);
			logFile.createNewFile();
			fh = new FileHandler(path);
			log.addHandler(fh);
			log.setUseParentHandlers(false);
		} catch (SecurityException | IOException e) 
		{
			log.log(Level.SEVERE, e.toString());
		}
	}
	
	/**
	 * Logs the exception;
	 * @param e
	 */
	public void log(Exception e)
	{
		log.log(Level.SEVERE, e.toString(), e);
	}
}
