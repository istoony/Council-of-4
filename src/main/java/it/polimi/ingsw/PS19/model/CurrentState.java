package it.polimi.ingsw.PS19.model;

public class CurrentState 
{
	private Boolean businessCardRequest;
	private Boolean cityBonusRequest;
	private int playerTurnId;
	
	private Boolean sendfullgame;
	
	public CurrentState() 
	{
		businessCardRequest = false;
		cityBonusRequest = false;
		playerTurnId = 0;
		sendfullgame = false;
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
	public Boolean getSendfullgame() {
		return sendfullgame;
	}
	public void setSendfullgame(Boolean sendfullgame) {
		this.sendfullgame = sendfullgame;
	}
	
}
