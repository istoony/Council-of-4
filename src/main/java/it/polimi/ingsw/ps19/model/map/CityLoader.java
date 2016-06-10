package it.polimi.ingsw.ps19.model.map;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import it.polimi.ingsw.ps19.model.FileNames;

public class CityLoader {
	
	
	//metodo principale, popola le protoregioni di città con annessi cammini
	static ArrayList<ArrayList<City>> citiesReader(ArrayList<MapLoader> idlist){
		
		ArrayList<ArrayList<City>> regioncitylist = new ArrayList<>();
			
		NodeList nList = FileReader.XMLReader(FileNames.MAP_FILE, "region");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) nNode;
				for(MapLoader elem : idlist){
					if(elem.id==Integer.parseInt(e.getAttribute("id"))){
						ArrayList<City> citylist = new ArrayList<>();
						for(int i=0; i<Integer.parseInt(e.getElementsByTagName("nOfCities").item(0).getTextContent()); i++){
							citylist.add(new City(Integer.parseInt(e.getElementsByTagName("cityid").item(i).getTextContent())));
						}
						regioncitylist.add(citylist);
					}		
				}					
			}
		}	
		nList = FileReader.XMLReader(FileNames.MAP_FILE, "city");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) nNode;
				
				for(ArrayList<City> rlist : regioncitylist){
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
	private static ArrayList<ArrayList<City>> pathBuilder(ArrayList<ArrayList<City>> citiesinregions){
		ArrayList<City> copia = new ArrayList<>();
		ArrayList<City> newneig = new ArrayList<>();
		
		for(ArrayList<City> reg : citiesinregions){	
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
	private static ArrayList<ArrayList<City>> roadWelder(ArrayList<ArrayList<City>> citiesbyregion){
		ArrayList<ArrayList<City>> couples;
		ArrayList<ArrayList<City>> rejoin = new ArrayList<>();

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
	private static ArrayList<ArrayList<City>> welding(ArrayList<City> regionL, ArrayList<City> regionR){
		for(City ell : regionL){
			for(City neig : ell.getNeighbours()){
				if(neig.getId()<0){
					for(City elr : regionR){
						for(City next : elr.getNeighbours()){
							if(next.getId()+1000==neig.getId()){
								ell.getNeighbours().set(ell.getNeighbours().indexOf(neig), elr);
								elr.getNeighbours().set(elr.getNeighbours().indexOf(next), ell);

							}
						}
					}
					
				}
			}
		}
		ArrayList<ArrayList<City>> weld2region = new ArrayList<>();
		weld2region.add(regionL);
		weld2region.add(regionR);
		return weld2region;
		
	}
	
	//trova i neighbours delle città (completa la lettura da file)
	private static ArrayList<City> retrieveNear(Element e){
		ArrayList<City> nearlist = new ArrayList<>();
		for(int i=0; i<Integer.parseInt(e.getElementsByTagName("nOfRoads").item(0).getTextContent()); i++){
			nearlist.add(new City(Integer.parseInt(e.getElementsByTagName("road").item(i).getTextContent())));
		}
		return nearlist;
	}
	
}
