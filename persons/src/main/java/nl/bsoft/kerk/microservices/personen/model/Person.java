package nl.bsoft.kerk.microservices.personen.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "PERSON")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @NotNull
    @Column(name = "USERNAME", unique = true)
    @Size(min = 6, max = 64)
    private String username;

    @NotNull
    @Column(name = "PASSWORD")
    @Size(min = 6, max = 64)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "person_grants",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles = new ArrayList<>();

}
