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
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public Role deleteRole(Long id) {
        Optional<Role> role = getRoleById(id);
        Role result = null;
        if (role.isPresent()) {
            result = role.get();
            roleRepository.deleteById(id);
        } else {
            log.warn("Role with id: {} not found - could not be deleted", id);
        }
        return result;
    }


}
