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

public class ProjectDetailsApp {

	private static final Logger LOGGER = LogManager.getLogger(ProjectDetailsApp.class);

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		Scanner scanner = new Scanner(System.in);
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("musicdb_pu");
			entityManager = entityManagerFactory.createEntityManager();
			ProjectDao projectDao = new ProjectDao(entityManager);
			ResearcherDao researcherDao = new ResearcherDao(entityManager);
			System.out.println("Make your choice:");
			System.out.println("1. Information about a researcher");
			System.out.println("2. Information about a project");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
				case 1 -> findResearcher(scanner, researcherDao);
				case 2 -> findProject(scanner, projectDao);
			}
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

	private static void findProject(Scanner scanner, ProjectDao projectDao) {
		System.out.println("Project name: ");
		String researcherName = scanner.nextLine();
		Project project = projectDao.findByName(researcherName);
		System.out.println("*** " + project.getName());
		project.getResearchers().forEach(r -> System.out.println("\t -> " + r.getName()));
	}

	private static void findResearcher(Scanner scanner, ResearcherDao researcherDao) {
		System.out.println("Researcher name: ");
		String researcherName = scanner.nextLine();
		Researcher researcher = researcherDao.findByName(researcherName);
		System.out.println(researcher);
	}
}
