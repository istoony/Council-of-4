package it.polimi.ingsw.PS19;

import org.junit.Test;

import it.polimi.ingsw.PS19.controller.GameController;
import it.polimi.ingsw.PS19.message.requests.RedrawBusinessCardMessage;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class RedrawBusinessCard 
{

	@Test
	public void test() 
	{

		Model m = new Model(2);
		GameController g = new GameController(m);
		System.out.println(m.toString());
		RedrawBusinessCardMessage message = new RedrawBusinessCardMessage(RegionType.PLAIN);
		message.setId(0);
		g.update(null, message);
		System.out.println("\n\n\n" + m.toString());
	}
}
