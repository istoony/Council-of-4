package it.polimi.ingsw.ps19.exceptions;

/**
 * Exception for when an illegal map is detected
 */
public class IllegalMapException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param message: message to display
	 */
	public IllegalMapException(String message){
		super(message);
	}
	
	
}
