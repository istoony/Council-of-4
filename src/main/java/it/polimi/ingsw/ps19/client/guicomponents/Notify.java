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
	private static final int N = 11;
	private List<JLabel> text;
	private JPanel pan = new JPanel();
	
	public Notify(String s){
		super();
		setResizable(false);
		setTitle("Messages");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		text = new ArrayList<>();
		text.add(new JLabel(s));
		add(pan);
		pan.setLayout(new GridLayout(N, 1));
		setVisible(false);
	}
	
	public void addMessage(String s){
		text.add(new JLabel(s));
		setStuff();
		add(pan);
	}
		
	@Override
	public void run() {
		setBounds(300, 300, 300, 400);
		pack();
		setVisible(true);
		setAutoRequestFocus(true);
	}
	
	public synchronized void setStuff(){
		setAutoRequestFocus(true);
		setAlwaysOnTop(true);
		List<JLabel> temp = new ArrayList<>();
		List<JLabel> temp2 = new ArrayList<>();	
		temp2.addAll(text);
		if(temp2.size()>=N){
			pan.removeAll();
			repaint();
			temp = temp2.subList(2, text.size());
			text.clear();
			text=temp;
		}
		for(int i=0; i<text.size(); i++){
			text.get(i).setVisible(true);
			pan.add(text.get(i));
		}


	}
}
