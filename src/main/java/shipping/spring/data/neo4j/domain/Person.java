package shipping.spring.data.neo4j.domain;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author
 */
@NodeEntity
public class Person {

    @Id
    @GeneratedValue
	private Long id;
	private String name;
	private int born;

	@JsonIgnoreProperties("person")
	@Relationship(type = "LIVE_IN")
	//@Relationship(type = "LIVES_IN", direction = Relationship.INCOMING)
	private Address address;

	@JsonIgnoreProperties("person")
	@Relationship(type = "ACTS_AS")
	private List<Role> roles;

	public Person() {
	}

	public Person(String name, int born) {
		this.name = name;
		this.born = born;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getBorn() {
		return born;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void addRole(Role role) {
		if (this.roles == null) {
			this.roles = new ArrayList<>();
		}
		this.roles.add(role);
	}

	public void addAddress(Address address) {
		//if (this.address == null) {
			this.address = new Address(address.getStreet(), address.getHouse(), address.getCity(), address.getZip());
		//}
		//this.addresses.add(address);
	}

//	public List<Movie> getMovies() {
//		return movies;
//	}
}