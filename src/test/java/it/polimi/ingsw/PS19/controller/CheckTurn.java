package it.polimi.ingsw.PS19.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.polimi.ingsw.ps19.controller.GameController;
import it.polimi.ingsw.ps19.message.requests.ElectCouncillorMessage;
import it.polimi.ingsw.ps19.message.requests.EndTurnMessage;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

public class CheckTurn {

	@Test
	public void test() 
	{
		//check if the turn change;
		Model m = new Model(4);
		GameController g = new GameController(m);
		int turn = 0;
		for(int i=0; i< 10; i++)
		{
			assertTrue(m.getCurrentState().getPlayerTurnId() == turn);
					//Count number of card of next player
			int numberOfCard = m.getPlayerById((turn + 1)%4).getPoliticcard().size();
			
			ElectCouncillorMessage message = new ElectCouncillorMessage(m.getMap().getAvailableCouncillor().getListofcolors().getRandomColor(), RegionType.MOUNTAIN);
			message.setId(turn);
			message.setMainAction(true);
		
			g.update(null, message);
		
			EndTurnMessage end = new EndTurnMessage();
			end.setId(turn);
		
			g.update(null, end);
		
			turn ++;
			turn = turn % 4;
			assertTrue(m.getCurrentState().getPlayerTurnId() == turn);
			assertTrue(m.getPlayerById(turn).getPoliticcard().size() == numberOfCard + 1);
			//System.out.println(numberOfCard);
		}
	}

}
