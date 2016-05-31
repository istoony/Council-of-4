	@Override
	public void checkAlreadyTurn() {
		// TODO Auto-generated method stub
		
	}
import it.polimi.ingsw.PS19.message.replies.Reply;
import it.polimi.ingsw.PS19.model.Model;

public class BuyHelper implements Action
{
	private static final int HELPERS = 1;
	private static final int MONEY = 3;
	private int id;
	private String result;
	
	public BuyHelper(int id) 
	{
		this.id = id;
	}

	@Override
	public Boolean execute(Model model) 
	{
		if(model.getPlayerById(id).getMoney() < MONEY)
			return false;
		model.getPlayerById(id).setMoney(model.getPlayerById(id).getMoney() - MONEY);
		model.getPlayerById(id).setHelpers(model.getPlayerById(id).getHelpers() + HELPERS);
		return true;
	}

	@Override
	public Boolean isPossible(Model model) 
	{
		if(Action.checkPlayerTurn(id, model))
		{
			result = Action.NOT_YOUR_TURN;
			return false;
		}
		
		if(model.getPlayerById(id).getMoney() < MONEY)
		{
			result = Action.NO_MONEY;
			return false;
		}
		result = Action.EVERYTHING_IS_OK;
		return true;
	}

	@Override
	public String getStringResult() 
	{
		return result;
	}


	@Override
	public void checkAlreadyTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reply createReplyMessage(Model model) {
		// TODO Auto-generated method stub
		return null;
	}

}
