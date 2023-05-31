package nl.bsoft.kerk.microservices.items.repository;

import nl.bsoft.kerk.microservices.items.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
/*
public interface ItemRepository extends JpaRepository<Item, Long> {
}
*/

public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
    Optional<Item> findById(long id);

    Item save(Item item);

    void deleteById(Long id);
}