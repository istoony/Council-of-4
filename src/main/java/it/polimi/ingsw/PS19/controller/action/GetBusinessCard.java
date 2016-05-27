package it.polimi.ingsw.PS19.controller.action;

import java.awt.Color;
import java.util.ArrayList;

import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.card.PoliticsCard;
import it.polimi.ingsw.PS19.model.parameter.Costants;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

public class GetBusinessCard implements Action 
{

	private static final int MONEY_1_CARDS = 10;
	private static final int MONEY_2_CARDS = 7;
	private static final int MONEY_3_CARDS = 4;
	int playerId;
	RegionType region;
	BusinessCard card;
	Boolean isFirstCard;
	ArrayList<PoliticsCard> politicsCard;
	
	public GetBusinessCard(int id, RegionType r, BusinessCard c, ArrayList<PoliticsCard> politicsCard) 
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
		for (PoliticsCard p : politicsCard)
		{
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
		else if(!isFirstCard)
		{
			selectedcard = model.getMap().getRegionByType(region).getSecondcard();
			model.getMap().getRegionByType(region).drowSecondCard();
		}
		else 
			return false;
		model.getPlayerById(playerId).addCardToHand(selectedcard);
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		Player player = model.getPlayerById(playerId);
			//controlla se tutte le carte che gli arrivano dal client sono presenti nella mano del player
			//se non è presente una carta ritorna false e non fa l'azione, potremmo cambiarlo in qualcosa di
			//più sofisticato che conta le carte presenti e se hai abbastanza soldi allora te la lascia fare
			//comunque. Per adesso lasciamolo così poi vedremo.
		for (PoliticsCard pc : politicsCard) 
			if(!player.findPoliticsCard(pc))
				return false;
			//Controlla se hai abbastanza MONEY in base al numero di carte che hai e in base ai joker
		int money = player.getMoney();
		
		if(money < numberOfNeedMoney() + numberOfJoker())
			return false;
		
			//controlla se esistono queste carte dentro la regione, per adesso uso == ma probabilmente
			//non funziona quindi
		
		BusinessCard firstCard = model.getMap().getRegionByType(region).getFirstcard();
		BusinessCard secondCard = model.getMap().getRegionByType(region).getSecondcard();
		
		if(firstCard == card)
		{
			isFirstCard = true;
			return true;
		}
		if(secondCard == card)
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
		for (PoliticsCard p : politicsCard) 
		{
			if(p.getColor().equals(Color.decode(Costants.JOKERCOLOR)))
				count ++;
		}
		return count;
	}
}
