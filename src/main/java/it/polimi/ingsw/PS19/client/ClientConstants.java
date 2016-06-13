/**
 * 
 */
package it.polimi.ingsw.PS19.client;

/**
 * Class that contains constants related to the clients
 */
public final class ClientConstants 
{
	
	public static final String IP_ADDRESS = "127.0.0.1";
	public static final Integer REMOTE_PORT = 4444;
	public static final Integer MAX_INPUT_ERRORS = 3;
	public static final Integer MAX_CONN_TRIES = 2;
	public static final Integer MAX_WRITING_TRIES = 5;
	public static final Integer MAX_SERVER_TIMEOUT_S = 120;
	
	private ClientConstants() { }
}