package hotgammon.variants;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import hotgammon.Color;
import hotgammon.Game;
import hotgammon.GameImpl;
import hotgammon.Location;
import hotgammon.variants.factory.BetaMonFactory;

import org.junit.Before;
import org.junit.Test;

public class TestBetaMon {

	private Game game;

	@Before
	public void setup() {
		game = new GameImpl(new BetaMonFactory());
		game.newGame();
	}
	
	@Test
	public void cannotMoveFromEmptyLocation(){
		game.nextTurn();
		assertFalse(game.move(Location.R2, Location.R3));
	}

	@Test
	public void blackCanMoveFromR1ToR2(){
		game.nextTurn(); 
		assertTrue(game.move(Location.R1, Location.R2));
	}
	
	@Test
	public void blackCannotMoveRedCheckerAtR6toR7(){
		game.nextTurn();
		assertFalse(game.move(Location.R6, Location.R7));
	}
	
	@Test
	public void blackCannotMoveTowardsRedInnerTable(){
		game.nextTurn();
		assertFalse(game.move(Location.R12, Location.R11));
	}
	
	@Test
	public void redCannotMoveTowardsBlackInnerTable(){
		game.nextTurn();
		game.nextTurn();
		assertFalse(game.move(Location.R6, Location.R7));
	}
	
	@Test
	public void noneCannotMoveTowardsBlackInnerTable(){
		assertEquals(Color.NONE, game.getPlayerInTurn());
		assertFalse(game.move(Location.R6, Location.R7));
	}
	
	@Test
	public void notPossibleForBlackToMove5AsFirstTurn(){
		game.nextTurn();
		
		assertFalse(game.move(Location.R12, Location.B8));
	}
	
	@Test
	public void notPossibleForRedToMove5AsFirstTurn(){
		game.nextTurn();
		game.nextTurn();
		
		assertFalse(game.move(Location.R8, Location.R3));
	}
	
	@Test
	public void redCanMoveFromR8ToR5(){
		game.nextTurn(); 
		game.nextTurn(); 
		assertTrue(game.move(Location.R8, Location.R5));
	}
	
	@Test
	public void blackCannotMoveFromR1ToR2Twice(){
		game.nextTurn(); 
		assertTrue(game.move(Location.R1, Location.R2));
		assertFalse(game.move(Location.R1, Location.R2));
	}
	
	@Test
	public void legalToMoveToLocationWhereOneBlackOpponentIspositionedAndOpponentIsMovedToBlackBar(){
		game.nextTurn(); 
		assertTrue(game.move(Location.B6, Location.B5));
		assertTrue(game.move(Location.B6, Location.B4));
		
		game.nextTurn();
		assertTrue(game.move(Location.B1, Location.B4));
		assertEquals(1, game.getCount(Location.B4));
		assertEquals(1, game.getCount(Location.B_BAR));
		assertEquals(Color.BLACK, game.getColor(Location.B_BAR));
	}
	
	@Test
	public void notLegalToMoveToLocationWhereOpponentHasMoreThanOneChecker(){
		game.nextTurn(); 
		assertFalse(game.move(Location.R12, Location.B12));
	}
	
	@Test
	public void legalToMoveToLocationWhereOneRedOpponentIsPositionedAndOpponentIsMovedToRedBar() {
		game.nextTurn(); 
		game.nextTurn(); 
		assertTrue(game.move(Location.B1, Location.B4));
		
		game.nextTurn();
		assertTrue(game.move(Location.B6, Location.B1));
		assertEquals(1, game.getCount(Location.B1));
		assertEquals(1, game.getCount(Location.R_BAR));
		assertEquals(Color.RED, game.getColor(Location.R_BAR));
		
	}
	
	@Test
	public void redIsNotAllowedToMoveAnotherCheckerIfOneIsLocatedAtTheBar() {
		game.nextTurn(); 
		game.nextTurn(); 
		game.move(Location.B1, Location.B4);
		
		game.nextTurn();
		game.move(Location.B6, Location.B1); // placing a checker at Red Bar
		
		game.nextTurn();
		assertFalse(game.move(Location.R8, Location.R7));
	}
	
	@Test
	public void blackIsNotAllowedToMoveAnotherCheckerIfOneIsLocatedAtTheBar(){ 
		game.nextTurn(); 
		game.move(Location.B6, Location.B5);
		game.move(Location.B6, Location.B4);
		
		game.nextTurn();
		game.move(Location.B1, Location.B4);
		game.nextTurn();
		
		assertFalse(game.move(Location.B8, Location.B3));
	}
	
	@Test
	public void blackIsAbleToMoveTheCheckerPlacedAtTheBar(){ 
		game.nextTurn(); 
		assertTrue(game.move(Location.B6, Location.B5));
		assertTrue(game.move(Location.B6, Location.B4));
		
		game.nextTurn();
		assertTrue(game.move(Location.B1, Location.B4));
		game.nextTurn();
		
		assertTrue(game.move(Location.B_BAR, Location.R5));
	}
}
