package hotgammon.variants.startingposition;

import hotgammon.Color;
import hotgammon.Location;
import hotgammon.StartingPositionStrategy;

public class BackgammonStartingPositionStrategy implements
		StartingPositionStrategy {

	private static final Color[] START_COLORS = {
			// Black's side
			Color.RED, Color.NONE, Color.NONE, Color.NONE, Color.NONE,
			Color.BLACK, Color.NONE, Color.BLACK, Color.NONE, Color.NONE,
			Color.NONE,
			Color.RED,

			// Red's side
			Color.BLACK, Color.NONE, Color.NONE, Color.NONE, Color.NONE,
			Color.RED, Color.NONE, Color.RED, Color.NONE, Color.NONE,
			Color.NONE, Color.BLACK,

			// Bars
			Color.NONE, Color.NONE,

			// Bear offs
			Color.NONE, Color.NONE };

	private static final int[] START_COUNTS = {
			// Black's side
			2, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 5,

			// Red's side
			2, 0, 0, 0, 0, 5, 0, 3, 0, 0, 0, 5,

			// Bars
			0, 0,

			// Bear offs
			0, 0 };

	@Override
	public int getStartCount(Location location) {
		return START_COUNTS[location.ordinal()];
	}

	@Override
	public Color getStartColor(Location location) {
		return START_COLORS[location.ordinal()];
	}
}
