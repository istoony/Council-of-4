package it.polimi.ingsw.PS19.model.parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import it.polimi.ingsw.PS19.model.card.PoliticsCard;

public class Costants 
{
	public static final String JOKERCOLOR = "#FEFEFE";
	public static final int JUMPCOST = 2;
	public static final int NO_ACTIVE_PLAYER = -1;
	public static final int BROADCAST_MESSAGE = -1;
	public static final Random RANDOM_NUMBER = new Random();
	public static final int N_OF_ACTION_TO_ADD = 1;

	private Costants()
	{
		//così sonar è contento
	}
	
	public static List<PoliticsCard> clonePoliticCard(List<PoliticsCard> politiccard) 
	{
		List<PoliticsCard> clone = new ArrayList<>();
		if(!politiccard.isEmpty())
			for (PoliticsCard p : politiccard)
				clone.add(new PoliticsCard(p.getColor()));
		return clone;
	}
}
