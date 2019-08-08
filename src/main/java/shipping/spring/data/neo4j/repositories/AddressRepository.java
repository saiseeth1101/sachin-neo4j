package shipping.spring.data.neo4j.repositories;

import java.util.Collection;

import shipping.spring.data.neo4j.domain.Address;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author
 * @author
 * @author
 */
@RepositoryRestResource(collectionResourceRel = "addresses", path = "addresses")
public interface AddressRepository extends Neo4jRepository<Address, Long> {

	//Address findByStreetName(@Param("streetName") String streetName);
	Address findByStreet(String street);

	//Collection<Address> findByStreetLike(@Param("streetName") String streetName);

    //@Query("MATCH (m:Addreee)<-[r:LIVES_IN]-(a:Address) RETURN m,r,a LIMIT {limit}")
	//Collection<Address> graph(@Param("limit") int limit);
	@Query("MATCH (m:Address) RETURN m")
	//@Query("MATCH (m:Address)<-[r:LIVES_IN]-(a:Address) RETURN m,r,a LIMIT {limit}")
	Collection<Address> graphAdd(@Param("limit") int limit);
}