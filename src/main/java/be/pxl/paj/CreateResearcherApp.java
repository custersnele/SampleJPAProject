package be.pxl.paj;

import be.pxl.paj.dao.ResearcherDao;
import be.pxl.paj.domain.ContactInformation;
import be.pxl.paj.domain.Researcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class CreateResearcherApp {

	private static final Logger LOGGER = LogManager.getLogger(CreateProjectApp.class);

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager entityManager = null;
		Scanner scanner = new Scanner(System.in);
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("musicdb_pu");
			entityManager = entityManagerFactory.createEntityManager();
			ResearcherDao researcherDao = new ResearcherDao(entityManager);
			System.out.println("Researcher name: ");
			String name = scanner.nextLine();
			System.out.println("Phone: ");
			String phone = scanner.nextLine();
			System.out.println("LinkedIn: ");
			String linkedIn = scanner.nextLine();
			System.out.println("E-mail: ");
			String email = scanner.nextLine();
			Researcher researcher = new Researcher();
			researcher.setName(name);
			ContactInformation contactInformation = new ContactInformation();
			contactInformation.setPhone(phone);
			contactInformation.setLinkedIn(linkedIn);
			contactInformation.setEmail(email);
			researcher.setContactInformation(contactInformation);
			researcherDao.save(researcher);
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
