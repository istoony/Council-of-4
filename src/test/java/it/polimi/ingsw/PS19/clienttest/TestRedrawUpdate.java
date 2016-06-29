package it.polimi.ingsw.PS19.clienttest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.ps19.client.clientmodel.ClientUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.DrawBusinessCardUpdate;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitor;
import it.polimi.ingsw.ps19.client.clientmodel.ReplyVisitorImpl;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.controller.GameController;
import it.polimi.ingsw.ps19.controller.action.BuyHelper;
import it.polimi.ingsw.ps19.controller.support.ActionMessages;
import it.polimi.ingsw.ps19.message.replies.DrawBusinessCardReply;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.message.replies.SendFullGameReply;
import it.polimi.ingsw.ps19.message.requests.RedrawBusinessCardMessage;
import it.polimi.ingsw.ps19.message.requests.SendFullGameMessage;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.parameter.RegionType;
import it.polimi.ingsw.ps19.server.WaitingRoom;
import it.polimi.ingsw.ps19.view.connection.Connection;
import it.polimi.ingsw.ps19.view.connection.RMIConnection;

public class TestRedrawUpdate {

	@Test
	public void test() 
	{
		/**
		 * In questo test creo una richiesta (che solitamente avviene nel client)
		 * La richiesta la giro al server che ritorna il gioco completo (Testando il model del client)
		 * Creo sul client l'azione di cambiare un consigliere, 
		 * la invio al server che lo cambia e ritorna il model aggiornato.
		 * aggiorno il model e controllo che i dati aggiornati e vecchi del balcone siano coerenti.
		 */
		
		
		Connection uno = new RMIConnection(true);
		uno.setActive();
	Connection due = new RMIConnection(true);
		due.setActive();
		
	Map<Integer, Connection> wRoom = new HashMap<>();
	wRoom.put(0, uno);
	wRoom.put(1, due);
	
	WaitingRoom.setConnection(wRoom);
		List<Integer> players = new ArrayList<>();
		players.add(0);
		players.add(1);
		
		
		Model m = new Model(players);
		GameController g = new GameController(m);
		g.update(null, new BuyHelper(0));
		m.getCurrentState().setPlayerTurnId(0);
		m.getPlayerById(0).setFastActionCounter(1);
		
		ClientModel clientModel = new ClientModel(m);
		SendFullGameMessage sendFullGame = new SendFullGameMessage(0);
		g.update(null, sendFullGame);
		ReplyVisitor visitor = new ReplyVisitorImpl();
		
		ClientUpdate update = g.getReply().display(visitor);
		
		assertTrue(g.getReply() instanceof SendFullGameReply);
		assertTrue(update != null);
		
		
		//update.update(clientModel, null);
		
		//assertTrue(clientModel.getActiveplayer() ==0 );
		assertTrue(clientModel.getAllCities() != null);
		assertTrue(clientModel.getAvailablecouncillor() != null);
		assertTrue(clientModel.getKing() != null);
		assertTrue(clientModel.getRegions() != null);
		assertTrue(clientModel.getPlayer() != null);
		
		BusinessCard first = clientModel.getRegionByType(RegionType.PLAIN).getFirstcard();
		BusinessCard second = clientModel.getRegionByType(RegionType.PLAIN).getSecondcard();
		
		RedrawBusinessCardMessage redraw = new RedrawBusinessCardMessage(RegionType.PLAIN);
		redraw.setId(0);
		
		g.update(null, redraw);
		
		assertTrue(g.getReply() instanceof DrawBusinessCardReply);
		assertTrue(g.getReply().getResult().equals(ActionMessages.EVERYTHING_IS_OK));
		Reply redrawReply = g.getReply();
		
		ClientUpdate redrawUpdate = redrawReply.display(visitor);
		
		assertTrue(redrawUpdate instanceof DrawBusinessCardUpdate);
		//redrawUpdate.update(clientModel, null);
		
		BusinessCard firstNew = clientModel.getRegionByType(RegionType.PLAIN).getFirstcard();
		BusinessCard secondNew = clientModel.getRegionByType(RegionType.PLAIN).getSecondcard();
		
		assertTrue(first.getId() != firstNew.getId());
		assertTrue(second.getId() != secondNew.getId());
		
	}

}
