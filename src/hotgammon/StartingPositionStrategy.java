package hotgammon;

public interface StartingPositionStrategy {
	public int getStartCount(Location location);
	public Color getStartColor(Location location);	
}
