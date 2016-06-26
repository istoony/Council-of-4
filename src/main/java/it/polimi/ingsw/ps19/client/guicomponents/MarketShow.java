package it.polimi.ingsw.ps19.client.guicomponents;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.polimi.ingsw.ps19.model.Market;
import it.polimi.ingsw.ps19.model.Order;
import it.polimi.ingsw.ps19.model.card.BusinessCard;

public class MarketShow extends JFrame implements Runnable{
	

	private static final long serialVersionUID = -8125141719159785399L;
	
	private List<JPanel> panels;
	private List<JLabel> text;
		
	public MarketShow(Market market){
		super();
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Market");
		JPanel jp;
		for(Entry<Integer, Order>  m : market.getListoforder().entrySet()){
			jp=createPanel(m.getValue(), m.getKey());
			panels.add(jp);
		}
		for(JPanel p: panels){
			add(p);
		}
	}
		
	
	private JPanel createPanel(Order order, int id){
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		p.setLayout(new GridLayout(6, 1));
		JLabel l = new JLabel("Order of player "+id);
		text.add(l);
		
		String s="Business cards: ";
		if(order.getBusinesscard().isEmpty()){
			s+="none";
		}
		else{
			for(BusinessCard b : order.getBusinesscard()){
				s+= b.toStringCities()+", ";
			}	
		}	
		l = new JLabel(s);
		text.add(l);
		
		s="Politic cards: ";
		if(order.getPoliticscard().isEmpty()){
			s+="none";
		}
		else{
			for(Color c : order.getPoliticscard()){
				s+= QuestionFrame.colorString(c)+", ";
			}	
		}	
		l = new JLabel(s);
		text.add(l);
		
		l = new JLabel("Helpers: "+order.getHelper());
		text.add(l);
		
		l = new JLabel("Price: "+order.getPrice());
		text.add(l);
		
		for(JLabel t : text){
			p.add(t);
		}
		
		return p;
	}		
		
	
	@Override
	public void run() {
		setBounds(300, 300, 300, 400);
		pack();
		setVisible(true);
		setAutoRequestFocus(true);
	}
		
}

