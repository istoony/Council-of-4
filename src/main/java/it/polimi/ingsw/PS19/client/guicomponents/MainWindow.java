package it.polimi.ingsw.PS19.client.guicomponents;

import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.model.Model;


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
		MainWindow window = new MainWindow(m);
		window.frame.setVisible(true);

	}


}
