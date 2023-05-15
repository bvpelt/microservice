package nl.bsoft.kerk.microservices.person.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.bsoft.kerk.microservices.person.model.Person;
import nl.bsoft.kerk.microservices.person.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@Transactional
public class PersonController {

    private PersonService personService;

    @GetMapping("/person")
    public ResponseEntity<List<Person>> getPersons() {
        List<Person> items = personService.getPersons();

        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Optional<Person> item = personService.getPersonById(id);

        Person result = null;
        if (item.isPresent()) {
            result = item.get();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        log.info("New person: {}", person);
        if (person.getCreated() == null) {
            person.setCreated(OffsetDateTime.now());
        }
        Person result = personService.addPerson(person);
        log.info("Added person: {}", result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "DELETED")
    @DeleteMapping("/person/{id}")
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }

}
