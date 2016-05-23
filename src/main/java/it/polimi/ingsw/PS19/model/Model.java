package it.polimi.ingsw.PS19.model;

import java.util.ArrayList;
import java.util.Observable;

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
