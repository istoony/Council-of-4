package it.polimi.ingsw.ps19.client.guicomponents;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.polimi.ingsw.ps19.client.ClientGUI;
import it.polimi.ingsw.ps19.client.language.Language;

public class ActionTypeChooserPanel extends JPanel{
	
	private static final long serialVersionUID = -2127659641196167037L;
	
	//TODO Remove
	private static final String MAIN_ACTION_TEXT = "Main Actions";
	private static final String FAST_ACTION_TEXT = "Fast Actions";
	private static final String MAIN_ACTION_TOOLT = "Take a Main Actions";
	private static final String FAST_ACTION_TOOLT= "Take a Fast Actions";

	
	private JButton mainButton;
	private JButton fastButton;
	private Language language;
	
	public ActionTypeChooserPanel(Language l) 
	{
		super();
		language = l;
		setLayout(new GridLayout(1,2));

		mainButton = new JButton(language.main);
		mainButton.setToolTipText(language.main);

		mainButton.setEnabled(false);
		
		fastButton = new JButton(language.quick);
		fastButton.setToolTipText(language.quick);

		fastButton.setEnabled(false);
		
		add(mainButton);
		add(fastButton);
		
	}
	
	protected void setListener(ClientGUI al){
		mainButton.setActionCommand(ClientGUI.getMainActionCommand());
		mainButton.addActionListener(al);
		fastButton.setActionCommand(ClientGUI.getFastActionCommand());
		fastButton.addActionListener(al);
	}
	
	protected void enableButtons(){
		mainButton.setEnabled(true);
		fastButton.setEnabled(true);
		repaint();
	}
	
	protected void disableButtons(){
		mainButton.setEnabled(false);
		fastButton.setEnabled(false);
		repaint();
	}
	
}
