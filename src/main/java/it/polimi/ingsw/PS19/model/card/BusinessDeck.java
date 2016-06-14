package it.polimi.ingsw.PS19.model.card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BusinessDeck implements Deck, Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3125331114395001758L;
	private static final int FIRST_CARD = 0;
	private List<BusinessCard> card;
	
	public BusinessDeck() {
		card = new ArrayList<>();
	}
	
	@Override
	public BusinessCard getFirstCard()
	{
		BusinessCard singlecard = card.get(FIRST_CARD);
		card.remove(FIRST_CARD);
		return singlecard;
	}
	@Override
	public void addToDeck(Card card) 
	{
		this.card.add((BusinessCard) card);
	}
	public void addToDeckRandom(BusinessCard c)
	{
		Random r = new Random();
		int number = r.nextInt(card.size());
		
		BusinessCard temp = card.get(number);
		card.set(number, c);
		
		for(int i = number + 1 ; i<card.size() - 1; i++)
		{
			c = card.get(i);
			card.set(i, temp);
			temp = card.get(i + 1);
		}
		card.add(temp);
		
	}
	public List<BusinessCard> getCard() {
		return card;
	}
	
	@Override
	public String toString() {
		String s = "\n+++++++++++++\n";
		
		for (Card c : card) 
		{
			s = s + c.toString() + "  ";
		}
		return s;
	}

	@Override
	public void shuffle() 
	{
		Collections.shuffle(card);
	}
	
	protected void setCardsId()
	{
		int i = 0;
		for (BusinessCard businessCard : card) 
		{
			businessCard.setId(i);
			i++;
		}
	}

	@Override
	public Card addToDeck(int position) {
		// TODO Auto-generated method stub
		return null;
	}

}
