package it.polimi.ingsw.PS19.controller.action;

import java.awt.Color;
import java.util.List;

import it.polimi.ingsw.PS19.message.requests.BuyHelperMessage;
import it.polimi.ingsw.PS19.message.requests.BuyMainActionMessage;
import it.polimi.ingsw.PS19.message.requests.ChangeKingPositionMessage;
import it.polimi.ingsw.PS19.message.requests.DrawPoliticsCardMessage;
import it.polimi.ingsw.PS19.message.requests.ElectCouncillorMessage;
import it.polimi.ingsw.PS19.message.requests.EndTurnMessage;
import it.polimi.ingsw.PS19.message.requests.GetBusinessCardMessage;
import it.polimi.ingsw.PS19.message.requests.PlayerDisconnectedMessage;
import it.polimi.ingsw.PS19.message.requests.RedrawBusinessCardMessage;
import it.polimi.ingsw.PS19.message.requests.SendFullGameMessage;
import it.polimi.ingsw.PS19.message.requests.SendOrderMessage;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.map.City;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.parameter.RegionType;


/**
 * The Class MessageInterpreterVisitorImp is an implementation of MessageInterpreterVisitor
 * For each message i use Getter to get all attributes of the message and create a specific
 * Action.
 */
public class MessageInterpreterVisitorImp implements MessageInterpreterVisitor {

	/**
	 * @requre ElectCouncillorMessage
	 * @return new ElectCouncillor
	 */
	@Override
	public Action visit(ElectCouncillorMessage message) 
	{
		int playerId = message.getId();
		RegionType region = message.getRegion();
		Color councicolor =  message.getColor();
		Boolean mainAction = message.getMainAction();
		King king = message.getKing();
		
		if(king != null)
			return new ElectCouncillor(councicolor, playerId, king, mainAction);	
		
		return new ElectCouncillor(councicolor, playerId, region, mainAction);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor#visit(it.polimi.ingsw.PS19.message.requests.SendFullGameMessage)
	 */
	@Override
	public Action visit(SendFullGameMessage message) 
	{
		return new SendFullGame();
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor#visit(it.polimi.ingsw.PS19.message.requests.BuyHelperMessage)
	 */
	@Override
	public Action visit(BuyHelperMessage message) 
	{
		int playerId = message.getId();
		return new BuyHelper(playerId);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor#visit(it.polimi.ingsw.PS19.message.requests.GetBusinessCardMessage)
	 */
	@Override
	public Action visit(GetBusinessCardMessage message) 
	{
		BusinessCard card = message.getCard();
		RegionType region = message.getRegion();
		int playerId = message.getId();
		List<Color> politicscard = message.getPoliticsCard();
		return new DrawBusinessCard(playerId, region, card, politicscard);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor#visit(it.polimi.ingsw.PS19.message.requests.DrawPoliticsCardMessage)
	 */
	@Override
	public Action visit(DrawPoliticsCardMessage message) 
	{
			int playerId = message.getId();
		return new DrawPoliticsCard(playerId);
	}
	
	/* (non-Javadoc)
	 * @see it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor#visit(it.polimi.ingsw.PS19.message.requests.EndTurnMessage)
	 */
	@Override
	public Action visit(EndTurnMessage message)
	{
		int playerId = message.getId();
		return new EndTurn(playerId);
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor#visit(it.polimi.ingsw.PS19.message.requests.ChangeKingPositionMessage)
	 */
	@Override
	public Action visit(ChangeKingPositionMessage message) 
	{
		City c = message.getCity();
		int id = message.getId();
		return new ChangeKingPosition(id, c, message.getPoliticCard());
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor#visit(it.polimi.ingsw.PS19.message.requests.BuyMainActionMessage)
	 */
	@Override
	public Action visit(BuyMainActionMessage message) 
	{
		return new BuyMainAction(message.getId());
	}

	/* (non-Javadoc)
	 * @see it.polimi.ingsw.PS19.controller.action.MessageInterpreterVisitor#visit(it.polimi.ingsw.PS19.message.requests.SendOrderMessage)
	 */
	@Override
	public Action visit(SendOrderMessage message) 
	{
		return new AddOrder(message.getOrder(), message.getId());
	}

	@Override
	public Action visit(RedrawBusinessCardMessage message) {
		return new RedrawBusinessCard(message.getRegion(), message.getId());
	}

	@Override
	public Action visit(PlayerDisconnectedMessage message) {
		return new PlayerDisconnectedAction(message.getId());
	}


}
