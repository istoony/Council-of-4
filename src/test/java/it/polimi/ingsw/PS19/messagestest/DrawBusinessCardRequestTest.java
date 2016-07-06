package it.polimi.ingsw.PS19.messagestest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.ps19.message.requests.DrawBusinessCardRequest;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

public class DrawBusinessCardRequestTest {

	/**
	 * This test makes sure that the 'DrawBusinessCardRequest' is properly initialized
	 */
	@Test
	public void testDrawBusinessCardRequest() 
	{
		List<Integer> players = new ArrayList<>();
		players.add(0);
		players.add(1);
		players.add(2);
		players.add(3);
		Model model = new Model(players);
		
		List<BusinessCard> business = new ArrayList<>();
		business.add(model.getMap().getRegionByType(RegionType.MOUNTAIN).getFirstcard());
		business.add(model.getMap().getRegionByType(RegionType.MOUNTAIN).getSecondcard());
		DrawBusinessCardRequest req = new DrawBusinessCardRequest(business);
		int i=0;
		for (BusinessCard b : req.getChosenCards()) 
		{
			assertTrue(b.compare(business.get(i)));
			i++;
		}
		
	}
}
