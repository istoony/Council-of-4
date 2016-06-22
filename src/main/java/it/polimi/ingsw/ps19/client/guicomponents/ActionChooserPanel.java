package it.polimi.ingsw.ps19.client.guicomponents;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

import it.polimi.ingsw.ps19.client.ClientGUI;
import it.polimi.ingsw.ps19.client.clientaction.ClientAction;


public class ActionChooserPanel extends JPanel{


	private static final long serialVersionUID = 2060889166987765721L;
	
	private List<JButton> actionlist;
	
	protected ActionChooserPanel() {
		super();
		actionlist= new ArrayList<>();

	}
	
	public void generateActions(List<ClientAction> actions){
		JButton j;
		setLayout(new GridLayout(1,actions.size()));
		for(ClientAction ca : actions){
			j=new JButton(ca.toString());
			j.setToolTipText(ca.toString());
			j.setActionCommand(ca.toString());
			actionlist.add(j);
		}
	}
	
	public void setListener(ClientGUI al){
		for(JButton j : actionlist){
			j.addActionListener(al);
		}
	}
	
	public void disableButtons(){
		for(JButton j : actionlist){
			j.setEnabled(false);
		}
		repaint();
	}

}
