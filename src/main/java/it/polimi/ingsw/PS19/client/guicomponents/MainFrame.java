package it.polimi.ingsw.PS19.client.guicomponents;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;

public class MainFrame extends JFrame {

	protected static final int MWIDTH = 1000;
	protected static final int MHEIGHT = 600;
	
	private static final String NAME = "CO4, GAME ";
	private int idgame;
	private ClientModel model;
	
	protected MainFrame(int id, ClientModel model){
		super(NAME+id);
		idgame = id;
		this.model=model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setBounds(100, 60, MWIDTH, MHEIGHT);
		setMinimumSize(this.getSize());
		getContentPane().setLayout(new BorderLayout());
	}
	
	protected void initialize(){
		MapPanel mpanel = new MapPanel();
		mpanel.initialize(model);

		getContentPane().add(mpanel, BorderLayout.CENTER);
		mpanel.setPreferredSize(new Dimension((int) (0.6*MWIDTH), MHEIGHT));
		mpanel.setVisible(true);

		
		InfoPanel infobox = new InfoPanel();
		infobox.initialize(this);
		getContentPane().add(infobox, BorderLayout.EAST);
		infobox.setPreferredSize(new Dimension((int) (0.3*MWIDTH), MHEIGHT));
	}

	/**
	 * @return the model
	 */
	public ClientModel getModel() {
		return model;
	}
	
}
