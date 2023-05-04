package nl.bsoft.kerk.microservices.personen.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "PERSON")
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "VOORNAAM")
    @Size(min = 1, max = 12)
    private String voornaam;


    @Column(name = "TUSSENVOEGSEL")
    @Size(max = 12)
    private String tussenvoegsel;

    @NotNull
    @Column(name = "ACHTERNAAM")
    @Size(min = 1, max = 64)
    private String achternaam;

    @Column(name = "CREATED")
    private LocalDateTime created;

    @NotNull
    @Column(name = "STATE")
    @Size(min = 1, max = 12)
    private String state;
}
