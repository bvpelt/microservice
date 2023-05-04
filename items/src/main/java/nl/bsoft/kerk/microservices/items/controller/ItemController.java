package nl.bsoft.kerk.microservices.items.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.bsoft.kerk.microservices.items.model.Item;
import nl.bsoft.kerk.microservices.items.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@Transactional
public class ItemController {

    private ItemService itemService;

    @GetMapping("/items")
    List<Item> getItems() {
        List<Item> items = itemService.getItems();

        return items;
    }

    @GetMapping("/items/{id}")
    Item getItemById(@PathVariable Long id) {
        Optional<Item> item = itemService.getItemById(id);

        Item result = null;
        if (item.isPresent()) {
            result = item.get();
        }

        return result;
    }

    @PostMapping("/items")
    public Item addItem(@RequestBody Item item) {
        log.info("New item: {}", item);
        if (item.getPublished() == null) {
            item.setPublished(LocalDateTime.now());
        }
        Item result = itemService.addItem(item);
        log.info("Added item: {}", result);
        return result;
    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

}
