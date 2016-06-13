package it.polimi.ingsw.PS19.controller.action;

import java.awt.Color;
import java.util.List;

import it.polimi.ingsw.PS19.message.replies.DrawBusinessCardReply;
import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.card.PoliticsCard;
import it.polimi.ingsw.PS19.model.parameter.Costants;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class DrawBusinessCard implements Action 
{

	private static final int MONEY_1_CARDS = 10;
	private static final int MONEY_2_CARDS = 7;
	private static final int MONEY_3_CARDS = 4;
	private int playerId;
	private RegionType region;
	private BusinessCard card;
	private Boolean isFirstCard;
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
			//in base alle carte arrivate tolgo dei soldi al player
			//
		player.addMoney((-1)*numberOfNeedMoney() - numberOfJoker());
		
			//
			//Tolgo la carta dal mazzo, la do al player e pesco una carta dalla regione
			//
		BusinessCard selectedcard;
		if(isFirstCard)
		{
			selectedcard = model.getMap().getRegionByType(region).getFirstcard();
			model.getMap().getRegionByType(region).drowFirstCard();
		}
		else
		{
			selectedcard = model.getMap().getRegionByType(region).getSecondcard();
			model.getMap().getRegionByType(region).drowSecondCard();
		}
		model.getPlayerById(playerId).addCardToHand(selectedcard);
		model.getPlayerById(playerId).setMainActionCounter(model.getPlayerById(playerId).getMainActionCounter() - 1);
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		if(Action.checkPlayerTurn(playerId, model))
		{
			result = ActionMessages.NOT_YOUR_TURN;
			return false;
		}
		
		Player player = model.getPlayerById(playerId);
		
			/*
			 *Controllo se per ogni colore che mi è arrivato ho almeno una carta di quel colore,
			 *Se non ho nessuna carta di quel colore RETURN FALSE
			 */
		
		for (Color color : politicsCard) 
		{
			PoliticsCard tempCard = new PoliticsCard(color);
			if(!player.findPoliticsCard(tempCard))
			{
				result = ActionMessages.NOT_HAVE_POLITIC_CARD;
				return false;
			}
		}
			//Controlla se hai abbastanza MONEY in base al numero di carte che hai e in base ai joker
		int money = player.getMoney();
		
		if(money < numberOfNeedMoney() + numberOfJoker())
		{
			result = ActionMessages.NO_MONEY;
			return false;
		}
		
			//controlla se esistono queste carte dentro la regione, per adesso uso == ma probabilmente
			//non funziona quindi
		
		if(findFirstSecondCard(model))
			return true;
		result = ActionMessages.NO_BUSINESS_CARD;
		return false;
	}

	private boolean findFirstSecondCard(Model model) 
	{
		BusinessCard firstCard = model.getMap().getRegionByType(region).getFirstcard();
		BusinessCard secondCard = model.getMap().getRegionByType(region).getSecondcard();
		
		result = ActionMessages.EVERYTHING_IS_OK;
		if(firstCard.getId() ==card.getId())
		{
			isFirstCard = true;
			return true;
		}
		if(secondCard.getId() == card.getId())
		{
			isFirstCard = false;
			return true;
		}
		return false;
	}

	private int numberOfNeedMoney()
	{
		if(politicsCard.size() == 1)
			return MONEY_1_CARDS;
		if(politicsCard.size() == 2)
			return MONEY_2_CARDS;
		if(politicsCard.size() == 3)
			return MONEY_3_CARDS;
		return 0;
	}
	
	private int numberOfJoker()
	{
		int count = 0;
		for (Color color : politicsCard) 
		{
			if(color.equals(Color.decode(Costants.JOKERCOLOR)))
				count ++;
		}
		return count;
	}

	@Override
	public String getStringResult() {
		return result;
	}


	@Override
	public Reply createReplyMessage(Model model) 
	{
		return new DrawBusinessCardReply(model.getPlayer(),model.getMap().getListaRegioni(),result);
	}
}
