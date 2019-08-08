package shipping.spring.data.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * @author
 * @author
 */
@SpringBootApplication
@EnableNeo4jRepositories("shipping.spring.data.neo4j.repositories")
public class SampleCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleCustomerApplication.class, args);
    }
}