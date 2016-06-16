package it.polimi.ingsw.PS19.controller;

import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.PS19.message.requests.GetBusinessCardMessage;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class DrawBusinessCard {

	@Test
	public void test() 
	{
		Model m = new Model(2);
		
		//Set to player 0 many cards
		m.getPlayerById(0).setPoliticCardToDraw(50);
		
		GameController g = new GameController(m);
		
		assertTrue(m.getPlayerById(0).getPoliticcard().size() == 50 + 6);
		
		ArrayList<Color> card = new ArrayList<>();
		System.out.println(m.toString());
		BusinessCard first = m.getMap().getRegionByType(RegionType.MOUNTAIN).getFirstcard();
		
		card.add(Color.decode("#FEFEFE"));
		card.add(Color.decode("#FEFEFE"));
		card.add(Color.decode("#FEFEFE"));
		card.add(Color.decode("#FEFEFE"));
		
		GetBusinessCardMessage mess = new GetBusinessCardMessage(m.getMap().getRegionByType(RegionType.MOUNTAIN).getFirstcard(),RegionType.MOUNTAIN);
		mess.setId(0);
		assertTrue(mess.getCard().getId() == first.getId());
		
		g.update(null, mess);
		
		assertTrue(m.getPlayerById(0).getFreebusinesscard().get(0).getId()==first.getId());
		
		assertTrue(m.getPlayerById(0).getMainActionCounter() == 0);
		//System.out.println(m.getMap().getRegionByType(RegionType.HILL).getBusinessdeck().getCard().size());
		
		assertTrue(m.getMap().getRegionByType(RegionType.HILL).getBusinessdeck().getCard().size() == 3-2 );
		
		//System.out.println(m.getMap().getRegionByType(RegionType.MOUNTAIN).getBusinessdeck().getCard().size());
		assertTrue(m.getMap().getRegionByType(RegionType.MOUNTAIN).getBusinessdeck().getCard().size() == 6-2-1 );
		
		//System.out.println(m.getMap().getRegionByType(RegionType.PLAIN).getBusinessdeck().getCard().size());
		assertTrue(m.getMap().getRegionByType(RegionType.PLAIN).getBusinessdeck().getCard().size() == 8-2 );
		System.out.println(m.toString());
		assertTrue(m.getPlayerById(0).getMoney() == 10 - 4 );
		System.out.println(m.toString());
	}

}
