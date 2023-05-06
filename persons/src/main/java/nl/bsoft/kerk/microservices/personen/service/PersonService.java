package nl.bsoft.kerk.microservices.personen.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.bsoft.kerk.microservices.personen.model.Person;
import nl.bsoft.kerk.microservices.personen.model.Role;
import nl.bsoft.kerk.microservices.personen.repository.PersonRepository;
import nl.bsoft.kerk.microservices.personen.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class PersonService {
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(Long id) {
        return personRepository.findById(id);
    }

    public Person addPerson(Person person) {
        Collection<Role> usedRoles = new ArrayList<>();
        person.getRoles().forEach(role -> {
            //
            // if role exist then get database role into person
            // else save role and add it to the person
            log.debug("Searching for role: {}", role.getName());
            Optional<Role> optionalRole = roleRepository.findByName(role.getName());
            Role savedRole = null;
            if (optionalRole.isPresent()) {
                savedRole = optionalRole.get();
                log.debug("Role found: {}", savedRole);
            } else {
                savedRole = roleRepository.save(role);
                log.debug("Role not found but created: {}", savedRole);
            }
            savedRole.getPersons().add(person);
            usedRoles.add(savedRole);
        });
        person.setRoles(usedRoles);
        Person savedPerson = personRepository.save(person);

        return savedPerson;
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
