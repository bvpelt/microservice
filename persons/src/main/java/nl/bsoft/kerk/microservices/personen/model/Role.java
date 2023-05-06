package nl.bsoft.kerk.microservices.personen.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "NAME", unique = true)
    @Size(min = 3, max = 24)
    private String name;

    @Column(name = "DESCRIPTION", unique = true)
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Collection<Person> persons = new ArrayList<>();
}
