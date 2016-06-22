package it.polimi.ingsw.PS19.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.polimi.ingsw.ps19.controller.GameController;
import it.polimi.ingsw.ps19.message.requests.BuyHelperMessage;
import it.polimi.ingsw.ps19.message.requests.BuyMainActionMessage;
import it.polimi.ingsw.ps19.message.requests.ElectCouncillorMessage;
import it.polimi.ingsw.ps19.model.Model;

public class BuyMainActionTest 
{

	@Test
	public void test() 
	{
		Model m = new Model(4);
		GameController g = new GameController(m);
		int i = 0;
		for(i=1; i<5;i++)
		{
		
			ElectCouncillorMessage elect = new ElectCouncillorMessage(
							m.getMap().getAvailableCouncillor().getListofcolors().getRandomColor(),
							m.getMap().getKing());
			elect.setId(0);
			elect.setMainAction(true);
			
			g.update(m, elect);
			
			assertTrue(m.getPlayerById(0).getMainActionCounter() == 0);
			//assertTrue(m.getPlayerById(0).getMoney() == (10 + 4*i));
			
			BuyHelperMessage helper = new BuyHelperMessage();
			helper.setId(0);
	
			g.update(null, helper);
			
			assertTrue("main: " + m.getPlayerById(0).getMainActionCounter(), 
					m.getPlayerById(0).getMainActionCounter() == 0);
			assertTrue("fast: " + m.getPlayerById(0).getFastActionCounter(), 
					m.getPlayerById(0).getFastActionCounter() == 0);
			assertTrue("money: " + m.getPlayerById(0).getMoney(), 
					m.getPlayerById(0).getMoney() == 100 + i);
			assertTrue("helper: "+ m.getPlayerById(0).getHelpers(), 
					m.getPlayerById(0).getHelpers() == 1 + i);
			m.getCurrentState().setPlayerTurnId(0);
			m.getPlayerById(0).setMainActionCounter(1);
			m.getPlayerById(0).setFastActionCounter(1);

		}
		BuyMainActionMessage mess = new BuyMainActionMessage();
		
		mess.setId(0);
		
		g.update(null, mess);
		
		assertTrue(m.getPlayerById(0).getMainActionCounter() == 2);
		assertTrue(m.getPlayerById(0).getFastActionCounter() == 0);
	}

}
