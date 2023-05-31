package nl.bsoft.kerk.microservices.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nl.bsoft.kerk.microservices.library.model.TimeUtil;
import nl.bsoft.kerk.microservices.person.model.Person;
import nl.bsoft.kerk.microservices.person.model.Role;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    public void addPerson01() throws Exception {

        // Create a Person object to add
        Person person = new Person();
        person.setId(1L);
        person.setVoornaam("JAN");
        person.setTussenvoegsel("de");
        person.setAchternaam("Vries");


        person.setCreated(TimeUtil.getDateTime("2023-04-01T08:00:00.000+02:00"));
        person.setState("created");
        person.setUsername("jan@gmail.com");
        person.setPassword("123456");

        ArrayList<Role> roles = new ArrayList<Role>();
        Role role = new Role();
        role.setName("USER");
        roles.add(role);

        person.setRoles(roles);

        // Convert the Person object to JSON
        String personJson = objectMapper.writeValueAsString(person);
        log.debug("peronsJson: {}", personJson);

        // Perform a POST request to add the Person entity
        MvcResult result = mvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personJson))
                .andExpect(status().isOk())
                .andReturn();
        log.info("MvcResult: {}", result);

        String responseAsString = result.getResponse().getContentAsString();
        log.info("Response: {}", responseAsString);

        Person person01 = null;

        try {
            person01 = objectMapper.readValue(responseAsString, Person.class);
        } catch (Exception e) {
            log.error("Execption: {}", e);
        }

        log.info("Response: {}", person01);
        Long personId = person01.getId();

        // Verify that the Person was added successfully
        mvc.perform(MockMvcRequestBuilders
                        .get("/person/{id}", personId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Long.toString(personId)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.voornaam").value(person.getVoornaam()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tussenvoegsel").value(person.getTussenvoegsel()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.achternaam").value(person.getAchternaam()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.state").value(person.getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(person.getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value(person.getPassword()))
        ;
    }

    @Test
    @Order(2)
    public void addPerson02() throws Exception {

        // Create a Person object to add
        Person person = new Person();
        person.setId(2L);
        person.setVoornaam("FRANS");
        person.setAchternaam("CORTENBACH");

        person.setCreated(TimeUtil.getDateTime("2023-04-02T08:00:00.00+02:00"));
        person.setState("created");
        person.setUsername("frans@gmail.com");
        person.setPassword("123456");

        ArrayList<Role> roles = new ArrayList<Role>();
        Role role = new Role();
        role.setName("ADMIN");
        roles.add(role);
        role = new Role();
        role.setName("USER");
        roles.add(role);

        person.setRoles(roles);

        // Convert the Person object to JSON
        String personJson = objectMapper.writeValueAsString(person);
        log.debug("peronsJson: {}", personJson);

        // Perform a POST request to add the Person entity
        MvcResult result = mvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personJson))
                .andExpect(status().isOk())
                .andReturn();
        log.info("MvcResult: {}", result);

        String responseAsString = result.getResponse().getContentAsString();
        log.info("Response: {}", responseAsString);

        Person person01 = null;

        try {
            person01 = objectMapper.readValue(responseAsString, Person.class);
        } catch (Exception e) {
            log.error("Execption: {}", e);
        }

        log.info("Response: {}", person01);
        Long personId = person01.getId();

        // Verify that the Person was added successfully
        mvc.perform(MockMvcRequestBuilders
                        .get("/person/{id}", personId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Long.toString(personId)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.voornaam").value(person.getVoornaam()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.achternaam").value(person.getAchternaam()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.state").value(person.getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(person.getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value(person.getPassword()))
        ;
    }

    @Test
    @Order(3)
    public void addPerson03() throws Exception {

        // Create a Person object to add
        Person person = new Person();
        person.setId(3L);
        person.setVoornaam("ASTERIX");
        person.setTussenvoegsel("van der");
        person.setAchternaam("Laan");

        person.setCreated(TimeUtil.getDateTime("2023-04-03T08:00:00+02:00"));
        person.setState("created");
        person.setUsername("asterix@gmail.com");
        person.setPassword("123456");

        ArrayList<Role> roles = new ArrayList<Role>();
        Role role = new Role();
        role.setName("ADMIN");
        roles.add(role);
        role = new Role();
        role.setName("DATABASE");
        roles.add(role);
        role = new Role();
        role.setName("EDITOR");
        roles.add(role);
        role = new Role();
        role.setName("USER");
        roles.add(role);

        person.setRoles(roles);

        // Convert the Person object to JSON
        String personJson = objectMapper.writeValueAsString(person);
        log.debug("peronsJson: {}", personJson);

        // Perform a POST request to add the Person entity
        MvcResult result = mvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(personJson))
                .andExpect(status().isOk())
                .andReturn();
        log.info("MvcResult: {}", result);

        String responseAsString = result.getResponse().getContentAsString();
        log.info("Response: {}", responseAsString);

        Person person01 = null;

        try {
            person01 = objectMapper.readValue(responseAsString, Person.class);
        } catch (Exception e) {
            log.error("Execption: {}", e);
        }

        log.info("Response: {}", person01);
        Long personId = person01.getId();

        // Verify that the Person was added successfully
        mvc.perform(MockMvcRequestBuilders
                        .get("/person/{id}", personId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Long.toString(personId)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.voornaam").value(person.getVoornaam()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tussenvoegsel").value(person.getTussenvoegsel()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.achternaam").value(person.getAchternaam()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.state").value(person.getState()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value(person.getUsername()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value(person.getPassword()))
        ;
    }

    @Test
    @Order(4)
    public void getPersonAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/person")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty())


                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].voornaam").value("JAN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].tussenvoegsel").value("de"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].achternaam").value("Vries"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].created").value("2023-04-01T10:00:00+02:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].state").value("created"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("jan@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].password").value("123456"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].roles[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].roles[0].name").value("USER"))

                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].voornaam").value("FRANS"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].tussenvoegsel").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].achternaam").value("CORTENBACH"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].created").value("2023-04-02T10:00:00+02:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].state").value("created"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].username").value("frans@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].password").value("123456"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].roles[0].id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].roles[0].name").value("ADMIN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].roles[1].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].roles[1].name").value("USER"))

                .andExpect(MockMvcResultMatchers.jsonPath("$[2].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].voornaam").value("ASTERIX"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].tussenvoegsel").value("van der"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].achternaam").value("Laan"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].created").value("2023-04-03T10:00:00+02:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].state").value("created"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].username").value("asterix@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].password").value("123456"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].roles[0].id").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].roles[0].name").value("ADMIN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].roles[1].id").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].roles[1].name").value("DATABASE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].roles[2].id").value("4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].roles[2].name").value("EDITOR"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].roles[3].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].roles[3].name").value("USER"))
        ;
    }

    @Test
    @Order(5)
    public void getPersonByIdAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/person/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.voornaam").value("JAN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tussenvoegsel").value("de"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.achternaam").value("Vries"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.created").value("2023-04-01T10:00:00+02:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.state").value("created"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("jan@gmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").value("123456"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles[0].id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles[0].name").value("USER"))
        ;
    }

    @Test
    @Order(6)
    public void getPersonByIdAPI_01() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/person/{id}", 5)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.voornaam").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tussenvoegsel").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.achternaam").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.created").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.state").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.password").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles[0].id").doesNotExist())
                .andExpect(MockMvcResultMatchers.jsonPath("$.roles[0].name").doesNotExist())
        ;
    }


}
