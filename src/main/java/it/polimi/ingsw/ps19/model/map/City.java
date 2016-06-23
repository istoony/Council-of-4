package it.polimi.ingsw.ps19.model.map;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.bonus.Bonus;
import it.polimi.ingsw.ps19.model.bonus.BonusFactory;
import it.polimi.ingsw.ps19.model.parameter.Costants;

public class City implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9001240521619279283L;
	private int id;
	private String name;
	private ArrayList<Bonus> bonus;
	private Color citycolor;
	private List<City> neighbours;
	private List<Integer> emporia; //array di id giocatori
	
	public City(int id){
		this.id=id;
		bonus = new ArrayList<>();
		emporia = new ArrayList<>();
		this.generateBonus();
	}
	
	/**
	 * Add an emporium on current city and add to player emporia
	 * this city
	 * @param p is the full player
	 */
	public void buildEmporium(Player p)
	{
		emporia.add(p.getId());
	}
	
	public int calculateMalusEmporium(){
		return emporia.size();
	}
	
	//applica i bonus delle città collegate
	public List<City> applyNetBonus(Player p, List<City> visited)
	{
		List<City> cityNet = new ArrayList<>();
		if(emporia.contains(p.getId()))
			cityNet.add(this);
		visited.add(this);
		for(City neig : neighbours)
		{
			if(!visited.contains(neig) && emporia.contains(p.getId()))
			{
				cityNet.addAll(neig.applyNetBonus(p, visited));
			}
		}
		return cityNet;
	}
		
	public void setParameters(String name, String color){
		this.name=name;
		this.citycolor = City.translateColor(color);
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

	public List<City> getNeighbours() 
	{
		return Costants.clone(neighbours);
	}

	//aggiungi vicini
	public void addNear(List<City> lis){
		this.neighbours = new ArrayList<>();
		this.neighbours.addAll(lis);
	}
	
	private void generateBonus()
	{
		this.bonus.addAll(BonusFactory.generateCityBonus());
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
		s += "\n neighbours:\n"; 
		for(City c : neighbours){
			s+=c.name+"\n";
		}
		return s;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the emporia
	 */
	public List<Integer> getEmporia() {
		return Costants.clone(emporia);
	}

	/**
	 * @return the bonus
	 */
	public List<Bonus> getBonus() {
		return Costants.clone(bonus);
	}
		
}
