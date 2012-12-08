package hotgammon.replay;

import hotgammon.Color;
import hotgammon.Game;
import hotgammon.GameImpl;
import hotgammon.Location;
import hotgammon.log.HotgammonLogDecorator;
import hotgammon.log.LogStrategy;
import hotgammon.variants.factory.AlphaMonFactory;
import hotgammon.variants.factory.BetaMonFactory;
import hotgammon.variants.factory.SemiMonFactory;
import junit.framework.Assert;

import org.hibernate.engine.transaction.spi.LocalStatus;
import org.junit.Before;
import org.junit.Test;

import com.sun.xml.internal.ws.api.pipe.NextAction;

public class TestReplay {
	private Game game;
	private LogStrategy logStrategy;

	@Before
	public void setup() {
		logStrategy = new LogStrategyStub();
		game = new HotgammonLogDecorator(new GameImpl(new BetaMonFactory()), logStrategy);
		game.newGame();
	}
	
	
	@Test
	public void afterReplayDiceThrownMustReturnTheSameAsDiceThrownDidTheFirstTime() {
		game.nextTurn();
		int[] diceThrown = game.diceThrown();
		
		game.newGame();
		
		logStrategy.logging(false);
		
		Replayer replayer = new Replayer(game, logStrategy);
		replayer.replay();
		
		int[] replayDiceThrown = game.diceThrown();
		
		Assert.assertEquals(diceThrown[0], replayDiceThrown[0]);
		Assert.assertEquals(diceThrown[1], replayDiceThrown[1]);
	}
	
	@Test
	public void ReplayMoveFromR1ToR3() {
		game.nextTurn();
		game.move(Location.R1, Location.R3);
		
		game.newGame();
		
		logStrategy.logging(false);
		
		Replayer replayer = new Replayer(game, logStrategy);
		replayer.replay();
		
		Assert.assertEquals(1, game.getCount(Location.R3));
		Assert.assertEquals(1, game.getCount(Location.R1));
	}
	
	@Test
	public void ReplayMoveAndDicethrownSeries() {
		game.nextTurn();
		game.move(Location.R1, Location.R3);
		game.nextTurn();
		game.move(Location.R6, Location.R3);
		game.nextTurn();
		game.move(Location.B_BAR, Location.R5);
		
		logStrategy.logging(false);
		
		game.newGame();
		
		Replayer replayer = new Replayer(game, logStrategy);
		replayer.replay();
		
		Assert.assertEquals(1, game.getCount(Location.R3));
		Assert.assertEquals(Color.RED, game.getColor(Location.R3));
		Assert.assertEquals(0, game.getCount(Location.B_BAR));
		Assert.assertEquals(Color.NONE, game.getColor(Location.B_BAR));
		Assert.assertEquals(1, game.getCount(Location.R5));
		Assert.assertEquals(Color.BLACK, game.getColor(Location.R5));
		
	}
	
	@Test
	public void ReplayMoveAndContinuePlayingAndReplayEverything() {
		game.nextTurn();
		game.move(Location.R1, Location.R3);
		
		logStrategy.logging(false);
		
		game.newGame();
		
		Replayer replayer = new Replayer(game, logStrategy);
		replayer.replay();
		
		logStrategy.logging(true);
		game.nextTurn();
		game.move(Location.R6, Location.R3);
		logStrategy.logging(false);
		
		game.newGame();
		
		replayer.replay();
		
		Assert.assertEquals(1, game.getCount(Location.R3));
		Assert.assertEquals(Color.RED, game.getColor(Location.R3));
		Assert.assertEquals(1, game.getCount(Location.B_BAR));
		Assert.assertEquals(Color.BLACK, game.getColor(Location.B_BAR));
	}
	
	
}
