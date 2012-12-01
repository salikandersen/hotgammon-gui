package hotgammon.variants;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import hotgammon.Color;
import hotgammon.Game;
import hotgammon.GameImpl;
import hotgammon.Location;
import hotgammon.variants.factory.AlphaMonFactory;

import org.junit.Before;
import org.junit.Test;

/**
 * Testing skeleton for AlphaMon.
 * 
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 * 
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class TestAlphaMon {
	private Game game;

	@Before
	public void setup() {
		game =  new GameImpl(new AlphaMonFactory());
		game.newGame();
	}

	@Test
	public void mustHaveNoPlayerInTurnAfterNewGame() {
		assertEquals(Color.NONE, game.getPlayerInTurn());
	}

	@Test
	public void mustHaveBlackPlayerInTurnAfterNewGame() {
		game.nextTurn(); // will throw [1,2] and thus black starts
		assertEquals(Color.BLACK, game.getPlayerInTurn());
	}

	@Test
	public void mustHaveTwoBlackCheckersOnR1() {
		checkNoAndColor(Location.R1, 2, Color.BLACK);
	}

	@Test
	public void mustHaveTwoRedCheckersOnB1() {
		checkNoAndColor(Location.B1, 2, Color.RED);
	}

	private void checkNoAndColor(Location location, int count, Color color) {
		assertEquals(color, game.getColor(location));
		assertEquals(count, game.getCount(location));
	}

	@Test
	public void mustHaveFiveRedCheckersOnB12() {
		checkNoAndColor(Location.B12, 5, Color.RED);
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

	@Test
	public void movingBlackCheckerFromR1ToR2ShouldBeValidFirstMove() {
		game.nextTurn(); // 2 Black checkers at R1 + 0 at R2
		assertTrue(game.move(Location.R1, Location.R2));
	}

	@Test
	public void movingBlackCheckerFromR1ToB1ShouldBeInvalidFirstMove() {
		game.nextTurn(); // 2 Black checkers at R1 + 2 Red checkers at B1
		assertEquals(Color.BLACK, game.getPlayerInTurn());
		assertFalse(game.move(Location.R1, Location.B1));
	}

	@Test
	public void movingBlackCheckerFromR1ToB8ShouldBeValidFirstMove() {
		game.nextTurn(); // 2 Black checkers at R1 + 3 Black checkers at B8
		assertEquals(Color.BLACK, game.getPlayerInTurn());
		assertTrue(game.move(Location.R1, Location.B8));
	}

	@Test
	public void movingCheckerFromR1ToR2ShouldLeaveOneCheckerAtR1AndOneAtR2() {
		game.nextTurn(); // 2 Black checkers at R1 + 0 at R2
		game.move(Location.R1, Location.R2); // 1 Black checker at R1 + 1 Black
												// checker at R2

		assertEquals(1, game.getCount(Location.R1));
		assertEquals(1, game.getCount(Location.R2));
	}

	@Test
	public void movingCheckerFromR1ToR2ShouldLeaveBlackCheckersAtR1AndAtR2() {
		game.nextTurn(); // 2 Black checkers at R1 + 0 at R2
		game.move(Location.R1, Location.R2); // 1 Black checker at R1 + 1 Black
												// checker at R2

		assertEquals(Color.BLACK, game.getColor(Location.R1));
		assertEquals(Color.BLACK, game.getColor(Location.R2));
	}

	@Test
	public void mustGive12AsFirstRoll() {
		game.nextTurn();
		int[] diceValues = game.diceThrown(); // Dice show 1-2
		assertEquals(1, diceValues[0]);
		assertEquals(2, diceValues[1]);
	}

	@Test
	public void mustGive34AsSecondRoll() {
		turn(Location.R1, Location.R2, Color.BLACK);
		game.nextTurn();
		int[] diceValues = game.diceThrown(); // Dice show 3-4
		assertEquals(3, diceValues[0]);
		assertEquals(4, diceValues[1]);
	}

	private void turn(Location from, Location to, Color playerInTurn) {
		game.nextTurn();
		
		assertEquals(playerInTurn, game.getPlayerInTurn());
		game.move(from, to);
		game.move(from, to);
	}

	@Test
	public void mustGive56AsThirdRoll() {
		turn(Location.R1, Location.R2, Color.BLACK);
		turn(Location.R6, Location.R7, Color.RED);
		game.nextTurn();
		int[] diceValues = game.diceThrown(); // Dice show 5-6
		assertEquals(5, diceValues[0]);
		assertEquals(6, diceValues[1]);
	}

	@Test
	public void mustGive13AsFourthRoll() {
		turn(Location.R1, Location.R2, Color.BLACK);
		turn(Location.R6, Location.R7, Color.RED);
		turn(Location.R1, Location.R2, Color.BLACK);
		game.nextTurn();
		int[] diceValues = game.diceThrown(); // Dice show 1-2
		assertEquals(1, diceValues[0]);
		assertEquals(2, diceValues[1]);
	}

	@Test
	public void mustGive34AsFifthRoll() {
		turn(Location.R1, Location.R2, Color.BLACK);
		turn(Location.R6, Location.R7, Color.RED);
		turn(Location.R1, Location.R2, Color.BLACK);
		turn(Location.R6, Location.R7, Color.RED);
		game.nextTurn();
		int[] diceValues = game.diceThrown(); // Dice show 3-4
		assertEquals(3, diceValues[0]);
		assertEquals(4, diceValues[1]);
	}

	@Test
	public void mustGive56AsSixthRoll() {
		turn(Location.R1, Location.R2, Color.BLACK);
		turn(Location.R6, Location.R7, Color.RED);
		turn(Location.R1, Location.R2, Color.BLACK);
		turn(Location.R6, Location.R7, Color.RED);
		turn(Location.R12, Location.B11, Color.BLACK);
		game.nextTurn();
		int[] diceValues = game.diceThrown(); // Dice show 5-6
		assertEquals(5, diceValues[0]);
		assertEquals(6, diceValues[1]);
	}

	@Test
	public void secondTurnMustBeRedPlayers() {
		turn(Location.R1, Location.R2, Color.BLACK);
		game.nextTurn();
		assertEquals(Color.RED, game.getPlayerInTurn());
	}

	@Test
	public void mustGive00AsDiceValuesAtStart() {
		int[] diceValues = game.diceThrown();
		assertEquals(0, diceValues[0]);
		assertEquals(0, diceValues[1]);
	}

	@Test
	public void mustGive0AsNoOfTurnsLeft() {
		assertEquals(0, game.getNumberOfMovesLeft());
	}

	@Test
	public void mustGive2AsNoOfTurnsLeftAfterNextTurn() {
		game.nextTurn();
		assertEquals(2, game.getNumberOfMovesLeft());
	}

	@Test
	public void mustGive1AsNoOfTurnsLeftAfter1Move() {
		game.nextTurn(); // 2 Black checkers at R1 + 0 at R2
		game.move(Location.R1, Location.R2);
		assertEquals(1, game.getNumberOfMovesLeft());
	}

	@Test
	public void mustGive0AsNoOfTurnsLeftAfter2Moves() {
		game.nextTurn(); // 2 Black checkers at R1 + 0 at R2
		game.move(Location.R1, Location.R2);
		game.move(Location.R1, Location.R2);
		assertEquals(0, game.getNumberOfMovesLeft());
	}

	@Test
	public void mustGiveRedAsWinnerAfter6Rolls() {
		winGame();
		assertEquals(Color.RED, game.winner());
	}

	private void winGame() {
		turn(Location.R1, Location.R2, Color.BLACK);
		turn(Location.R6, Location.R7, Color.RED);
		turn(Location.R1, Location.R2, Color.BLACK);
		turn(Location.R6, Location.R7, Color.RED);
		turn(Location.R12, Location.B11, Color.BLACK);
		turn(Location.R6, Location.R7, Color.RED);
		game.nextTurn();
	}

	@Test
	public void mustBeInvalidMoveR2ToR3AsStartMove() {
		game.nextTurn(); // 0 checkers at R2 + 0 checkers at R3
		assertFalse(game.move(Location.R2, Location.R3));
	}

	@Test
	public void mustGiveNoneAnd0CheckersAtR1() {
		game.nextTurn(); // 2 checkers at R1 + 0 checkers at R2 + R3
		game.move(Location.R1, Location.R2);
		game.move(Location.R1, Location.R3);

		assertEquals(0, game.getCount(Location.R1));
		assertEquals(Color.NONE, game.getColor(Location.R1));
	}

	@Test
	public void mustBeInvalidMoveB1ToR4AsStartMove() {
		game.nextTurn(); // 2 Red checkers at B1 + 0 checkers at R4
		assertFalse(game.move(Location.B1, Location.R4));
	}

	@Test
	public void mustBeInvalidForBlackToMove3CheckersWith12DiceValues() {
		game.nextTurn(); // 2 checkers at R1 + 0 checkers at R2 + R3
		game.move(Location.R1, Location.R2);
		game.move(Location.R1, Location.R3);
		assertFalse(game.move(Location.R12, Location.R3));
	}

	@Test
	public void mustBeNoneAfterGameWon() {
		winGame();
		assertEquals(Color.NONE, game.getPlayerInTurn());
	}

	@Test
	public void mustBeInvalidToMoveAfterGameWon() {
		winGame();
		assertFalse(game.move(Location.R12, Location.R3));
		assertFalse(game.move(Location.B12, Location.R3));
		assertFalse(game.move(Location.R9, Location.R3));
	}
	
	@Test
	public void newGameMustResetTheBoard(){
		winGame();
		game.newGame();
		boardMustHaveCorrectSetupAtStart();
		mustHaveNoPlayerInTurnAfterNewGame();
	}
}
