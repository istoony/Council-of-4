package it.polimi.ingsw.PS19;



<<<<<<< HEAD
import it.polimi.ingsw.PS19.controller.action.ChangeKingPosition;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.map.City;
=======
import it.polimi.ingsw.ps19.controller.action.ChangeKingPosition;
import it.polimi.ingsw.ps19.model.Model;
import it.polimi.ingsw.ps19.model.map.City;
>>>>>>> branch 'master' of https://bitbucket.org/CoF_ps19/ps19.git

public class CheckCityLinksAndKingsMove {
	
	public static void main(String[] args) {
		
		
		Model m = new Model(2);
		
		//starting city
		City city2;
		ChangeKingPosition move;
		
		//set player money
		for(int mon=9; mon>0; mon=mon-2){
			m.getPlayerById(0).setMoney(mon);
		
			//check the shortest path to all the cities in the map
			System.out.println("my money: "+mon);
			for(int i=0; i<m.getMap().getListaRegioni().size(); i++){
				for(int j=0; j<m.getMap().getListaRegioni().get(i).getCities().size(); j++){
					city2 = m.getMap().getListaRegioni().get(i).getCities().get(j);
					move = new ChangeKingPosition(0, city2);
					System.out.println("Can i move to " +city2.getName());
					System.out.println(move.isPossible(m));
				}
			}
		}

	}
	
}
