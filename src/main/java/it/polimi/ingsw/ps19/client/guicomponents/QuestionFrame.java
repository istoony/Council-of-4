package it.polimi.ingsw.ps19.client.guicomponents;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.polimi.ingsw.ps19.client.ClientGUI;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
import it.polimi.ingsw.ps19.model.map.City;
import it.polimi.ingsw.ps19.model.parameter.RegionType;

public class QuestionFrame extends JFrame implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3228711242324950944L;
	
	private JPanel pane;
	private List<JButton> choices;
	
	private JTextField input;
	
	public QuestionFrame(ClientGUI gui, List<?> obj){
		super();
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		choices = new ArrayList<>();
		pane = new JPanel();
		
		if(obj.get(0) instanceof RegionType){
			setTitle("Choose the region");
			pane.setToolTipText("Choose the region");
			for(Object  o : obj){
				createButtons(((RegionType)o).toString(), gui);
			}
		}
		else if(obj.get(0) instanceof Color){
			setTitle("Choose the color");
			pane.setToolTipText("Choose the color");
			for(Object  o : obj){
				createButtons((colorString((Color)o)), gui);
			}
		}
		else if(obj.get(0) instanceof PoliticsCard){
			setTitle("Choose the card");
			pane.setToolTipText("Choose the card");
			for(Object  o : obj){
				createButtons(((PoliticsCard)o).toString(), gui);
			}
		}
		else if(obj.get(0) instanceof City){
			setTitle("Choose the city");
			pane.setToolTipText("Choose the city");
			for(Object  o : obj){
				createButtons(((City)o).getName(), gui);
			}
		}
		else if(obj.get(0) instanceof BusinessCard){
			setTitle("Choose the business card");
			pane.setToolTipText("Choose business card");
			for(Object  o : obj){
				createButtons(((BusinessCard)o).toStringCities()+"\n"+((BusinessCard)o).toStringBonus(), gui);
			}
		}
		
		addButtons();
	}
	
	
	public QuestionFrame(ClientGUI gui, int n){
		super();
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		choices = new ArrayList<>();
		pane = new JPanel();
		setTitle("Choose how many Helpers to sell");
		for(Integer i=0; i<=n; i++){
			createButtons(i.toString(), gui);
		}
		addButtons();
	}
	
	public QuestionFrame(ClientGUI gui, String s){
		super();
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		choices = new ArrayList<>();
		pane = new JPanel();
		JLabel text = new JLabel();
		if(s.equals("")){
			setTitle("Insert the Price");
			text.setText("Insert the price");
		}
		else {
			setTitle("Insertion Panel");
			text.setText(s);
		}
		JPanel title = new JPanel();
		setLayout(new GridLayout(2, 1));
		title.setVisible(true);
		text.setVisible(true);
		title.add(text);
		add(title);
		
		input = new JTextField();
		input.addActionListener(gui);
		pane.add(input);
		pane.setVisible(true);
		add(pane);
	}
	
	
	public QuestionFrame(ClientGUI gui, Map<City, Integer> citiesECost) {
		super();
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		choices = new ArrayList<>();
		pane = new JPanel();
		
		
		setTitle("Choose the city");
		pane.setToolTipText("Choose the city");
		for(Entry<City, Integer> entry: citiesECost.entrySet()){
			createButtons(entry.getKey().getName()+"("+entry.getValue()+")", gui);
		}
		
		addButtons();
	}


	private void createButtons(String s, ClientGUI c){
		JButton j = new JButton(s);
		j.setToolTipText(s);
		j.setActionCommand(s);
		j.addActionListener(c);
		j.setEnabled(true);
		j.setVisible(true);
		choices.add(j);
	}
	
	private void addButtons(){
		for(JButton j : choices){
			pane.add(j);
		}
		pane.setVisible(true);
		add(pane);
	}
	
	
	
	@Override
	public void run() {
		setBounds(300, 300, 300, 400);
		pack();
		setVisible(true);
		setAutoRequestFocus(true);
	}
	
	public void close(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}

	
	private String colorString(Color c){
		String s="";
		if(c.equals(Color.decode("#FF0000"))){
			s= "Red";
		}
		else if(c.equals(Color.decode("#0000FF"))){
			s= "Blue";
		}
		else if(c.equals(Color.decode("#FF7F00"))){
			s= "Orange";
		}
		else if(c.equals(Color.decode("#000000"))){
			s= "White";
		}
		else if(c.equals(Color.decode("#FFFFFF"))){
			s=  "Black";
		}
		else if(c.equals(Color.decode("#FFC0CB"))){
			s= "Pink";
		}
		return s;
	}
	
	
	

	/**
	 * @return the input
	 */
	public JTextField getInput() {
		return input;
	}
	
}
