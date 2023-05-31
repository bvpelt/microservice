package nl.bsoft.kerk.microservices.items.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.bsoft.kerk.microservices.items.model.Item;
import nl.bsoft.kerk.microservices.items.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ItemService {
    @Autowired
    private final ItemRepository itemRepository;

    /*
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public Item deleteItem(Long id) {
        Optional<Item> item = getItemById(id);
        Item result = null;
        if (item.isPresent()) {
            result = item.get();
            itemRepository.deleteById(id);
        } else {
            log.warn("Item with id: {} not found - could not be deleted", id);
        }

        return result;
    }
*/

    public Page<Item> getItems(Pageable pageable) {
        return itemRepository.findAll(pageable);
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public Item deleteItem(Long id) {
        Optional<Item> item = getItemById(id);
        Item result = null;
        if (item.isPresent()) {
            result = item.get();
            itemRepository.deleteById(id);
        } else {
            log.warn("Item with id: {} not found - could not be deleted", id);
        }

        return result;
    }

}
