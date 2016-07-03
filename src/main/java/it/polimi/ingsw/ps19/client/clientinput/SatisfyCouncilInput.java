package it.polimi.ingsw.ps19.client.clientinput;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
import it.polimi.ingsw.ps19.model.map.Balcony;
import it.polimi.ingsw.ps19.model.map.Region;
import it.polimi.ingsw.ps19.model.parameter.RegionType;
import it.polimi.ingsw.ps19.model.parameter.Costants;

/**
 * Abstract class to be implemented by those inputs which require to satisfy a council
 */
public abstract class SatisfyCouncilInput extends ClientAction 
{	
	private int minimum = 0;
	
	protected List<RegionType> getAvailableRegions()
	{
		List<RegionType> availableRegions = new ArrayList<>();
		for(Region r: model.getRegions())
			if(isCouncilSatisfiable(r.getBalcony()) && !(r.getFirstcard()== null && r.getSecondcard() == null))
				availableRegions.add(r.getType());
		return availableRegions;
	}
	
	protected boolean isCouncilSatisfiable(Balcony balcony)
	{
		List<PoliticsCard> playerHand = model.getMyPlayer().getPoliticcard();
		int playerMoney = model.getMyPlayer().getMoney();
		int cost = getCost(balcony, playerHand);
		if(cost <= 11 && cost <= playerMoney)
			return true;
		return false;
	}
	
	protected boolean kingAvailable(int min)
	{
		minimum = min;
		return isCouncilSatisfiable(model.getKing().getBalcony());
	}
	
	private int getCost(Balcony balcony, List<PoliticsCard> cards)
	{
		int cost = 13;
		for(Color balconyColor : balcony.getCouncilcolor())
			for(int i = 0; i < cards.size(); i++)
			{
				
				if(balconyColor.equals(cards.get(i).getColor()))
				{
					cost -= 3;
					cards.remove(i);
					break;
				}
				if(cost == 1)
					cost = 0;
			} 
		cost -= (getJollyNumber(cards) * 2);
		return cost;
	}
	
	private int getJollyNumber(List<PoliticsCard> cards)
	{
		int jolliesNumber = 0;
		for(PoliticsCard card : cards)
			if(card.getColor().equals(Color.decode(Costants.JOKERCOLOR)))
				jolliesNumber++;	
		return jolliesNumber;
	}
	
	private PoliticsCard newJolly()
	{
		return new PoliticsCard(Color.decode(Costants.JOKERCOLOR));
	}

	protected List<PoliticsCard> getAvailablePolitics(List<Color> balcony, List<PoliticsCard> cards)
	{
		List<PoliticsCard> availableCards = new ArrayList<>();
		if(balcony.isEmpty() || cards.isEmpty())
			return availableCards;
		for(Color balconyColor : balcony)
		{
			for(int i = 0; i < cards.size(); i++)
			{
				
				if(balconyColor.equals(cards.get(i).getColor()))
				{
					availableCards.add(cards.get(i));
					cards.remove(i);
				}
			}
		}
		if(getJollyNumber(cards) >0)
			availableCards.add(newJolly());
		return availableCards;
	}
	
	protected List<Color> satisfyCouncil(Balcony balcon, ClientUI userInterface) throws InvalidInsertionException
	{
		List<Color> satisfyingColors = new ArrayList<>();
		DeckId balcony = new DeckId(balcon.getCouncilcolor());
		DeckId playerHand = new DeckId(model.getMyPlayer().getPoliticcard());
		DeckId usefulHand = getUsefulDeck(playerHand, balcony);
		int minCards = getMinimumCardsToDraw();
		List<DeckId> combinations = usefulHand.getCombination(minCards);
		for(int i = minCards +1; i <= 4; i++)
			combinations.addAll(usefulHand.getCombination(i));
		combinations = getValidDecks(combinations, balcony);
		combinations = getSignificantDecks(combinations);
		for(int i = 0; i < 4; i++)
		{
			List<Color> differentColors = getDifferentColors(combinations);
			if(i >= minCards)
				differentColors.add(null);
			Color chosenColor = userInterface.getColor(differentColors);
			if(chosenColor != null)
			{
				satisfyingColors.add(chosenColor);
				combinations = getContainingDecks(combinations, chosenColor);
				int j = 0;
				while(j < combinations.size())
				{
					DeckId deck = combinations.get(j);
					deck.subtractCard(new CardId(chosenColor));
					if(deck.getDeck().isEmpty())
						combinations.remove(deck);
					else 
						j++;
				}
				//combinations = getSignificantDecks(combinations);
			}
			if(combinations.isEmpty() || chosenColor == null)
				break;
		}
		return satisfyingColors;
	}
	
	/**
	 * returns deck without colors not present in reference deck
	 * @param mainDeck: deck to reduce
	 * @param referenceDeck: reference deck
	 * @return
	 */
	private DeckId getUsefulDeck(DeckId mainDeck, DeckId referenceDeck)
	{
		List<CardId> mainCards = mainDeck.getDeck();
		List<CardId> usefulCards = new ArrayList<>();
		for(CardId card : mainCards)
			if(referenceDeck.contains(card))
				usefulCards.add(card);
		return new DeckId(usefulCards);
	}
	
	private int getMinimumCardsToDraw()
	{
		int money = model.getMyPlayer().getMoney() - minimum;
		if(money >= 10)
			return 1;
		else if(money >= 7)
			return 2;
		else if(money >= 4)
			return 3;
		else 
			return 4;
	}
	
	/**
	 * returns only decks which are not equivalent between each others
	 * @param decks: general list of decks
	 * @return
	 */
	private List<DeckId> getSignificantDecks(List<DeckId> decks)
	{
		if(decks.isEmpty())
			return decks;
		List<DeckId> significantDecks = new ArrayList<>();
		for(int i = 0; i < decks.size(); i++)
		{
			DeckId deckComparing = decks.get(i);
			significantDecks.add(deckComparing);
			int j = i + 1;
			while(j < decks.size())
			{
				DeckId deckCompared = decks.get(j);
				if(deckComparing.equivalentNotJoker(deckCompared))
					decks.remove(deckCompared);
				else
					j++;
			}
		}
		return significantDecks;
	}
	
	/**
	 * Returns only the decks that satisfy the reference decks;
	 * @param decks: decks to be validated
	 * @param referenceDeck: Balcony deck
	 * @return
	 */
	private List<DeckId> getValidDecks(List<DeckId> decks, DeckId referenceDeck)
	{
		List<DeckId> validDecks = new ArrayList<>();
		for(DeckId deck : decks)
			if(deck.equivalentOrContained(referenceDeck) && deck.isCheaper(model.getMyPlayer().getMoney()))
				validDecks.add(deck);
		return validDecks;
	}
	
	private List<DeckId> getContainingDecks(List<DeckId> decks, Color color)
	{
		if(decks.isEmpty())
			return decks;
		CardId card = new CardId(color);
		List<DeckId> containingDecks = new ArrayList<>();
		for(DeckId deck : decks)
			if(deck.containsNotJoker(card))
				containingDecks.add(deck);
		return containingDecks;
	}
	
	private List<Color> getDifferentColors(List<DeckId> decks)
	{
		List<CardId> cards = new ArrayList<>();
		if(decks.isEmpty())
			return new ArrayList<>();
		List<CardId> differentCards = new ArrayList<>();
		for(DeckId deck : decks)
			cards.addAll(deck.getDeckClone());
		while(!cards.isEmpty())
		{
			CardId card = cards.get(0);
			differentCards.add(card);
			int j = 0;
			while(j < cards.size())
			{
				CardId listCard = cards.get(j);
				if(card.equivalentNotJolly(listCard))
					cards.remove(listCard);
				else
					j++;
			}
		}
		List<Color> differentColors = new ArrayList<>();
		for(CardId card : differentCards)
			differentColors.add(card.getColor());
		return differentColors;
	}
	
	/**
	 * private class to properly manage cards
	 */
	public class CardId
	{
		private Color color;
		
		/**
		 * constructor
		 * @param c: color
		 */
		public CardId(Color c)
		{
			color = c;
		}
		
		/**
		 * Verifies that the passed card has the same colors as caller's
		 * @param card
		 * @return
		 */
		public boolean equivalentNotJolly(CardId card) 
		{
			if(card != null && card.getColor().equals(color))
				return true;
			return false;
		}

		public Color getColor()
		{
			return color;
		}
		
		public boolean isJoker()
		{
			if(color.equals(Color.decode(Costants.JOKERCOLOR)))
				return true;
			return false;
		}
				
		/**
		 * Verifies that the passed card is joker or it has the same colors as caller's
		 * @param cardId
		 * @return
		 */
		public boolean equivalent(CardId cardId)
		{
			if(color.equals(cardId.getColor()) || cardId.getColor().equals(Color.decode(Costants.JOKERCOLOR)))
				return true;
			return false;
		}
	}
	
	private class DeckId
	{
		List<CardId> cardsId = new ArrayList<>();
		
		/**
		 * Constructor
		 * @param cards: list of cards/cardiID or colors to be converted in deck
		 */
		@SuppressWarnings("unchecked")
		public DeckId(List<?> cards)
		{
			if(cards.isEmpty())
				return;
			else if(cards.get(0) instanceof CardId)
			{
				cardsId = new ArrayList<>();
				cardsId.addAll((ArrayList<CardId>)cards);
			}
			else if(cards.get(0) instanceof Color)
			{
				cardsId = new ArrayList<>();
				for(int i = 0; i < cards.size(); i++)
					cardsId.add(new CardId((Color)(cards.get(i))));
			}
			else if(cards.get(0) instanceof PoliticsCard)
			{
				cardsId = new ArrayList<>();
				for(int i = 0; i < cards.size(); i++)
					cardsId.add(new CardId(((PoliticsCard)(cards.get(i))).getColor()));
			}
		}
		
		/**
		 * returns a reference to the list of cards in the deck
		 * @return
		 */
		public List<CardId> getDeck()
		{
			return cardsId;
		}
		
		/**
		 * returns a clone of the contained deck
		 * @return
		 */
		public List<CardId> getDeckClone()
		{
			List<CardId> clone = new ArrayList<>();
			if(!cardsId.isEmpty())
				clone.addAll(cardsId);
			return clone;
		}

		
		/**
		 * Returns whether the deck calling is equivalent or contained in the parameter
		 * @param deck: Containing deck;
		 * @return
		 */
		public boolean equivalentOrContained(DeckId deck)
		{
			List<CardId> secondDeck = deck.getDeckClone();
			List<CardId> firstDeck = this.getDeckClone();
			while(!firstDeck.isEmpty())
			{
				CardId cardOne = firstDeck.get(0);
				int j = 0;
				boolean found = false;
				while(j < secondDeck.size() && !found)
				{
					CardId cardTwo = secondDeck.get(j);
					if(cardTwo.equivalent(cardOne))
					{
						firstDeck.remove(cardOne);
						secondDeck.remove(cardTwo);
						found = true;
					}
					else
					{
						j++;
					}
				}
				if(!found)
					return false;
			}
			return true;
		}
		
		/**
		 * Returns whether the deck calling is equivalent or contained in the parameter
		 * @param deck: Containing deck;
		 * @return
		 */
		public boolean equivalentNotJoker(DeckId deck)
		{
			List<CardId> secondDeck = deck.getDeckClone();
			List<CardId> firstDeck = this.getDeckClone();
			if(firstDeck.size() != secondDeck.size())
				return false;
			while(!firstDeck.isEmpty())
			{
				CardId cardOne = firstDeck.get(0);
				int j = 0;
				boolean found = false;
				while(j < secondDeck.size() && !found)
				{
					CardId cardTwo = secondDeck.get(j);
					if(cardTwo.equivalentNotJolly(cardOne))
					{
						firstDeck.remove(cardOne);
						secondDeck.remove(cardTwo);
						found = true;
					}
					else
					{
						j++;
					}
				}
				if(!found)
					return false;
			}
			return true;
		}
		
		/**
		 * Returns whether the deck calling is equivalent or contains the parameter
		 * @param deck: Contained deck
		 * @return
		 */
		@SuppressWarnings("unused")
		public boolean equivalentOrContaining(DeckId deck)
		{
			return deck.equivalentOrContained(this);
		}
		
		/**
		 * returns whether the deck contains a card equivalent to the parameter
		 * @param card
		 * @return
		 */
		public boolean contains(CardId card)
		{
			for(CardId deckCard : cardsId)
				if(deckCard.equivalent(card))
					return true;
			return false;
		}
		
		/**
		 * returns whether the deck contains a card equivalent to the parameter and not joker
		 * @param card
		 * @return
		 */
		public boolean containsNotJoker(CardId card)
		{
			for(CardId deckCard : cardsId)
				if(card.equivalentNotJolly(deckCard))
					return true;
			return false;
		}
		
		/**
		 * returns a list of decks with all the possible combinations of n cards of the deck;
		 * @param n: number of cards in combination
		 * @return 
		 */
		public List<DeckId> getCombination(int n)
		{
			List<DeckId> combinations = new ArrayList<>();
			if(n >= cardsId.size())
			{
				combinations.add(this);
				return combinations;
			}
			int[] s = new int[n];
		    for (int i = 0; (s[i] = i) < n - 1; i++);  // first creates the index sequence: 0, 1, 2, ...
		    combinations.add(getSubset(this, s));
		    while(true)
		    {
		        int i;
		        // find position of item that can be incremented
		        for (i = n - 1; i >= 0 && s[i] == cardsId.size() - n + i; i--); 
		        if (i < 0)
		            break;
		        else 
		        {
		            s[i]++;                    // increment this item
		            for (++i; i < n; i++)	   // fill up remaining items
		                s[i] = s[i - 1] + 1; 
		            combinations.add(getSubset(this, s));
		        }
			}
			return combinations;
		}
		
		/**
		 * Removes the first card of the same color as the parameter's
		 * @param card
		 */
		public void subtractCard(CardId card)
		{
			if(!contains(card))
				return;
			for(int i = 0; i < cardsId.size(); i++)
			{
				CardId deckCard = cardsId.get(i);
				if(deckCard.equivalentNotJolly(card))
				{
					cardsId.remove(deckCard);
					return;
				}
			}
		}
		
		/**
		 * returns whether this deck costs less than the parameter
		 * @param money
		 * @return
		 */
		public boolean isCheaper(int money)
		{
			int cost = 10;
			cost -= (cardsId.size() * 3);
			if(cost == 1)
				cost = 0;
			for(CardId card : cardsId)
				if(card.isJoker())
					cost++;
			if(cost <= money)
				return true;
			return false;
		}
		
		@Override
		public String toString()
		{
			String s = "[";
			for(CardId card : cardsId)
			{
				s = s.concat("#" + Integer.toHexString(card.getColor().getRGB()).substring(2).toUpperCase() + ", ");
			}
			s += "]";
			return s;
		}
	
 		private DeckId getSubset(DeckId input, int[] subset) {
			List<CardId> result = new ArrayList<>();
		    for (int i = 0; i < subset.length; i++) 
		        result.add(i, input.getDeck().get(subset[i]));
		    return new DeckId(result);
		}
	}
}

