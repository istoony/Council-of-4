package it.polimi.ingsw.ps19.model.bonus;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.ps19.model.parameter.Costants;

public class BonusFactory 
{

	private static final String BONUS_GET_CITY_BONUS = "bonus-get-city-bonus";
	private static final String BONUS_GET_BUSINESS_CARD_BONUS = "bonus-get-business-card-bonus";
	private static final String BONUS_MORE_BUSINESS_CARD = "bonus-more-business-card";
	private static final String BONUS_MORE_VICTORY_POINTS = "bonus-more-victory-points";
	private static final String BONUS_ANOTHER_TURN = "bonus-another-turn";
	private static final String BONUS_MORE_POLITICSCARD = "bonus-more-politicscard";
	private static final String BONUS_MORE_NOBILITYTRACK = "bonus-more-nobilitytrack";
	private static final String BONUS_MORE_HELPER = "bonus-more-helper";
	private static final String BONUS_MORE_MONEY = "bonus-more-money";

	//BOUNDS FOR CITY BONUS
	private static final int MAX_CITY_HELPERS = 5;
	private static final int MAX_CITY_CARDS = 3;
	private static final int MAX_CITY_MONEY = 4;
	private static final int MAX_CITY_VP = 5;
	private static final int MAX_CITY_NOBILITY = 3;
	private static final int NUMBER_OF_KIND_OF_BONUS = 5;
	private static final int MAX_BONUS_PER_CITY = 4;
	
	/*bonus-more-money
    bonus-more-helper
    bonus-more-nobilitytrack
    bonus-more-politicscard
    bonus-another-turn
    bonus-more-victory-points
    bonus-more-business-card
    bonus-get-business-card-bonus
    bonus-get-city-bonus*/
	
	private BonusFactory(){
		//null
	}
	
	
	public static Bonus getBonus(String typeofbonus, int parameter)
	{
		Bonus bonus = null;
		if(typeofbonus.equals(BONUS_MORE_MONEY))
			bonus = new MoreMoney(parameter);
		
		if(typeofbonus.equals(BONUS_MORE_HELPER))
			bonus = new MoreHelpers(parameter);
		
		if(typeofbonus.equals(BONUS_MORE_NOBILITYTRACK))
			bonus = new MoreNobilityPoints(parameter);
					
		if(typeofbonus.equals(BONUS_MORE_POLITICSCARD))
			bonus = new DrawPoliticCard(parameter);
						
		if(typeofbonus.equals(BONUS_ANOTHER_TURN))
			bonus = new MoreMainAction(parameter);
							
		if(typeofbonus.equals(BONUS_MORE_VICTORY_POINTS))
			bonus = new MoreVictoryPoints(parameter);
					
		if(typeofbonus.equals(BONUS_MORE_BUSINESS_CARD))
			bonus = new DrawBusinessCard(parameter);
			
		return bonus;	
	}
	public static Bonus getBonus(String typeofbonus)
	{
		Bonus bonus = null;
		if(typeofbonus.equals(BONUS_GET_BUSINESS_CARD_BONUS))
			bonus = new ReuseBusinessCardBonus();
																			
		if(typeofbonus.equals(BONUS_GET_CITY_BONUS))
			bonus = new GetCityBonus();
		return bonus;
	}
	
	public static List<Bonus> generateCityBonus()
	{
		int i;
		List<Integer> truthTable = new ArrayList<>();
		List<Bonus> bon = new ArrayList<>();
		
		for(int count=0; count<NUMBER_OF_KIND_OF_BONUS; count++)
		{
			truthTable.add(1);
		}
		
		int max = Costants.RANDOM_NUMBER.nextInt(MAX_BONUS_PER_CITY-1)+1;
		for(int count=0; count<max; count++){
			
			i = Costants.RANDOM_NUMBER.nextInt(NUMBER_OF_KIND_OF_BONUS);
			while(truthTable.get(i)==0){
				i = Costants.RANDOM_NUMBER.nextInt(NUMBER_OF_KIND_OF_BONUS);
			}
		
			switch(i)
			{
				case 0 : cardsBonus(bon);
						break;
				case 1 : helpersBonus(bon);
						break;
				case 2 : moneyBonus(bon);
						break;
				case 3 : nobilityBonus(bon);
						break;		
				case 4 :vpBonus(bon);
						break;
				default: return bon;
			}
			truthTable.set(i, 0);
			
		}
		return bon;
	}
	
	private static void cardsBonus(List<Bonus> list)
	{
		int n = Costants.RANDOM_NUMBER.nextInt(MAX_CITY_CARDS-1)+1;
		list.add(new MoreVictoryPoints(n));
	}
	
	private static void helpersBonus(List<Bonus> list)
	{
		int n = Costants.RANDOM_NUMBER.nextInt(MAX_CITY_HELPERS-1)+1;
		list.add(new MoreVictoryPoints(n));
	}
	
	private static void moneyBonus(List<Bonus> list)
	{
		int n = Costants.RANDOM_NUMBER.nextInt(MAX_CITY_MONEY-1)+1;
		list.add(new MoreVictoryPoints(n));
	}
	
	private static void nobilityBonus(List<Bonus> list)
	{
		int n = Costants.RANDOM_NUMBER.nextInt(MAX_CITY_NOBILITY-1)+1;
		list.add(new MoreVictoryPoints(n));
	}
	
	private static void vpBonus(List<Bonus> list)
	{
		int n = Costants.RANDOM_NUMBER.nextInt(MAX_CITY_VP-1)+1;
		list.add(new MoreVictoryPoints(n));
	}
}
