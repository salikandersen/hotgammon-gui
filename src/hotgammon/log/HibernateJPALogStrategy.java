package hotgammon.log;

import java.util.List;

import hotgammon.log.entity.HotgammonLog;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class HibernateJPALogStrategy implements LogStrategy {

	private EntityManager entityManager;
	public HibernateJPALogStrategy(){
		this.entityManager = EntityManagerResources.getEntityManager();
	}
	@Override
	public void log(HotgammonLog hotgammonLog) {
		entityManager.getTransaction().begin();
		entityManager.persist(hotgammonLog);
		entityManager.getTransaction().commit();
	}
	
	public List<HotgammonLog> getAllHotgammonLogs() {
		Query query = entityManager.createNamedQuery("find.all.hotgammonlogs");
		return query.getResultList();
	}
	
	
}
