package nl.bsoft.kerk.microservices.items.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.bsoft.kerk.microservices.items.model.Item;
import nl.bsoft.kerk.microservices.items.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@Transactional
public class ItemController {

    private ItemService itemService;

    @GetMapping("/item")
    ResponseEntity<List<Item>> getItems() {
        List<Item> items = itemService.getItems();

        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    @GetMapping("/item/{id}")
    ResponseEntity<Item>  getItemById(@PathVariable Long id) {
        Optional<Item> item = itemService.getItemById(id);

        Item result = null;
        if (item.isPresent()) {
            result = item.get();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/item")
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        log.info("New item: {}", item);
        if (item.getPublished() == null) {
            item.setPublished(ZonedDateTime.now());
        }
        Item result = itemService.addItem(item);
        log.info("Added item: {}", result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/item/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }

}
