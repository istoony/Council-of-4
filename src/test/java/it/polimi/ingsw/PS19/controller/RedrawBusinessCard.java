package it.polimi.ingsw.PS19.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.polimi.ingsw.ps19.controller.GameController;
import it.polimi.ingsw.ps19.message.requests.RedrawBusinessCardMessage;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

public class RedrawBusinessCard 
{

	@Test
	public void test() 
	{
		/**
		 * Controllo che le carte iniziali e le carte dopo aver eseguito la mossa
		 * redraw siano diverse.
		 * TODO: se una cosa funziona 100 volte è sicuramente corretta
		 */
		for(int i =0; i< 100; i++)
		{

			Model m = new Model(2);
			BusinessCard first = m.getMap().getRegionByType(RegionType.PLAIN).getFirstcard();
			BusinessCard second = m.getMap().getRegionByType(RegionType.PLAIN).getSecondcard();
			//System.out.print(m.getMap().getRegionByType(RegionType.PLAIN).toString());
			GameController g = new GameController(m);
			RedrawBusinessCardMessage message = new RedrawBusinessCardMessage(RegionType.PLAIN);
		
			message.setId(0);
		
			g.update(null, message);
		
			BusinessCard firstNew = m.getMap().getRegionByType(RegionType.PLAIN).getFirstcard();
			BusinessCard secondNew = m.getMap().getRegionByType(RegionType.PLAIN).getSecondcard();

			assertTrue("prima " + first.getId() + " - seconda " + firstNew.getId(), first.getId() != firstNew.getId());
			assertTrue("prima " + second.getId() + " - seconda " + secondNew.getId(), second.getId() != secondNew.getId());
		}
	}
}
