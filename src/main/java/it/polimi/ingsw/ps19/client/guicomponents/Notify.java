package it.polimi.ingsw.ps19.client.guicomponents;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Notify extends JFrame implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4932512229185402449L;
	private JPanel main;
	private List<JLabel> text;
	
	public Notify(String s){
		super("Messages");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		main = new JPanel();
		text = new ArrayList<>();
		text.add(new JLabel(s));
		setLayout(new GridLayout(text.size(), 1));
		setVisible(false);
	}
	
	public void addMessage(String s){
		text.add(new JLabel(s));
		setStuff();
	}
		
	@Override
	public void run() {
		setBounds(300, 300, 300, 400);
		main.setVisible(true);
		add(main);
		pack();
		setVisible(true);
		setAutoRequestFocus(true);
	}
	
	public void setStuff(){
		setLayout(new GridLayout(text.size(), 1));
		for(JLabel j: text){
			j.setVisible(true);
			main.add(j);
		}
	}
}
