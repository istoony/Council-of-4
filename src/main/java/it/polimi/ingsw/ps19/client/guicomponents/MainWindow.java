package it.polimi.ingsw.ps19.client.guicomponents;

import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.client.language.Language;


public class MainWindow implements Runnable {

	private MainFrame frame;
	private ClientModel model;
	private static int idgame=0;
	private Language language;

	
	/**
	 * Create the application.
	 */
	public MainWindow(ClientModel m, Language l) 
	{
		language = l;
		model = m;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new MainFrame(idgame, model, language);
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

	/**
	 * @return the frame
	 */
	public MainFrame getFrame() {
		return frame;
	}

}
