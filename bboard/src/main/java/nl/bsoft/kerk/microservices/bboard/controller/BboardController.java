package nl.bsoft.kerk.microservices.bboard.controller;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.bsoft.kerk.microservices.bboard.service.BboardService;
import nl.bsoft.kerk.microservices.items.model.Item;
import nl.bsoft.kerk.microservices.person.model.Person;
import nl.bsoft.kerk.microservices.person.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@RestController
@Transactional
public class BboardController {

    private BboardService bboardService;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getPersons() {
        ResponseEntity<List<Item>> items = bboardService.getItems();

        return items;
    }


    @GetMapping("/items/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        ResponseEntity<Item> item = bboardService.getItemById(id);

        Item result = null;
        if (item.getStatusCode().is2xxSuccessful()) {
            result = item.getBody();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
