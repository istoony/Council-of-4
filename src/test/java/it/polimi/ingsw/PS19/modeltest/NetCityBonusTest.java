package it.polimi.ingsw.PS19.modeltest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.City;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class NetCityBonusTest {

	@Test
	public void test() 
	{
		Model model = new Model(2);
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
		List<Integer> correct = new ArrayList<>();
		correct.add(7);
		correct.add(8);
		correct.add(14);
		correct.add(10);
		correct.add(9);
		
		//chiamo l' applyNetBonus a questa funzione serve un vettore vuoto  ritorna 
		//un vettore con le città collegate alla prima e dove ho costruito
		List<City> visited = new ArrayList<>();
		List<City> total = id7.applyNetBonus(p, visited);

		//confronto il valore ritornato dalla funzione con il vettore corretto di id
		for (City city : total)
			assertTrue(correct.contains(city.getId()));
	}

}
