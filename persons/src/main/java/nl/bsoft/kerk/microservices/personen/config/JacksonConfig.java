package nl.bsoft.kerk.microservices.personen.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.key.OffsetDateTimeKeyDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;

@Configuration
public class JacksonConfig {
    public static ObjectMapper getObjectMapper() {
        return new JacksonConfig().objectMapper();
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {


        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(getTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, false);

        //mapper.setDateFormat(new StdDateFormat());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        mapper.setDateFormat(sdf);

        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return mapper;
    }

    private JavaTimeModule getTimeModule() {
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addDeserializer(OffsetDateTime.class, InstantDeserializer.OFFSET_DATE_TIME);
        javaTimeModule.addSerializer(OffsetDateTime.class, OffsetDateTimeSerializer.INSTANCE);
        javaTimeModule.addKeyDeserializer(OffsetDateTime.class, OffsetDateTimeKeyDeserializer.INSTANCE);
        return javaTimeModule;
    }
}