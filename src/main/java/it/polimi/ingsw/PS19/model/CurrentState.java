package it.polimi.ingsw.ps19.model;

public class CurrentState 
{
	private Boolean businessCardRequest;
	private Boolean cityBonusRequest;
	private int playerTurnId;
	
	private Boolean timeToMarket;
	
	public CurrentState() 
	{
		businessCardRequest = false;
		cityBonusRequest = false;
		playerTurnId = 0;
		timeToMarket = false;
	}
	
	public void setBusinessCardRequest(Boolean businessCardRequest) {
		this.businessCardRequest = businessCardRequest;
	}
	public void setCityBonusRequest(Boolean cityBonusRequest) {
		this.cityBonusRequest = cityBonusRequest;
	}
	public void setPlayerTurnId(int playerTurnId) {
		this.playerTurnId = playerTurnId;
	}
	public Boolean getBusinessCardRequest() {
		return businessCardRequest;
	}
	public Boolean getCityBonusRequest() {
		return cityBonusRequest;
	}
	public int getPlayerTurnId() {
		return playerTurnId;
	}
	public void setTimeToMarket(Boolean timeToMarket) 
	{
		this.timeToMarket = timeToMarket;
	}
	public Boolean getTimeToMarket() 
	{
		return timeToMarket;
	}
	
	@Override
	public String toString() 
	{
		String s = "";
		s += "\nbusinessCard = " + businessCardRequest;
		s += "\nCityBonus = " + cityBonusRequest;
		s += "\nTimeToMarket" + timeToMarket;
		return s;
	} 
	
	
}
