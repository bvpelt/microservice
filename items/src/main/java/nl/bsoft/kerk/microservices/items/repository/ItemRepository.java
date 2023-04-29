package nl.bsoft.kerk.microservices.items.repository;

import nl.bsoft.kerk.microservices.items.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, Long> {
}
