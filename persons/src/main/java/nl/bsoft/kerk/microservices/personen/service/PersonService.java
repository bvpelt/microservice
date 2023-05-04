package nl.bsoft.kerk.microservices.personen.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.bsoft.kerk.microservices.personen.model.Person;
import nl.bsoft.kerk.microservices.personen.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    public Person addPerson(Person item) {
        return personRepository.save(item);
    }

    public Person deletePerson(Long id) {
        Optional<Person> item = getPersonById(id);
        Person result = null;
        if (item.isPresent()) {
            result = item.get();
            personRepository.deleteById(id);
        } else {
            log.warn("Person with id: {} not found - could not be deleted", id);
        }

        return result;
    }


}
