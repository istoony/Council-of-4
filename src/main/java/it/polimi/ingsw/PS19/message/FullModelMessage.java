package it.polimi.ingsw.PS19.message;

import it.polimi.ingsw.PS19.model.Model;

public class FullModelMessage extends Message 
{
	private static final long serialVersionUID = -1404243999374556411L;
	public Model model;
	
	public FullModelMessage(Model m) 
	{
		model = m;
	}
	
	public Model getModel()
	{
		return model;
	}
	
	@Override
	public String getString() 
	{
		return null;
	}

}
