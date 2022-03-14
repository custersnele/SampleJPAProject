package be.pxl.paj.dao;

import be.pxl.paj.domain.Researcher;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.function.Consumer;

public class ResearcherDao {
	private EntityManager entityManager;

	public ResearcherDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void save(Researcher researcher) {
		executeInsideTransaction(entityManager -> entityManager.persist(researcher));
	}

	public void update(Researcher researcher) {
		executeInsideTransaction(entityManager -> entityManager.merge(researcher));
	}

	public void delete(Researcher researcher) {
		executeInsideTransaction(entityManager -> entityManager.remove(researcher));
	}

	public Researcher findByEmail(String email) {
		// TODO: implement this method
		return null;
	}

	public Researcher findByName(String name) {
		TypedQuery<Researcher> query = entityManager.createQuery("SELECT r FROM Researcher r WHERE r.name = :name", Researcher.class);

		query.setParameter("name", name);
		return query.getSingleResult();
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
