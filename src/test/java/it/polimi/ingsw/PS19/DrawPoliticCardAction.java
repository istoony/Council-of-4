package it.polimi.ingsw.PS19;

import org.junit.Test;

import it.polimi.ingsw.PS19.controller.GameController;
import it.polimi.ingsw.PS19.message.DrawPoliticsCardMessage;
import it.polimi.ingsw.PS19.model.Model;

public class DrawPoliticCardAction {

	@Test
	public void test() 
	{
		Model m = new Model(4);
		GameController g = new GameController(m);
		System.out.println(m.getPlayerById(0).toString());
		for(int i = 0; i< 500; i++)
		{
			DrawPoliticsCardMessage mess = new DrawPoliticsCardMessage();
			mess.setId(0);
			g.update(null, mess);
			System.out.println(m.getPlayerById(0).toString());
		}
	}

}
