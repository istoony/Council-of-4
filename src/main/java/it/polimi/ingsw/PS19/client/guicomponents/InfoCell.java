package it.polimi.ingsw.PS19.client.guicomponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.card.PoliticsCard;

public class InfoCell extends JPanel{
	
	private static final String TOOLTIP0 = "Information about the game.";
	private static final String TOOLTIP1 = "Information about the player.";
	private static final String TOOLTIP2 = "Information about the other players.";
	private static final String INDENTATION = "    ";
	private static final String INDENTATION_2ND_POLITIC ="                                         ";
	
	private List<JLabel> infos;
	
	protected InfoCell(){
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setVisible(true);
		setLayout(new GridLayout(9, 0));
		infos = new ArrayList<>();
	}
	
	protected void setInfo(ClientModel m){
		setToolTipText(TOOLTIP0);
		infos = constructInfoCell(m);
		for(JLabel jl : infos){
			jl.setVisible(true);
			add(jl);
		}
	}
	
	protected void updateInfo(ClientModel m){
		removeAll();
		infos.clear();
		setInfo(m);
	}

	protected void setInfo(Player p){
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
		ArrayList<JLabel> colors = new ArrayList<>();
		lst.add(new JLabel(INDENTATION+"--- Information about the Game ---"));
		lst.add(new JLabel(INDENTATION+"The King is in: "+m.getKing().getCurrentcity().getName()));
		for(int i=0; i<m.getKing().getBalcony().getCouncilcolor().size(); i++){
			colors.add(new JLabel());
			colors.get(i).setBackground(m.getKing().getBalcony().getCouncilcolor().get(i));
			colors.get(i).setVisible(true);
		}
		lst.add(new JLabel(INDENTATION+"The King council is: "));
		lst.addAll(colors);
		lst.add(new JLabel(INDENTATION+"It's the turn of the player number "+m.getActiveplayer()));
		return lst;
	}
	
	private ArrayList<JLabel> constructPlayerCell(Player p){
		ArrayList<JLabel> lst = new ArrayList<>();
		lst.add(new JLabel(INDENTATION+"--- Information about the Player n"+p.getId()+" ---"));
		lst.add(new JLabel(INDENTATION+"Victory Points: "+p.getVictoryPoints()));
		lst.add(new JLabel(INDENTATION+"Nobility Path: "+p.getNobilityPoints()));
		lst.add(new JLabel(INDENTATION+"Money: "+p.getMoney()));
		lst.add(new JLabel(INDENTATION+"Emporia left: "+(p.getMaxemporia()-p.getMyEmporia().size())));
		lst.add(new JLabel(INDENTATION+"Politic Cards in hand: "+p.getPoliticcard().size()));
		lst.add(new JLabel(INDENTATION+"Helpers: "+p.getHelpers()));
		return lst;
	}
	
	private ArrayList<JLabel> constructMyPlayerCell(Player p){
		ArrayList<JLabel> lst = new ArrayList<>();
		String s= "";
		String reserve= "";
		int count=0;
		lst.add(new JLabel(INDENTATION+"--- Information about the Player n"+p.getId()+" ---"));
		lst.add(new JLabel(INDENTATION+"Victory Points: "+p.getVictoryPoints()));
		lst.add(new JLabel(INDENTATION+"Nobility Path: "+p.getNobilityPoints()));
		lst.add(new JLabel(INDENTATION+"Money: "+p.getMoney()));
		lst.add(new JLabel(INDENTATION+"Emporia left: "+(p.getMaxemporia()-p.getMyEmporia().size())));
		for(PoliticsCard c : p.getPoliticcard()){
			count++;
			if(count<10){
				s+=c.toString()+" ";
			}
			else{
				reserve +=c.toString()+" ";
			}
		}
		lst.add(new JLabel(INDENTATION+"Politic Cards in hand: "+s));
		if(reserve!=""){
			lst.add(new JLabel(INDENTATION+INDENTATION_2ND_POLITIC+reserve));
		}
		lst.add(new JLabel(INDENTATION+"Helpers: "+p.getHelpers()));
		return lst;
		
	}

}
