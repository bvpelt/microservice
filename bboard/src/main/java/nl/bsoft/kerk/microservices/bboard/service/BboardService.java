package nl.bsoft.kerk.microservices.bboard.service;

import lombok.extern.slf4j.Slf4j;
import nl.bsoft.kerk.microservices.bboard.client.BboardClient;
import nl.bsoft.kerk.microservices.items.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BboardService {

    @Autowired
    private BboardClient bboardClient;
    public ResponseEntity<List<Item>> getItems() {
        return bboardClient.getItems();
    }

    public ResponseEntity<Item>  getItemById(Long id) {
        return bboardClient.getItem(id);
    }
}
