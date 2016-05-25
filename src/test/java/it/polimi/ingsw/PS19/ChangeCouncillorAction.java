package it.polimi.ingsw.PS19;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import it.polimi.ingsw.PS19.controller.GameController;
import it.polimi.ingsw.PS19.controller.action.MainElectRegionCouncillor;
import it.polimi.ingsw.PS19.message.SendActionMessage;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.map.Balcony;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class ChangeCouncillorAction {

	@Test
	public void test() 
	{
		Model m = new Model(2);
		System.out.println(m.toString());
		//Region r = m.getMap().getRegionByType(RegionType.HILL);
		GameController g = new GameController(m);
		for (RegionType reg : RegionType.values()) 
		{
			MainElectRegionCouncillor ac = new MainElectRegionCouncillor(Color.BLACK, 0, reg);
			SendActionMessage me = new SendActionMessage(ac, 0);
			g.update(null, me);
			System.out.println("+++++++++++++++++++++++\n" + m.g);
		}
		
	}

}
