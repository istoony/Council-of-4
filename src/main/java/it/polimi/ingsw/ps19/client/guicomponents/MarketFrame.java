package it.polimi.ingsw.ps19.client.guicomponents;


import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.polimi.ingsw.ps19.client.ClientGUI;
import it.polimi.ingsw.ps19.client.ClientUI;
import it.polimi.ingsw.ps19.client.language.Language;
import it.polimi.ingsw.ps19.model.Order;

public class MarketFrame extends JFrame implements Runnable{
	
	private static final long serialVersionUID = -2543651389696923487L;
	private Language language;
	private JPanel pane;
	private JLabel text;
	private List<JButton> choices;		
		
	/**
	 * @param orders current orders
	 * @param gui the GUI
	 * @param l language
	 */
	public MarketFrame(List<Order> orders, ClientGUI gui, Language l){
		super();
		language = l;
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLayout(new BorderLayout());
		choices = new ArrayList<>();
		pane = new JPanel();
		text = new JLabel("Buttons indicate price, look the tooltip for more info about the Order");
		setTitle(language.getMarket());
		text.setToolTipText(language.getChooseOrder());
		add(text, BorderLayout.NORTH);
		int i=0;
		for(Order  o : orders){
			createButtons(o, gui, i);
			i++;
		}
		addButtons();
		}
		
	private void createButtons(Order order, ClientGUI c, int code){	
		try
		{
			JButton j = new JButton(((Integer)order.getPrice()).toString());
			j.setToolTipText(language.getString(order));
			j.setActionCommand(((Integer)code).toString());
			j.addActionListener(c);
			j.setEnabled(true);
			j.setVisible(true);
			choices.add(j);
		}
		catch(NullPointerException e)
		{
			ClientUI.log.log(e);
			JButton j = new JButton(language.getNothing());
			j.setToolTipText(language.getNothing());
			j.setActionCommand(language.getNothing());
			j.addActionListener(c);
			j.setEnabled(true);
			j.setVisible(true);
			choices.add(j);
		}
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
		
	/**
	 * close the frame
	 */
	public void close(){
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
	}
}
