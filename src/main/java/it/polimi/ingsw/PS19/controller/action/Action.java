package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.model.Model;

public interface Action
{
	public static final String NOT_YOUR_TURN = "It's not Your Turn";
	public static final String POLITIC_DECK_IS_OVER = "Politic deck is over";
	public static final String EVERYTHING_IS_OK = "Everything is OK!";
	public static final String NO_MONEY = "You don't have enough money!";
	public static final String NO_HELPERS = "You don't have enough helpers!";
	public static final String MAIN_ACTION = "You have to play one main action";
	public static final String NOT_HAVE_POLITIC_CARD = "You haven't this politic card";
	public static final String NO_BUSINESS_CARD = "Can't find business card";	
	public static final String BUILD_EMPORIA = "You already have an emporium in this city";
	public static final String GENERIC_ERROR = "ERROR!";
	
	

	public Boolean execute(Model model);
	
	public Boolean isPossible(Model model);
	
	public String getStringResult();
	
<<<<<<< Upstream, based on branch 'master' of https://bitbucket.org/CoF_ps19/ps19
	public Reply createReplyMessage(Model model);
=======
	public void createReplyMessage();
>>>>>>> 0ccb890 Refactor Messages
	
	public void checkAlreadyTurn();
	
	public Reply createReplyMessage(Model model);
	
	public void checkAlreadyTurn();
	
	public static Boolean checkPlayerTurn(int id1, Model m)
	{
		return id1 != m.getCurrentState().getPlayerTurnId();
	}
	

}
