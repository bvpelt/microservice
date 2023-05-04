package nl.bsoft.kerk.microservices.personen.repository;

import nl.bsoft.kerk.microservices.personen.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long> {
}
