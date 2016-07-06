package it.polimi.ingsw.ps19.client.guicomponents;


import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.client.language.Language;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6108686046610655235L;
	
	protected static final int MWIDTH = 1200;
	protected static final int MHEIGHT = 800;
	
	private static final String NAME = "CO4, GAME ";
	private ClientModel model;
	private Language language;
	InfoPanel infobox;
	MapPanel mpanel;
	
	/**
	 * create a mainframe with the current parameters
	 * @param id
	 * @param model
	 * @param l
	 */
	protected MainFrame(int id, ClientModel model, Language l)
	{
		super(NAME+id);
		language = l;
		this.model=model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		setBounds(100, 60, MWIDTH, MHEIGHT);
		setMinimumSize(this.getSize());
		getContentPane().setLayout(new BorderLayout());
	}
	
	/**
	 * start the GUI with the current clientmodel
	 */
	protected void initialize(){
		mpanel = new MapPanel(language);
		mpanel.initialize(model);

		getContentPane().add(mpanel, BorderLayout.CENTER);
		mpanel.setPreferredSize(new Dimension((int) (0.65*MWIDTH), MHEIGHT));
		mpanel.setVisible(true);

		
		infobox = new InfoPanel(language);
		infobox.initialize(this);
		getContentPane().add(infobox, BorderLayout.EAST);
		infobox.setPreferredSize(new Dimension((int) (0.35*MWIDTH), MHEIGHT));
	}

	
	/**
	 * update the info side
	 * @param m
	 */
	protected void updateInfoBox(ClientModel m){
		model = m;
		infobox.update(this);
	}
	
	/**
	 * update the map frame
	 * @param m
	 */
	protected void updateMap(ClientModel m){
		model = m;
		mpanel.update(this);
	}
	
	/**
	 * draw roads between cities
	 */
	protected void drawRoads(){
		mpanel.drawRoads();
	}
	
	
	
	/**
	 * @return the model
	 */
	public ClientModel getModel() {
		return model;
	}

	/**
	 * @return the infobox
	 */
	public InfoPanel getInfobox() {
		return infobox;
	}
	
}
