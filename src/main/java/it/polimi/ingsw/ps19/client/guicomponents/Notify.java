package it.polimi.ingsw.ps19.client.guicomponents;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Notify extends JFrame implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4932512229185402449L;
	private static final int N = 10;
	private List<JLabel> text;
	
	public Notify(String s){
		super();
		setResizable(false);
		setTitle("Messages");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		text = new ArrayList<>();
		text.add(new JLabel(s));
		setLayout(new GridLayout(N, 1));
		setVisible(false);
	}
	
	public void addMessage(String s){
		text.add(new JLabel(s));
		setStuff();
	}
		
	@Override
	public void run() {
		setBounds(300, 300, 300, 400);
		pack();
		setVisible(true);
		setAutoRequestFocus(true);
	}
	
	public void setStuff(){
		setAutoRequestFocus(true);
		setAlwaysOnTop(true);
		List<JLabel> temp;
		if(text.size()>N){
			temp = text.subList(4, text.size());
			text.clear();
			text=temp;
		}
		for(JLabel j: text){
			j.setVisible(true);
			add(j);
		}
	}
}
