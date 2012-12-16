package hotgammon.log.integration;

import hotgammon.Color;
import hotgammon.Game;
import hotgammon.GameImpl;
import hotgammon.Location;
import hotgammon.log.DirectSqlLogStrategy;
import hotgammon.log.HotgammonLogDecorator;
import hotgammon.log.entity.DiceThrownLog;
import hotgammon.log.entity.HotgammonLog;
import hotgammon.log.entity.MoveLog;
import hotgammon.variants.factory.AlphaMonFactory;

import java.sql.SQLException;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestDirectSqlLogStrategy {
	private Game game;
	private DirectSqlLogStrategy logStrategy;

	@Before
	public void setup() throws SQLException {
		logStrategy = new DirectSqlLogStrategy();
		game = new HotgammonLogDecorator(new GameImpl(new AlphaMonFactory()), logStrategy);
		game.newGame();
		logStrategy.resetLogs();
	}
	
	@Test
	public void testMove(){
		game.nextTurn();
		
		Location from = Location.R1;
		Location to = Location.B4;
		
		game.move(from, to);
		
		List<HotgammonLog> allHotgammonLogs = logStrategy.getAllHotgammonLogs();
		
		int numberOfMoveLogsFound = 0;
		for (HotgammonLog hotgammonLog : allHotgammonLogs) {
			
			if(hotgammonLog instanceof MoveLog){
				MoveLog moveLog = (MoveLog) hotgammonLog;
				Assert.assertEquals(from, moveLog.getFromLocation());
				Assert.assertEquals(to, moveLog.getToLocation());
				Assert.assertEquals(Color.BLACK, moveLog.getPlayer());
				Assert.assertEquals(true, moveLog.getResultOfAction());
				numberOfMoveLogsFound++;
			}
		}
		
		Assert.assertEquals(1, numberOfMoveLogsFound);
	}
	
	@Test
	public void testDiceThrown(){
		game.nextTurn();
		int[] diceValuesLeft = game.diceValuesLeft();
		
		List<HotgammonLog> allHotgammonLogs = logStrategy.getAllHotgammonLogs();
		
		int numberOfDiceThrownLogsFound = 0;
		for (HotgammonLog hotgammonLog : allHotgammonLogs) {
			if(hotgammonLog instanceof DiceThrownLog){
				DiceThrownLog moveLog = (DiceThrownLog) hotgammonLog;
				Assert.assertEquals(diceValuesLeft[0], moveLog.getDiceOne());
				Assert.assertEquals(diceValuesLeft[1], moveLog.getDiceTwo());
				numberOfDiceThrownLogsFound++;
			}
		}
		
		Assert.assertEquals(1, numberOfDiceThrownLogsFound);
	}

	@Test
	public void testResetLogs(){
		game.nextTurn();
		Assert.assertEquals(1, logStrategy.getAllHotgammonLogs().size());
		logStrategy.resetLogs();
		Assert.assertEquals(0, logStrategy.getAllHotgammonLogs().size());
	}
}
