package it.polimi.ingsw.PS19;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import it.polimi.ingsw.PS19.controller.GameController;
import it.polimi.ingsw.PS19.message.DrawPoliticsCardMessage;
import it.polimi.ingsw.PS19.message.GetBusinessCardMessage;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class CompleteModelTest {

	@Test
	public void testModel() 
	{
		Model m = new Model(2);
		GameController g = new GameController(m);
		Scanner in = new Scanner(System.in); 
		System.out.println(m.toString());
		int turn = 0;
		while(true)
		{
			System.out.println("CURRENT TURN" + turn);getClass();
			for(int i = 0; i<6; i++)
			{
				DrawPoliticsCardMessage message = new DrawPoliticsCardMessage();
				message.setId(turn);
				g.update(null, message);
			}
			System.out.println(m.getPlayerById(turn).toString());
			System.out.println("MAIN ACTION: \n"
					+ "1 - Corrompere un consiglio e comprare una business");
			int action = in.nextInt();
			if(action == 1)
			{
				System.out.println("Inserisci la regione");
				int r;
				r = in.nextInt();
				RegionType region = null;
				if(r == 1) 
					region = RegionType.PLAIN;
				if(r == 2) 
					region = RegionType.HILL;
				if(r == 3) 
					region = RegionType.MOUNTAIN;
			
				System.out.println(m.getMap().getRegionByType(region).toString());
				int c = in.nextInt();
				BusinessCard card = null;
				if(c == 1)
					card = m.getMap().getRegionByType(region).getFirstcard();
				else 
					card = m.getMap().getRegionByType(region).getSecondcard();

			
				int i = 0;
				ArrayList<Color> polcard = new ArrayList<>();
				while(i != -1)
				{
					System.out.println("BALCONE DELLA REGIONE" + m.getMap().getRegionByType(region).getBalcony().toString());
					System.out.println(m.getPlayerById(turn).toString());
					System.out.println("SCEGLI LA CARTA CHE VUOI SELEZIONARE");
					i = in.nextInt();
					if(i!=-1)
					{
						polcard.add(m.getPlayerById(turn).getPoliticcard().get(i).getColor());
						System.out.println("HAI SCELTO  " +polcard.get(polcard.size() -1).toString());
					}
				}
				
				GetBusinessCardMessage draw = new GetBusinessCardMessage(card, region,polcard);
				draw.setId(turn);
				g.update(null, draw);
			}
			System.out.println(m.toString());
			if(turn == 1)
				turn = 0;
			else 
				turn = 1;
		}
	}

}
