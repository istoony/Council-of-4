package it.polimi.ingsw.PS19;

import java.awt.Color;
import java.util.ArrayList;

import org.junit.Test;

import it.polimi.ingsw.PS19.controller.GameController;
import it.polimi.ingsw.PS19.message.DrawPoliticsCardMessage;
import it.polimi.ingsw.PS19.message.GetBusinessCardMessage;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class DrawPoliticCardAction {

	@Test
	public void test() 
	{
		Model m = new Model(2);
		GameController g = new GameController(m);
		System.out.println(m.getPlayerById(0).toString());
		for(int i = 0; i< 5; i++)
		{
			DrawPoliticsCardMessage mess = new DrawPoliticsCardMessage();
			mess.setId(0);
			g.update(null, mess);
			System.out.println(m.getPlayerById(0).toString());
			
		}
		ArrayList<Color> pc = new ArrayList<>();
		pc.add(Color.decode("#FF0000"));
		pc.add(Color.decode("#0000FF"));
		//pc.add(Color.decode("#FEFEFE"));
		BusinessCard card = m.getMap().getRegionByType(RegionType.MOUNTAIN).getFirstcard();
		
		GetBusinessCardMessage businessMessage = new GetBusinessCardMessage(card, RegionType.MOUNTAIN, pc);
		businessMessage.setId(0);
		g.update(null, businessMessage);
		System.out.println(m.getPlayerById(0).toString());
	}

}
