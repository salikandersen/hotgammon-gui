package hotgammon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hotgammon.variants.factory.AlphaMonFactory;

public class TestGameObserver {

	private Game game;
	private GameObserverStub stub;

	@Before
	public void setup() {
		game = new GameImpl(new AlphaMonFactory());
		stub = new GameObserverStub();
		game.addObserver(stub);
	}

	@Test
	public void shouldBeNotifiedWhenCheckerIsMoved() {
		game.newGame();
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R2));
		assertEquals(Location.R1, stub.from);
		assertEquals(Location.R2, stub.to);
	}

	@Test
	public void shouldBeNotifiedWhenDiceAreRolled() {
		game.newGame();
		game.nextTurn();
		int[] diceValues = game.diceThrown();
		assertEquals(diceValues[0], stub.values[0]);
		assertEquals(diceValues[1], stub.values[1]);
	}
}
