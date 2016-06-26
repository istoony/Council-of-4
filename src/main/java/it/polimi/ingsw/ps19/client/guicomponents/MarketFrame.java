package it.polimi.ingsw.ps19.client.guicomponents;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import it.polimi.ingsw.ps19.client.ClientGUI;
import it.polimi.ingsw.ps19.model.Order;
import it.polimi.ingsw.ps19.model.card.BusinessCard;

public class MarketFrame extends JFrame implements Runnable{
	
	private static final long serialVersionUID = -2543651389696923487L;
	private JPanel pane;
	private JLabel text;
	private List<JButton> choices;		
		
	public MarketFrame(List<Order> orders, ClientGUI gui){
		super();
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(new BorderLayout());
		choices = new ArrayList<>();
		pane = new JPanel();
		text = new JLabel("Buttons indicate price, look the tooltip for more info about the Order");
		setTitle("Market");
		text.setToolTipText("Choose what to buy");
		add(text, BorderLayout.NORTH);
		int i=0;
		for(Order  o : orders){
			createButtons(o, gui, i);
			i++;
		}
		addButtons();
		}
		
	private void createButtons(Order order, ClientGUI c, int code){
		JButton j = new JButton(((Integer)order.getPrice()).toString());
		j.setToolTipText(orderToString(order));
		j.setActionCommand(((Integer)code).toString());
		j.addActionListener(c);
		j.setEnabled(true);
		j.setVisible(true);
		choices.add(j);
	}
		
	private String orderToString(Order order){
		String s="Business cards: ";
		if(order.getBusinesscard().isEmpty()){
			s="Politic cards: ";
		}
		else{
			for(BusinessCard b : order.getBusinesscard()){
				s+= b.toStringCities()+", ";
			}	
			s+="Politic cards: ";
		}
		if(order.getPoliticscard().isEmpty()){
			if(order.getBusinesscard().isEmpty()){
				s = "Helpers: ";
			}
			s+="Helpers: ";
		}
		else{
			for(Color c : order.getPoliticscard()){
				s+= QuestionFrame.colorString(c)+", ";
			}
			s+="Helpers: ";
		}
		if(order.getHelper()==0){
			return s;
		}
		else {
			s+= ((Integer)order.getHelper()).toString();
		}
		return s;
	}
	
	
	private void addButtons(){
		for(JButton j : choices){
			pane.add(j);
		}
		pane.setVisible(true);
		add(pane, BorderLayout.CENTER);
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
}
