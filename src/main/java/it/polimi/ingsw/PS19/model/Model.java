package it.polimi.ingsw.PS19.model;

import java.util.ArrayList;
import java.util.Observable;

import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.message.MessageType;
import it.polimi.ingsw.PS19.message.replies.SendFullGameReply;
import it.polimi.ingsw.PS19.model.map.Map;
import it.polimi.ingsw.PS19.model.map.MapLoader;

public class Model extends Observable
{
	
	private Market market;
	private Map map;
	private ArrayList<Player> player;
	private int numberofplayer;
	
	public Model(int numberofplayer) 
	{
		this.numberofplayer = numberofplayer;
		
		PlayerFactory();
		
		map = MapLoader.builder();
		
		market = new Market();
		
	}
	
	private void PlayerFactory()
	{
		player = new ArrayList<Player>();
		
		for(int i = 0; i < numberofplayer; i++)
		{
			player.add(new Player(i));
		}
		Player.setStartingItems(player, "mapfile/playerconfig.xml");
	}
	
	public Map getMap() 
	{
		return map;
	}
	
	public Player getPlayerById(int i)
	{
		return player.get(i);
	}
	
	public void createMessage(MessageType messageType)
	{
		Message message;
		switch (messageType) 
		{
			case GAME_STARTED:
			{
				message = new SendFullGameReply(this);
				break;
			}

			default:
				break;
		}
		notifyObservers(message);
	}
	
	@Override
	public String toString() 
	{
		String s = "++++++++++++++++++++\n";
		s = s + "number of player: " + numberofplayer + "\n";
		s = s + map.toString() + "\n*************PLAYERSSSS************\n";
		for (Player p : player) 
		{
			s = s + p.toString() + "\n";
		}
		
		s += "\n++++++++++++++++++++\n";
		return s;
	}
	
	
public static void main(String[] args) 
{
	Model m = new Model(2);
	System.out.println(m.toString());
}	
}
