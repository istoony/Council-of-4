package it.polimi.ingsw.PS19.model;

import java.util.ArrayList;
import java.util.Observable;

import it.polimi.ingsw.PS19.model.card.PoliticDeck;
import it.polimi.ingsw.PS19.model.map.Map;
import it.polimi.ingsw.PS19.model.map.MapLoader;
import it.polimi.ingsw.PS19.model.parameter.ColorManager;

public class Model extends Observable
{
	
	private Market market;
	private Map map;
	private ArrayList<Player> player;
	private int numberofplayer;
	
	public Model(int numberofplayer) 
	{
		this.numberofplayer = numberofplayer;
		
		map = MapLoader.builder();
		
		market = new Market();
		
	}
	
	@Override
	public String toString() 
	{
		String s = "++++++++++++++++++++\n";
		s = s + "number of player: " + numberofplayer + "\n";
		s = s + map.toString();
		s += "\n++++++++++++++++++++\n";
		return s;
	}
	
	
public static void main(String[] args) 
{
	Model m = new Model(2);
	System.out.println(m.toString());
}	
}
