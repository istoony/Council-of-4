package it.polimi.ingsw.ps19.controller.action;

import it.polimi.ingsw.ps19.message.replies.ChangeKingPositionReply;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.map.Region;


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
		City real = getRealCity(model);
		if(real==null){
			result = ActionMessages.GENERIC_ERROR;
			return false;
		}
		int helperscost = real.calculateMalusEmporium();
		int moneycost = JUMPCOST*(model.getMap().calculateShorterPath(model.getMap().getKing().getCurrentcity(), city).size()-1);
		
		model.getMap().getKing().setCurrentcity(real);
		real.buildEmporium(model.getPlayerById(playerId));
		model.getPlayerById(playerId).setHelpers(model.getPlayerById(playerId).getHelpers()-helperscost);
		model.getPlayerById(playerId).setMoney(model.getPlayerById(playerId).getMoney()-moneycost);
		result = ActionMessages.EVERYTHING_IS_OK;
		return true;
	}

	@Override
	public Boolean isPossible(Model model) {
		if(model.getPlayerById(playerId).getMaxemporia()==0){
			result = ActionMessages.NO_BUILD;
			return false;
		}
		
		int requiredmoney = JUMPCOST*(model.getMap().calculateShorterPath(model.getMap().getKing().getCurrentcity(), city).size()-1);
		if(model.getPlayerById(playerId).getMoney()>=requiredmoney){
			City real = getRealCity(model);
			if(real==null){	
				result = ActionMessages.GENERIC_ERROR;
				return false;
			}
			if(real.getEmporia().contains((Integer)playerId)){
				result = ActionMessages.BUILD_EMPORIA;
				return false;
			}
			else if(real.calculateMalusEmporium()>model.getPlayerById(playerId).getHelpers()){
				result = ActionMessages.NO_HELPERS;
				return false;
			}
			result = ActionMessages.EVERYTHING_IS_OK;
			return true;
		}
		result = ActionMessages.NO_MONEY;
		return false;
	}
	
	private City getRealCity(Model m){
		for(Region r : m.getMap().getListaRegioni()){
			for(City c : r.getCities()){
				if(c.equals(city)){
					return c;
				}
			}
		}
		return null;
	}

	@Override
	public String getStringResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reply createReplyMessage(Model model) 
	{
		return new ChangeKingPositionReply(model.getPlayer(), model.getMap().getKing(), result, model.getCurrentState().getPlayerTurnId());
	}

}
