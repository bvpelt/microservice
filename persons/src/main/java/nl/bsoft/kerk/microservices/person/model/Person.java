package nl.bsoft.kerk.microservices.person.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

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

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSSZ][.SSZ][.SZ]")
    @Column(name = "CREATED")
    private ZonedDateTime created;

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


    private boolean sameRoles(Collection<Role> lhs, Collection<Role> rhs) {
        boolean equals = false;
        if (lhs != null && rhs != null) {
            equals = lhs.size() == rhs.size() && lhs.containsAll(rhs) && rhs.containsAll(lhs);
        } else if (lhs == null && rhs == null) {
            equals = true;
        }
        return equals;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return voornaam.equals(person.voornaam) && Objects.equals(tussenvoegsel, person.tussenvoegsel) && achternaam.equals(person.achternaam) && Objects.equals(created, person.created) && state.equals(person.state) && username.equals(person.username) && password.equals(person.password) && sameRoles(roles, person.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(voornaam, tussenvoegsel, achternaam, created, state, username, password, roles);
    }
}
