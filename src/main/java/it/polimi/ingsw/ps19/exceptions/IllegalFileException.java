package it.polimi.ingsw.ps19.exceptions;


/**
 * Exception to notify of an invalid file
 */
public class IllegalFileException extends RuntimeException
{
	private static final long serialVersionUID = 1835921492296401892L;

	/**
	 * Constructor
	 * @param message
	 */
	public IllegalFileException(String message){
		super(message);
	}
}
