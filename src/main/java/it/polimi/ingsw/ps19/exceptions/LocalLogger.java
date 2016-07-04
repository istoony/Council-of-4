package it.polimi.ingsw.ps19.exceptions;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Logger class that creates and properly initializes a logger
 */
public class LocalLogger
{
	private final Logger log;
	FileHandler fh;
	
	/**
	 * Constructor
	 * @param logName: name of the logger
	 */
	public LocalLogger(String logName)
	{
		log = Logger.getLogger(logName.toUpperCase());
		String dateTime =  LocalDateTime.now().toString();
		while(dateTime.contains("-"))
		{
			
			String part1 = dateTime.substring(0, dateTime.indexOf("-") - 1);
			String part2 = dateTime.substring(dateTime.indexOf("-") + 1);
			dateTime = part1.concat(part2);
		}
		while(dateTime.contains(":"))
		{
			
			String part1 = dateTime.substring(0, dateTime.indexOf(":") - 1);
			String part2 = dateTime.substring(dateTime.indexOf(":") + 1);
			dateTime = part1.concat(part2);
		}
		dateTime = dateTime.substring(0, dateTime.indexOf(".") - 1);
		String path = "C:\\Logs\\CouncilOfFour\\" + dateTime;
		String filePath = path + "\\" + logName + ".log";
		try {
			new File(path).mkdirs();
			File logFile = new File(filePath);
			logFile.createNewFile();
			fh = new FileHandler(filePath);
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
	public synchronized void log(Exception e)
	{
		log.log(Level.SEVERE, e.toString(), e);
	}
}
