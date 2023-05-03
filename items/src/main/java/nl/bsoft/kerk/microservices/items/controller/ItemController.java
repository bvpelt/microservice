package nl.bsoft.kerk.microservices.items.controller;

import lombok.AllArgsConstructor;
import nl.bsoft.kerk.microservices.items.model.Item;
import nl.bsoft.kerk.microservices.items.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
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
        if (item.getPublished() == null) {
            item.setPublished(LocalDateTime.now());
        }
        return itemService.addItem(item);
    }

    @DeleteMapping("/items/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

}
