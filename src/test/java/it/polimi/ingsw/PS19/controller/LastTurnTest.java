package it.polimi.ingsw.PS19.controller;

import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.ps19.controller.GameController;
import it.polimi.ingsw.ps19.controller.support.ActionMessages;
import it.polimi.ingsw.ps19.message.replies.ElectCouncillorReply;
import it.polimi.ingsw.ps19.message.replies.EndGameReply;
import it.polimi.ingsw.ps19.message.requests.BuyHelperMessage;
import it.polimi.ingsw.ps19.message.requests.ChangeKingPositionMessage;
import it.polimi.ingsw.ps19.message.requests.ElectCouncillorMessage;
import it.polimi.ingsw.ps19.message.requests.PlayerDisconnectedMessage;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
import it.polimi.ingsw.ps19.model.parameter.RegionType;
import it.polimi.ingsw.ps19.server.WaitingRoom;
import it.polimi.ingsw.ps19.view.connection.Connection;
import it.polimi.ingsw.ps19.view.connection.RMIConnection;

public class LastTurnTest {

	@Test
	public void test() 
	{
		/**
		 * Creo 5 player nella waitingroom, ne disconnetto subito 4,
		 * Ne rimane uno e il server entra nello stato di "last turn"
		 * fa giocare le azioni all'ultimo player rimanente ed invia il messaggio di End Game
		 */
		Connection uno = new RMIConnection(true);
			uno.setActive();
		Connection due = new RMIConnection(true);
			due.setActive();
			due.setDisconnected();
		Connection tre = new RMIConnection(true);
			tre.setActive();
			tre.setDisconnected();
		Connection quattro = new RMIConnection(true);
			quattro.setActive();
			quattro.setDisconnected();
		Connection cinque = new RMIConnection(true);
			cinque.setActive();
			cinque.setDisconnected();
	
		Map<Integer, Connection> wRoom = new HashMap<>();
		wRoom.put(10, uno);
		wRoom.put(11, due);
		wRoom.put(12, tre);
		wRoom.put(13, quattro);
		wRoom.put(14, cinque);
	
		WaitingRoom.setConnection(wRoom);
		
		/**
		 * inizializzo il model con i 5 player aggiungendo i player disconnessi
		 */
	
		List<Integer> players = new ArrayList<>();
		players.add(10);
		players.add(11);
		players.add(12);
		players.add(13);
		players.add(14);
		
		Model m = new Model(players);
		m.getCurrentState().addDisconnectedPlayer(11);
		
		m.getCurrentState().addDisconnectedPlayer(12);
	
		m.getCurrentState().addDisconnectedPlayer(13);
	
		m.getCurrentState().addDisconnectedPlayer(14);
		
		GameController g = new GameController(m);
		
		
			//invio un messaggio al controller informandolo che si è disconnesso il player 13
			//in questo modo capisce che rimane un solo player connesso e gli fa giocare l'ultimo turno
		PlayerDisconnectedMessage dis = new PlayerDisconnectedMessage(13);
		g.update(null, dis);
		
		ArrayList<Color> card = new ArrayList<>();
		card.add(Color.decode("#FEFEFE"));
		
			//do in mano al player un joker e provo a far spostare il re su una città
			//sicuramente troppo lontana per poterselo permettere
		m.getPlayerById(10).addCardToHand(new PoliticsCard(Color.decode("#FEFEFE")));
		
		ChangeKingPositionMessage mess = new ChangeKingPositionMessage(
				m.getMap().getRegionByType(RegionType.MOUNTAIN).getCityById(11), card);
		mess.setId(10);
		
		g.update(null, mess);
		
		assertTrue(g.getReply().getResult(), g.getReply() instanceof ElectCouncillorReply);
		assertTrue(g.getReply().getResult() == ActionMessages.NO_MONEY);
		
		BuyHelperMessage helper = new BuyHelperMessage();
		helper.setId(10);
		
		g.update(null, helper);
		
		ElectCouncillorMessage elect = new ElectCouncillorMessage(
				m.getMap().getAvailableCouncillor().getListofcolors().getRandomColor(), 
				m.getMap().getKing());
		elect.setMainAction(true);
		elect.setId(10);
	
		g.update(null, elect);
		
		assertTrue(g.getReply().getResult(), g.getReply() instanceof EndGameReply);
	
	}

}
