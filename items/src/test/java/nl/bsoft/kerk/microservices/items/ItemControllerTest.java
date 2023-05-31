package nl.bsoft.kerk.microservices.items;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nl.bsoft.kerk.microservices.items.model.Item;
import nl.bsoft.kerk.microservices.library.model.TimeUtil;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class ItemControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    public void addItem01() throws Exception {

        // Create a Item object to add
        Item item = new Item();
        item.setId(1L);
        item.setCategory("Nieuws");
        item.setTitle("Test title - nieuws");
        item.setAuthor("Mark");
        item.setPublished(TimeUtil.getDateTime("2023-04-01T08:00:00+02:00"));
        item.setContent("Dit is de inhoud van het test item nieuws");

        // Convert the Item object to JSON
        String itemJson = objectMapper.writeValueAsString(item);
        log.debug("itemJson: {}", itemJson);

        // Perform a POST request to add the Item entity
        MvcResult result = mvc.perform(post("/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(itemJson))
                .andExpect(status().isOk())
                .andReturn();
        log.info("MvcResult: {}", result);

        String responseAsString = result.getResponse().getContentAsString();
        log.info("Response: {}", responseAsString);

        Item item01 = null;

        try {
            item01 = objectMapper.readValue(responseAsString, Item.class);
        } catch (Exception e) {
            log.error("Execption: {}", e);
        }

        log.info("Response: {}", item01);
        Long itemId = item01.getId();

        // Verify that the Person was added successfully
        mvc.perform(MockMvcRequestBuilders
                        .get("/item/{id}", itemId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Long.toString(itemId)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value(item.getCategory()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(item.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(item.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(item.getContent()))
        ;
    }

    @Test
    @Order(1)
    public void addItem02() throws Exception {

        // Create a Item object to add
        Item item = new Item();
        item.setId(2L);
        item.setCategory("Economie");
        item.setTitle("Test title - economie");
        item.setAuthor("Martijn");
        item.setPublished(TimeUtil.getDateTime("2023-04-03T08:00:00+02:00"));
        item.setContent("Dit is de inhoud van het test item over economie");

        // Convert the Item object to JSON
        String itemJson = objectMapper.writeValueAsString(item);
        log.debug("itemJson: {}", itemJson);

        // Perform a POST request to add the Item entity
        MvcResult result = mvc.perform(post("/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(itemJson))
                .andExpect(status().isOk())
                .andReturn();
        log.info("MvcResult: {}", result);

        String responseAsString = result.getResponse().getContentAsString();
        log.info("Response: {}", responseAsString);

        Item item01 = null;

        try {
            item01 = objectMapper.readValue(responseAsString, Item.class);
        } catch (Exception e) {
            log.error("Execption: {}", e);
        }

        log.info("Response: {}", item01);
        Long itemId = item01.getId();

        // Verify that the Person was added successfully
        mvc.perform(MockMvcRequestBuilders
                        .get("/item/{id}", itemId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(item.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value(item.getCategory()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value(item.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value(item.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value(item.getContent()))
        ;
    }

    @Test
    @Order(3)
    public void getItemByIdAPI() throws Exception {
        Long itemId = 1L;

        mvc.perform(MockMvcRequestBuilders
                        .get("/item/{id}", itemId)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())

                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(Long.toString(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.category").value("Nieuws"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test title - nieuws"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("Mark"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("Dit is de inhoud van het test item nieuws"))

        ;
    }


    @Test
    @Order(4)
    public void getItemAPI() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/item")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[*].id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[*].id").isNotEmpty())

                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].id").value(Long.toString(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].category").value("Nieuws"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].title").value("Test title - nieuws"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].author").value("Mark"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[0].content").value("Dit is de inhoud van het test item nieuws"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[1].id").value(Long.toString(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[1].category").value("Economie"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[1].title").value("Test title - economie"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[1].author").value("Martijn"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.[1].content").value("Dit is de inhoud van het test item over economie"))
        ;
    }

}
