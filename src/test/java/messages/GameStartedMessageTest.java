package messages;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.polimi.ingsw.ps19.message.replies.GameStartedMessage;

public class GameStartedMessageTest {

	@Test
	public void testGameStartedMessage() 
	{		
		GameStartedMessage test = new GameStartedMessage(0, "TEST", 4 , 0);
		
		assertTrue(test.getActivePlayer() == 0);
		assertTrue(test.getResult() == "TEST");
		assertTrue(test.getNumberOfPlayer() == 4);
		assertTrue(test.getPlayerNumber() == 0);		
	}

}
