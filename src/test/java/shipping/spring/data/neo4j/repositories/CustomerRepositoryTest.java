package shipping.spring.data.neo4j.repositories;

import static org.junit.Assert.*;

import java.util.Collection;

import shipping.spring.data.neo4j.domain.Address;
import shipping.spring.data.neo4j.domain.Order;
import shipping.spring.data.neo4j.domain.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import shipping.spring.data.neo4j.repositories.AddressRepository;
import shipping.spring.data.neo4j.repositories.OrderRepository;
import shipping.spring.data.neo4j.repositories.PersonRepository;

/**
 * @author pdtyreus
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class CustomerRepositoryTest {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Before
	public void setUp() {
//		Address address = new Address("streetName1", 1, "SanJose", 95134L);
//
//		addressRepository.save(address);
//
//		Person keanu = new Person("Keanu Reeves", 1964);
//		personRepository.save(keanu);
		//personRepository.save(keanu);

//		Role neo = new Role(address, keanu);
//		neo.addRoleName("Neo");
//
//		keanu.addRole(neo);
		//keanu.addAddress(address);
//		address.addPerson(keanu);
//
//		addressRepository.save(address);
//
//		//Order Details
//
//		Order orderDetails = new Order(1L, "Sports");
//
//		//orderRepository.save(orderDetails);
//
//		orderDetails.addSourceAddress(new Address("streetNameSrc", 2, "SanJose", 95132L));
//		orderDetails.addDestinationAddress(new Address("streetNameDest", 3, "SanJose", 95134L));
//		orderDetails.addPerson(keanu);

		//orderRepository.save(orderDetails);
	}

	/**
	 * Test of graph method, of class MovieRepository.
	 */
	@Test
	public void testGraph() {
//		Collection<Person> graph = personRepository.graph(5);
//		Collection<Address> graphAdd = addressRepository.graphAdd(5);
//		Collection<Order> graphOrder = orderRepository.graphOrder(5);

		//assertEquals(1, graph.size());

		//Person person = graph.iterator().next();
		//Address address = graphAdd.iterator().next();

		//assertEquals(1, person.getRoles().size());

		//assertEquals("Keanu Reeves", person.getName());
		//assertEquals("Keanu Reeves", movie.getRoles().iterator().next().getPerson().getName());
	}
}