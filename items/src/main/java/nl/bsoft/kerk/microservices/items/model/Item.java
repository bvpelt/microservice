package nl.bsoft.kerk.microservices.items.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "ITEM")
@Data
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "CATEGORY")
    private String category;

    @NotNull
    @Column(name = "TITLE")
    private String title;

    @NotNull
    @Column(name = "AUTHOR")
    private String author;

    /*
    If not present, the current date-time of moment data is presented is used
     */
    @Column(name = "PUBLISHED")
    private LocalDateTime published;

    @NotNull
    @Column(name = "CONTENT")
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(category, item.category) && Objects.equals(title, item.title) && Objects.equals(author, item.author) && Objects.equals(published, item.published) && Objects.equals(content, item.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, title, author, published, content);
    }

}
