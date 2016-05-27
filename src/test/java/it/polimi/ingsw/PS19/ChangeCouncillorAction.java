package it.polimi.ingsw.PS19;

import java.awt.Color;

import org.junit.Test;

import it.polimi.ingsw.PS19.controller.GameController;
import it.polimi.ingsw.PS19.message.ElectCouncillorMessage;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class ChangeCouncillorAction {

	@Test
	public void test() 
	{
		Model m = new Model(2);
		//System.out.println(m.toString());
		//Region r = m.getMap().getRegionByType(RegionType.HILL);
		GameController g = new GameController(m);
		for (int i = 0; i < 50 ; i++) 
		{
		for (RegionType reg : RegionType.values()) 
		{
			Color color = m.getMap().getCouncilcolors().getRandomColor();
			System.out.println("FIRST++++"+ reg.toString() +"COLORR" + color.toString() + "\n" + m.getMap().getRegionByType(reg).getBalcony().toString());
			ElectCouncillorMessage me = new ElectCouncillorMessage(color, reg);
			me.setId(0);
			me.setMainAction(true);
			g.update(null, me);
			System.out.println("SECOND++++\n" + m.getMap().getRegionByType(reg).getBalcony().toString());
		}
		}
		
	}

}
