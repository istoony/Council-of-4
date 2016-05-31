package it.polimi.ingsw.PS19.model.bonus;

import java.awt.Color;
import java.util.ArrayList;

import it.polimi.ingsw.PS19.model.map.City;
import it.polimi.ingsw.PS19.model.map.Region;

public class CityColorBonus {
	
	private Color color;
	private int points;
	private ArrayList<City> citylist;
	
	public CityColorBonus(String c, int p){
		color=Color.decode(c);
		points=p;
	}
	
	public void joinBonusToCity(ArrayList<Region> rcitylist){
		citylist = new ArrayList<City>();
		for(Region r : rcitylist){
			for(City c : r.getCities()){
				if(color.equals(c.getCitycolor())){
					citylist.add(c);
				}
			}
		}
		
	}
	
	
	/**
	 * @return the citylist
	 */
	public ArrayList<City> getCitylist() {
		return citylist;
	}

	/**
	 * @param citylist the citylist to set
	 */
	public void setCitylist(ArrayList<City> citylist) {
		this.citylist = citylist;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CityColorBonus [color=" + color + ", points=" + points +"]";
	}


	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}
	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}
}
