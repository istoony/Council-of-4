package it.polimi.ingsw.PS19.controller;

import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Test;

import it.polimi.ingsw.PS19.message.requests.ElectCouncillorMessage;
import it.polimi.ingsw.PS19.message.requests.EndTurnMessage;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class CheckTurn {

	@Test
	public void test() 
	{
		//check if the turn change;
		Model m = new Model(4);
		GameController g = new GameController(m);
		int turn = 0;
		for(int i=0; i< 5; i++)
		{
			assertTrue(m.getCurrentState().getPlayerTurnId() == turn);
					//Count number of card of next player
			int numberOfCard = m.getPlayerById((turn + 1)%4).getPoliticcard().size();
			
			ElectCouncillorMessage message = new ElectCouncillorMessage(Color.decode("#0000FF"), RegionType.MOUNTAIN);
			message.setId(turn);
			message.setMainAction(true);
		
			g.update(null, message);
		
			EndTurnMessage end = new EndTurnMessage();
			end.setId(turn);
		
			g.update(null, end);
		
			turn ++;
			turn = turn % 4;
			assertTrue(m.getCurrentState().getPlayerTurnId() == turn);
			assertTrue(m.getPlayerById(turn).getPoliticcard().size() > numberOfCard);
			System.out.println(numberOfCard);
		}
	}

}
