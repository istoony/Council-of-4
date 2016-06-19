package it.polimi.ingsw.PS19.client.clientaction;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.PS19.client.ClientUI;
import it.polimi.ingsw.PS19.exceptions.clientexceptions.InvalidInsertionException;
import it.polimi.ingsw.PS19.model.card.PoliticsCard;
import it.polimi.ingsw.PS19.model.map.Balcony;
import it.polimi.ingsw.PS19.model.map.Region;
import it.polimi.ingsw.PS19.model.parameter.RegionType;
import it.polimi.ingsw.PS19.model.parameter.Costants;

/**
 * Abstract class to be implemented by those inputs which require to satisfy a council
 */
public abstract class SatisfyCouncilInput extends ClientAction 
{	
	protected List<RegionType> getAvailableRegions()
	{
		List<RegionType> availableRegions = new ArrayList<>();
		for(Region r: model.getRegions())
		{
			
			if(getCost(r.getBalcony(), model.getMyPlayer().getPoliticcard()) <= 10 && getCost(r.getBalcony(), model.getMyPlayer().getPoliticcard()) <= model.getMyPlayer().getMoney())
				availableRegions.add(r.getType());
		}
		return availableRegions;
	}
	
	protected boolean kingAvailable()
	{
		if(getCost(model.getKing().getBalcony(), model.getMyPlayer().getPoliticcard()) <= 10 && getCost(model.getKing().getBalcony(), model.getMyPlayer().getPoliticcard()) <= model.getMyPlayer().getMoney())
			return true;
		return false;
	}
	
	private int getCost(Balcony balcony, List<PoliticsCard> cards)
	{
		int cost = 13;
		List<Boolean> catched = new ArrayList<>();
		for(int j = 0; j < balcony.getCouncilcolor().size(); j++)
		{
			catched.add(false);
			Color balconyColor = balcony.getCouncilcolor().get(j); 
			for(int i = 0; i < cards.size(); i++)
			{
				
				if(balconyColor.equals(cards.get(i).getColor()))
				{
					cost -= 3;
					catched.set(j, true);
					cards.remove(i);
					break;
				}
				if(cost == 1)
					cost = 0;
			}
		}
		cost -= getJollyNumber(cards) * 2; 
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
	
	protected List<Color> satisfyCouncil(Balcony balcon, ClientUI UserInterface) throws InvalidInsertionException
	{
		List<Color> satisfyingColors = new ArrayList<>();
		DeckId balcony = new DeckId(balcon.getCouncilcolor());
		DeckId playerHand = new DeckId(model.getMyPlayer().getPoliticcard());
		DeckId usefulHand = getUsefulDeck(playerHand, balcony);
		int minCards = getMinimumCardsToDraw();
		List<DeckId> combinations = usefulHand.getCombination(minCards);
		for(int i = minCards +1; i < 4; i++)
			combinations.addAll(usefulHand.getCombination(i));
		for(int i = 0; i < 4; i++)
		{
			combinations = getValidDecks(combinations, balcony);
			List<Color> differentColors = getDifferentColors(combinations);
			if(i >= minCards-1)
				differentColors.add(null);
			Color chosenColor = UserInterface.getColor(differentColors);
			if(chosenColor == null)
				break;
			combinations = getContainingDecks(combinations, chosenColor);
			for(DeckId deck : combinations)
				deck.subtractCard(new CardId(chosenColor, 0));
			combinations = getSignificantDecks(combinations);
			if(combinations.isEmpty())
				break;
			satisfyingColors.add(chosenColor);
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
		List<CardId> mainCards = mainDeck.getDeckClone();
		for(CardId card : mainCards)
		{
			if(!referenceDeck.contains(card))
				mainCards.remove(card);
		}
		return new DeckId(mainCards);
	}
	/*
	 * Metodo che fa tutto
	 * Deve capire quante carte minimo
	 * trova le combinazioni con quel numero di carte e more
	 * prende le combinazioni valide
	 * Trova i diversi colori
	 * ne fa scegliere uno
	 * si salva la carta
	 * Toglie la carta dal deck del balcone
	 * ricomincia da capo considerando solo le soluzioni contenenti quella carta
	 * valuta se può smettere
	 * 
	 * Per i jokers da pensare una minima
	 */
	
	/*
	protected List<Color> satisfyCouncil(Balcony balcon, ClientUI userInterface) throws InvalidInsertionException
	{
		List<Color> colors = new ArrayList<>();
		int count = 0;
		List<Color> balcony = balcon.getCouncilcolor();
		List<PoliticsCard> politicCards = model.getMyPlayer().getPoliticcard();
		PoliticsCard politicCard;
		politicCards = getAvailablePolitics(balcony, politicCards);
		while(count < 4 && !politicCards.isEmpty())
		{
			if(count > getMinimumCardsToDraw())
				politicCards.add(null);
			politicCard = userInterface.getPolitic(politicCards);
			if(politicCard == null)
				break;
			if(count > getMinimumCardsToDraw())
				politicCards.remove(politicCards.size()-1);
			balcony.remove(politicCard.getColor());
			colors.add(politicCard.getColor());
			politicCards.remove(politicCard);
			count++;
			politicCards = getAvailablePolitics(balcony, politicCards);
		}
		return colors;
	}*/
	
	private int getMinimumCardsToDraw()
	{
		int money = model.getMyPlayer().getMoney();
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
		for(DeckId deckComparing : decks)
		{
			significantDecks.add(deckComparing);
			for(DeckId deckCompared : decks)
				if(deckComparing.equivalent(deckCompared))
					decks.remove(deckCompared);
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
			if(deck.equivalentOrContained(referenceDeck))
				validDecks.add(deck);
		return validDecks;
	}
	
	private List<DeckId> getContainingDecks(List<DeckId> decks, Color color)
	{
		if(decks.isEmpty())
			return decks;
		CardId card = new CardId(color, 0);
		List<DeckId> containingDecks = new ArrayList<>();
		for(DeckId deck : decks)
			if(deck.contains(card))
				containingDecks.add(deck);
		return containingDecks;
	}
	
	private List<Color> getDifferentColors(List<DeckId> decks)
	{
		List<CardId> cards = new ArrayList<>();
		if(decks.isEmpty())
			return null;
		List<CardId> differentCards = new ArrayList<>();
		for(DeckId deck : decks)
			cards.addAll(deck.getDeckClone());
		for(CardId card : cards)
		{
			differentCards.add(card);
			cards.remove(card);
			for(CardId listCard : cards)
				if(card.equivalent(listCard))
					cards.remove(listCard);
		}
		List<Color> differentColors = new ArrayList<>();
		for(CardId card : differentCards)
			differentColors.add(card.getColor());
		return differentColors;
	}
	
	private class CardId
	{
		private Color color;
		private int id;
		public CardId(Color c, int identifier)
		{
			color = c;
			id = identifier;
		}
		
		public Color getColor()
		{
			return color;
		}
		
		public int getId()
		{
			return id;
		}
		
		public boolean equals(CardId cardId)
		{
			if(id == cardId.getId() && color.equals(cardId.getColor()))
				return true;
			return false;
		}
		
		/**
		 * Verifies that the passed card has the same colors as caller's
		 * @param cardId
		 * @return
		 */
		public boolean equivalent(CardId cardId)
		{
			if(color.equals(cardId.getColor()))
				return true;
			return false;
		}
	}
	
	private class DeckId
	{
		List<CardId> cardsId;
		private final CardId jolly = new CardId(Color.decode(Costants.JOKERCOLOR), 0);
		
		/**
		 * Constructor
		 * @param cards: list of cards/cardiID or colors to be converted in deck
		 */
		@SuppressWarnings("unchecked")
		public DeckId(List<?> cards)
		{
			if(cards.isEmpty())
				return;
			if(cards.get(0) instanceof CardId)
			{
				cardsId = new ArrayList<>();
				cardsId.addAll((ArrayList<CardId>)cards);
				return;
			}
			if(cards.get(0) instanceof Color)
			{
				cardsId = new ArrayList<>();
				for(int i = 0; i < cards.size(); i++)
					cardsId.add(new CardId((Color)cards.get(i), i));
				return;
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
			clone.addAll(cardsId);
			return clone;
		}
		
		/**
		 * Returns whether the two deck are equivalent
		 * @param deck: comparing deck
		 * @return
		 */
		public boolean equivalent(DeckId deck)
		{
			if(deck.getDeckClone().size() != cardsId.size())
				return false;
			return equivalentOrContained(deck);
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
			for(CardId cardOne : firstDeck)
				for(CardId cardTwo : secondDeck)
					if(cardOne.equivalent(cardTwo))
					{
						firstDeck.remove(cardOne);
						secondDeck.remove(cardTwo);
						break;
					}
			if(firstDeck.isEmpty())
				return true;
			return false;
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
		 * returns weather the deck contains a card equivalent to the parameter
		 * @param card
		 * @return
		 */
		public boolean contains(CardId card)
		{
			for(CardId deckCard : cardsId)
				if(card.equivalent(deckCard))
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
			for(CardId deckCard : cardsId)
				if(deckCard.equivalent(card))
				{
					if(cardsId.remove(deckCard));
					break;
				}
		}
		
		public DeckId getCleanDeck()
		{
			List<CardId> cleanDeck = new ArrayList<>();
			for(CardId card : cardsId)
				if(!card.equivalent(jolly))
					cleanDeck.add(card);
			return new DeckId(cleanDeck);
		}

		private DeckId getSubset(DeckId input, int[] subset) {
			List<CardId> result = new ArrayList<>();
		    for (int i = 0; i < subset.length; i++) 
		        result.add(i, input.getDeck().get(subset[i]));
		    return new DeckId(result);
		}
	}
}

