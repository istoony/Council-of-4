/*
 * @Author Andrea Milanta
 */
package it.polimi.ingsw.ps19.server;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.polimi.ingsw.ps19.exceptions.viewexceptions.ReaderException;
import it.polimi.ingsw.ps19.exceptions.viewexceptions.WriterException;
import it.polimi.ingsw.ps19.message.replies.ConnectionReply;
import it.polimi.ingsw.ps19.message.replies.StringMessage;
import it.polimi.ingsw.ps19.message.requests.ConnectionMessage;
import it.polimi.ingsw.ps19.model.parameter.Costants;
import it.polimi.ingsw.ps19.view.connection.Connection;
import it.polimi.ingsw.ps19.view.connection.ConnectionStatus;
import it.polimi.ingsw.ps19.view.connection.RMIConnection;
import it.polimi.ingsw.ps19.view.connection.SocketConnection;

/**
 * This static class (class with only static method) collects the users requiring to play and starts a new game when there are the conditions to do so;
 */
public class WaitingRoom 
{
	protected static final Logger log = Logger.getLogger("SERVER_LOGGER");
	
	private static List<Integer> room = new ArrayList<>();								//List of users(connections) waiting to play
	private static Map<Integer,Connection> connectedUsers = new HashMap<>();
	private static long secondPlayerTime = -1;																//Time of connection of second player in queue
	private static Thread t = null;																			//Timer Thread. We keep it's reference for closing it properly
	private static ExecutorService executorService;																			//Thread pool used by connections for writing
	private static Mutex mux = new Mutex();																	//Mutex
	
	private static int counter = 10;
	
	private WaitingRoom(){}
	
	/**
	 * starts timer
	 */
	public static void startTimer()
	{
		executorService = Executors.newFixedThreadPool(Constants.MAX_PLAYERS);
		if(t != null) 
			return;
		t = new TimerThread();
		t.start();
	}
	
	/**
	 * add a new RMI connection
	 * @param conn
	 * @param newGame
	 * @param key
	 */
	public static void addConnection(RMIConnection conn, boolean newGame, int key)
	{
		conn.setExecutor(executorService);
		try 
		{
			if(!newGame && substituteConnection(conn, key))
				conn.write(new ConnectionReply(true, key));
			else
			{
				int k = addConnectionToWR(conn);
				conn.write(new ConnectionReply(false, k));
			}
		} 
		catch (WriterException e1) 
		{
			log.log(Level.SEVERE, e1.toString(), e1);
		}

	}
	
	/**
	 * Add a new Socket connectio 
	 * @param conn
	 */
	public static void addConnection(SocketConnection conn)
	{
		conn.setExecutor(executorService);
		try 
		{
			ConnectionMessage mex = (ConnectionMessage) conn.read(30);
			if(!mex.getNewGame() && substituteConnection(conn, mex.getKey()))
				conn.write(new ConnectionReply(true, mex.getKey()));
			else
			{
				int key = addConnectionToWR(conn);
				conn.write(new ConnectionReply(false, key));
			}
		} 
		catch (TimeoutException | WriterException | InterruptedException | ReaderException e1) 
		{
			log.log(Level.SEVERE, e1.toString(), e1);
		}
	}
	
	/**
	 * Method to add a connection aka player to the waiting room
	 * @param conn: new connection
	 */
	private static int addConnectionToWR(Connection conn)
	{
		mux.lock();
		connectedUsers.put(counter, conn);
		room.add(counter);
		counter++;
		ServerManager.serverCLI.showNotification(room.size() + " players in Waiting room");
		mux.unlock();
		if(room.size() == 2) 
		{
			secondPlayerTime = System.currentTimeMillis();
		}
		else roomIsFull();
		return counter - 1;
	}
	
	/**
	 * substitute connection into hashmap
	 * @param conn
	 * @param key
	 * @return
	 */
	private static boolean substituteConnection(Connection conn, int key)
	{
		if(connectedUsers.get(key) != null && connectedUsers.get(key).getStatus() == ConnectionStatus.DISCONNECTED)
		{
			connectedUsers.replace(key, conn);
			return true;
		}
		return false;
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
	 * Before doing anything check that there are at least two players in the room
	 * This is to avoid creating a single player game, which could happen in one peculiar situation due to
	 * the timer thread
	 * If there are at least two players a new game is started. 
	 * The secondPlayertime is reset and a new Threadpool is created
	 * The creation is locked to avoid interference if a new player is added in the meantime
	 * N.B. Interference is only possible if the game is started by the timeout
	 */
	public static synchronized void startGame()
	{
		if(room.size() < 2) 
			return; 						//Check to avoid concurrency in one particular case		
		mux.lock();
		ServerManager.serverCLI.showNotification(LocalDateTime.now() + " New game has started with " + room.size() + " players!");  //TEST
		List<Integer> fullRoom = Costants.clone(room);
		new GameFactory(fullRoom);
		room.clear();
		mux.unlock();
		secondPlayerTime = -1;								//Resets secondPlayerTime
		executorService = Executors.newFixedThreadPool(10);	//Creates new ThreadPool
		ServerManager.serverCLI.showNotification(room.size() + " players in waiting room");
	}
	
	/**Gets connection from hashmap
	 * @param key
	 * @return
	 */
	public static Connection getConnection(Integer key)
	{
		return connectedUsers.get(key);
	}
 	
	/**
	 * Returns whether the connection with the passed id is connected
	 * @param id
	 * @return
	 */
	public static boolean isConnected(Integer id)
	{
		if(connectedUsers.get(id) != null && connectedUsers.get(id).getStatus() != ConnectionStatus.DISCONNECTED)
			return true;
		return false;
	}
	
	/**
	 * Method to safely quit the thread
	 * Includes a notification to all waiting users
	 */
 	public static void quit()
 	{
 		t.interrupt();
 		for(Entry<Integer,Connection> entry : connectedUsers.entrySet())
 		{
			try {
				entry.getValue().write(new StringMessage("Server Quits"));
				remove(entry.getKey());
			} catch (WriterException e) 
 			{
				log.log(Level.SEVERE, e.toString(), e);
 			}
 		}
 	}
 	
 	/**
 	 * connectedUsers setter for test purposes
 	 * @param newMap
 	 */
 	public static void setConnection(Map<Integer,Connection> newMap)
 	{
 		connectedUsers = newMap;
 	}
 	
 	/**
 	 * removes the list of connection with id passed
 	 * @param ids
 	 */
 	public static void removeMany(List<Integer> ids)
 	{
 		for(Integer id : ids)
 			remove(id);
 	}
 	
 	/**
 	 * removes connection from hashmap
 	 * @param id
 	 */
 	public static void remove(int id)
 	{
 		connectedUsers.get(id).close();
 		connectedUsers.remove(id);
 	}
}
