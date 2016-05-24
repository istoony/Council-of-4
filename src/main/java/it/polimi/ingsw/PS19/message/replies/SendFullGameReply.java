package it.polimi.ingsw.PS19.message.replies;

import it.polimi.ingsw.PS19.message.Message;
import it.polimi.ingsw.PS19.model.Model;

public class SendFullGameReply extends Message 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4734240442883203509L;

	private Model model;
	public SendFullGameReply(Model model) 
	{
		this.model = model;
	}
	
	public Model getModel() 
	{
		return model;
	}
	
	
	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return null;
	}

}
