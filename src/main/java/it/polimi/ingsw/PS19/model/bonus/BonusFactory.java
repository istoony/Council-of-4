package it.polimi.ingsw.PS19.model.bonus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	private static final int MAX_CITY_HELPERS = 2;
	private static final int MAX_CITY_CARDS = 2;
	private static final int MAX_CITY_MONEY = 3;
	private static final int MAX_CITY_VP = 3;
	private static final int MAX_CITY_NOBILITY = 2;
	private static final int NUMBER_OF_KIND_OF_BONUS = 5;
	private static final int MAX_BONUS_PER_CITY = 2;
	
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
		if(typeofbonus.equals(BONUS_MORE_MONEY))
			return new MoreMoney(parameter);
		
		if(typeofbonus.equals(BONUS_MORE_HELPER))
			return new MoreHelpers(parameter);
		
		if(typeofbonus.equals(BONUS_MORE_NOBILITYTRACK))
			return new MoreNobilityPoints(parameter);
					
		if(typeofbonus.equals(BONUS_MORE_POLITICSCARD))
			return new DrawPoliticCard(parameter);
						
		if(typeofbonus.equals(BONUS_ANOTHER_TURN))
			return new MoreMainAction(parameter);
							
		if(typeofbonus.equals(BONUS_MORE_VICTORY_POINTS))
			return new MoreVictoryPoints(parameter);
					
		if(typeofbonus.equals(BONUS_MORE_BUSINESS_CARD))
			return new DrawBusinessCard(parameter);
		
		if(typeofbonus.equals(BONUS_GET_BUSINESS_CARD_BONUS))
			return new ReuseBusinessCardBonus(parameter);
																			
		if(typeofbonus.equals(BONUS_GET_CITY_BONUS))
			return new GetCityBonus(parameter);
			
		return null;	
	}
	
	public static List<Bonus> generateCityBonus(){
		Random k = new Random();
		Random r = new Random();
		int n;
		int i;
		ArrayList<Integer> truthTable = new ArrayList<>();
		ArrayList<Bonus> bon = new ArrayList<>();
		
		for(int count=0; count<NUMBER_OF_KIND_OF_BONUS; count++)
		{
			truthTable.add(1);
		}
		
		int max = k.nextInt(MAX_BONUS_PER_CITY-1)+1;
		for(int count=0; count<max; count++){
			
			i = r.nextInt(NUMBER_OF_KIND_OF_BONUS);
			while(truthTable.get(i)==0){
				i = r.nextInt(NUMBER_OF_KIND_OF_BONUS);
			}
		
			switch(i){
				case 0 : {
					n = r.nextInt(MAX_CITY_CARDS-1)+1;
					bon.add(new DrawPoliticCard(n));
					break;
				}
				case 1 : {
					n = r.nextInt(MAX_CITY_HELPERS-1)+1;
					bon.add(new MoreHelpers(n));
					break;
				}
				case 2 : {
					n = r.nextInt(MAX_CITY_MONEY-1)+1;
					bon.add(new MoreMoney(n));
					break;
				}
				case 3 : {
					n = r.nextInt(MAX_CITY_NOBILITY-1)+1;
					bon.add(new MoreNobilityPoints(n));
					break;
				}		
				case 4 : {
					n = r.nextInt(MAX_CITY_VP-1)+1;
					bon.add(new MoreVictoryPoints(n));
					break;
				}
			}
			truthTable.set(i, 0);
			
		}
		return bon;
	}
}
