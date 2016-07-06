package it.polimi.ingsw.ps19.model.map;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.ps19.model.parameter.FileNames;
import it.polimi.ingsw.ps19.model.parameter.FileReader;

/**
 * Class to load cities from files
 */
public class CityLoader 
{	
	private CityLoader() {
		super();
	}
	
	
	//metodo principale, popola le protoregioni di città con annessi cammini
	static List<List<City>> citiesReader(List<MapLoader> idlist){
		
		List<List<City>> regioncitylist = new ArrayList<>();
			
		NodeList nList = FileReader.xMLReader(FileNames.MAP_FILE, "region");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) nNode;
				for(MapLoader elem : idlist){
					if(elem.id==Integer.parseInt(e.getAttribute("id"))){
						List<City> citylist = new ArrayList<>();
						for(int i=0; i<Integer.parseInt(e.getElementsByTagName("nOfCities").item(0).getTextContent()); i++){
							citylist.add(new City(Integer.parseInt(e.getElementsByTagName("cityid").item(i).getTextContent())));
						}
						regioncitylist.add(citylist);
					}		
				}					
			}
		}	
		nList = FileReader.xMLReader(FileNames.MAP_FILE, "city");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) nNode;
				
				for(List<City> rlist : regioncitylist)
				{
					for(City el : rlist){
						if(el.getId()==Integer.parseInt(e.getAttribute("id"))){
							el.setParameters(e.getElementsByTagName("name").item(0).getTextContent(), e.getElementsByTagName("color").item(0).getTextContent());
							el.addNear(CityLoader.retrieveNear(e));	
					}		
				}	
				}
			}
		}	
		regioncitylist = CityLoader.pathBuilder(regioncitylist);
		regioncitylist = CityLoader.roadWelder(regioncitylist);
		
		return regioncitylist;
		
		
		

	}
	
	//Collega le città per nome
	private static List<List<City>> pathBuilder(List<List<City>> citiesinregions){
		List<City> copia = new ArrayList<>();
		List<City> newneig = new ArrayList<>();
		
		for(List<City> reg : citiesinregions){	
			copia.clear();
			copia.addAll(reg);
			for(City el : reg){
				newneig.clear();
				for(City neig : el.getNeighbours()){
					if(neig.getId()<0){
						newneig.add(neig);
					}
					else{
						for(City el2 : copia){
							if(neig.getId()==el2.getId()){
								newneig.add(el2);
							}
						}
					}
				}
				el.addNear(newneig);
			}
		}
		
		return citiesinregions;
	}
	
	//"salda" i neighbours tra le varie regions
	private static List<List<City>> roadWelder(List<List<City>> citiesbyregion){
		List<List<City>> couples;
		List<List<City>> rejoin = new ArrayList<>();

		for(int i=0; i<citiesbyregion.size()-1; i++){

			couples = CityLoader.welding(citiesbyregion.get(i), citiesbyregion.get(i+1));
			if(i==0){
				rejoin = couples;
			}
			else{
				rejoin.add(couples.get(1));
			}
		}
		return rejoin;
	}
	
	//metodo accessorio del precedente
	private static List<List<City>> welding(List<City> regionL, List<City> regionR){
		for(City ell : regionL){
			for(City neig : ell.getNeighbours()){
				if(neig.getId()<0)
					addNeighbours(regionR, ell, neig);
			}
		}
		List<List<City>> weld2region = new ArrayList<>();
		weld2region.add(regionL);
		weld2region.add(regionR);
		return weld2region;
		
	}


	private static void addNeighbours(List<City> regionR, City ell, City neig) {
		for(City elr : regionR){
			for(City next : elr.getNeighbours()){
				if(next.getId()+1000==neig.getId()){
					ell.getNeighbours().set(ell.getNeighbours().indexOf(neig), elr);
					elr.getNeighbours().set(elr.getNeighbours().indexOf(next), ell);

				}
			}
		}
	}
	
	//trova i neighbours delle città (completa la lettura da file)
	private static List<City> retrieveNear(Element e){
		List<City> nearlist = new ArrayList<>();
		for(int i=0; i<Integer.parseInt(e.getElementsByTagName("nOfRoads").item(0).getTextContent()); i++){
			nearlist.add(new City(Integer.parseInt(e.getElementsByTagName("road").item(i).getTextContent())));
		}
		return nearlist;
	}
	

	
}
