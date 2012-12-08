package hotgammon.log;

import java.util.List;

import junit.framework.Assert;

import hotgammon.GameImpl;
import hotgammon.log.entity.HotgammonLog;
import hotgammon.replay.LogStrategyStub;
import hotgammon.variants.factory.AlphaMonFactory;

import org.junit.Before;
import org.junit.Test;

public class TestDecorator {

	
	private HotgammonLogDecorator game;
	private LogStrategyStub logStrategy;

	@Before
	public void setup() {
		logStrategy = new LogStrategyStub();
		game = new HotgammonLogDecorator(new GameImpl(new AlphaMonFactory()), logStrategy);
		game.newGame();
		
	}
	
	@Test
	public void nothingShouldBeRecordedIfLoggingIsDisabled() {
		logStrategy.logging(false);
		game.nextTurn();
		
		List<HotgammonLog> allHotgammonLogs = logStrategy.getAllHotgammonLogs();
		
		Assert.assertEquals(0, allHotgammonLogs.size());
		
		
	}
	
}
