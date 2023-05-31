package nl.bsoft.kerk.microservices.bboard.client;

import lombok.extern.slf4j.Slf4j;
import nl.bsoft.kerk.microservices.items.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class BboardClient {
    private final RestTemplate restTemplate;

    @Autowired
    public BboardClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Value("${client.items-api}/item")
    private String apiUrl;

    //public List<Item> getItems() {
    public  ResponseEntity<List<Item>> getItems() {
        String url = apiUrl;

        log.debug("Start client - get items from: {}", url);
        ResponseEntity<List<Item>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Item>>() {}
        );

        // Access the response body and headers
        List<Item> responseBody = response.getBody();

        HttpHeaders headers = response.getHeaders();

        log.debug("Result: body: {}\n headers: {}", responseBody, headers);

        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } else {
            return ResponseEntity.status(response.getStatusCode()).body(null);
        }
    }

    public ResponseEntity<Item> getItem(Long id) {
        String url = apiUrl+"/" + Long.toString(id);

        log.debug("Start client - get items from: {}", url);
        ResponseEntity<Item> response = restTemplate.getForEntity(url, Item.class);
/*
        ResponseEntity<List<Item>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Item>() {}
        );
*/
        // Access the response body and headers
        Item responseBody = response.getBody();

        HttpHeaders headers = response.getHeaders();

        log.debug("Result: body: {}\n headers: {}", responseBody, headers);

        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(HttpStatus.OK).body(responseBody);
        } else {
            return ResponseEntity.status(response.getStatusCode()).body(null);
        }
    }
}
