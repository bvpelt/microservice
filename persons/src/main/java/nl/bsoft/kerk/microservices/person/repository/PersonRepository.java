package nl.bsoft.kerk.microservices.person.repository;

import nl.bsoft.kerk.microservices.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long> {
}
