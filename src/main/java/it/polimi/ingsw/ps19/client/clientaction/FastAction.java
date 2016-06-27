package it.polimi.ingsw.ps19.client.clientaction;

import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.client.language.Language;

/**
 * Class that contains the fast actions
 */
public class FastAction extends ClientActionChooser 
{
	
	/**
	 * Constructor
	 * @param m
	 */
	public FastAction(ClientModel m) 
	{
		super(m);
		actions.add(new RedrawBusinessCardInput(model));
		actions.add(new BuyHelperInputs(model));
		actions.add(new ElectCouncillorInputs(model, false));
		actions.add(new BuyMainActionInput(model));	
		actions.add(new EndTurnInput(model));
	}
	
	@Override
	public boolean isPossible() 
	{
		if(model.getMyPlayer().getFastActionCounter() > 0)
			return true;
		return false;
	}

	@Override
	public String toString(Language l)
	{
		return l.getString(this);
	}
	

}
