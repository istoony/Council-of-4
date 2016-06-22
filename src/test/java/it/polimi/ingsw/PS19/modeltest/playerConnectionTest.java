package it.polimi.ingsw.PS19.modeltest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.ps19.model.Model;

public class playerConnectionTest {

	@Test
	public void test() 
	{
		
		/**
		 * Controllo se l'algoritmo per cambiare il turno funziona correttamente.
		 * Inizializzo una partita con 6 player, disconnetto il 5 il 3 e l'1 
		 * con una serie di chiamte che trovano il prossimo id 'giveNextCorrectId'
		 * e con una serie di chiamate al metodo 'setPlayerTurnId'
		 * Imposto un turno completo controllando che lo scorrere degli id sia conforme 
		 * a quanto aspettato
		 * 
		 * TODO: creare nel vettore waitingroom la parte giusta di codice
		 * 
		 */
		List<Integer> players = new ArrayList<>();
		players.add(0);
		players.add(1);
		players.add(2);
		players.add(3);
		players.add(4);
		players.add(5);
		Model model = new Model(players);
	
		for(int i = 0; i<6; i++)
			assertTrue(model.getPlayerById(i) != null);
		
		//model.getCurrentState().disconnectPlayer(3);
		//model.getCurrentState().disconnectPlayer(5);
		//model.getCurrentState().disconnectPlayer(1);
		
		assertTrue("id = " + model.getCurrentState().getPlayerTurnId(), model.getCurrentState().getPlayerTurnId() == 0);
		model.getCurrentState().setPlayerTurnId(model.getCurrentState().giveNextCorrectId(model.getCurrentState().getPlayerTurnId()));
		
		//assertTrue("id = " + model.getCurrentState().getPlayerTurnId(),model.getCurrentState().getPlayerTurnId() == 1);
		//model.getCurrentState().setPlayerTurnId(model.getCurrentState().giveNextCorrectId());
		
		assertTrue("id = " + model.getCurrentState().getPlayerTurnId(),model.getCurrentState().getPlayerTurnId() == 2);

		model.getCurrentState().setPlayerTurnId(model.getCurrentState().giveNextCorrectId(model.getCurrentState().getPlayerTurnId()));
		
		assertTrue("id = " + model.getCurrentState().getPlayerTurnId(),model.getCurrentState().getPlayerTurnId() == 4);

		model.getCurrentState().setPlayerTurnId(model.getCurrentState().giveNextCorrectId(model.getCurrentState().getPlayerTurnId()));
		
		assertTrue("id = " + model.getCurrentState().getPlayerTurnId(),model.getCurrentState().getPlayerTurnId() == 0);
		
		
	}

}
