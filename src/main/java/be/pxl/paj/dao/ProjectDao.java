package be.pxl.paj.dao;

import be.pxl.paj.domain.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.function.Consumer;

public class ProjectDao {
	private static final Logger LOGGER = LogManager.getLogger(ProjectDao.class);

	private final EntityManager entityManager;

	public ProjectDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Project project) {
		// TODO: check if the name of the project is unique.
		//  If the name is not unique, throw a EntityAlreadyExistsException.
		// You still have to implement the exception class.
		executeInsideTransaction(entityManager -> entityManager.persist(project));
	}

	public void delete(Project project) {
		executeInsideTransaction(entityManager -> entityManager.remove(project));
	}

	public Project findByName(String name) {
		TypedQuery<Project> query = entityManager.createQuery("SELECT p FROM Project p WHERE p.name = :name", Project.class);

		query.setParameter("name", name);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			LOGGER.warn("No project found with name [" + name + "]");
			return null;
		}
	}

	private void executeInsideTransaction(Consumer<EntityManager> action) {
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			action.accept(entityManager);
			tx.commit();
		}
		catch (RuntimeException e) {
			tx.rollback();
			throw e;
		}
	}
}
