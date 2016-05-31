package it.polimi.ingsw.PS19.model.map;

import java.awt.Color;
import java.util.ArrayList;

import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.bonus.Bonus;

public class City {
	
	private int id;
	private String name;
	private ArrayList<Bonus> bonus;
	private Color citycolor;
	private ArrayList<City> neighbours;
	private ArrayList<Integer> emporia; //array di id giocatori
	
	public City(int id){
		this.id=id;
		bonus = new ArrayList<>();
		emporia = new ArrayList<>();
	}
	
	public void buildEmporium(Player p){
		emporia.add(p.getId());
		p.addToMyEmporia(this);
	}
	
	public int calculateMalusEmporium(){
		return emporia.size();
	}
	
	//applica i bonus delle città collegate
	public void applyNetBonus(Player p, ArrayList<City> visited){
		this.applyBonus(p);
		visited.add(this);
		for(City neig : neighbours){
			if(!visited.contains(neig)&&emporia.contains(p.getId())){
				neig.applyNetBonus(p, visited);
			}
		}
	}
	
	public void applyBonus(Player p){
		for(Bonus b : bonus){
			b.giveBonus(p);
		}
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
	
	public Color getCitycolor() 
	{
		return citycolor;
	}

	public int getId() 
	{
		return id;
	}
	public ArrayList<City> getNeighbours() 
	{
		return neighbours;
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
