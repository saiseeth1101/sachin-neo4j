package shipping.spring.data.neo4j.controller;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import shipping.spring.data.neo4j.domain.Address;
import shipping.spring.data.neo4j.domain.Order;
import shipping.spring.data.neo4j.model.OrderRequest;
import shipping.spring.data.neo4j.domain.Person;
import shipping.spring.data.neo4j.repositories.AddressRepository;
import shipping.spring.data.neo4j.repositories.OrderRepository;
import shipping.spring.data.neo4j.repositories.PersonRepository;
import shipping.spring.data.neo4j.services.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.json.JSONObject;
import org.json.XML;

/**
 * @author
 * @author
 */
@RestController
@RequestMapping("/")
public class CustomerController {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private OrderRepository orderRepository;

	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@JsonIgnore
	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/graph")
	public Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit) {
		return customerService.graph(limit == null ? 100 : limit);
//		return new HashMap<String, Object>();
	}


//	@PostMapping(path = "/members", consumes = "application/json", produces = "application/json")
//	public void addMember(@RequestBody Member member) {
//		//code
//	}

	@PostMapping(value="/saveGraphPost" , consumes = "application/json" , produces = "application/json")
	public String createOrders(@RequestBody OrderRequest orderRequest) throws Exception {


		//MOCK DATA
		Address address = new Address("streetName1", 1, "SanJose", 95134L);

		addressRepository.save(address);

		Person keanu = new Person("Keanu Reeves", 1964);
		personRepository.save(keanu);
		//personRepository.save(keanu);

		//		Role neo = new Role(address, keanu);
		//		neo.addRoleName("Neo");
		//
		//		keanu.addRole(neo);
		//keanu.addAddress(address);
		address.addPerson(keanu);


		addressRepository.save(address);

		Order orderDetails = new Order(1L, "Sports");
		orderRepository.save(orderDetails);

		Address srcAddr = new Address("streetNameSrc", 2, "SanJose", 95132L);
		Address destAddr = new Address("streetNameDest", 3, "SanJose", 95134L);
		orderDetails.addSourceAddress(srcAddr);
		orderDetails.addDestinationAddress(destAddr);
		orderDetails.addPerson(keanu);

		orderRepository.save(orderDetails);
		//MOCK DATA

		return "Successfully created entities";
	}

	@GetMapping(value="/savegraph")
	public String createPurchases() {
		Address address = new Address("streetName1", 1, "SanJose", 95134L);

		addressRepository.save(address);

		Person keanu = new Person("Keanu Reeves", 1964);
		personRepository.save(keanu);
		//personRepository.save(keanu);

		//		Role neo = new Role(address, keanu);
		//		neo.addRoleName("Neo");
		//
		//		keanu.addRole(neo);
		//keanu.addAddress(address);
		address.addPerson(keanu);


		addressRepository.save(address);

		Order orderDetails = new Order(1L, "Sports");
		orderRepository.save(orderDetails);

		Address srcAddr = new Address("streetNameSrc", 2, "SanJose", 95132L);
		Address destAddr = new Address("streetNameDest", 3, "SanJose", 95134L);
		orderDetails.addSourceAddress(srcAddr);
		orderDetails.addDestinationAddress(destAddr);
		orderDetails.addPerson(keanu);

		orderRepository.save(orderDetails);


		return "Successfully created entities";
	}


	@GetMapping(value="/getOrders", produces = "application/json")
	public List<OrderRequest> getOrders() {
		List<OrderRequest> returnOrders = new ArrayList<OrderRequest>();
		Iterable<Order> orders = orderRepository.findAll();
		if (!orders.iterator().hasNext()) {
			return returnOrders;
		} else {
			for (Order order : orders) {
				OrderRequest orderRequest = new OrderRequest();
				try {
					if (order != null && order.getOrderNumber() != null) {
						orderRequest.setOrderNumber(order.getOrderNumber());
					}
					if (order != null && order.getOrderType() != null) {
						orderRequest.setOrderType(order.getOrderType());
					}
					if (order != null && order.getDestinationAddress() != null) {
						orderRequest.setDestinationAddress(order.getDestinationAddress());
					}
					if (order != null && order.getSourceAddress() != null) {
						orderRequest.setSourceAddress(order.getSourceAddress());
					}
					if (order != null && order.getPerson().getName() != null) {
						orderRequest.setPersonName(order.getPerson().getName());
					}
					if (order != null && order.getPerson().getBorn() > 0) {
						orderRequest.setPersonBorn(order.getPerson().getBorn());
					}
					returnOrders.add(orderRequest);
				} catch (Exception ex) {
					LOGGER.error("Exception while accessing order objects:{}", ex.getMessage());
				}
			}
		}
		return returnOrders;
	}
	@GetMapping("/graphAdd")
	public Map<String, Object> graphAdd(@RequestParam(value = "limit",required = false) Integer limit) {
		return customerService.graphAdd(limit == null ? 100 : limit);
		//return new HashMap<String, Object>();
	}

	@GetMapping("/graphOrder")
	public Map<String, Object> graphOrder(@RequestParam(value = "limit",required = false) Integer limit) {
		return customerService.graphOrder(limit == null ? 100 : limit);
		//		return new HashMap<String, Object>();
	}
}
