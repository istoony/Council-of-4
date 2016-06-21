package it.polimi.ingsw.PS19.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.polimi.ingsw.ps19.controller.GameController;
import it.polimi.ingsw.ps19.message.requests.ElectCouncillorMessage;
import it.polimi.ingsw.ps19.message.requests.EndTurnMessage;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

public class CheckTurn {

	private static final int NUMBER_OF_PLAYER = 10;

	@Test
	public void test() 
	{
		//check if the turn change;
		Model m = new Model(NUMBER_OF_PLAYER);
		GameController g = new GameController(m);
		int turn = 0;
		for(int i=0; i< NUMBER_OF_PLAYER; i++)
		{
			assertTrue(m.getCurrentState().getPlayerTurnId() == turn);
					//Count number of card of next player
			int numberOfCard = m.getPlayerById((turn + 1)%NUMBER_OF_PLAYER).getPoliticcard().size();
			
			ElectCouncillorMessage message = new ElectCouncillorMessage(m.getMap().getAvailableCouncillor().getListofcolors().getRandomColor(), RegionType.MOUNTAIN);
			message.setId(turn);
			message.setMainAction(true);
		
			g.update(null, message);
		
			EndTurnMessage end = new EndTurnMessage();
			end.setId(turn);
		
			g.update(null, end);
		
			turn ++;
			turn = turn % NUMBER_OF_PLAYER;
			assertTrue("turn " + turn + "other" + m.getCurrentState().getPlayerTurnId(),
					m.getCurrentState().getPlayerTurnId() == turn);
			assertTrue("turn " + turn + "card" + m.getPlayerById(turn).getPoliticcard().size(), 
					m.getPlayerById(turn).getPoliticcard().size() == numberOfCard + 1);
			//System.out.println(numberOfCard);
		}
	}

}
