package it.polimi.ingsw.PS19.model.map;

import java.awt.Color;
import java.util.ArrayList;

import it.polimi.ingsw.PS19.model.bonus.Bonus;

public class City {
	
	int id;
	String name;
	Bonus bonus;
	Color citycolor;
	ArrayList<City> neighbours;
	//reference to his own region
	
	public City(int id){
		this.id=id;
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
	
	public void addNear(ArrayList<City> lis){
		this.neighbours = new ArrayList<City>();
		this.neighbours.addAll(lis);
	}
		
}
