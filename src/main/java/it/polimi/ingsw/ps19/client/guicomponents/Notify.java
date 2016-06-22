package it.polimi.ingsw.ps19.client.guicomponents;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Notify extends JFrame implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4932512229185402449L;
	private JPanel main;
	private JLabel text;
	
	public Notify(String s){
		super();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		main = new JPanel();
		text = new JLabel(s);
		setVisible(false);
	}
		
	@Override
	public void run() {
		setBounds(200, 200, 300, 400);
		text.setVisible(true);
		main.setVisible(true);
		main.add(text);
		add(main);
		pack();
		setVisible(true);
		setAutoRequestFocus(true);
	}
}
