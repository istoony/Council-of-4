package it.polimi.ingsw.ps19.controller.action;

import java.awt.Color;
import java.util.List;

import it.polimi.ingsw.ps19.controller.support.ActionMessages;
import it.polimi.ingsw.ps19.controller.support.SupportMethod;
import it.polimi.ingsw.ps19.message.replies.DrawBusinessCardReply;
import it.polimi.ingsw.ps19.message.replies.Reply;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

public class DrawBusinessCard implements Action 
{

	private int playerId;
	private RegionType region;
	private BusinessCard card;
	private List<Color> politicsCard;
	
	private String result;
	
	public DrawBusinessCard(int id, RegionType r, BusinessCard c, List<Color> politicsCard) 
	{
		playerId = id;
		region = r;
		card = c;
		this.politicsCard = politicsCard;
	}
	
	@Override
	public Boolean execute(Model model) 
	{		
		Player player = model.getPlayerById(playerId);
			//
			//Per ogni carta arrivata dal Messaggio rimuovo la carta dal player e la aggiungo alla
			//fine del mazzo
			//
		for(int i = 0; i < politicsCard.size(); i++)
		{
			PoliticsCard p = new PoliticsCard(politicsCard.get(i));
			player.removeCardToHand(p);
			model.getMap().getPoliticdeck().addToDeck(p);
		}
			//
			//in base alle carte arrivate tolgo dei soldi al player tenendo conto dei joker
			//
		player.setMoney(player.getMoney() - SupportMethod.numberOfNeedMoney(politicsCard) - SupportMethod.numberOfJoker(politicsCard));
		
			//
			//Tolgo la carta dal mazzo, la do al player e pesco una carta dalla regione
			//
		BusinessCard selectedcard = SupportMethod.removeCardFromRegionAndAddToPlayer(model, player, card, region);
		
			//
			//Assegno i bonus della carta, dopo ogni bonus assegnato controllo avanzamenti sul percorso della
			//nobiltà, se ho bonus da applicare applico anche quelli.
			//		
		SupportMethod.giveListOfBonus(model, player, selectedcard.getBonus());
		
		player.setMainActionCounter(player.getMainActionCounter() - SupportMethod.N_OF_ACTION_TO_ADD);
		result = ActionMessages.EVERYTHING_IS_OK;
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		if(!SupportMethod.checkPlayerTurnAndAction(model, playerId, SupportMethod.MAIN_ACTION))
		{
			result = ActionMessages.GENERIC_ERROR;
			return false;
		}
		
		Player player = model.getPlayerById(playerId);
		
			/*
			 *Controllo se per ogni colore che mi è arrivato ho almeno una carta di quel colore,
			 *Se non ho nessuna carta di quel colore RETURN FALSE
			 */
		
		if(!(SupportMethod.findPoliticCard(politicsCard, player)))
		{
			result = ActionMessages.NO_MONEY;
			return false;
		}
			
			//Controlla se hai abbastanza MONEY in base al numero di carte che hai e in base ai joker
		int money = player.getMoney();
		
		if(money < SupportMethod.numberOfNeedMoney(politicsCard) + SupportMethod.numberOfJoker(politicsCard))
		{
			result = ActionMessages.NO_MONEY;
			return false;
		}
		
		if(SupportMethod.findExistBusinessCard(model,region,card))
		{
			result = ActionMessages.EVERYTHING_IS_OK;
			return true;
		}
		result = ActionMessages.NO_BUSINESS_CARD;
		return false;
	}

	@Override
	public Reply createReplyMessage(Model model) 
	{
		return new DrawBusinessCardReply(model.getCurrentState().getPlayerTurnId(), result,
				model.getPlayer(), model.getMap().getRegionList());
	}
}
