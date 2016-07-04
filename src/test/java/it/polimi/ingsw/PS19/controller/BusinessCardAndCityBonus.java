package it.polimi.ingsw.PS19.controller;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.ps19.controller.GameController;
import it.polimi.ingsw.ps19.controller.support.SupportMethod;
import it.polimi.ingsw.ps19.message.replies.GetBusinessCardOrCityBonusReply;
import it.polimi.ingsw.ps19.message.requests.BuyHelperMessage;
import it.polimi.ingsw.ps19.message.requests.GetBusinessCardOrBonusMessage;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.bonus.Bonus;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.parameter.RegionType;
import it.polimi.ingsw.ps19.server.WaitingRoom;
import it.polimi.ingsw.ps19.view.connection.Connection;
import it.polimi.ingsw.ps19.view.connection.RMIConnection;

public class BusinessCardAndCityBonus {

	@Test
	public void testBusinessCardOrCityBonusBusinessCardInt() 
	{
		Connection uno = new RMIConnection(true);
			uno.setActive();
		Connection due = new RMIConnection(true);
			due.setActive();
		Connection tre = new RMIConnection(true);
			tre.setActive();
			tre.setDisconnected();
	
		Map<Integer, Connection> wRoom = new HashMap<>();
		wRoom.put(10, uno);
		wRoom.put(11, due);
		wRoom.put(12, tre);
	
		WaitingRoom.setConnection(wRoom);
	
	
		List<Integer> players = new ArrayList<>();
		players.add(10);
		players.add(11);
		
		Model m = new Model(players);
		m.getCurrentState().addDisconnectedPlayer(12);
		
		GameController g = new GameController(m);
		
		BusinessCard card = m.getMap().getRegionByType(RegionType.HILL).getFirstcard();
		
		Player prova = new Player(10);
		prova.setMoney(7);
		prova.setNobilityPoints(0);
		prova.setHelpers(2);
		prova.setVictoryPoints(0);
		
		for (Bonus b : card.getBonus())
		{
			b.giveBonus(prova);
			SupportMethod.checkNobilityPathBonus(m, prova);
		}
		
		SupportMethod.removeCardFromRegionAndAddToPlayer(m, 
				m.getPlayerById(10), card, RegionType.HILL);
		
		m.getPlayerById(10).setBusinessCardRequest(true);
		
			//azione utilizzata solamente per avviare il model e avere la richiesta di una business card
			//come messaggio di risposta
		BuyHelperMessage helper = new BuyHelperMessage();
		helper.setId(10);
		
		g.update(null, helper);
		
		assertTrue(g.getReply() instanceof GetBusinessCardOrCityBonusReply);
		GetBusinessCardOrBonusMessage mes = new GetBusinessCardOrBonusMessage(null, card);
		mes.setId(10);
		
		g.update(null, mes);
		
		assertTrue(prova.getMoney() == m.getPlayerById(10).getMoney());
		assertTrue(prova.getHelpers() == m.getPlayerById(10).getHelpers());
		assertTrue(prova.getVictoryPoints() == m.getPlayerById(10).getVictoryPoints());
		assertTrue(prova.getNobilityPoints() == m.getPlayerById(10).getNobilityPoints());
		
		
	}

	@Test
	public void testBusinessCardOrCityBonusCityInt() 
	{
		//true("Not yet implemented");
	}

}
