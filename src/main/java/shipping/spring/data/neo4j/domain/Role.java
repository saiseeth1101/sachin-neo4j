package shipping.spring.data.neo4j.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.*;

/**
 * @author
 */
@RelationshipEntity(type = "ACTS_AS")
public class Role {

    @Id
    @GeneratedValue
	private Long id;
	private List<String> roles = new ArrayList<>();

	@StartNode
	private Person person;

	@EndNode
	private Address address;

	public Role() {
	}

	public Role(Address address, Person actor) {
		this.address = address;
		this.person = actor;
	}

	public Long getId() {
	    return id;
	}

	public List<String> getRoles() {
	    return roles;
	}

	public Person getPerson() {
	    return person;
	}

	public Address getAddress() {
	    return address;
	}

    public void addRoleName(String name) {
        if (this.roles == null) {
            this.roles = new ArrayList<>();
        }
        this.roles.add(name);
    }
}