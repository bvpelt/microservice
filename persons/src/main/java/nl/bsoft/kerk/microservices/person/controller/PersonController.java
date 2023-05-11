package nl.bsoft.kerk.microservices.person.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.bsoft.kerk.microservices.person.model.Person;
import nl.bsoft.kerk.microservices.person.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@Transactional
public class PersonController {

    private PersonService personService;

    @GetMapping("/person")
    List<Person> getPersons() {
        List<Person> items = personService.getPersons();

        return items;
    }

    @GetMapping("/person/{id}")
    Person getPersonById(@PathVariable Long id) {
        Optional<Person> item = personService.getPersonById(id);

        Person result = null;
        if (item.isPresent()) {
            result = item.get();
        }

        return result;
    }

    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person) {
        log.info("New item: {}", person);
        if (person.getCreated() == null) {
            person.setCreated(LocalDateTime.now());
        }
        Person result = personService.addPerson(person);
        log.info("Added item: {}", result);
        return result;
    }

    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }

}
