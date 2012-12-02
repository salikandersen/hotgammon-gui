import hotgammon.Color;
import hotgammon.Location;
import hotgammon.log.entity.MoveLog;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class TestHibernate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
		
			
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		MoveLog hotgammonLog = new MoveLog();
		hotgammonLog.setPlayer(Color.RED);
		hotgammonLog.setFromLocation(Location.B1);
		hotgammonLog.setToLocation(Location.B4);
		em.persist(hotgammonLog);
		em.getTransaction().commit();
		
		System.out.print(hotgammonLog.getId());
		System.out.print(hotgammonLog.getCreated());
		System.out.print(hotgammonLog.getPlayer());
	}

}
