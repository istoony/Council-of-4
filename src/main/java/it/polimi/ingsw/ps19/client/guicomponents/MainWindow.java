package it.polimi.ingsw.PS19.client.guicomponents;

import java.awt.Color;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.card.BusinessCard;
import it.polimi.ingsw.PS19.model.card.PoliticsCard;


public class MainWindow {

	private MainFrame frame;
	private ClientModel model;
	private static int idgame=0;

	
	/**
	 * Create the application.
	 */
	public MainWindow(ClientModel m) {
		model = m;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new MainFrame(idgame, model);
		frame.initialize();
	}
	
	
	public static void main(String args[]){
		Model mm = new Model(3);
		ClientModel m = new ClientModel(mm);
		m.getMyPlayer().setStartingAction();

		
		PoliticsCard c = new PoliticsCard(Color.decode("#FFFFFF"));
		PoliticsCard d = new PoliticsCard(Color.decode("#FFFFFF"));
		PoliticsCard e = new PoliticsCard(Color.decode("#000000"));
		PoliticsCard f = new PoliticsCard(Color.decode("#0000FF"));
		PoliticsCard g = new PoliticsCard(Color.decode("#FF0000"));
		PoliticsCard h = new PoliticsCard(Color.decode("#000000"));
		PoliticsCard j = new PoliticsCard(Color.decode("#0000FF"));
		PoliticsCard k = new PoliticsCard(Color.decode("#FF0000"));
		BusinessCard aa = new BusinessCard(null, null, m.getAllCities().get(0));
		
		m.getMyPlayer().addCardToHand(c);
		m.getMyPlayer().addCardToHand(d);
		m.getMyPlayer().addCardToHand(e);
		m.getMyPlayer().addCardToHand(f);
		m.getMyPlayer().addCardToHand(g);
		m.getMyPlayer().addCardToHand(h);
		m.getMyPlayer().addCardToHand(j);
		m.getMyPlayer().addCardToHand(k);
		m.getMyPlayer().addCardToHand(g);
		m.getMyPlayer().addCardToHand(h);
		m.getMyPlayer().addCardToHand(j);
		m.getMyPlayer().addCardToHand(k);
		m.getPlayer().get(1).addCardToHand(c);
		m.getPlayer().get(1).addCardToHand(e);
		m.getPlayer().get(1).addCardToHand(f);
		m.getMyPlayer().addCardToHand(aa);
		
		
		MainWindow window = new MainWindow(m);
		window.frame.setVisible(true);
		window.frame.drawRoads();

	}


}
