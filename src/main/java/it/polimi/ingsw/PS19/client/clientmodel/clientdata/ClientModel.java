package it.polimi.ingsw.PS19.client.clientmodel.clientdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.polimi.ingsw.PS19.model.Market;
import it.polimi.ingsw.PS19.model.Model;
import it.polimi.ingsw.PS19.model.Player;
import it.polimi.ingsw.PS19.model.map.AvailableCouncillor;
import it.polimi.ingsw.PS19.model.map.City;
import it.polimi.ingsw.PS19.model.map.King;
import it.polimi.ingsw.PS19.model.map.NobilityPath;
import it.polimi.ingsw.PS19.model.map.Region;
import it.polimi.ingsw.PS19.model.parameter.RegionType;

/**
 * Class that rappresents the local model
 */
public class ClientModel 
{
	private King king;
	private List<Region> regions;
	private AvailableCouncillor availablecouncillor;
	private List<Player> player;
	private int activeplayer;
	private String result;
	private NobilityPath nobilitypath;
	private int currentId;
	
	private Market market;
	
	/**
	 * Constructor
	 * @param id: local player Id
	 */
	public ClientModel(int id) 
	{
		currentId = id;
	}
	
	public ClientModel(Model m){
		regions = m.getMap().getListaRegioni();
		king = m.getMap().getKing();
		player = m.getPlayer();
		nobilitypath = m.getMap().getNobilityPath();
	}
	
	
	public void setKing(King king) {
		this.king = king;
	}
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}
	public void setActiveplayer(int activeplayer) {
		this.activeplayer = activeplayer;
	}
	public void setPlayer(List<Player> player) {
		this.player = player;
	}
	public int getActiveplayer() {
		return activeplayer;
	}
	public King getKing() {
		return king;
	}
	public List<Player> getPlayer() {
		return player;
	}
	public List<Region> getRegions() {
		return regions;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public void setAvailablecouncillor(AvailableCouncillor availablecouncillor) {
		this.availablecouncillor = availablecouncillor;
	}
	public void setNobilitypath(NobilityPath nobilitypath) {
		this.nobilitypath = nobilitypath;
	}
	public AvailableCouncillor getAvailablecouncillor() {
		return availablecouncillor;
	}
	public NobilityPath getNobilitypath() {
		return nobilitypath;
	}
	
	public Player getMyPlayer()
	{
		if(player.size() < currentId)
			return null;
		return player.get(currentId);
	}
	public Market getMarket() {
		return market;
	}
	public void setMarket(Market market) 
	{
		this.market = market;
	}
	
	
	/**
	 * Get Region from type
	 * @param r: regionType
	 * @return Region
	 */
	public Region getRegionByType(RegionType r)
	{
		for (Region region : regions) 
		{
			if(region.getType() == r)
				return region;
		}
		return null;
	}

	/**
	 * returns the list of all the cities in the map
	 * @return: List of all the cities in the map
	 */
	public List<City> getAllCities()
	{
		List<City> cities = new ArrayList<>();
		for (Region region : regions) 
			cities.addAll(region.getCities());
		return cities;
	}
	
	/**
	 * Gets list of cities for the shortest path
	 * @param start
	 * @param end
	 * @return
	 */
	public List<City> calculateShorterPath(City start, City end){
		ArrayList<City> result = new ArrayList<>();
		for(Region r : regions){
			for(City c : r.getCities()){
				if(start.equals(c)){
					HashMap<City, City> visited = new HashMap<>(); 
					HashMap<City, City> parentree = new HashMap<>(); 
					ArrayList<City> frontier = new ArrayList<>();
					result = recursiveBFS(start, end, result, c, visited, frontier, parentree);
				}
			}
		}
		return result;
	}
	
	private ArrayList<City> recursiveBFS(City root, City end, ArrayList<City> path, City start, HashMap<City, City> visited, ArrayList<City> frontier, HashMap<City, City> parenttree)
	{
		if(root.equals(end))
		{
			path.add(end);
			City temp;
			while(!path.contains(start)){
				temp = parenttree.get(root);
				path.add(temp);
				root=temp;
			}
			return path;
		}
		
		for(City c : root.getNeighbours()){
			if(!parenttree.containsKey(c)){
				parenttree.put(c, root);
			}
		}
		frontier.addAll(root.getNeighbours());
		City newroot = frontier.get(0);
		frontier.remove(0);
		while(visited.containsValue(newroot)){
			newroot = frontier.get(0);
			frontier.remove(0);
		}
		visited.put(newroot, root);
		return recursiveBFS(newroot, end, path, start, visited, frontier, parenttree);
	}
}
