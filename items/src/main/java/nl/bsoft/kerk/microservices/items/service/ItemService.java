package nl.bsoft.kerk.microservices.items.service;

import lombok.AllArgsConstructor;
import nl.bsoft.kerk.microservices.items.model.Item;
import nl.bsoft.kerk.microservices.items.repository.ItemRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }
}
