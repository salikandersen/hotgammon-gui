package hotgammon.log;

import java.util.List;

import hotgammon.log.entity.HotgammonLog;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class HibernateJPALogStrategy implements LogStrategy {

	private EntityManager entityManager;
	private boolean enabled = true;
	public HibernateJPALogStrategy(){
		this.entityManager = EntityManagerResources.getEntityManager();
	}
	@Override
	public void log(HotgammonLog hotgammonLog) {
		if (enabled) {
			entityManager.getTransaction().begin();
			entityManager.persist(hotgammonLog);
			entityManager.getTransaction().commit();
		}
	}
	
	public List<HotgammonLog> getAllHotgammonLogs() {
		Query query = entityManager.createNamedQuery("find.all.hotgammonlogs");
		return query.getResultList();
	}
	@Override
	public void logging(boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public void resetLogs() {
		entityManager.getTransaction().begin();
		Query query = entityManager.createNamedQuery("delete.all.hotgammonlogs");
		query.executeUpdate();
		entityManager.getTransaction().commit();
	}
}
