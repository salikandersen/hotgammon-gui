package hotgammon.unittest;

import static org.junit.Assert.assertEquals;
import hotgammon.GameImpl;
import hotgammon.variants.factory.AlphaMonFactory;

import org.junit.Before;
import org.junit.Test;

public class TestDiceStrategy {
private GameImpl game;
	
	@Before
	public void setup() {
		game =  new GameImpl(new AlphaMonFactory());
		game.newGame();		
	}
	
	@Test
	public void mustGive12AsFirstRoll() {
		
		game.nextTurn();
		int[] diceValues = game.diceThrown(); // Dice show 1-2
		assertEquals(1, diceValues[0]);
		assertEquals(2, diceValues[1]);
	}
}
