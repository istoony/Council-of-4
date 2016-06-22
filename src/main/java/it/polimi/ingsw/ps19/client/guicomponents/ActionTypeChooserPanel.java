package it.polimi.ingsw.ps19.client.guicomponents;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ActionTypeChooserPanel extends JPanel implements ActionListener{
	
	private static final String MAIN_ACTION_TEXT = "Main Actions";
	private static final String FAST_ACTION_TEXT = "Fast Actions";
	private static final String MAIN_ACTION_TOOLT = "Take a Main Actions";
	private static final String FAST_ACTION_TOOLT= "Take a Fast Actions";
	private static final String MAIN_ACTION_COMMAND = "Take a Main Actions";
	private static final String FAST_ACTION_COMMAND= "Take a Fast Actions";
	
	private JButton mainButton;
	private JButton fastButton;

	
	public ActionTypeChooserPanel() {
		super();
		setLayout(new GridLayout(1,2));

		mainButton = new JButton(MAIN_ACTION_TEXT);
		mainButton.setToolTipText(MAIN_ACTION_TOOLT);
		mainButton.setActionCommand(MAIN_ACTION_COMMAND);
		mainButton.addActionListener(this);
		mainButton.setEnabled(true);
		
		fastButton = new JButton(FAST_ACTION_TEXT);
		fastButton.setToolTipText(FAST_ACTION_TOOLT);
		fastButton.setActionCommand(FAST_ACTION_COMMAND);
		fastButton.addActionListener(this);
		fastButton.setEnabled(false);
		
		add(mainButton);
		add(fastButton);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg) {
		if(arg.getActionCommand().equals(MAIN_ACTION_COMMAND)){
			//TODO send command and get a clientactionchooser
			// 
		}
		else if(arg.getActionCommand().equals(FAST_ACTION_COMMAND)){
			
		}
	}
	
}
