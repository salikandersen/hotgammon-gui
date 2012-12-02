package hotgamon.log;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import junit.framework.Assert;

import hotgammon.Color;
import hotgammon.Game;
import hotgammon.GameImpl;
import hotgammon.Location;
import hotgammon.log.EntityManagerResources;
import hotgammon.log.HibernateJPALogStrategy;
import hotgammon.log.HotgammonLogDecorator;
import hotgammon.log.entity.HotgammonLog;
import hotgammon.log.entity.MoveLog;
import hotgammon.variants.factory.AlphaMonFactory;

import org.junit.Before;
import org.junit.Test;

public class TestMoveLog {
	private Game game;
	private EntityManager em;
	private HibernateJPALogStrategy logStrategy;

	@Before
	public void setup() {
		logStrategy = new HibernateJPALogStrategy();
		game = new HotgammonLogDecorator(new GameImpl(new AlphaMonFactory()), logStrategy);
		game.newGame();
		
		em = EntityManagerResources.getEntityManager();
	}
	
	@Test
	public void testMove(){
		game.nextTurn();
		
		Location from = Location.R1;
		Location to = Location.B4;
		
		game.move(from, to);
		
		Query query = em.createQuery("select max(id) from MoveLog m");
		
		List<HotgammonLog> allHotgammonLogs = logStrategy.getAllHotgammonLogs();
		Assert.assertEquals(1, allHotgammonLogs.size());
		
		MoveLog moveLog = (MoveLog) allHotgammonLogs.get(0);
		Assert.assertEquals(from, moveLog.getFromLocation());
		Assert.assertEquals(to, moveLog.getToLocation());
		Assert.assertEquals(Color.BLACK, moveLog.getPlayer());
		Assert.assertEquals(true, moveLog.getResultOfAction());
		
		
	}
}
