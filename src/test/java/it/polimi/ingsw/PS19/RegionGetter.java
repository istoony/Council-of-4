package it.polimi.ingsw.PS19;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.map.Region;

public class RegionGetter {

	@Test
	public void test() 
	{
		Model m = new Model(3);
		List<Region> r = m.getMap().getListaRegioni();
				//controlla se il puntatore di R è lo stesso che ritorna dal model
				//se sono diversi il test va a buon fine ed è giusto perchè vuol
				//dire che getListaRegioni ritorna una copia delle regioni che posso modificare come voglio
				//le regioni originali rimangono nel model
		assertTrue(r != m.getMap().getListaRegioni());
				
				//Ritorno proprio il king quindi non posso adoperarlo per fare quel che voglio.
				//
				
		King k = m.getMap().getKing();
		
		assertTrue(k == m.getMap().getKing());
	}

}
