package it.polimi.ingsw.PS19.model.map;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import it.polimi.ingsw.PS19.model.bonus.Bonus;
import it.polimi.ingsw.PS19.model.bonus.MoreVictoryPoints;

public class LoadCityColor 
{
	private static Map<String, Bonus> citycolor = new HashMap<String, Bonus>();
	
	public LoadCityColor()
	{
		FileReader f;
		try
		{
			f = new FileReader("prova.txt");
			BufferedReader b;
			b=new BufferedReader(f);
		}
		catch(FileNotFoundException e){
			System.out.println("file non trovato");
			createCityColorFile();
		}
			
		String color, appoggio;
		int bonusnumber;
		while(true) 
		{
			color=b.readLine();
			appoggio = b.readLine();
			
			bonusnumber=Integer.parseInt(appoggio);
			Bonus bonus = new MoreVictoryPoints(bonusnumber);
			
			citycolor.put(color, bonus);
		}
	}

	private void createCityColorFile()
	{
	
	}
}
