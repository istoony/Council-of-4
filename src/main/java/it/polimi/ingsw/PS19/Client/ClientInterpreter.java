package it.polimi.ingsw.PS19.Client;

import java.util.Observable;
import java.util.Observer;

public class ClientInterpreter extends Observable implements Observer
{

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		notifyObservers(arg1);
	}

}
