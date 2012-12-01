package hotgammon.variants.startingposition;

import hotgammon.Color;
import hotgammon.Location;
import hotgammon.StartingPositionStrategy;

public class HyperGammonStartingPositionStrategi implements
		StartingPositionStrategy {
	
	@Override
	public int getStartCount(Location location) {
		switch (location) {
		case B1:
		case B2:
		case B3:
		case R1:
		case R2:
		case R3:
			return 3;
		default:
			return 0;
		}
	}

	@Override
	public Color getStartColor(Location location) {
		switch (location) {
		case B1:
		case B2:
		case B3:
			return Color.RED;
		case R1:
		case R2:
		case R3:
			return Color.BLACK;
		default:
			return Color.NONE;
		}
	}
}
