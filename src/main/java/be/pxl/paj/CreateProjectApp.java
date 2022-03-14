package be.pxl.paj;

import be.pxl.paj.dao.ProjectDao;
import be.pxl.paj.domain.Project;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class CreateProjectApp {

	private static final Logger LOGGER = LogManager.getLogger(CreateProjectApp.class);

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		Scanner scanner = new Scanner(System.in);
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("musicdb_pu");
			entityManager = entityManagerFactory.createEntityManager();
			ProjectDao projectDao = new ProjectDao(entityManager);
			System.out.println("Project name: ");
			String name = scanner.nextLine();
			Project project = new Project(name);
			projectDao.save(project);
		}
		finally {
			if (entityManager != null) {
				entityManager.close();
			}
			if (entityManagerFactory != null) {
				entityManagerFactory.close();
			}
		}
	}
}
