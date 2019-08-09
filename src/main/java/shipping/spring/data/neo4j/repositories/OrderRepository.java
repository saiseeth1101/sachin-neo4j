package shipping.spring.data.neo4j.repositories;

import java.util.Collection;

import org.springframework.stereotype.Repository;
import shipping.spring.data.neo4j.domain.Order;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author
 * @author saiseeth
 * @author
 */
@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrderRepository extends Neo4jRepository<Order, Long> {

    //Address findByStreetName(@Param("streetName") String streetName);
    Order findByOrderNumber(Long orderNumber);

    //Collection<Address> findByStreetLike(@Param("streetName") String streetName);

    //@Query("MATCH (m:Address)<-[r:LIVES_IN]-(a:Address) RETURN m,r,a LIMIT {limit}")
    //Collection<Address> graph(@Param("limit") int limit);
    @Query("MATCH (m:Order) RETURN m")
    Collection<Order> graphOrder(@Param("limit") int limit);
}