package it.polimi.ingsw.PS19.clienttest;

import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.ps19.client.clientinput.BuildEmporiumInputs;
import it.polimi.ingsw.ps19.client.clientinput.BuildWithKingInputs;
import it.polimi.ingsw.ps19.client.clientinput.BuyHelperInputs;
import it.polimi.ingsw.ps19.client.clientinput.BuyMainActionInput;
import it.polimi.ingsw.ps19.client.clientinput.ElectCouncillorInputs;
import it.polimi.ingsw.ps19.client.clientinput.EndTurnInput;
import it.polimi.ingsw.ps19.client.clientinput.FastAction;
import it.polimi.ingsw.ps19.client.clientinput.GetBusinessPermitInput;
import it.polimi.ingsw.ps19.client.clientinput.MainAction;
import it.polimi.ingsw.ps19.client.clientinput.MarketSell;
import it.polimi.ingsw.ps19.client.clientinput.RedrawBusinessCardInput;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.client.language.English;
import it.polimi.ingsw.ps19.client.language.Italiano;
import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.Order;
import it.polimi.ingsw.ps19.model.bonus.DrawBusinessCard;
import it.polimi.ingsw.ps19.model.bonus.DrawPoliticCard;
import it.polimi.ingsw.ps19.model.bonus.GetCityBonus;
import it.polimi.ingsw.ps19.model.bonus.MoreHelpers;
import it.polimi.ingsw.ps19.model.bonus.MoreMainAction;
import it.polimi.ingsw.ps19.model.bonus.MoreMoney;
import it.polimi.ingsw.ps19.model.bonus.MoreNobilityPoints;
import it.polimi.ingsw.ps19.model.bonus.MoreVictoryPoints;
import it.polimi.ingsw.ps19.model.bonus.ReuseBusinessCardBonus;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

public class LanguageTest 
{

	/**
	 * This test checks all the method of the language test making sure they don't return null
	 */
	
	
	@Test
	public void testPlayerDisconnected() 
	{
		new Italiano();
		assertTrue(Language.playerDisconnected(0) != null);
		new English();
		assertTrue(Language.playerDisconnected(0) != null);
	}

	@Test
	public void testGetStringBuildWithKingInputs() {
		Language l = new Italiano();
		assertTrue(l.getString(new BuildWithKingInputs(new ClientModel(0))) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new BuildWithKingInputs(new ClientModel(0))) != null);
	}

	@Test
	public void testGetStringBuildEmporiumInputs() 
	{
		Language l = new Italiano();
		assertTrue(l.getString(new BuildEmporiumInputs(new ClientModel(0))) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new BuildEmporiumInputs(new ClientModel(0))) != null);
	}

	@Test
	public void testGetStringElectCouncillorInputs() 
	{
		Language l = new Italiano();
		assertTrue(l.getString(new ElectCouncillorInputs(new ClientModel(0), true)) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new ElectCouncillorInputs(new ClientModel(0), true)) != null);
	}

	@Test
	public void testGetStringEndTurnInput() 
	{
		Language l = new Italiano();
		assertTrue(l.getString(new EndTurnInput(new ClientModel(0))) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new EndTurnInput(new ClientModel(0))) != null);
	}

	@Test
	public void testGetStringGetBusinessPermitInput() 
	{
		Language l = new Italiano();
		assertTrue(l.getString(new GetBusinessPermitInput(new ClientModel(0))) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new GetBusinessPermitInput(new ClientModel(0))) != null);
	}

	@Test
	public void testGetStringRedrawBusinessCardInput() {
		Language l = new Italiano();
		assertTrue(l.getString(new RedrawBusinessCardInput(new ClientModel(0))) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new RedrawBusinessCardInput(new ClientModel(0))) != null);
	}

	@Test
	public void testGetStringBuyHelperInputs() {
		Language l = new Italiano();
		assertTrue(l.getString(new BuyHelperInputs(new ClientModel(0))) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new BuyHelperInputs(new ClientModel(0))) != null);
	}

	@Test
	public void testGetStringBuyMainActionInput() {
		Language l = new Italiano();
		assertTrue(l.getString(new BuyMainActionInput(new ClientModel(0))) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new BuyMainActionInput(new ClientModel(0))) != null);
	}

	@Test
	public void testGetStringMarketSell() 
	{
		Language l = new Italiano();
		assertTrue(l.getString(new MarketSell(new ClientModel(0))) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new MarketSell(new ClientModel(0))) != null);
	}

	@Test
	public void testGetStringFastAction() {
		Language l = new Italiano();
		assertTrue(l.getString(new FastAction(new ClientModel(0))) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new FastAction(new ClientModel(0))) != null);
	}

	@Test
	public void testGetStringMainAction() 
	{
		Language l = new Italiano();
		assertTrue(l.getString(new MainAction(new ClientModel(0))) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new MainAction(new ClientModel(0))) != null);
	}

	@Test
	public void testGetStringDrawBusinessCard() {
		Language l = new Italiano();
		assertTrue(l.getString(new DrawBusinessCard(0)) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new DrawBusinessCard(0)) != null);
	}

	@Test
	public void testGetStringDrawPoliticCardInt() {
		Language l = new Italiano();
		assertTrue(l.getString(new DrawPoliticCard(0)) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new DrawPoliticCard(0)) != null);
	}

	@Test
	public void testGetStringGetCityBonus() 
	{
		Language l = new Italiano();
		assertTrue(l.getString(new GetCityBonus()) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new GetCityBonus()) != null);
	}

	@Test
	public void testGetStringMoreHelpersInt() {
		Language l = new Italiano();
		assertTrue(l.getString(new MoreHelpers(0)) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new MoreHelpers(1)) != null);
	}

	@Test
	public void testGetStringMoreMainActionInt() {
		Language l = new Italiano();
		assertTrue(l.getString(new MoreMainAction(0)) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new MoreMainAction(1)) != null);
	}

	@Test
	public void testGetStringMoreMoneyInt() {
		Language l = new Italiano();
		assertTrue(l.getString(new MoreMoney(0)) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new MoreMoney(1)) != null);
	}

	@Test
	public void testGetStringMoreNobilityPointsInt() {
		Language l = new Italiano();
		assertTrue(l.getString(new MoreNobilityPoints(0)) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new MoreNobilityPoints(1)) != null);
	}

	@Test
	public void testGetStringMoreVictoryPointsInt() {
		Language l = new Italiano();
		assertTrue(l.getString(new MoreVictoryPoints(0)) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new MoreVictoryPoints(1)) != null);
	}

	@Test
	public void testGetStringReuseBusinessCardBonus() 
	{
		Language l = new Italiano();
		assertTrue(l.getString(new ReuseBusinessCardBonus()) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new ReuseBusinessCardBonus()) != null);
	}

	@Test
	public void testGetStringPoliticsCard() {
		Language l = new Italiano();
		assertTrue(l.getString(new PoliticsCard(null)) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new PoliticsCard(null)) != null);
	}

	@Test
	public void testGetStringBalcony() 
	{
		List<Integer> players = new ArrayList<>();
		players.add(0);
		players.add(1);
		Model m = new Model(players);
		
		Language l = new Italiano();
		assertTrue(l.getString(m.getMap().getRegionByType(RegionType.MOUNTAIN).getBalcony()) != null);
		Language l2 = new English();
		assertTrue(l2.getString(m.getMap().getRegionByType(RegionType.MOUNTAIN).getBalcony()) != null);
	}

	@Test
	public void testGetStringOrder() 
	{
		Language l = new Italiano();
		assertTrue(l.getString(new Order()) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new Order()) != null);
	}

	@Test
	public void testGetStringBusinessCard() {
		Language l = new Italiano();
		assertTrue(l.getString(new BusinessCard(RegionType.MOUNTAIN)) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new BusinessCard(RegionType.MOUNTAIN)) != null);
	}

	@Test
	public void testGetStringBonus() {
		Language l = new Italiano();
		assertTrue(l.getString(new MoreHelpers(2)) != null);
		Language l2 = new English();
		assertTrue(l2.getString(new MoreHelpers(1)) != null);
	}

	@Test
	public void testGetStringCity() {
		List<Integer> players = new ArrayList<>();
		players.add(0);
		players.add(1);
		Model m = new Model(players);
		
		Language l = new Italiano();
		assertTrue(l.getString(m.getMap().getRegionByType(RegionType.MOUNTAIN).getCities().get(0)) != null);
		Language l2 = new English();
		assertTrue(l2.getString(m.getMap().getRegionByType(RegionType.MOUNTAIN).getCities().get(1)) != null);
	}

	@Test
	public void testGetStringColor() 
	{
		Language l = new Italiano();
		assertTrue(l.getString(Color.RED) != null);
		Language l2 = new English();
		assertTrue(l2.getString(Color.RED) != null);
	}

	@Test
	public void testGetStringRegionType() 
	{		
		Language l = new Italiano();
		assertTrue(l.getString(RegionType.MOUNTAIN) != null);
		Language l2 = new English();
		assertTrue(l2.getString(RegionType.MOUNTAIN) != null);
	}

	@Test
	public void testGetNewGame() {
		Language l = new Italiano();
		assertTrue(l.getNewGame() != null);
		Language l2 = new English();
		assertTrue(l2.getNewGame() != null);
	}

	@Test
	public void testGetReconnect() {
		Language l = new Italiano();
		assertTrue(l.getReconnect() != null);
		Language l2 = new English();
		assertTrue(l2.getReconnect() != null);
	}

	@Test
	public void testGetInsertPassword() {
		Language l = new Italiano();
		assertTrue(l.getInsertPassword() != null);
		Language l2 = new English();
		assertTrue(l2.getInsertPassword() != null);
	}

	@Test
	public void testGetInvalidInsertion() {
		Language l = new Italiano();
		assertTrue(l.getInvalidInsertion() != null);
		Language l2 = new English();
		assertTrue(l2.getInvalidInsertion() != null);
	}

	@Test
	public void testGetWaiting() {
		Language l = new Italiano();
		assertTrue(l.getWaiting() != null);
		Language l2 = new English();
		assertTrue(l2.getWaiting() != null);
	}

	@Test
	public void testGetSocketCreated() {
		Language l = new Italiano();
		assertTrue(l.getSocketCreated() != null);
		Language l2 = new English();
		assertTrue(l2.getSocketCreated() != null);
	}

	@Test
	public void testGetConnSuccess() {
		Language l = new Italiano();
		assertTrue(l.getConnSuccess() != null);
		Language l2 = new English();
		assertTrue(l2.getConnSuccess() != null);
	}

	@Test
	public void testGetConnInsucces() {
		Language l = new Italiano();
		assertTrue(l.getConnInsucces() != null);
		Language l2 = new English();
		assertTrue(l2.getConnInsucces() != null);
	}

	@Test
	public void testGetKillClient() {
		Language l = new Italiano();
		assertTrue(l.getKillClient() != null);
		Language l2 = new English();
		assertTrue(l2.getKillClient() != null);
	}

	@Test
	public void testGetReconnected() {
		Language l = new Italiano();
		assertTrue(l.getReconnected() != null);
		Language l2 = new English();
		assertTrue(l2.getReconnected() != null);
	}

	@Test
	public void testGetConnPass() {
		Language l = new Italiano();
		assertTrue(l.getConnPass() != null);
		Language l2 = new English();
		assertTrue(l2.getConnPass() != null);
	}

	@Test
	public void testGetInsertIp() {
		Language l = new Italiano();
		assertTrue(l.getInsertIp() != null);
		Language l2 = new English();
		assertTrue(l2.getInsertIp() != null);
	}

	@Test
	public void testGetUseStdIp() {
		Language l = new Italiano();
		assertTrue(l.getUseStdIp() != null);
		Language l2 = new English();
		assertTrue(l2.getUseStdIp() != null);
	}

	@Test
	public void testGetInsertPort() {
		Language l = new Italiano();
		assertTrue(l.getInsertPort() != null);
		Language l2 = new English();
		assertTrue(l2.getInsertPort() != null);
	}

	@Test
	public void testGetUseStdPort() {
		Language l = new Italiano();
		assertTrue(l.getUseStdPort() != null);
		Language l2 = new English();
		assertTrue(l2.getUseStdPort() != null);
	}

	@Test
	public void testGetServerQuits() {
		Language l = new Italiano();
		assertTrue(l.getServerQuits() != null);
		Language l2 = new English();
		assertTrue(l2.getServerQuits() != null);
	}

	@Test
	public void testGetInvalidObj() {
		Language l = new Italiano();
		assertTrue(l.getInvalidObj() != null);
		Language l2 = new English();
		assertTrue(l2.getInvalidObj() != null);
	}

	@Test
	public void testGetNothing() {
		Language l = new Italiano();
		assertTrue(l.getNothing() != null);
		Language l2 = new English();
		assertTrue(l2.getNothing() != null);
	}

	@Test
	public void testGetWinner() {
		Language l = new Italiano();
		assertTrue(l.getWinner() != null);
		Language l2 = new English();
		assertTrue(l2.getWinner() != null);
	}

	@Test
	public void testGetResult() {
		Language l = new Italiano();
		assertTrue(l.getResult() != null);
		Language l2 = new English();
		assertTrue(l2.getResult() != null);
	}

	@Test
	public void testGetChooseActionTypeTitle() {
		Language l = new Italiano();
		assertTrue(l.getChooseActionTypeTitle() != null);
		Language l2 = new English();
		assertTrue(l2.getChooseActionTypeTitle() != null);
	}

	@Test
	public void testGetChooseRegionTitle() {
		Language l = new Italiano();
		assertTrue(l.getChooseRegionTitle() != null);
		Language l2 = new English();
		assertTrue(l2.getChooseRegionTitle() != null);
	}

	@Test
	public void testGetKing() {
		Language l = new Italiano();
		assertTrue(l.getKing() != null);
		Language l2 = new English();
		assertTrue(l2.getKing() != null);
	}

	@Test
	public void testGetChooseActionTitle() {
		Language l = new Italiano();
		assertTrue(l.getChooseActionTitle() != null);
		Language l2 = new English();
		assertTrue(l2.getChooseActionTitle() != null);
	}

	@Test
	public void testGetChooseColor() {
		Language l = new Italiano();
		assertTrue(l.getChooseColor() != null);
		Language l2 = new English();
		assertTrue(l2.getChooseColor() != null);
	}

	@Test
	public void testGetChooseBusinessCardTitle() {
		Language l = new Italiano();
		assertTrue(l.getChooseBusinessCardTitle() != null);
		Language l2 = new English();
		assertTrue(l2.getChooseBusinessCardTitle() != null);
	}

	@Test
	public void testGetChoosePoliticCardTitle() {
		Language l = new Italiano();
		assertTrue(l.getChoosePoliticCardTitle() != null);
		Language l2 = new English();
		assertTrue(l2.getChoosePoliticCardTitle() != null);
	}

	@Test
	public void testGetChooseCityTitle() {
		Language l = new Italiano();
		assertTrue(l.getChooseCityTitle() != null);
		Language l2 = new English();
		assertTrue(l2.getChooseCityTitle() != null);
	}

	@Test
	public void testGetBonuses() {
		Language l = new Italiano();
		assertTrue(l.getBonuses() != null);
		Language l2 = new English();
		assertTrue(l2.getBonuses() != null);
	}

	@Test
	public void testGetMap() {
		Language l = new Italiano();
		assertTrue(l.getMap() != null);
		Language l2 = new English();
		assertTrue(l2.getMap() != null);
	}

	@Test
	public void testGetNobilityPath() {
		Language l = new Italiano();
		assertTrue(l.getNobilityPath() != null);
		Language l2 = new English();
		assertTrue(l2.getNobilityPath() != null);
	}

	@Test
	public void testGetActivePlayerId() {
		Language l = new Italiano();
		assertTrue(l.getActivePlayerId() != null);
		Language l2 = new English();
		assertTrue(l2.getActivePlayerId() != null);
	}

	@Test
	public void testGetYouArePlayer() {
		Language l = new Italiano();
		assertTrue(l.getYouArePlayer() != null);
		Language l2 = new English();
		assertTrue(l2.getYouArePlayer() != null);
	}

	@Test
	public void testGetPlayer() {
		Language l = new Italiano();
		assertTrue(l.getPlayer() != null);
		Language l2 = new English();
		assertTrue(l2.getPlayer() != null);
	}

	@Test
	public void testGetNumEmporiaLeft() {
		Language l = new Italiano();
		assertTrue(l.getNumEmporiaLeft() != null);
		Language l2 = new English();
		assertTrue(l2.getNumEmporiaLeft() != null);
	}

	@Test
	public void testGetMoney() {
		Language l = new Italiano();
		assertTrue(l.getMoney() != null);
		Language l2 = new English();
		assertTrue(l2.getMoney() != null);
	}

	@Test
	public void testGetVictoryPoints() {
		Language l = new Italiano();
		assertTrue(l.getVictoryPoints() != null);
		Language l2 = new English();
		assertTrue(l2.getVictoryPoints() != null);
	}

	@Test
	public void testGetNobilityPoints() {
		Language l = new Italiano();
		assertTrue(l.getNobilityPoints() != null);
		Language l2 = new English();
		assertTrue(l2.getNobilityPoints() != null);
	}

	@Test
	public void testGetNumOfHelpers() {
		Language l = new Italiano();
		assertTrue(l.getNumOfHelpers() != null);
		Language l2 = new English();
		assertTrue(l2.getNumOfHelpers() != null);
	}

	@Test
	public void testGetNumberOf() {
		Language l = new Italiano();
		assertTrue(l.getNumberOf() != null);
		Language l2 = new English();
		assertTrue(l2.getNumberOf() != null);
	}

	@Test
	public void testGetPoliticCards() {
		Language l = new Italiano();
		assertTrue(l.getPoliticCards() != null);
		Language l2 = new English();
		assertTrue(l2.getPoliticCards() != null);
	}

	@Test
	public void testGetAvailable() {
		Language l = new Italiano();
		assertTrue(l.getAvailable() != null);
		Language l2 = new English();
		assertTrue(l2.getAvailable() != null);
	}

	@Test
	public void testGetQuick() {
		Language l = new Italiano();
		assertTrue(l.getQuick() != null);
		Language l2 = new English();
		assertTrue(l2.getQuick() != null);
	}

	@Test
	public void testGetMain() {
		Language l = new Italiano();
		assertTrue(l.getMain() != null);
		Language l2 = new English();
		assertTrue(l2.getMain() != null);
	}

	@Test
	public void testGetFreeBusiness() {
		Language l = new Italiano();
		assertTrue(l.getFreeBusiness() != null);
		Language l2 = new English();
		assertTrue(l2.getFreeBusiness() != null);
	}

	@Test
	public void testGetUsedBusiness() {
		Language l = new Italiano();
		assertTrue(l.getUsedBusiness() != null);
		Language l2 = new English();
		assertTrue(l2.getUsedBusiness() != null);
	}

	@Test
	public void testGetCurrentCity() {
		Language l = new Italiano();
		assertTrue(l.getCurrentCity() != null);
		Language l2 = new English();
		assertTrue(l2.getCurrentCity() != null);
	}

	@Test
	public void testGetRegion() {
		Language l = new Italiano();
		assertTrue(l.getRegion() != null);
		Language l2 = new English();
		assertTrue(l2.getRegion() != null);
	}

	@Test
	public void testGetCities() {
		Language l = new Italiano();
		assertTrue(l.getCities() != null);
		Language l2 = new English();
		assertTrue(l2.getCities() != null);
	}

	@Test
	public void testGetBusinessCards() {
		Language l = new Italiano();
		assertTrue(l.getBusinessCards() != null);
		Language l2 = new English();
		assertTrue(l2.getBusinessCards() != null);
	}

	@Test
	public void testGetFirstCard() {
		Language l = new Italiano();
		assertTrue(l.getFirstCard() != null);
		Language l2 = new English();
		assertTrue(l2.getFirstCard() != null);
	}

	@Test
	public void testGetSecondCard() {
		Language l = new Italiano();
		assertTrue(l.getSecondCard() != null);
		Language l2 = new English();
		assertTrue(l2.getSecondCard() != null);
	}

	@Test
	public void testGetHowManyHelpersToSell() {
		Language l = new Italiano();
		assertTrue(l.getHowManyHelpersToSell() != null);
		Language l2 = new English();
		assertTrue(l2.getHowManyHelpersToSell() != null);
	}

	@Test
	public void testGetSetPrice() {
		Language l = new Italiano();
		assertTrue(l.getSetPrice() != null);
		Language l2 = new English();
		assertTrue(l2.getSetPrice() != null);
	}

	@Test
	public void testGetChooseOrder() {
		Language l = new Italiano();
		assertTrue(l.getChooseOrder() != null);
		Language l2 = new English();
		assertTrue(l2.getChooseOrder() != null);
	}

	@Test
	public void testGetMarket() {
		Language l = new Italiano();
		assertTrue(l.getMarket() != null);
		Language l2 = new English();
		assertTrue(l2.getMarket() != null);
	}

	@Test
	public void testGetOrders() {
		Language l = new Italiano();
		assertTrue(l.getOrders() != null);
		Language l2 = new English();
		assertTrue(l2.getOrders() != null);
	}

	@Test
	public void testGetBalcony() {
		Language l = new Italiano();
		assertTrue(l.getBalcony() != null);
		Language l2 = new English();
		assertTrue(l2.getBalcony() != null);
	}

	@Test
	public void testGetInfo() {
		Language l = new Italiano();
		assertTrue(l.getInfo() != null);
		Language l2 = new English();
		assertTrue(l2.getInfo() != null);
	}

	@Test
	public void testGetHelpers() {
		Language l = new Italiano();
		assertTrue(l.getHelpers() != null);
		Language l2 = new English();
		assertTrue(l2.getHelpers() != null);
	}

	@Test
	public void testGetPrice() {
		Language l = new Italiano();
		assertTrue(l.getPrice() != null);
		Language l2 = new English();
		assertTrue(l2.getPrice() != null);
	}

	@Test
	public void testGetInfoYou() {
		Language l = new Italiano();
		assertTrue(l.getInfoYou() != null);
		Language l2 = new English();
		assertTrue(l2.getInfoYou() != null);
	}

	@Test
	public void testGetInfoPlayer() {
		Language l = new Italiano();
		assertTrue(l.getInfoPlayer() != null);
		Language l2 = new English();
		assertTrue(l2.getInfoPlayer() != null);
	}

	@Test
	public void testGetInfoGame() {
		Language l = new Italiano();
		assertTrue(l.getInfoGame() != null);
		Language l2 = new English();
		assertTrue(l2.getInfoGame() != null);
	}

	@Test
	public void testGetInfoOthers() {
		Language l = new Italiano();
		assertTrue(l.getInfoOthers() != null);
		Language l2 = new English();
		assertTrue(l2.getInfoOthers() != null);
	}

	@Test
	public void testGetInfoCity() {
		Language l = new Italiano();
		assertTrue(l.getInfoCity() != null);
		Language l2 = new English();
		assertTrue(l2.getInfoCity() != null);
	}

	@Test
	public void testGetEmporiaOfPlayers() {
		Language l = new Italiano();
		assertTrue(l.getInfoCity() != null);
		Language l2 = new English();
		assertTrue(l2.getInfoCity() != null);
	}

	@Test
	public void testGetNoEmporia() {
		Language l = new Italiano();
		assertTrue(l.getNoEmporia() != null);
		Language l2 = new English();
		assertTrue(l2.getNoEmporia() != null);
	}

	@Test
	public void testGetYouNoBusiness() {
		Language l = new Italiano();
		assertTrue(l.getYouNoBusiness() != null);
		Language l2 = new English();
		assertTrue(l2.getYouNoBusiness() != null);
	}

	@Test
	public void testGetYouNoEmporia() {
		Language l = new Italiano();
		assertTrue(l.getYouNoEmporia() != null);
		Language l2 = new English();
		assertTrue(l2.getYouNoEmporia() != null);
	}

}
