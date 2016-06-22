package it.polimi.ingsw.ps19.client.guicomponents;

import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;


public class MainWindow implements Runnable {

	private MainFrame frame;
	private ClientModel model;
	private static int idgame=0;

	
	/**
	 * Create the application.
	 */
	public MainWindow(ClientModel m) {
		model = m;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new MainFrame(idgame, model);
		frame.initialize();
	}

	@Override
	public void run() {
		initialize();
		frame.setVisible(true);
		frame.drawRoads();
		frame.setAlwaysOnTop(true);
		frame.setAutoRequestFocus(true);
	}
	
	public void update(ClientModel m){
		model=m;
		frame.updateInfoBox(model);
		frame.updateMap(model);
	}

}
