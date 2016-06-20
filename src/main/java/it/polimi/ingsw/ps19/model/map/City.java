package it.polimi.ingsw.PS19.model.map;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.bonus.Bonus;
import it.polimi.ingsw.PS19.model.bonus.BonusFactory;

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
		p.addToMyEmporia(this);
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

	public List<City> getNeighbours() 
	{
		return neighbours;
	}

	//aggiungi vicini
	public void addNear(List<City> lis){
		this.neighbours = new ArrayList<>();
		this.neighbours.addAll(lis);
	}
	
	private void generateBonus(){
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
		return emporia;
	}

	/**
	 * @param emporia the emporia to set
	 */
	public void setEmporia(List<Integer> emporia) {
		this.emporia = emporia;
	}

	/**
	 * @return the bonus
	 */
	public List<Bonus> getBonus() {
		return bonus;
	}
		
}
