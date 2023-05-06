package nl.bsoft.kerk.microservices.personen.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.bsoft.kerk.microservices.personen.model.Person;
import nl.bsoft.kerk.microservices.personen.model.Role;
import nl.bsoft.kerk.microservices.personen.service.PersonService;
import nl.bsoft.kerk.microservices.personen.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@Transactional
public class RoleController {

    private RoleService roleService;

    @GetMapping("/role")
    List<Role> getRoles() {
        List<Role> items = roleService.getRoles();

        return items;
    }

    @GetMapping("/role/{id}")
    Role getRoleById(@PathVariable Long id) {
        Optional<Role> item = roleService.getRoleById(id);

        Role result = null;
        if (item.isPresent()) {
            result = item.get();
        }

        return result;
    }

    @DeleteMapping("/role/{id}")
    public void deletePerson(@PathVariable Long id) {
        roleService.deleteRole(id);
    }

}
