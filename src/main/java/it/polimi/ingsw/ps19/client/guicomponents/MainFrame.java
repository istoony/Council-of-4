package it.polimi.ingsw.ps19.client.guicomponents;


import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6108686046610655235L;
	
	protected static final int MWIDTH = 1200;
	protected static final int MHEIGHT = 800;
	
	private static final String NAME = "CO4, GAME ";
	private ClientModel model;
	InfoPanel infobox;
	MapPanel mpanel;
	
	protected MainFrame(int id, ClientModel model){
		super(NAME+id);
		this.model=model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		
		setBounds(100, 60, MWIDTH, MHEIGHT);
		setMinimumSize(this.getSize());
		//setExtendedState(JFrame.MAXIMIZED_BOTH); 
		getContentPane().setLayout(new BorderLayout());
	}
	
	protected void initialize(){
		mpanel = new MapPanel();
		mpanel.initialize(model);

		getContentPane().add(mpanel, BorderLayout.CENTER);
		mpanel.setPreferredSize(new Dimension((int) (0.65*MWIDTH), MHEIGHT));
		mpanel.setVisible(true);

		
		infobox = new InfoPanel();
		infobox.initialize(this);
		getContentPane().add(infobox, BorderLayout.EAST);
		infobox.setPreferredSize(new Dimension((int) (0.35*MWIDTH), MHEIGHT));
	}

	
	protected void updateInfoBox(ClientModel m){
		model = m;
		infobox.update(this);
	}
	
	protected void updateMap(ClientModel m){
		model = m;
		mpanel.update(this);
	}
	
	protected void drawRoads(){
		mpanel.drawRoads();
	}
	
	
	
	/**
	 * @return the model
	 */
	public ClientModel getModel() {
		return model;
	}
	
}
