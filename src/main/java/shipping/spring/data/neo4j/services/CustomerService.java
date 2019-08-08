package shipping.spring.data.neo4j.services;

import java.util.*;

import shipping.spring.data.neo4j.domain.Order;
import shipping.spring.data.neo4j.domain.Role;
import shipping.spring.data.neo4j.repositories.OrderRepository;
import shipping.spring.data.neo4j.repositories.PersonRepository;
import shipping.spring.data.neo4j.domain.Address;
import shipping.spring.data.neo4j.domain.Person;
import shipping.spring.data.neo4j.repositories.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    private final static Logger LOG = LoggerFactory.getLogger(CustomerService.class);

	private final AddressRepository addressRepository;
	private final PersonRepository personRepository;
	private final OrderRepository orderRepository;

	public CustomerService(PersonRepository personRepository, AddressRepository addressRepository, OrderRepository orderRepository) {
		this.personRepository = personRepository;
		this.addressRepository = addressRepository;
		this.orderRepository = orderRepository;
	}

	private Map<String, Object> toD3Format(Collection<Person> customers) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		int i = 0;
		Iterator<Person> result = customers.iterator();
		while (result.hasNext()) {
			Person person = result.next();
			nodes.add(map("p1", person.getName(), "label", "person"));
			int target = i;
			i++;
			for (Role erole : person.getRoles()) {
				Map<String, Object> role = map("title", erole.getPerson().getName(), "label", "role");
				int source = nodes.indexOf(role);
				if (source == -1) {
					nodes.add(role);
					source = i++;
				}
				rels.add(map("source", source, "target", target));
			}
		}
		LOG.info("NODES PERSON:{}", nodes.size());
		return map("nodes", nodes, "links", rels);
	}

	private Map<String, Object> toD3FormatAddress(Collection<Address> addressesList) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		int i = 0;
		Iterator<Address> result = addressesList.iterator();
		while (result.hasNext()) {
			Address address = result.next();
			nodes.add(map("a1", address.getStreet(), "label", "address"));
			int target = i;
			i++;
			for (Person eperson : address.getPersonList()) {
				Map<String, Object> persn = map("title", eperson.getName(), "label", "name");
				int source = nodes.indexOf(persn);
				if (source == -1) {
					nodes.add(persn);
					source = i++;
				}
				rels.add(map("source", source, "target", target));
			}
		}
		LOG.info("NODES:{}", nodes.size());
		return map("nodes", nodes, "links", rels);
	}

	private Map<String, Object> toD3FormatOrderDetails(Collection<Order> orderDetails) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		int i = 0;
		Iterator<Order> result = orderDetails.iterator();
		while (result.hasNext()) {
			Order orderDetails1 = result.next();
			nodes.add(map("a1", orderDetails1.getOrderNumber(), "label", "order"));
			int target = i;
			i++;
			//for (OrderDetails eachOrder : orderDetails1.getPerson()) {
			//Add Person
				Map<String, Object> persn = map("title", orderDetails1.getPerson(), "label", "name");
				int source = nodes.indexOf(persn);
				if (source == -1) {
					nodes.add(persn);
					source = i++;
				}
				rels.add(map("source", source, "target", target));

			//Add Source Address
			Map<String, Object> srcAddress = map("title", orderDetails1.getSourceAddress(), "label", "address");
			int source1 = nodes.indexOf(srcAddress);
			if (source1 == -1) {
				nodes.add(srcAddress);
				source1 = i++;
			}
			rels.add(map("source", source1, "target", target));

			// Add Dest Address

			Map<String, Object> destAddress = map("title", orderDetails1.getDestinationAddress(), "label", "address");
			int source2 = nodes.indexOf(destAddress);
			if (source2 == -1) {
				nodes.add(destAddress);
				source2 = i++;
			}
			rels.add(map("source", source2, "target", target));
			//}
		}
		LOG.info("NODES:{}", nodes.size());
		return map("nodes", nodes, "links", rels);
	}

	private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		return result;
	}

    @Transactional(readOnly = true)
    public Person findByName(String personName) {
        Person person = personRepository.findByName(personName);
        return person;
    }

	@Transactional(readOnly = true)
	public Address findByStreet(String street) {
		Address address = addressRepository.findByStreet(street);
		return address;
	}

	@Transactional(readOnly = true)
	public Order findByOrderNumber(Long orderNumber) {
		Order orderDetails = orderRepository.findByOrderNumber(orderNumber);
		return orderDetails;
	}


	@Transactional(readOnly = true)
	public Map<String, Object>  graph(int limit) {
		Collection<Person> result = personRepository.graph(5);
		LOG.info("RESULT graph:{}", result);
		return toD3Format(result);
	}

	@Transactional(readOnly = true)
	public Map<String, Object>  graphAdd(int limit) {
		Collection<Address> result = addressRepository.graphAdd(5);
		LOG.info("RESULT graphAdd:{}", result);
		return toD3FormatAddress(result);
	}

	@Transactional(readOnly = true)
	public Map<String, Object>  graphOrder(int limit) {
		Collection<Order> result = orderRepository.graphOrder(5);
		LOG.info("RESULT graphOrderDetails:{}", result);
		return toD3FormatOrderDetails(result);
	}
}
