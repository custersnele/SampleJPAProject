package be.pxl.paj;

import be.pxl.paj.dao.ProjectDao;
import be.pxl.paj.dao.ResearcherDao;
import be.pxl.paj.domain.Project;
import be.pxl.paj.domain.Researcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AssignProjectApp {

	private static final Logger LOGGER = LogManager.getLogger(AssignProjectApp.class);

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		Scanner scanner = new Scanner(System.in);
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("musicdb_pu");
			entityManager = entityManagerFactory.createEntityManager();
			ProjectDao projectDao = new ProjectDao(entityManager);
			ResearcherDao researcherDao = new ResearcherDao(entityManager);
			System.out.println("Project name: ");
			String projectName = scanner.nextLine();
			System.out.println("Researcher name: ");
			String researcherName = scanner.nextLine();
			Project project = projectDao.findByName(projectName);
			Researcher researcher = researcherDao.findByName(researcherName);
			researcher.setProject(project);
			researcherDao.update(researcher);
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
