/*
 *  * @author Andrea Milanta
 */
package it.polimi.ingsw.ps19.server;

/**
 * This class collects all the constants (final values) of the programs which are used by more than one class
 */
public final class Constants
{
	public static final int SOCKET_PORT = 49126;
	public static final int RMI_PORT = 49127;
	public static final String RMI_SERVER_STUB_NAME = "RMI Server";
	public static final int MAX_PLAYERS = 5;
	public static final int MAX_WRITING_TRIES = 5;
	public static final int MAX_WAIT_TIME_S = 10;
	public static final long PLAYER_TIMEOUT_TIME_S = 1200;
		
	private Constants() {}
}
