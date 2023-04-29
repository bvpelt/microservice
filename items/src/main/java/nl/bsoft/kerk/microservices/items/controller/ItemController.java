package nl.bsoft.kerk.microservices.items.controller;

import lombok.AllArgsConstructor;
import nl.bsoft.kerk.microservices.items.model.Item;
import nl.bsoft.kerk.microservices.items.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
public class ItemController {

    private ItemService itemService;

    @GetMapping("/items")
    List<Item> getItems() {
        List<Item> items = itemService.getItems();

        return items;
    }

    @PostMapping("/items")
    public Item addItem(@RequestBody Item item) {
        if (item.getPublished() == null) {
            item.setPublished(new Date());
        }
        return itemService.addItem(item);
    }

}
