/*
 * @Author Andrea Milanta
 */
package it.polimi.ingsw.PS19.server;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.PS19.exceptions.viewexceptions.WriterException;
import it.polimi.ingsw.PS19.message.replies.StringMessage;
import it.polimi.ingsw.PS19.view.connection.Connection;
import it.polimi.ingsw.PS19.view.connection.SocketConnection;

/**
 * This static class (class with only static method) collects the users requiring to play and starts a new game when there are the conditions to do so;
 */
public class WaitingRoom 
{
	protected static final Logger log = Logger.getLogger("SERVER_LOGGER");
	
	private static ArrayList<Connection> room = new ArrayList<>();								//List of users(connections) waiting to play
	private static long secondPlayerTime = -1;																//Time of connection of second player in queue
	private static Thread t = null;																			//Timer Thread. We keep it's reference for closing it properly
	private static ExecutorService executorService = Executors.newFixedThreadPool(Constants.MAX_PLAYERS);	//Thread pool used by connections for writing
	private static Mutex mux = new Mutex();																	//Mutex
	
	private WaitingRoom(){}
	
	/**
	 * starts timer
	 */
	public static void startTimer()
	{
		if(t != null) 
			return;
		t = new TimerThread();
		t.start();
	}
	
	/**
	 * Method to add a connection aka player to the waiting room
	 * @param conn: new connection
	 */
	public static void addConnection(Connection conn)
	{
		conn.setExecutor(executorService);
		try
		{
			conn.write(new StringMessage("Connected!"));
			conn.write(new StringMessage("waiting for players"));
		} catch(WriterException e)
		{
			log.log(Level.SEVERE, e.toString(), e);
		}

		mux.lock();
		room.add(conn);
		ServerManager.serverCLI.showNotification(room.size() + " players in Waiting room");
		mux.unlock();
		if(room.size() == 2) 
		{
			secondPlayerTime = System.currentTimeMillis();
		}
		else roomIsFull();
	}
	
	/**
	 * Method to check if the room is full (Enough players to start a game)
	 * if so calls start game
	 */
	private static boolean roomIsFull()
	{
		if(room.size() >= Constants.MAX_PLAYERS) 
		{
			startGame();
			return true;
		}
		return false;
	}
	
	/**
	 * Method to get the time of connection of the second player
	 * If no player is present default value of secondPlayerTime is -1
	 */
	public static long getStartTime()
	{
		return secondPlayerTime;
	}
	
	/**
	 * Method to starts a new game
	 * Before doing anything check that there are at least to players in the room
	 * This is to avoid creating a single player game, which could happen in one peculiar situation due to
	 * the timer thread
	 * If there are at least two players a new game is started. 
	 * The secondPlayertime is reset and a new Threadpool is created
	 * The creation is locked to avoid interference if a new player is added in the meantime
	 * N.B. Interference is only possible if the game is started by the timeout
	 */
	public static synchronized void startGame()
	{
		if(room.size() <2) 
			return; 						//Check to avoid concurrency in one particular case		
		mux.lock();
		ServerManager.serverCLI.showNotification(LocalDateTime.now() + " New game has started with " + room.size() + " players!");  //TEST
		@SuppressWarnings("unchecked")
		ArrayList<Connection> fullRoom = (ArrayList<Connection>) room.clone();
		new GameFactory(fullRoom);
		room.clear();
		mux.unlock();
		secondPlayerTime = -1;								//Resets secondPlayerTime
		executorService = Executors.newFixedThreadPool(10);	//Creates new ThreadPool
		ServerManager.serverCLI.showNotification(room.size() + " players in waiting room");
	}
 	
	/**
	 * Method to safely quit the thread
	 * Includes a notification to all waiting users
	 */
 	public static void quit()
 	{
 		t.interrupt();
 		for(Connection c: room)
 		{
			try {
				c.write(new StringMessage("ServerQuits"));
			} catch (WriterException e) 
 			{
				log.log(Level.SEVERE, e.toString(), e);
 			}
 		}
 	}
}
