package it.polimi.ingsw.PS19.controller.action;

import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.map.City;


public class ChangeKingPosition implements Action {

	int playerId;
	City city;
	String result; //vedi l'interfaccia action, per ogni errore metti in questa stringa il perchè dell'errore
	private static final int JUMPCOST = 2;
	
	public ChangeKingPosition(int id, City c) //occhio che C non avrà lo stesso puntatore alla città salvata nel
											//model, quidi conviene confrontare gli ID per muovere il re
	{
		playerId = id;
		city = c;
	}
	
	@Override
	public Boolean execute(Model model) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isPossible(Model model) {
		int requiredmoney = JUMPCOST*(model.getMap().calculateShorterPath(model.getMap().getKing().getCurrentcity(), city).size()-1);
		if(model.getPlayerById(playerId).getMoney()>=requiredmoney){
			if(city.getEmporia().contains((Integer)playerId)){
				result = ActionMessages.BUILD_EMPORIA;
				return false;
			}
			result = ActionMessages.EVERYTHING_IS_OK;
			return true;
		}
		result = ActionMessages.NO_MONEY;
		return false;
	}

	@Override
	public String getStringResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reply createReplyMessage(Model model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void checkAlreadyTurn() {
		// TODO Auto-generated method stub

	}

}
