package be.pxl.paj;

import be.pxl.paj.domain.Superhero;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistJPA {

	private static final Logger LOGGER = LogManager.getLogger(PersistJPA.class);

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musicdb_pu");
		EntityManager entityManager = emf.createEntityManager();
		Superhero superhero = new Superhero("Clark", "Kent", "Superman");
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.persist(superhero);
		transaction.commit();

		long superHeroId = superhero.getId();
		LOGGER.info("Superhero saved with id [" + superHeroId + "]");
		//entityManager.clear();

		Superhero savedSuperhero = entityManager.find(Superhero.class, superHeroId);
		LOGGER.info(savedSuperhero.getSuperheroName());
		entityManager.close();
		emf.close();
	}
}
