package shipping.spring.data.neo4j.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author
 */
@NodeEntity
public class Address {

	@Id
	@GeneratedValue
	private Long id;
	private Long zip;
	private String street;
	private int house;
	private String city;

	@JsonIgnoreProperties("address")
	@Relationship(type = "LIVES_IN")
	private List<Person> personList = new ArrayList<>();

	public Address() {
	}

	public Address(String streetName, int houseNumber, String city, Long zipcode) {
		this.street = streetName;
		this.house = houseNumber;
		this.city = city;
		this.zip = zipcode;
	}

	public Long getId() {
		return id;
	}

	public Long getZip() {
		return zip;
	}

	public String getStreet() {
		return street;
	}

	public int getHouse() {
		return house;
	}

	public String getCity() {
		return city;
	}

	public void addPerson(Person person) {
		if (this.personList == null) {
			this.personList = new ArrayList<>();
		}
		this.personList.add(person);
	}

	public List<Person> getPersonList(){
		return personList;
	}


}