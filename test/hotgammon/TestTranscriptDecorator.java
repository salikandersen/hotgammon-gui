package hotgammon;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import hotgammon.variants.factory.AlphaMonFactory;

import org.junit.Before;
import org.junit.Test;

public class TestTranscriptDecorator {
	private Game game;
	private ByteArrayOutputStream output;
	
	@Before
	public void setup()
	{
		output = new ByteArrayOutputStream();
		game = new TranscriptDecorator(new GameImpl(new AlphaMonFactory()),output);
	}

	@Test
	public void shouldGiveTranscriptWhenNewGameStarted() throws UnsupportedEncodingException
	{
		game.newGame();
		assertEquals("New game started", new String(output.toByteArray(), "UTF-8"));
	}
	
	@Test
	public void shouldGiveTranscriptBlacksTurn() throws UnsupportedEncodingException
	{
		game.newGame();
		output.reset();
		game.nextTurn();
		assertEquals("Now it is BLACK's turn", new String(output.toByteArray(), "UTF-8"));
	}
	
	@Test
	public void shouldGiveTranscriptBlackMoveChecker() throws UnsupportedEncodingException
	{
		game.newGame();
		game.nextTurn();
		output.reset();
		game.move(Location.R1, Location.R3);
		assertEquals("BLACK moves (R1,R3)", new String(output.toByteArray(), "UTF-8"));
	}
	
	@Test
	public void shouldGiveTranscriptBlackFailsToMoveChecker() throws UnsupportedEncodingException
	{
		game.newGame();
		game.nextTurn();
		output.reset();
		game.move(Location.R2, Location.R3);
		assertEquals("BLACK fails to move (R2,R3)", new String(output.toByteArray(), "UTF-8"));
	}
	
	@Test
	public void shouldGiveTranscriptPlayerThrowsDice() throws UnsupportedEncodingException
	{
		game.newGame();
		game.nextTurn();
		output.reset();
		game.diceThrown();
		assertEquals("Dice rolled: 1-2", new String(output.toByteArray(), "UTF-8"));
	}
}
