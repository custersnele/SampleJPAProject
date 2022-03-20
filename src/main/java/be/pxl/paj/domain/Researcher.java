package be.pxl.paj.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Researcher {

	private static final Logger LOGGER = LogManager.getLogger(Researcher.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 40, nullable = false)
	private String name;
	@OneToOne(cascade = CascadeType.ALL)
	private ContactInformation contactInformation;
	@ManyToOne
	private Project project;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}

	// TODO: write unit tests for this method
	public void setProject(Project project) {
		if (this.project != null) {
			if (this.project.equals(project)) {
				LOGGER.info("Researcher [" + name + "] already assigned to [" + project.getName() + "]");
				return;
			}
			this.project.removeResearcher(this);
		}
		this.project = project;
		project.addResearcher(this);
	}

	@Override
	public String toString() {
		return "Researcher{" +
				"id=" + id +
				", name='" + name + '\'' +
				", contactInformation=" + contactInformation +
				'}';
	}
}
