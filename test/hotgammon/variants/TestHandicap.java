package hotgammon.variants;

import static org.junit.Assert.*;
import hotgammon.Game;
import hotgammon.GameImpl;
import hotgammon.Location;
import hotgammon.variants.factory.HandicapFactory;

import org.junit.Before;
import org.junit.Test;

public class TestHandicap {
	private Game game;

	@Before
	public void setup() {
		game =  new GameImpl(new HandicapFactory());
		game.newGame();
	}
	
	@Test
	public void blackMoveBlackCheckerAtB6toB7(){
		game.nextTurn();
		assertTrue(game.move(Location.B6, Location.B7));
	}
	
	@Test
	public void redMoveRedCheckerAtR6toR7(){
		game.nextTurn();
		game.nextTurn();
		assertFalse(game.move(Location.R6, Location.R7));
	}

}
