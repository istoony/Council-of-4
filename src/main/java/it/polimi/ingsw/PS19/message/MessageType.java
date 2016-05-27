/*
 * @author Andrea Milanta
 */
package it.polimi.ingsw.PS19.message;

public enum MessageType 
{	
	
	
	/***
	 * 
	 * PROBABILMENTE DA ELIMINARE
	 */
	SIMPLE,
	STRING,
	
	//Notificatioj Messages
	GAME_STARTED,
	SEND_FULL_GAME,
	PLAYER_DISCONNECTED,
	ID_ACTIVE_PLAYER,
	
	//Action Messages
	BUY_BUSINESS_PERMIT,
	MAIN_ELECT_REGION_COUNCILLOR,
	MAIN_ELECT_KING_COUNCILLOR,
	REQUIRE_ACTION;
	
	
}
