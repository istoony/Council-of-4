package it.polimi.ingsw.PS19.controller.action;

import java.awt.Color;
import java.util.List;

import it.polimi.ingsw.PS19.message.replies.ChangeKingPositionReply;
import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.card.PoliticsCard;
import it.polimi.ingsw.PS19.model.map.City;
import it.polimi.ingsw.PS19.model.map.Region;
import it.polimi.ingsw.PS19.model.parameter.Costants;


public class ChangeKingPosition extends SupportMethod implements Action{

	int playerId;
	City city;
	String result; //vedi l'interfaccia action, per ogni errore metti in questa stringa il perchè dell'errore
	List<Color> politicCard;
	
	public ChangeKingPosition(int id, City c, List<Color> politic) //occhio che C non avrà lo stesso puntatore alla città salvata nel
											//model, quidi conviene confrontare gli ID per muovere il re
	{
		playerId = id;
		city = c;
		politicCard = politic;
		
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
		int moneycost = Costants.JUMPCOST*(model.getMap().calculateShorterPath(model.getMap().getKing().getCurrentcity(), city).size()-1);
		
		model.getMap().getKing().setCurrentcity(real);
		real.buildEmporium(model.getPlayerById(playerId));
		model.getPlayerById(playerId).setHelpers(model.getPlayerById(playerId).getHelpers()-helperscost);
		model.getPlayerById(playerId).setMoney(model.getPlayerById(playerId).getMoney()-moneycost);
		result = ActionMessages.EVERYTHING_IS_OK;
		return true;
	}

	@Override
	public Boolean isPossible(Model model) {
		if(model.getPlayerById(playerId).getMaxemporia()==0)
		{
			result = ActionMessages.NO_BUILD;
			return false;
		}
		Player player = model.getPlayerById(playerId);
		
		for(int i = 0; i < politicCard.size(); i++)
		{
			PoliticsCard p = new PoliticsCard(politicCard.get(i));
			player.removeCardToHand(p);
			model.getMap().getPoliticdeck().addToDeck(p);
		}
			//
			//in base alle carte arrivate tolgo dei soldi al player
			//
		player.addMoney((-1)*numberOfNeedMoney(politicCard) - numberOfJoker(politicCard));
		
		int requiredmoney = Costants.JUMPCOST*(model.getMap().calculateShorterPath(model.getMap().getKing().getCurrentcity(), city).size()-1);
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
	
	private City getRealCity(Model m)
	{
		for(Region r : m.getMap().getListaRegioni()){
			for(City c : r.getCities()){
				if(c.getId() == city.getId()){
					return c;
				}
			}
		}
		return null;
	}

	@Override
	public String getStringResult() {
		return result;
	}

	@Override
	public Reply createReplyMessage(Model model) 
	{
		return new ChangeKingPositionReply(model.getPlayer(), model.getMap().getKing(), result, model.getCurrentState().getPlayerTurnId());
	}

}
