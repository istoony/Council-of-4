package it.polimi.ingsw.ps19.client.guicomponents;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ActionChooserPanel extends JPanel implements ActionListener{
	
	private static final int MAIN_ACTION = 4;
	private static final int FAST_ACTION = 5;
	
	private static final String MAIN_ACTION_TEXT = "Main Actions";
	private static final String FAST_ACTION_TEXT = "Fast Actions";
	private static final String MAIN_ACTION_TOOLT = "Take a Main Actions";
	private static final String FAST_ACTION_TOOLT= "Take a Fast Actions";
	private static final String MAIN_ACTION_COMMAND = "Take a Main Actions";
	private static final String FAST_ACTION_COMMAND= "Take a Fast Actions";
	
	private JButton mainButton;
	private JButton fastButton;
	
	public ActionChooserPanel() {
		super();
		setLayout(new FlowLayout());
		mainButton = new JButton(MAIN_ACTION_TEXT);
		mainButton.setToolTipText(MAIN_ACTION_TOOLT);
		mainButton.setActionCommand(MAIN_ACTION_COMMAND);
		mainButton.addActionListener(this);
		mainButton.setEnabled(true);
		//mainButton.setPreferredSize(new Dimension(50, 20));
		
		fastButton = new JButton(FAST_ACTION_TEXT);
		fastButton.setToolTipText(FAST_ACTION_TOOLT);
		fastButton.setActionCommand(FAST_ACTION_COMMAND);
		fastButton.addActionListener(this);
		fastButton.setEnabled(false);
		//fastButton.setPreferredSize(new Dimension(50, 20));
		
		add(mainButton);
		add(fastButton);
		
	}
	
	
	public ActionChooserPanel() {
		super();
		setLayout(new FlowLayout());
		mainButton = new JButton(MAIN_ACTION_TEXT);
		mainButton.setToolTipText(MAIN_ACTION_TOOLT);
		mainButton.setActionCommand(MAIN_ACTION_COMMAND);
		mainButton.addActionListener(this);
		mainButton.setEnabled(true);
		//mainButton.setPreferredSize(new Dimension(50, 20));
		
		fastButton = new JButton(FAST_ACTION_TEXT);
		fastButton.setToolTipText(FAST_ACTION_TOOLT);
		fastButton.setActionCommand(FAST_ACTION_COMMAND);
		fastButton.addActionListener(this);
		fastButton.setEnabled(false);
		//fastButton.setPreferredSize(new Dimension(50, 20));
		
		add(mainButton);
		add(fastButton);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
