package it.polimi.ingsw.PS19.modeltest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.parameter.RegionType;
import it.polimi.ingsw.ps19.server.WaitingRoom;
import it.polimi.ingsw.ps19.view.connection.Connection;
import it.polimi.ingsw.ps19.view.connection.RMIConnection;

public class NetCityBonusTest {

	@Test
	public void test() 
	{
		/**
		 * This tests checks if, after an emporium is built on a city, the correct net of connected cities is returned.
		 * We build an emporia un some cities.
		 * We 'manually' build a vector of the connected cities
		 * The algorithm is executed and the returned vector is confronted with the "manual" one
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
		Model model = new Model(players);
		Player p = model.getPlayerById(0);
		
		//copio il puntatore della città dentro la variabile
		City id7 = model.getMap().getRegionByType(RegionType.HILL).getCityById(7);
		City id8 = model.getMap().getRegionByType(RegionType.HILL).getCityById(8);
		City id14 = model.getMap().getRegionByType(RegionType.MOUNTAIN).getCityById(14);
		City id2 = model.getMap().getRegionByType(RegionType.PLAIN).getCityById(2);
		City id9 = model.getMap().getRegionByType(RegionType.HILL).getCityById(9);
		City id10 = model.getMap().getRegionByType(RegionType.HILL).getCityById(10);
		
		//costruisco gli empori
		id7.buildEmporium(p);
		id8.buildEmporium(p);
		id14.buildEmporium(p);
		id2.buildEmporium(p);
		id10.buildEmporium(p);
		id9.buildEmporium(p);
		
		//in base alla mappa creo il vettore degli id corretti
		List<Integer> test = new ArrayList<>();
		test.add(7);
		test.add(8);
		test.add(14);
		test.add(10);
		test.add(9);
		
		//chiamo l' applyNetBonus a questa funzione serve un vettore vuoto  ritorna 
		//un vettore con le città collegate alla prima e dove ho costruito
		List<City> visited = new ArrayList<>();
		List<City> total = id7.applyNetBonus(p, visited);

		//confronto il valore ritornato dalla funzione con il vettore corretto di id
		for (City city : total)
			assertTrue(test.contains(city.getId()));
	}

}
