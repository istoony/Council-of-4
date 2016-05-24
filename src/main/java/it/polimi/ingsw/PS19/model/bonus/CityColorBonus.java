package it.polimi.ingsw.PS19.model.bonus;

import java.awt.Color;

public class CityColorBonus {
	
	private Color color;
	private int points;
	private boolean avaible;
	
	public CityColorBonus(String c, int p){
		color=Color.decode(c);
		points=p;
		avaible=true;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CityColorBonus [color=" + color + ", points=" + points + ", avaible=" + avaible + "]";
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
	/**
	 * @return the avaible
	 */
	public boolean isAvaible() {
		return avaible;
	}
	/**
	 * @param avaible the avaible to set
	 */
	public void setAvaible(boolean avaible) {
		this.avaible = avaible;
	}
}
