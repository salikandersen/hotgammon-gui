package hotgammon.variants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import hotgammon.Color;
import hotgammon.GameImpl;
import hotgammon.Location;
import hotgammon.variants.factory.SemiMonFactory;

import org.junit.Before;
import org.junit.Test;

public class TestSemiMon {

	private GameImpl game;

	@Before
	public void setup() {
		game =  new GameImpl(new SemiMonFactory());
		game.newGame();
	}	
	
	@Test
	public void boardMustHaveCorrectSetupAtStart() {
		checkNoAndColor(Location.R1, 2, Color.BLACK);
		checkNoAndColor(Location.R2, 0, Color.NONE);
		checkNoAndColor(Location.R3, 0, Color.NONE);
		checkNoAndColor(Location.R4, 0, Color.NONE);
		checkNoAndColor(Location.R5, 0, Color.NONE);
		checkNoAndColor(Location.R6, 5, Color.RED);
		checkNoAndColor(Location.R7, 0, Color.NONE);
		checkNoAndColor(Location.R8, 3, Color.RED);
		checkNoAndColor(Location.R9, 0, Color.NONE);
		checkNoAndColor(Location.R10, 0, Color.NONE);
		checkNoAndColor(Location.R11, 0, Color.NONE);
		checkNoAndColor(Location.R12, 5, Color.BLACK);

		checkNoAndColor(Location.B1, 2, Color.RED);
		checkNoAndColor(Location.B2, 0, Color.NONE);
		checkNoAndColor(Location.B3, 0, Color.NONE);
		checkNoAndColor(Location.B4, 0, Color.NONE);
		checkNoAndColor(Location.B5, 0, Color.NONE);
		checkNoAndColor(Location.B6, 5, Color.BLACK);
		checkNoAndColor(Location.B7, 0, Color.NONE);
		checkNoAndColor(Location.B8, 3, Color.BLACK);
		checkNoAndColor(Location.B9, 0, Color.NONE);
		checkNoAndColor(Location.B10, 0, Color.NONE);
		checkNoAndColor(Location.B11, 0, Color.NONE);
		checkNoAndColor(Location.B12, 5, Color.RED);

		checkNoAndColor(Location.B_BAR, 0, Color.NONE);
		checkNoAndColor(Location.R_BAR, 0, Color.NONE);

		checkNoAndColor(Location.B_BEAR_OFF, 0, Color.NONE);
		checkNoAndColor(Location.R_BEAR_OFF, 0, Color.NONE);
	}
	
	private void checkNoAndColor(Location location, int count, Color color) {
		assertEquals(color, game.getColor(location));
		assertEquals(count, game.getCount(location));
	}
	
	@Test
	public void redMoveRedCheckerAtR6toR7(){
		game.nextTurn();
		game.nextTurn();
		assertFalse(game.move(Location.R6, Location.R7));
	}
	
	@Test
	public void winnerIsNoneAfterSixTurns() {
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertEquals(Color.NONE, game.winner());
	}
	
	@Test
	public void mustGiveBetween1And6AsFirstRoll() {		
		game.nextTurn();
		int[] diceValues = game.diceThrown(); // Dice show 1-2
		assertEquals(2, diceValues.length);
		assertTrue(diceValues[0] > 0);
		assertTrue(diceValues[0] < 7);
		assertTrue(diceValues[1] > 0);
		assertTrue(diceValues[1] < 7);
	}

}
