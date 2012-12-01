package hotgammon.unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import hotgammon.Color;
import hotgammon.Game;
import hotgammon.GameImpl;
import hotgammon.Location;
import hotgammon.variants.factory.AlphaMonFactory;
import hotgammon.variants.winning.BackgammonWinningStrategy;

public class TestBackGammonWinningStrategy {

	
	private BackgammonWinningStrategy winningStrategy;
	private Game game;
	
	@Before
	public void setup() {
		winningStrategy = new BackgammonWinningStrategy();
		game = new GameImpl(new AlphaMonFactory());
		winningStrategy.setGame(game);
	}
	
	
	@Test
	public void winnerIsBlackIfAllBlackCheckersArePlacedAtTheBlackBearOff() {
		removeAllCheckersAndAddThemToBearOff(Color.BLACK);
		
		assertEquals(Color.BLACK , winningStrategy.getWinner());
	}
	
	@Test
	public void winnerIsNoneIfNotAllBlackCheckersArePlacedAtTheBlackBearOff() {
		assertEquals(Color.NONE, winningStrategy.getWinner());
	}
	

	@Test
	public void winnerIsRedIfAllRedCheckersArePlacedAtTheRedBearOff() {
		removeAllCheckersAndAddThemToBearOff(Color.RED);
		assertEquals(Color.RED , winningStrategy.getWinner());
	}
	
	private void removeAllCheckersAndAddThemToBearOff(Color player) {
		Location bearOff = null;
		
		switch (player) {
		case BLACK:
			bearOff = Location.B_BEAR_OFF;
			break;
		case RED: 
			bearOff = Location.R_BEAR_OFF;
			break;
		default:
			throw new IllegalStateException("Should never happen");
		}
		
		for (Location loc : Location.values()) { // Empty all locations, where the color is right
			while (game.getBoard().getColor(loc).equals(player)) {
				game.getBoard().removeChecker(loc);
			}
		}
		
		for (int i = 0; i < 15; i++ ) { // add fifteen checkers to the relevant bear_off
			game.getBoard().addChecker(bearOff, player);
		}
	}
}
