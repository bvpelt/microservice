package nl.bsoft.kerk.microservices.person.repository;

import nl.bsoft.kerk.microservices.person.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
