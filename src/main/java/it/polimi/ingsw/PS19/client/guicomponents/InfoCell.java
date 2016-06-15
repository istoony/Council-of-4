package it.polimi.ingsw.PS19.client.guicomponents;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import it.polimi.ingsw.PS19.client.clientmodel.clientdata.ClientModel;
import it.polimi.ingsw.PS19.model.Player;

public class InfoCell extends JPanel{
	
	private static final String TOOLTIP0 = "Information about the game.";
	private static final String TOOLTIP1 = "Information about the player.";
	private static final String TOOLTIP2 = "Information about the other players.";
	private static final String INDENTATION = "    ";
	
	private List<JLabel> infos;
	
	protected InfoCell(){
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		setVisible(true);
		setLayout(new GridLayout(7, 0));
		infos = new ArrayList<>();
	}
	
	protected void setInfo(ClientModel m){
		infos = constructInfoCell(m);
		for(JLabel jl : infos){
			jl.setVisible(true);
			add(jl);
		}
	}

	protected void setInfo(Player p){
		infos = constructPlayerCell(p);
		for(JLabel jl : infos){
			jl.setVisible(true);
			add(jl);
		}
	}
	
	private ArrayList<JLabel> constructInfoCell(ClientModel m){
		ArrayList<JLabel> lst = new ArrayList<>();
		lst.add(new JLabel(INDENTATION+"--- Information about the Game ---"));
		lst.add(new JLabel(INDENTATION+"The King is in: "+m.getKing().getCurrentcity().getName()));
		lst.add(new JLabel(INDENTATION+"It's the turn of the player number "+m.getActiveplayer()));
		return lst;
	}
	
	private ArrayList<JLabel> constructPlayerCell(Player p){
		ArrayList<JLabel> lst = new ArrayList<>();
		lst.add(new JLabel(INDENTATION+"--- Information about the Player n"+p.getId()+" ---"));
		lst.add(new JLabel(INDENTATION+"Victory Points: "+p.getVictoryPoints()));
		lst.add(new JLabel(INDENTATION+"Nobility Path: "+p.getNobilityPoints()));
		lst.add(new JLabel(INDENTATION+"Emporia left: "+p.getMyEmporia().size()));
		lst.add(new JLabel(INDENTATION+"Politic Cards in hand: "+p.getPoliticcard().size()));
		lst.add(new JLabel(INDENTATION+"Helpers: "+p.getHelpers()));
		return lst;
	}
	
	
	protected void Update(ClientModel model){
		//TODO
	}
}
