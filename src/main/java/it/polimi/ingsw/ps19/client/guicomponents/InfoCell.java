package it.polimi.ingsw.ps19.client.guicomponents;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.polimi.ingsw.ps19.client.ClientGUI;
import it.polimi.ingsw.ps19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.ps19.model.Player;
import it.polimi.ingsw.ps19.model.bonus.Bonus;
import it.polimi.ingsw.ps19.model.card.BusinessCard;
import it.polimi.ingsw.ps19.model.card.PoliticsCard;
import it.polimi.ingsw.ps19.model.map.NobilityPath;

public class InfoCell extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8704625760479949960L;
	
	private static final String TOOLTIP0 = "Information about the game.";
	private static final String TOOLTIP1 = "Information about the player.";
	private static final String TOOLTIP2 = "Information about the other players.";
	private static final String INDENTATION = "    ";
	private static final String INDENTATION_2ND_POLITIC ="                                         ";
	
	ClientGUI listener;
	
	private List<JLabel> infos;
	private JFrame nobility;
	private ActionTypeChooserPanel actionsType;
	private ActionChooserPanel actions;
	
	protected InfoCell(){
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setVisible(true);
		infos = new ArrayList<>();
	}
	
	protected void setInfo(ClientModel m){
		setLayout(new GridLayout(7, 0));
		setToolTipText(TOOLTIP0);
		infos = constructInfoCell(m);
		for(JLabel jl : infos){
			jl.setVisible(true);
			add(jl);
		}
		NobilityButton nb = new NobilityButton(m.getNobilitypath());
		nb.addActionListener(this);
		add(nb);
		
		actionsType = new ActionTypeChooserPanel();
		add(actionsType);

		actions = new ActionChooserPanel();
		add(actions);
	}
	
	public void setListerner(ClientGUI g){
		listener=g;
		actionsType.setListener(g);
	}
	
	public void enableActionType(){
		actionsType.enableButtons();
	}
	
	public void disableActionType(){
		actionsType.disableButtons();
	}
	
	
	
	protected void updateInfo(ClientModel m){
		removeAll();
		infos.clear();
		setInfo(m);
	}

	protected void setInfo(Player p){
		setLayout(new GridLayout(8, 0));
		setToolTipText(TOOLTIP2);
		infos = constructPlayerCell(p);
		for(JLabel jl : infos){
			jl.setVisible(true);
			add(jl);
		}
	}
	
	protected void updateInfo(Player p){
		removeAll();
		infos.clear();
		setInfo(p);
	}
	
	protected void setMyInfo(Player p){
		setLayout(new GridLayout(12, 0));
		setToolTipText(TOOLTIP1);
		infos = constructMyPlayerCell(p);
		for(JLabel jl : infos){
			jl.setVisible(true);
			add(jl);
		}
	}
	
	protected void updateMyInfo(Player p){
		removeAll();
		infos.clear();
		setMyInfo(p);
	}
	
	private ArrayList<JLabel> constructInfoCell(ClientModel m){
		ArrayList<JLabel> lst = new ArrayList<>();
		String s = "";
		lst.add(new JLabel(INDENTATION+"--- Information about the Game ---"));
		lst.add(new JLabel(INDENTATION+"The King is in: "+m.getKing().getCurrentcity().getName()));
		s=m.getKing().getBalcony().toString();
		lst.add(new JLabel(INDENTATION+"The King council is: "+s));
		lst.add(new JLabel(INDENTATION+"It's the turn of the player number "+m.getActiveplayer()));
		return lst;
	}
	
	private ArrayList<JLabel> constructPlayerCell(Player p){
		ArrayList<JLabel> lst = new ArrayList<>();
		lst.add(new JLabel(INDENTATION+"--- Information about the Player n"+p.getId()+" ---"));
		lst.add(new JLabel(INDENTATION+"Victory Points: "+p.getVictoryPoints()));
		lst.add(new JLabel(INDENTATION+"Nobility Path: "+p.getNobilityPoints()));
		lst.add(new JLabel(INDENTATION+"Money: "+p.getMoney()));
		lst.add(new JLabel(INDENTATION+"Emporia left: "+(p.getMaxemporia())));
		lst.add(new JLabel(INDENTATION+"Business Cards in hand: "+p.getFreebusinesscard().size()));
		lst.add(new JLabel(INDENTATION+"Politic Cards in hand: "+p.getPoliticcard().size()));
		lst.add(new JLabel(INDENTATION+"Helpers: "+p.getHelpers()));
		return lst;
	}
	
	private ArrayList<JLabel> constructMyPlayerCell(Player p){
		ArrayList<JLabel> lst = new ArrayList<>();
		String s="";
		String reserve= "";
		int count=0;
		lst.add(new JLabel(INDENTATION+"--- Information about YOU --- "));
		lst.add(new JLabel(INDENTATION+"Victory Points: "+p.getVictoryPoints()));
		lst.add(new JLabel(INDENTATION+"Nobility Path: "+p.getNobilityPoints()));
		lst.add(new JLabel(INDENTATION+"Money: "+p.getMoney()));
		lst.add(new JLabel(INDENTATION+"Emporia left: "+(p.getMaxemporia())));
		for(PoliticsCard c : p.getPoliticcard()){
			count++;
			if(count<10){
				s+=c.toString()+", ";
			}
			else{
				reserve +=c.toString()+", ";
			}
		}
		lst.add(new JLabel(INDENTATION+"Politic Cards in hand: "+s));
		if(reserve!=""){
			lst.add(new JLabel(INDENTATION+INDENTATION_2ND_POLITIC+reserve));
		}
		lst.add(new JLabel(INDENTATION+"Helpers: "+p.getHelpers()));
		lst.add(new JLabel(INDENTATION+"Business Cards: "));
		for(BusinessCard c : p.getFreebusinesscard()){
			lst.add(new JLabel(INDENTATION+INDENTATION+INDENTATION+"Card: "+c.toStringCities()));
		}
		return lst;
		
	}

	private void showNobility(NobilityPath p){
		if(nobility!=null){
			nobility.dispatchEvent(new WindowEvent(nobility, WindowEvent.WINDOW_CLOSING));
		}
		nobility = createNobility(p);
		nobility.setLocationRelativeTo(this);
		nobility.setAlwaysOnTop(true);
		nobility.setVisible(true);
		nobility.setResizable(false);
	}
	
	private JFrame createNobility(NobilityPath p){
		JFrame f = new JFrame("Nobility Path");
		f.setSize(550, 600);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setLayout(new GridLayout(30, 2));
		
		JLabel pos = new JLabel(INDENTATION+"position:");
		JLabel title = new JLabel("bonus");
		pos.setVisible(true);
		title.setVisible(true);
		
		f.add(pos);
		f.add(title);
		
		for(int i = 0; i <= p.getMaxKey(); i++){
			String str = INDENTATION;
			pos = new JLabel(INDENTATION+i);
			if(p.getNobility().get(i)!=null){
				for(Bonus b : p.getNobility().get(i)){
					str += b.toString()+", ";
				}
				title = new JLabel(str);
			}
			else{
				title = new JLabel(str);
			}
			pos.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			title.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			pos.setVisible(true);
			title.setVisible(true);
			f.add(pos);
			f.add(title);
		}
		f.pack();
		return f;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(NobilityButton.NOBILITY_COMMAND)){
			showNobility(((NobilityButton) e.getSource()).getPath());
		}
		
	}

	/**
	 * @return the actions
	 */
	public ActionChooserPanel getActions() {
		return actions;
	}

}
