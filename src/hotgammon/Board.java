package hotgammon;

import java.util.HashMap;
import java.util.Map;

public class Board {
	
	private final Map<Location, Color> locationColors = new HashMap<Location, Color>();
	private final Map<Location, Integer> locationCounts = new HashMap<Location, Integer>();
	private StartingPositionStrategy startingPositionStrategy;
	
	public Board(StartingPositionStrategy startingPostionStrategy){
		this.startingPositionStrategy = startingPostionStrategy;
		reset();		
		setUpPositions();		
	}

	private void reset() {
		for (Location location : Location.values()) {
			locationCounts.put(location, 0);
			locationColors.put(location, Color.NONE);
		}
	}
	
	private void setUpPositions(){
		for (Location location : Location.values()) {
			for(int i = 0; i < startingPositionStrategy.getStartCount(location);i++){
				addChecker(location, startingPositionStrategy.getStartColor(location));
			}
		}
	}
	
	public void addChecker(Location to, Color player) {
		Integer countTo = locationCounts.get(to);
		if (countTo == null) {
			countTo=0;
		}
		countTo++;
		locationCounts.put(to, countTo);
		if (countTo == 1) // first checker added
		{
			locationColors.put(to, player);
		}
	}

	public void removeChecker(Location from) {
		int countFrom = locationCounts.get(from);
		countFrom--;
		locationCounts.put(from, countFrom);
		if (countFrom == 0) // last checker removed
		{
			locationColors.put(from, Color.NONE);
		}
	}
	
	public boolean isEmpty(Location to) {
		return locationColors.get(to).equals(Color.NONE);
	}
	
	public Color getColor(Location location) {
		return locationColors.get(location);
	}

	public int getCount(Location location) {
		return locationCounts.get(location);
	}
	
	public void moveChecker(Location from, Location to, Color player) {
		removeChecker(from);
		addChecker(to, player);
	}
}
