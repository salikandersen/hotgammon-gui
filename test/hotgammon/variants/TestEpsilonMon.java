package hotgammon.variants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import hotgammon.GameImpl;
import hotgammon.variants.factory.EpsilonMonFactory;

import org.junit.Before;
import org.junit.Test;

public class TestEpsilonMon {
	private GameImpl game;
	
	@Before
	public void setup() {
		game =  new GameImpl(new EpsilonMonFactory());
		game.newGame();		
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
