package hotgammon.variants;

import static org.junit.Assert.assertEquals;
import hotgammon.Color;
import hotgammon.Game;
import hotgammon.GameImpl;
import hotgammon.variants.factory.GammaMonFactory;

import org.junit.Before;
import org.junit.Test;

public class TestGammaMon {

	private Game game;

	@Before
	public void setup() {
		game = new GameImpl(new GammaMonFactory());
		game.newGame();
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
}
