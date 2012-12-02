package hotgammon.log;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerResources {
	private static EntityManagerFactory emf;
	
	static {
		emf = Persistence.createEntityManagerFactory("hotgammon");
	}
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
}
