package nl.bsoft.kerk.microservices.person.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.bsoft.kerk.microservices.person.model.Role;
import nl.bsoft.kerk.microservices.person.service.RoleService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
