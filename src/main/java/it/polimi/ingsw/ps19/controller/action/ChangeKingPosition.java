package it.polimi.ingsw.ps19.controller.action;

import java.awt.Color;
import java.util.List;

import it.polimi.ingsw.ps19.controller.support.ActionMessages;
import it.polimi.ingsw.ps19.controller.support.SupportMethod;
import it.polimi.ingsw.ps19.message.replies.ChangeKingPositionReply;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.parameter.Costants;


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
		City real = getRealCity(model, city);
		if(real==null){

			result = ActionMessages.GENERIC_ERROR;
			return false;
		}
		
		Player player = model.getPlayerById(playerId);
		
		removeCardToHand(model, player, politicCard);
			//
			//in base alle carte arrivate tolgo dei soldi al player, non tolgo ancora i soldi
			//dovuti al movimento del re
			//
		player.setMoney(player.getMoney() - numberOfNeedMoney(politicCard) - numberOfJoker(politicCard));
		
			//
			//in base agli empori costruiti calcolo gli helper necessari
			//Calcolo anche il costo del movimento del re
			//
		int helperscost = real.calculateMalusEmporium();
		int moneycost = 0;
		if(real.getId() != model.getMap().getKing().getCurrentcity().getId())
			moneycost = Costants.JUMPCOST*(Costants.calculateShorterPath(model.getMap().getKing().getCurrentcity(), 
				city, model.getMap().getRegionList()).size() - 1);
		
			//Sposto il re nella città nuova
		model.getMap().getKing().setCurrentcity(real);
			//costruisco l'emporio sulla città
		real.buildEmporium(model.getPlayerById(playerId));
			//aggiungo la città ai miei empori
		model.getPlayerById(playerId).addToMyEmporia(real);
		
			//aggiorno tutti i parametri del giocatore
		model.getPlayerById(playerId).setHelpers(model.getPlayerById(playerId).getHelpers()-helperscost);
		model.getPlayerById(playerId).setMoney(model.getPlayerById(playerId).getMoney()-moneycost);
			
			//do al giocatore i bonus che gli spettano
		giveBonusToPlayer(model, findRegion(model, real), player, real.getId());
				
		model.getPlayerById(playerId).setMainActionCounter(model.getPlayerById(playerId).getMainActionCounter() - SupportMethod.N_OF_ACTION_TO_ADD);
		result = ActionMessages.EVERYTHING_IS_OK;
		checkPlayerVictory(model, player, result);
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		if(!SupportMethod.checkPlayerTurnAndAction(model,playerId, result, SupportMethod.MAIN_ACTION))
			return false;
		if(model.getPlayerById(playerId).getMaxemporia()==0)
		{
			result = ActionMessages.NO_BUILD;
			return false;
		}
		Player player = model.getPlayerById(playerId);
		if(!(findPoliticCard(politicCard, player)))
		{
			result = ActionMessages.NO_MONEY;
			return false;
		}
		
		int requiredmoney =  Costants.JUMPCOST*(Costants.calculateShorterPath(model.getMap().getKing().getCurrentcity(), 
				city, model.getMap().getRegionList()).size() - 1);
		requiredmoney += numberOfNeedMoney(politicCard) + numberOfJoker(politicCard);
		
		if(model.getPlayerById(playerId).getMoney()>=requiredmoney)
		{
			City real = getRealCity(model, city);
			if(real==null)
			{	
				result = ActionMessages.GENERIC_ERROR;
				return false;
			}
			if(real.getEmporia().contains((Integer)playerId))
			{
				result = ActionMessages.BUILD_EMPORIA;
				return false;
			}
			else if(real.calculateMalusEmporium()>model.getPlayerById(playerId).getHelpers())
			{
				result = ActionMessages.NO_HELPERS;
				return false;
			}
			result = ActionMessages.EVERYTHING_IS_OK;
			return true;
		}
		result = ActionMessages.NO_MONEY;
		return false;
	}
	
	@Override
	public Reply createReplyMessage(Model model) 
	{
		return new ChangeKingPositionReply(model.getCurrentState().getPlayerTurnId(), result, 
				model.getPlayer(), model.getMap().getKing());
	}

}
