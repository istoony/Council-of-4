package it.polimi.ingsw.ps19.client.guicomponents;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

import it.polimi.ingsw.ps19.client.ClientGUI;
import it.polimi.ingsw.ps19.client.clientinput.ClientAction;
import it.polimi.ingsw.ps19.client.language.Language;


public class ActionChooserPanel extends JPanel implements Runnable{


	private static final long serialVersionUID = 2060889166987765721L;
	
	private List<JButton> actionlist;
	Language language;
	
	protected ActionChooserPanel(Language l) 
	{
		super();
		language = l;
		actionlist= new ArrayList<>();
		setVisible(true);

	}
	
	public void generateActions(List<ClientAction> actions){
		JButton j;
		setLayout(new GridLayout(1,actions.size()));
		for(ClientAction ca : actions){
			j=new JButton(language.getString(ca));
			j.setToolTipText(language.getString(ca));
			j.setActionCommand(language.getString(ca));
			j.setEnabled(true);
			j.setVisible(true);
			actionlist.add(j);
			add(j);
		}
		invalidate();
		repaint();
	}
	
	public synchronized void setListener(ClientGUI al){
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

	@Override
	public void run() {
		repaint();
		
	}

}
