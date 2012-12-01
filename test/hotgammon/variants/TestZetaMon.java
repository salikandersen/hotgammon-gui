package hotgammon.variants;

import static org.junit.Assert.assertEquals;
import hotgammon.Color;
import hotgammon.Game;
import hotgammon.GameImpl;
import hotgammon.Location;
import hotgammon.variants.factory.ZetaMonFactory;

import org.junit.Before;
import org.junit.Test;

public class TestZetaMon {
	private Game game;

	@Before
	public void setup() {
		game =  new GameImpl(new ZetaMonFactory());
		game.newGame();
	}
	
	@Test
	public void TestVerifyThreeRedCheckersAtB1(){
		checkLocationContents(Location.B1, 3, Color.RED);
	}
	
	private void checkLocationContents(Location location, int expectedCount,
			Color expectedColor) {
		checkExpectedCount(location, expectedCount);
		assertEquals(
				location.name() + "s color should be " + expectedColor.name(),
				expectedColor, game.getColor(location));
	}

	private void checkExpectedCount(Location location, int expectedCount) {
		assertEquals("There should be " + expectedCount + " checkers in "
				+ location.name(), expectedCount, game.getCount(location));
	}

	@Test
	public void shouldHaveCorrectSetupAtStart() {
		checkLocationContents(Location.R1, 3, Color.BLACK);
		checkLocationContents(Location.R2, 3, Color.BLACK);
		checkLocationContents(Location.R3, 3, Color.BLACK);
		checkLocationContents(Location.R4, 0, Color.NONE);
		checkLocationContents(Location.R5, 0, Color.NONE);
		checkLocationContents(Location.R6, 0, Color.NONE);
		checkLocationContents(Location.R7, 0, Color.NONE);
		checkLocationContents(Location.R8, 0, Color.NONE);
		checkLocationContents(Location.R9, 0, Color.NONE);
		checkLocationContents(Location.R10, 0, Color.NONE);
		checkLocationContents(Location.R11, 0, Color.NONE);
		checkLocationContents(Location.R12, 0, Color.NONE);

		checkLocationContents(Location.B1, 3, Color.RED);
		checkLocationContents(Location.B2, 3, Color.RED);
		checkLocationContents(Location.B3, 3, Color.RED);
		checkLocationContents(Location.B4, 0, Color.NONE);
		checkLocationContents(Location.B5, 0, Color.NONE);
		checkLocationContents(Location.B6, 0, Color.NONE);
		checkLocationContents(Location.B7, 0, Color.NONE);
		checkLocationContents(Location.B8, 0, Color.NONE);
		checkLocationContents(Location.B9, 0, Color.NONE);
		checkLocationContents(Location.B10, 0, Color.NONE);
		checkLocationContents(Location.B11, 0, Color.NONE);
		checkLocationContents(Location.B12, 0, Color.NONE);

		checkLocationContents(Location.B_BAR, 0, Color.NONE);
		checkLocationContents(Location.R_BAR, 0, Color.NONE);

		checkLocationContents(Location.B_BEAR_OFF, 0, Color.NONE);
		checkLocationContents(Location.R_BEAR_OFF, 0, Color.NONE);
	}
	
}
