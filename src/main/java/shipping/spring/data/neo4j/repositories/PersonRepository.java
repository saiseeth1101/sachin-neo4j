package shipping.spring.data.neo4j.repositories;

import shipping.spring.data.neo4j.domain.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Collection;

/**
 * @author
 * @author
 */
@RepositoryRestResource(collectionResourceRel = "persons", path = "persons")
public interface PersonRepository extends Neo4jRepository<Person, Long> {

    Person findByName(String name);
    //Address findByStreetName(@Param("streetName") String streetName);

   // Collection<Address> findByStreetLike(@Param("streetName") String streetName);
    @Query("MATCH (m:Person) RETURN m")
    //@Query("MATCH (m:Person)<-[r:LIVES_IN]-(a:Address) RETURN m,r,a LIMIT {limit}")
    Collection<Person> graph(@Param("limit") int limit);

}