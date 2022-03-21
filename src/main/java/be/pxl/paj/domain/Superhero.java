package be.pxl.paj.domain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Superhero {

	private static final Logger LOGGER = LogManager.getLogger(Superhero.class);

	@Id
	@GeneratedValue
	private long id;
	private String firstName;
	private String lastName;
	private String superheroName;
	@Column(name="notes")
	private String description;

	protected Superhero() {
		// JPA only
	}

	public Superhero(String firstName, String lastName, String superheroName) {
		LOGGER.debug("Creating a new superhero...");
		this.firstName = firstName;
		this.lastName = lastName;
		this.superheroName = superheroName;
	}

	public String getFirstName() {
		LOGGER.trace("FirstName of " + superheroName + " was revealed.");
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		LOGGER.fatal("LastName of " + superheroName + " was revealed.");
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSuperheroName() {
		return superheroName;
	}

	public void setSuperheroName(String superheroName) {
		this.superheroName = superheroName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Superhero{" +
				"superheroName='" + superheroName + '\'' +
				'}';
	}
}
