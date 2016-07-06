package it.polimi.ingsw.ps19.client.guicomponents;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.model.Market;
import it.polimi.ingsw.ps19.model.Order;

public class MarketShow extends JFrame implements Runnable{
	

	private static final long serialVersionUID = -8125141719159785399L;
	
	private List<JPanel> panels;
	private List<JLabel> text;
	Language language = null;
		
	/**
	 * create a frame of the selected market and language
	 * @param market
	 * @param l
	 */
	public MarketShow(Market market, Language l){
		super();
		text = new ArrayList<>();
		panels = new ArrayList<>();
		language = l;
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
		JLabel l = new JLabel(language.getPlayer() + ": " + id);
		text.add(l);
		String s= language.getBusinessCards() + ": ";
		if(order.getBusinesscard().isEmpty())
			s+="0";
		else
		{
			s += language.getString(order.getBusinesscard().get(0));
			for(int i = 1; i < order.getBusinesscard().size(); i++)
				s = s.concat(language.getString(order.getBusinesscard().get(i)) + ", ");
		}	
		l = new JLabel(s);
		text.add(l);
		
		s = language.getPoliticCards() + ": ";
		if(order.getPoliticscard().isEmpty())
			s+="0";
		else
		{
			s += language.getString(order.getPoliticscard().get(0));
			for(int i = 1; i < order.getPoliticscard().size(); i++)
				s = s.concat(language.getString(order.getPoliticscard().get(i)) + ", ");
		}	
		l = new JLabel(s);
		text.add(l);
		
		l = new JLabel(language.getHelpers() + ": " + order.getHelper());
		text.add(l);
		
		l = new JLabel(language.getPrice() + ": " + order.getPrice());
		text.add(l);
		
		for(JLabel t : text)
			p.add(t);
		
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

