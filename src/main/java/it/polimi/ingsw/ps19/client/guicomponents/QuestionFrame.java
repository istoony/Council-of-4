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
import it.polimi.ingsw.ps19.client.language.Language;
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
	private Language language = null;
	
	private JTextField input;
	
	public QuestionFrame(ClientGUI gui, List<?> obj, Language l)
	{
		super();
		setLanguage(l);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		choices = new ArrayList<>();
		pane = new JPanel();
		try{
			if(obj.get(0)==null){
			setTitle("Choose");
			pane.setToolTipText("Choose");
			for(int i = 0; i < obj.size(); i++){
				createButtons(language.nothing, gui);
			}
		}
		else if(obj.get(0) instanceof RegionType){
			setTitle(language.chooseRegionTitle);
			pane.setToolTipText(language.chooseRegionTitle);
			for(Object  o : obj){
				createButtons(language.getString((RegionType)o), gui);
			}
		}
		else if(obj.get(0) instanceof Color){
			setTitle(language.chooseColor);
			pane.setToolTipText(language.chooseColor);
			for(Object  o : obj){
				createButtons(language.getString((Color)o), gui);
			}
		}
		else if(obj.get(0) instanceof PoliticsCard){
			setTitle(language.choosePoliticCardTitle);
			pane.setToolTipText(language.choosePoliticCardTitle);
			for(Object  o : obj){
				createButtons(language.getString((PoliticsCard)o), gui);
			}
		}
		else if(obj.get(0) instanceof City){
			setTitle(language.chooseCityTitle);
			pane.setToolTipText(language.chooseCityTitle);
			for(Object  o : obj){
				createButtons(language.getString((City)o), gui);
			}
		}
		else if(obj.get(0) instanceof BusinessCard){
			setTitle(language.chooseBusinessCardTitle);
			pane.setToolTipText(language.chooseBusinessCardTitle);
			for(Object  o : obj){
				createButtons(language.getString((BusinessCard)o), gui);
			}
		}
		}
		catch(NullPointerException e){
			createButtons(language.nothing, gui);
		}
		
		addButtons();
	}
	
	
	public QuestionFrame(ClientGUI gui, int n, Language l)
	{
		super();
		setLanguage(l);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		choices = new ArrayList<>();
		pane = new JPanel();
		setTitle(language.howManyHelpersToSell);
		pane.setToolTipText(language.howManyHelpersToSell);
		for(Integer i=0; i<=n; i++){
			createButtons(i.toString(), gui);
		}
		addButtons();
	}
	
	public QuestionFrame(ClientGUI gui, String s, Language l)
	{
		super();
		setLanguage(l);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		choices = new ArrayList<>();
		pane = new JPanel();
		JLabel text = new JLabel();
		if(s.equals("")){
			setTitle(language.setPrice);
			text.setText(language.setPrice);
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
		
		input = new JTextField(20);
		input.addActionListener(gui);
		pane.add(input);
		pane.setVisible(true);
		add(pane);
	}
	
	
	public QuestionFrame(ClientGUI gui, Map<City, Integer> citiesECost, Language l)
	{
		super();
		setLanguage(l);
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		choices = new ArrayList<>();
		pane = new JPanel();
		
		
		setTitle(language.chooseCityTitle);
		pane.setToolTipText(language.chooseCityTitle);
		for(Entry<City, Integer> entry: citiesECost.entrySet()){
			createButtons(language.getString(entry.getKey()) + "(" + entry.getValue().toString() + ")", gui);
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
	
	/**
	 * @return the input
	 */
	public JTextField getInput() {
		return input;
	}
	
	private void setLanguage(Language l)
	{
		if(language == null)
			language = l;
	}
	
}
