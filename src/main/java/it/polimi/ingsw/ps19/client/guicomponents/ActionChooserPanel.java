package it.polimi.ingsw.ps19.client.guicomponents;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import it.polimi.ingsw.ps19.client.clientaction.ClientActionChooser;

public class ActionChooserPanel extends JPanel implements ActionListener {

	private static final int MAIN_ACTION = 4;
	private static final int FAST_ACTION = 5;
	
	private List<JButton> actionlist;
	ClientActionChooser actionchoosen;
	
	public ActionChooserPanel() {
		super();
		actionlist= new ArrayList<>();
		//setLayout(new GridLayout(1,c.possibleActions().size()));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
