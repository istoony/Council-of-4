package it.polimi.ingsw.PS19.model.bonus;

public class BonusFactory 
{

	private static final String BONUS_GET_CITY_BONUS = "bonus-get-city-bonus";
	private static final String BONUS_GET_TWO_TOKEN = "bonus-get-two-token";
	private static final String BONUS_GET_BUSINESS_CARD_BONUS = "bonus-get-business-card-bonus";
	private static final String BONUS_MORE_BUSINESS_CARD = "bonus-more-business-card";
	private static final String BONUS_MORE_VICTORY_POINTS = "bonus-more-victory-points";
	private static final String BONUS_ANOTHER_TURN = "bonus-another-turn";
	private static final String BONUS_MORE_POLITICSCARD = "bonus-more-politicscard";
	private static final String BONUS_MORE_NOBILITYTRACK = "bonus-more-nobilitytrack";
	private static final String BONUS_MORE_HELPER = "bonus-more-helper";
	private static final String BONUS_MORE_MONEY = "bonus-more-money";

	/*bonus-more-money
    bonus-more-helper
    bonus-more-nobilitytrack
    bonus-more-politicscard
    bonus-another-turn
    bonus-more-victory-points
    bonus-more-business-card
    bonus-get-business-card-bonus
    bonus-get-two-token
    bonus-get-city-bonus*/
	
	
	public BonusFactory() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public static Bonus getBonus(String typeofbonus, int parameter)
	{
		if(typeofbonus.equals(BONUS_MORE_MONEY))
			return new MoreMoney(parameter);
		
		if(typeofbonus.equals(BONUS_MORE_HELPER))
			return new MoreHelpers(parameter);
		
		if(typeofbonus.equals(BONUS_MORE_NOBILITYTRACK))
			return new MoreNobilityPoints(parameter);
					
	//	if(typeofbonus.equals(BONUS_MORE_POLITICSCARD))
						
		if(typeofbonus.equals(BONUS_ANOTHER_TURN))
			return new MoreMainAction();
							
		if(typeofbonus.equals(BONUS_MORE_VICTORY_POINTS))
			return new MoreVictoryPoints(parameter);
					
	//	if(typeofbonus.equals(BONUS_MORE_BUSINESS_CARD))
									
	//	if(typeofbonus.equals(BONUS_GET_BUSINESS_CARD_BONUS))
										
	//	if(typeofbonus.equals(BONUS_GET_TWO_TOKEN))
											
	//	if(typeofbonus.equals(BONUS_GET_CITY_BONUS))
		return null;	
	}
}
