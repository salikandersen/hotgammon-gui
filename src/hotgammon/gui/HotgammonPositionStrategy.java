package hotgammon.gui;

import frs.hotgammon.view.Convert;
import hotgammon.Location;

import java.awt.Point;

import minidraw.boardgame.PositioningStrategy;

public class HotgammonPositionStrategy implements PositioningStrategy<Location> {

	@Override
	public Point calculateFigureCoordinatesForProps(String keyOfProp) {
		if (keyOfProp.equals("die0")) {
		      return new Point(220, 200);
		    }
		if (keyOfProp.equals("die1")) {
		      return new Point(300, 200);
		    }
		return null;
	}

	@Override
	public Point calculateFigureCoordinatesIndexedForLocation(Location location,
			int count) {
		return Convert.locationAndCount2xy(location, count);
	}

}
