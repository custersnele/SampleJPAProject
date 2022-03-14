package be.pxl.paj;

import be.pxl.paj.domain.Superhero;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class RetrieveJPA {

	private static final Logger LOGGER = LogManager.getLogger(RetrieveJPA.class);

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musicdb_pu");
		EntityManager entityManager = emf.createEntityManager();
		TypedQuery<Superhero> query = entityManager.createQuery("SELECT s FROM Superhero s WHERE s.superheroName = :name", Superhero.class);;
		query.setParameter("name", "Superman");
		Superhero result = query.getSingleResult();

		LOGGER.info(result.getFirstName());

		entityManager.close();
		emf.close();
	}
}
