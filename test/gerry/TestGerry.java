package gerry;
import gerry.GameAdapterImpl;
import gerry.Gerry;
import gerry.Move;
import hotgammon.Color;
import hotgammon.Game;
import hotgammon.GameImpl;
import hotgammon.Location;
import hotgammon.variants.factory.AlphaMonFactory;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * Learning tests for "Gerry" - a backgammon game AI player.
 * 
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 * 
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class TestGerry {
	private int[] openingboard;
	private Gerry gerry;
	private Move move;

	@Before
	public void setUp() {
		gerry = new Gerry();
		openingboard = new int[28];
		// standard backgammon setup
		openingboard[1] = 2;
		openingboard[6] = -5;
		openingboard[8] = -3;
		openingboard[12] = 5;
		openingboard[13] = -5;
		openingboard[17] = 3;
		openingboard[19] = 5;
		openingboard[24] = -2;
	}

	@Test
	public void testOpening1() {
		move = gerry.play(openingboard, new int[] { 1, 2 });
		// 1-2 -> B8-B7 + B7-B5
		assertEquals(move.getFrom(0), 17);
		assertEquals(move.getTo(0), 18);
		assertEquals(move.getFrom(1), 18);
		assertEquals(move.getTo(1), 20);
	}

	@Test
	public void testOpening() {
		Game game = new GameImpl(new AlphaMonFactory());
		assertEquals(0, game.getCount(Location.B7));
		assertEquals(Color.NONE, game.getColor(Location.B7));
		assertEquals(3, game.getCount(Location.B8));
		assertEquals(0, game.getCount(Location.B5));
		game.newGame();
		game.nextTurn();
		gerry.play(new GameAdapterImpl(game));
		// 1-2 -> B8-B7 + B7-B5
		assertEquals(2, game.getCount(Location.B8));
		assertEquals(1, game.getCount(Location.B5));
		assertEquals(Color.BLACK, game.getColor(Location.B5));
	}
}
