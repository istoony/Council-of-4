/*
 * @Author Andrea Milanta
 */
package it.polimi.ingsw.PS19.server;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import it.polimi.ingsw.PS19.exceptions.viewexceptions.WriterException;
import it.polimi.ingsw.PS19.message.requests.StringMessage;
import it.polimi.ingsw.PS19.view.connection.Connection;
import it.polimi.ingsw.PS19.view.connection.SocketConnection;

/*
 * This static class (class with only static method) collects the users requiring to play and starts
 * a new game when there are the conditions to do so;
 */
public class WaitingRoom 
{
	private static ArrayList<Connection> room = new ArrayList<Connection>();			//List of users(connections) waiting to play
	private static long secondPlayerTime = -1;		//Time of connection of second player in queue;
	private static Thread t = null;						//Timer Thread. We keep it's reference for closing it properly;
	private static ExecutorService executorService = Executors.newFixedThreadPool(Constants.MAX_PLAYERS);	//Thread pool used by connections for writing;

	private static Mutex mux = new Mutex();				//Mutex
	
	public static void StartTimer()
	{
		if(t != null) 
			return;
		t = new TimerThread();
		t.start();
	}
	
	/*
	 * Method to add a connection to the room;
	 * Gets a socket and creates a new connection
	 * There is a lock on the proper adding to avoid interfering with the createGame method
	 * After insertion it checks weather the user inserted is second in line (if so it resets the secondPlayerTime) 
	 * else it checks whether the room is "full"
	 */
	public static void addConnection(Socket clientSocket) throws IOException
	{
		Connection conn = new SocketConnection(clientSocket, executorService);
		try
		{
			conn.write(new StringMessage("Connected!"));
			conn.write(new StringMessage("waiting for players"));
		} catch(WriterException e)
		{
			e.printStackTrace();
		}
		mux.lock();
		room.add(conn);
		System.out.println(room.size() + " players in Waiting room");
		mux.unlock();
		if(room.size() == 2) 
		{
			secondPlayerTime = System.currentTimeMillis();
		}
		else roomIsFull();
	}
	
	/*
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
	
	/*
	 * Method to get the time of connection of the second player
	 * If no player is present default value of secondPlayerTime is -1
	 */
	public static long getStartTime()
	{
		return secondPlayerTime;
	}
	
	/*
	 * Method to starts a new game
	 * Before doing anything check that there are at least to players in the room
	 * This is to avoid creating a single player game, which could happen in one peculiar situation due to
	 * the timer thread
	 * If there are at least two players a new game is started. 
	 * The secondPlayertime is reset and a new Threadpool is created
	 * The creation is locked to avoid interference if a new player is added in the meantime;
	 * N.B. Interference is only possible if the game is started by the timeout
	 */
	public static synchronized void startGame()
	{
		if(room.size() <2) return; 						//Check to avoid concurrency in one particular case		
		
		System.out.println(LocalDateTime.now() + " New game has started with " + room.size() + " players!");  //TEST
		
		mux.lock();
		new GameFactory(room);
		//new Thread(new GameFactory(room)).start();
		/*
		 * Fake game for test purpose only	
		ArrayList<Future<Integer>> writeReturnList = new ArrayList<Future<Integer>>();
		for(Connection c:room) writeReturnList.add(c.write(new StringMessage("Game has started")));
		for(int i = 0; i<writeReturnList.size(); i++)
		{
			Future<Integer> r = writeReturnList.get(i);
			
			int attempts;
			try 
			{
				attempts = r.get();
				System.out.println("Wrote to player:" + i + " in " + attempts + "attempts");
			} catch (InterruptedException | ExecutionException e) 
			{
				System.out.println("Could not write to player:" + i);
				room.get(i).setDisconnected();
			}
		}
		//*/
		room.clear();
		mux.unlock();
		secondPlayerTime = -1;								//Resets secondPlayerTime
		executorService = Executors.newFixedThreadPool(10);	//Creates new ThreadPool
		System.out.println(room.size() + " players in waiting room");
	}
 	
	/*
	 * Method to safely quit the thread
	 * Includes a notification to all waiting users
	 */
 	public static void quit()
 	{
 		t.interrupt();
 		for(Connection c: room)
			try {
				c.write(new StringMessage("ServerQuits"));
			} catch (WriterException e) {
				e.printStackTrace();
			}
 	}
}
