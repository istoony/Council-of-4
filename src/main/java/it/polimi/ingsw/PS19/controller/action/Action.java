package it.polimi.ingsw.PS19.controller.action;

import java.io.Serializable;

import it.polimi.ingsw.PS19.model.Model;

public interface Action extends Serializable
{

	public Boolean execute(Model model);
	
	public Boolean isPossible(Model model);
	

}
