package it.polimi.ingsw.PS19.model.map;

import java.util.ArrayList;
import java.util.Random;

import it.polimi.ingsw.PS19.model.paramiter.CouncilColor;

public class Balcony 
{
	private ArrayList<Color> councilcolor = new ArrayList();
	private static final int NUMBER_OF_COUNCIL = 4;
	
	private AvaibleCouncillor avaiblecouncillor;
	
	public Balcony() 
	{
		this.avaiblecouncillor = new AvaibleCouncillor();
		for(int i = 0; i< NUMBER_OF_COUNCIL; i++)
		{
			CouncilColor color = randomCouncilColor();
			if(avaiblecouncillor.decrement(color) == true)
				councilcolor.add(color);
			else
				i--;
		}
	}
	
	public Balcony(AvaibleCouncillor avaiblecouncillor) 
	{
		this.avaiblecouncillor = avaiblecouncillor;
		for(int i = 0; i< NUMBER_OF_COUNCIL; i++)
		{
			Color color = avaiblecouncillor.get
			if(avaiblecouncillor.decrement(color) == true)
				councilcolor.add(color);
			else
				i--;
		}
	}
	
	public boolean setNewCouncill(CouncilColor color) 
	{
		if(avaiblecouncillor.decrement(color) == false)
			return false;
		avaiblecouncillor.increment(shiftRight());
		councilcolor.set(0,color);
		return true;
	}
	
	private CouncilColor shiftRight() 
	{
	    //make temp variable to hold last element
	    CouncilColor temp = councilcolor.get(NUMBER_OF_COUNCIL-1); 
	    
	    //make a loop to run through the array list
	    for(int i = NUMBER_OF_COUNCIL-1; i > 0; i--)
	    {
	        //set the last element to the value of the 2nd to last element
	    	councilcolor.set(i,councilcolor.get(i-1)); 
	    	
	    }
	    return temp;
	}
	/**
	 * Probabilmente da spostare dentro councilcolor
	 * @return
	 */
	private CouncilColor randomCouncilColor() 
	{
	    int pick = new Random().nextInt(CouncilColor.values().length);
	    return CouncilColor.values()[pick];
	}
	
	@Override
	public String toString() 
	{
		String s="*************\n";
		for ( CouncilColor c : councilcolor) 
		{
			s = s + " " + c.toString();
		}
		s = s + avaiblecouncillor.toString();
		
		return s;
	}
	
	
	public static void main(String[] args) 
	{
		Balcony b = new Balcony();
		System.out.println(b.toString());
		b.setNewCouncill(CouncilColor.BLUE);
		System.out.println(b.toString());
		b.setNewCouncill(CouncilColor.WHITE);
		System.out.println(b.toString());
		b.setNewCouncill(CouncilColor.WHITE);
		System.out.println(b.toString());
		b.setNewCouncill(CouncilColor.PINK);
		System.out.println(b.toString());
		b.setNewCouncill(CouncilColor.WHITE);
		System.out.println(b.toString());
		b.setNewCouncill(CouncilColor.WHITE);
		System.out.println(b.toString());
		b.setNewCouncill(CouncilColor.WHITE);
		System.out.println(b.toString());
		b.setNewCouncill(CouncilColor.BLACK);
	}
	
}
