package it.polimi.ingsw.PS19.model.map;

import java.awt.Color;
import java.util.ArrayList;

import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.bonus.Bonus;

public class City {
	
	int id;
	String name;
	private ArrayList<Bonus> bonus;
	Color citycolor;
	ArrayList<City> neighbours;
	//reference to his own region
	
	public City(int id){
		this.id=id;
		bonus = new ArrayList<Bonus>();
	}
	
	
	public void setParameters(String name, String color){
		this.name=name;
		this.citycolor = City.translateColor(color);
	}
	
	public void setParameters(String name, Color color){
		this.name=name;
		this.citycolor = color;
	}
	
	public void setParameters(String name, Color color, int id){
		this.id=id;
		this.name=name;
		this.citycolor = color;
	}
	
	private static Color translateColor(String color){
		return Color.decode(color);
	}
	
	public char getCapitalLetter(){
		return this.name.charAt(0);
	}
	
	public Color getCitycolor() 
	{
		return citycolor;
	}
	
	public void addNear(ArrayList<City> lis){
		this.neighbours = new ArrayList<City>();
		this.neighbours.addAll(lis);
	}
	
	public void addBonus(Bonus bonus)
	{
		this.bonus.add(bonus);
	}
	
	@Override
	public String toString() 
	{
		String s = "";
		s += "   id:" + id + "\n";
		s += "   name:" + name + "\n";
		for (Bonus b : bonus) 
		{
			s += "   bonus:" + b.toString();
		}
		s += "\n   citycolor:" + citycolor + "\n";
		return s;
	}
		
}
