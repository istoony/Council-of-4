/*
 * @Author Andrea Milanta
 */
package it.polimi.ingsw.ps19.server;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

import java.io.IOException;

/*
 * Main class of the server
 * manages socket connections.
 */
public class ServerManager 
{
	private static ServerSocket serverSocket;	//Socket del server;
	
	public static void main(String[] args) 
	{
		/*
		 * Creates Server socket
		 */
		try
		{
			serverSocket = new ServerSocket(Constants.PORT);
			System.out.println("the server socket creation has been successful");
		}
		catch(IOException e)
		{
			System.out.println("Something went wrong in creating a serversocket");
			e.printStackTrace();
		}
		
		WaitingRoom.StartTimer();
		/*
		 * Waits for new clients and adds them to waiting room
		 */
		while(true)
		{
			try
			{
				Socket clientSocket = serverSocket.accept();
				//TEST
				System.out.println(LocalDateTime.now() + " New client Connected");
				WaitingRoom.addConnection(clientSocket);
			}
			catch(IOException e)
			{
				
			}
		}
	}
}
