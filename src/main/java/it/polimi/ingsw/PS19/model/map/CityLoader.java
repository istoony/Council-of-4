package it.polimi.ingsw.PS19.model.map;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CityLoader {
	
	
	//metodo principale, popola le protoregioni di città con annessi cammini
	static void CitiesReader(ArrayList<MapLoader> idlist){
		
		ArrayList<ArrayList<City>> regioncitylist = new ArrayList<ArrayList<City>>();
			
		NodeList nList = FileReader.XMLReader(MapLoader.MAP_FILE, "region");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) nNode;
				for(MapLoader elem : idlist){
					if(elem.id==Integer.parseInt(e.getAttribute("id"))){
						ArrayList<City> citylist = new ArrayList<City>();
						for(int i=0; i<Integer.parseInt(e.getElementsByTagName("nOfCities").item(0).getTextContent()); i++){
							citylist.add(new City(Integer.parseInt(e.getElementsByTagName("cityid").item(i).getTextContent())));
						}
						regioncitylist.add(citylist);
					}		
				}					
			}
		}	
		nList = FileReader.XMLReader(MapLoader.MAP_FILE, "city");
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) nNode;
				
				for(ArrayList<City> rlist : regioncitylist){
					for(City el : rlist){
						if(el.id==Integer.parseInt(e.getAttribute("id"))){
							el.setParameters(e.getElementsByTagName("name").item(0).getTextContent(), e.getElementsByTagName("color").item(0).getTextContent());
							el.addNear(CityLoader.retrieveNear(e));
							
					}		
				}	
				}
			}
		}	
		regioncitylist = CityLoader.pathBuilder(regioncitylist);
		regioncitylist = CityLoader.roadWelder(regioncitylist);
		
		// parte di test
		/*
		for(ArrayList<City> cityl : regioncitylist){
			for(City c : cityl){
				System.out.println(c.name);
				for(City cc : c.neighbours){
					System.out.println("1G: "+cc.name);
					for(City ccc : cc.neighbours){
					//	System.out.println("2G: "+ccc.name);
					}
				}
			}
		}
	*/ 
		//perchè questo funziona e quello sopra no? anche il debug è corretto..
		for(City c : regioncitylist.get(2)){
			System.out.println(c.name);
			for(City cc : c.neighbours){
				System.out.println("1G: "+cc.name);
				for(City ccc : cc.neighbours){
					System.out.println("2G: "+ccc.name);
				}
			}
			
		}
		
		
		
		

	}
	
	//Collega le città per nome
	private static ArrayList<ArrayList<City>> pathBuilder(ArrayList<ArrayList<City>> citiesinregions){
		ArrayList<City> copia = new ArrayList<City>();
		ArrayList<City> newneig = new ArrayList<City>();
		
		for(ArrayList<City> reg : citiesinregions){	
			copia.clear();
			copia.addAll(reg);
			for(City el : reg){
				newneig.clear();
				for(City neig : el.neighbours){
					if(neig.id<0){
						newneig.add(neig);
					}
					else{
						for(City el2 : copia){
							if(neig.id==el2.id){
								newneig.add(el2);
								//neig.setParameters(el2.name, el2.citycolor);
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
		ArrayList<ArrayList<City>> couples = new ArrayList<ArrayList<City>>();
		ArrayList<ArrayList<City>> rejoin = new ArrayList<ArrayList<City>>();

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
			for(City neig : ell.neighbours){
				if(neig.id<0){
					for(City elr : regionR){
						for(City next : elr.neighbours){
							if(next.id+1000==neig.id){
								//next.setParameters(ell.name, ell.citycolor, ell.id);
								//neig.setParameters(elr.name, elr.citycolor, elr.id);
								ell.neighbours.set(ell.neighbours.indexOf(neig), elr);
								elr.neighbours.set(elr.neighbours.indexOf(next), ell);

							}
						}
					}
					
				}
			}
		}
		ArrayList<ArrayList<City>> weld2region = new ArrayList<ArrayList<City>>();
		weld2region.add(regionL);
		weld2region.add(regionR);
		return weld2region;
		
	}
	
	//trova i neighbours delle città (completa la lettura da file)
	private static ArrayList<City> retrieveNear(Element e){
		ArrayList<City> nearlist = new ArrayList<City>();
		for(int i=0; i<Integer.parseInt(e.getElementsByTagName("nOfRoads").item(0).getTextContent()); i++){
			nearlist.add(new City(Integer.parseInt(e.getElementsByTagName("road").item(i).getTextContent())));
		}
		return nearlist;
	}
	
}
