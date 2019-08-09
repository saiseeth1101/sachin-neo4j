package shipping.spring.data.neo4j.repositories;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Assert;
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
 * @author saiseeth
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
		Person keanu = new Person("Keanu Reeves", 1964);

		//Order Details

		Order orderDetails = new Order(1L, "Sports");


		orderDetails.addSourceAddress(new Address("streetNameSrc", 2, "SanJose", 95132L));
		orderDetails.addDestinationAddress(new Address("streetNameDest", 3, "SanJose", 95134L));
		orderDetails.addPerson(keanu);

		orderRepository.save(orderDetails);
	}

	/**
	 * Test of graph method, of class OrderRepository.
	 */
	@Test
	public void testGraph() {

		Collection<Order> graphOrder = orderRepository.graphOrder(1);
		Order order = graphOrder.iterator().next();
		Assert.assertEquals(order.getPerson().getName() ,("Keanu Reeves"));
		Assert.assertEquals(order.getOrderType(),("Sports"));

	}
}