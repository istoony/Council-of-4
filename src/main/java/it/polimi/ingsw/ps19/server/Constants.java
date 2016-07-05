/*
 *  * @author Andrea Milanta
 */
package it.polimi.ingsw.ps19.server;

/**
 * This class collects all the constants (final values) of the programs which are used by more than one class
 */
public final class Constants
{
	public static final int SOCKET_PORT = 49153;
	public static final int RMI_PORT = 49154;
	public static final String RMI_SERVER_STUB_NAME = "RMI Server";
	private static Integer maxPlayers = null;
	public static final int MAX_WRITING_TRIES = 5;
	public static final int MAX_WAIT_TIME_S = 10;
	private static long playerTimeout;
		
	private Constants() {}
	
	public static void setMaxPlayers(final int mp)
	{
		if(maxPlayers == null)
			maxPlayers = mp;
	}
	
	public static void setPlayerTimeout(final long pt)
	{
		if(playerTimeout == 0)
			playerTimeout = pt;
	}
	
	public static Integer getMaxPlayers() {
		return maxPlayers;
	}
	
	public static long getPlayerTimeout() {
		return playerTimeout;
	}
}
