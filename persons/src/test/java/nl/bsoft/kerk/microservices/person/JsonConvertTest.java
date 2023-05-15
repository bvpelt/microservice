package nl.bsoft.kerk.microservices.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nl.bsoft.kerk.microservices.person.model.Person;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@Slf4j
@SpringBootTest()
public class JsonConvertTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void deserializeTest() {
        String json = "{\"id\":1,\"voornaam\":\"Peter\",\"tussenvoegsel\":\"van de\",\"achternaam\":\"Riet\",\"created\":\"2023-05-15T20:55:56.626343327+02:00\",\"state\":\"created\",\"username\":\"peter@gmail.com\",\"password\":\"123456\",\"roles\":[{\"id\":1,\"name\":\"ADMIN\"},{\"id\":2,\"name\":\"EDITOR\"}]}";

        Person person = null;

        try {
            person = objectMapper.readValue(json, Person.class);
            Assert.isTrue(person.getId() == 1L, "Invalid person id");
        } catch (Exception e) {
            log.error("Error: {}", e);
        }


    }
}
