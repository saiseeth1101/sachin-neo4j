package shipping.spring.data.neo4j.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * @author
 */
@NodeEntity
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    private Long orderNumber;
    private String orderType;


    @JsonIgnoreProperties("order")
    @Relationship(type = "BELONGS_TO")
    private Person person;

    @JsonIgnoreProperties("order")
    @Relationship(type = "SOURCE_ADDRESS")
    private Address sourceAddress;

    @JsonIgnoreProperties("order")
    @Relationship(type = "DESTINATION_ADDRESS")
    private Address destinationAddress;


    public Order() {
    }

    public Order(Long orderNumber, String orderType) {
        this.orderNumber = orderNumber;
        this.orderType = orderType;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public String getOrderType() {
        return orderType;
    }

    public Address getSourceAddress() {
        return sourceAddress;
    }

    public Address getDestinationAddress() {
        return destinationAddress;
    }

    public Person getPerson() {
        return person;
    }

    public void addSourceAddress(Address address) {
        this.sourceAddress = new Address(address.getStreet(), address.getHouse(), address.getCity(), address.getZip());
    }

    public void addDestinationAddress(Address address) {
        this.destinationAddress = new Address(address.getStreet(), address.getHouse(), address.getCity(), address.getZip());
    }

    public void addPerson(Person person) {
        this.person = new Person(person.getName(), person.getBorn());
    }

}