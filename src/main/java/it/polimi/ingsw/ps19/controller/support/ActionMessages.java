package it.polimi.ingsw.ps19.controller.support;

public class ActionMessages 
{
	public static final String NOT_YOUR_TURN = "It's not Your Turn";
	public static final String POLITIC_DECK_IS_OVER = "Politic deck is over";
	public static final String EVERYTHING_IS_OK = "Everything is OK!";
	public static final String NO_MONEY = "You don't have enough money!";
	public static final String NO_HELPERS = "You don't have enough helpers!";
	public static final String MAIN_ACTION = "You have to play one main action";
	public static final String NOT_HAVE_POLITIC_CARD = "You haven't this politic card";
	public static final String NO_BUSINESS_CARD = "Can't find business card";	
	public static final String BUILD_EMPORIA = "You already have an emporium in this city";
	public static final String GENERIC_ERROR = "ERROR!";
	public static final String NO_BUILD = "You have already finish yours emporia!";
	public static final String PLAYER_DISCONNECTED = "Player Disconnected. id = ";
	public static final String NO_MARKET_TIME ="this is not Market time";
	public static final String BUSINESS_CARD_REQUEST = "Please select a Business card or City to give a Bonus";
	public static final String TIME_TO_MARKET = "Now is time to Maket";
	public static final String COLOR_NOT_AVAILABLE = "This color is not corrected";
	public static final String NO_ACTION_TO_DO_IT= "You don't have action to do what you want";
	public static final String YOU_ALREADY_BOUGHT ="you've already bought";
	public static final String PLAYER_HAS_BOUGHT ="One Player has bought, now is your turn";
	public static final String PLAYER_WIN_GAME ="The game was won by the player with id ";
	public static final String END_GAME = "Enjoy your life, the game is over";
	public static final String NO_TIME_TO_BONUS = "You don't have Business Card or City Bonus pending requests";
	public static final String BUSINESS_CARD_TO_DRAW_REQUEST = "Please select a Business card to draw";
	public static final String NO_BUSINESS_CARD_TO_SELECT = "You don't have business card to draw";
	
	
	private ActionMessages() { }
	
	protected static String playerWinGameResultBuilder(int id)
	{
		return PLAYER_WIN_GAME + id;
	}
}
