package it.polimi.ingsw.PS19.controller;

import java.util.Observable;
import java.util.Observer;

import it.polimi.ingsw.PS19.model.Model;

public class GameController implements Observer
{
	private Model model;
	private int numberofplayer;
	
	
	public GameController() 
	{
		model = new Model(2);
		
	}
	public void update(Observable arg0, Object arg1) 
	{
		System.out.println(arg1.getClass().toString());
	}

}
